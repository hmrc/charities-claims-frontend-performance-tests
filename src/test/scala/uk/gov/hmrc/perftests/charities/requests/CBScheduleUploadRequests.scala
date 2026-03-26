/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.charities.requests

import io.gatling.core.Predef._
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

import scala.concurrent.duration.DurationInt

object CBScheduleUploadRequests extends ServicesConfiguration with BaseRequests {

  val loginToAuthWizard: HttpRequestBuilder =
    http("Login to auth wizard")
      .get(s"$baseUrl$redirectUrl")
      .check(status.is(303))
      .check(header("Location").is(s"$redirectUrl$makeACharityClaim"))

  val navigateToAboutCommunityBuildingsSchedule: HttpRequestBuilder =
    http("Navigate to About the Community Buildings Schedule page")
      .get(s"$baseUrl$redirectUrl$aboutTheCB")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("About Community Buildings schedule"))

  val navigateToUploadCommunityBuildingsSchedule: HttpRequestBuilder =
    http("Navigate to Upload your Community Buildings schedule Page")
      .get(s"$baseUrl$redirectUrl$uploadCB")
      .check(status.is(200))
      .check(regex("Upload a Community Buildings schedule"))
      .check(regex("""<form[^>]*action="([^"]+)"""").saveAs("uploadUrl"))
      .check(regex("""name="success_action_redirect"\s+value="([^"]+)"""").saveAs("successActionRedirect"))
      .check(regex("""name="x-amz-credential"\s+value="([^"]+)"""").saveAs("xAmzCredential"))
      .check(
        regex("""name="x-amz-meta-upscan-initiate-response"\s+value="([^"]+)"""").saveAs(
          "xAmzMetaUpscanInitiateResponse"
        )
      )
      .check(regex("""name="x-amz-meta-original-filename"\s+value="([^"]+)"""").saveAs("xAmzMetaOriginalFilename"))
      .check(regex("""name="x-amz-algorithm"\s+value="([^"]+)"""").saveAs("xAmzAlgorithm"))
      .check(regex("""name="x-amz-signature"\s+value="([^"]+)"""").saveAs("xAmzSignature"))
      .check(regex("""name="error_action_redirect"\s+value="([^"]+)"""").saveAs("errorActionRedirect"))
      .check(regex("""name="x-amz-meta-session-id"\s+value="([^"]+)"""").saveAs("xAmzMetaSessionId"))
      .check(regex("""name="x-amz-meta-callback-url"\s+value="([^"]+)"""").saveAs("xAmzMetaCallbackURL"))
      .check(regex("""name="x-amz-date"\s+value="([^"]+)"""").saveAs("xAmzDate"))
      .check(
        regex("""name="x-amz-meta-upscan-initiate-received"\s+value="([^"]+)"""").saveAs(
          "xAmzMetaUpscanInitiateReceived"
        )
      )
      .check(regex("""name="x-amz-meta-request-id"\s+value="([^"]+)"""").saveAs("xAmzMetaRequestId"))
      .check(regex("""name="key"\s+value="([^"]+)"""").saveAs("key"))
      .check(regex("""name="acl"\s+value="([^"]+)"""").saveAs("acl"))
      .check(regex("""name="x-amz-meta-consuming-service"\s+value="([^"]+)"""").saveAs("xAmzMetaConsumingService"))
      .check(regex("""name="policy"\s+value="([^"]+)"""").saveAs("policy"))

  def postFileToUpscanCB(fileName: String): HttpRequestBuilder =
    http("Send a File valid at both validation and Upscan service, to Upscan's upload URL Received")
      .post("#{uploadUrl}")
      .asMultipartForm
      .bodyPart(StringBodyPart("x-amz-meta-callback-url", "#{xAmzMetaCallbackURL}"))
      .bodyPart(StringBodyPart("x-amz-date", "#{xAmzDate}"))
      .bodyPart(StringBodyPart("success_action_redirect", "#{successActionRedirect}"))
      .bodyPart(StringBodyPart("x-amz-credential", "#{xAmzCredential}"))
      .bodyPart(StringBodyPart("x-amz-meta-upscan-initiate-response", "#{xAmzMetaUpscanInitiateResponse}"))
      .bodyPart(StringBodyPart("x-amz-meta-upscan-initiate-received", "#{xAmzMetaUpscanInitiateReceived}"))
      .bodyPart(StringBodyPart("x-amz-meta-request-id", "#{xAmzMetaRequestId}"))
      .bodyPart(StringBodyPart("x-amz-algorithm", "#{xAmzAlgorithm}"))
      .bodyPart(StringBodyPart("key", "#{key}"))
      .bodyPart(StringBodyPart("acl", "#{acl}"))
      .bodyPart(StringBodyPart("x-amz-signature", "#{xAmzSignature}"))
      .bodyPart(StringBodyPart("error_action_redirect", "#{errorActionRedirect}"))
      .bodyPart(StringBodyPart("x-amz-meta-original-filename", fileName))
      .bodyPart(StringBodyPart("x-amz-meta-session-id", "#{xAmzMetaSessionId}"))
      .bodyPart(StringBodyPart("x-amz-meta-consuming-service", "#{xAmzMetaConsumingService}"))
      .bodyPart(StringBodyPart("policy", "#{policy}"))
      .bodyPart(RawFileBodyPart("file", fileName))
      .check(status.is(303))
      .check(header("Location").saveAs("UpscanUploadResponse"))

  val getUpscanUploadResponseCB: HttpRequestBuilder =
    http(s"Upscan upload redirect")
      .get("#{UpscanUploadResponse}")
      .check(status.in(303))

  val navigateToCBUploaded: HttpRequestBuilder =
    http("Navigate to your Community Buildings Schedule upload page")
      .get(s"$baseUrl$redirectUrl$uploadedCBPage")
      .check(status.is(200))
      .check(saveCsrfToken())

  val resetUploadStatusCB: ActionBuilder =
    exec(s => s.remove("uploadStatus")).actionBuilders.head

  val pollUntilUploadedCB: List[ActionBuilder] =
    asLongAsDuring(
      session =>
        session("uploadStatus").asOption[String] match {
          case Some(status) => !status.equalsIgnoreCase("UPLOADED")
          case None         => true
        },
      5.minutes,
      exitASAP = true
    )(
      pause(5.second)
        .exec(
          http("Get the file verification/validation status on your Other Income Schedule Upload Page")
            .get(s"$baseUrl$redirectUrl$uploadedCBPage")
            .check(status.is(200))
            .check(regex("""<strong[^>]*>\s*([\s\S]*?)\s*</strong>""").saveAs("uploadStatusRaw"))
            .check(saveCsrfToken())
            .check(regex("Your Community Buildings schedule upload"))
        )
        .exec { session =>
          val cleaned = session("uploadStatusRaw").asOption[String].map(_.trim).getOrElse("")
          session
            .remove("uploadStatusRaw")
            .set("uploadStatus", cleaned)
        }
    ).actionBuilders

  val getFileVerificationStatusCB: List[ActionBuilder] =
    resetUploadStatusCB :: pollUntilUploadedCB

  val continueFromUploadedPageCB: HttpRequestBuilder =
    http("CONTINUE from your CONNECTED CHARITIES Upload Page")
      .post(s"$baseUrl$redirectUrl$uploadedCBPage")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))
      .check(header("Location").saveAs("nextPageURL"))

  val removeCBFromUploadedPage: HttpRequestBuilder =
    http("Navigate to remove Schedule from Uploaded CB Page that DELETES the schedule upload")
      .get(s"$baseUrl$redirectUrl$removeCBFromUploaded")
      .check(status.is(303))

  val navigateToCheckYourCBSchedule: HttpRequestBuilder =
    http("Navigate to check your Community Buildings Schedule page")
      .get(s"$baseUrl" + "#{nextPageURL}")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Check your Community Buildings schedule"))

  val navigateToProblemWithYourCBSchedule: HttpRequestBuilder =
    http("Navigate to Problem with your Community Buildings Schedule page")
      .get(s"$baseUrl" + "#{nextPageURL}")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("There is a problem with the data in your Community Buildings schedule"))

  val clickAttachUpdatedScheduleButtonCB: HttpRequestBuilder =
    http("Click on Attach an updated Community Buildings Schedule Button on Problem page, that Deletes the uploaded schedule")
      .post(s"$baseUrl$redirectUrl$checkCBProblem")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val selectUpdateScheduleNoCB: HttpRequestBuilder =
    http("Select Update Schedule as NO on check your CB page")
      .post(s"$baseUrl$redirectUrl$checkCBSuccess")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "false")
      .check(status.is(303))

  val selectUpdateScheduleYesCB: HttpRequestBuilder =
    http("Select Update Schedule as YES on check your CB page")
      .post(s"$baseUrl$redirectUrl$checkCBSuccess")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToUpdateCBWarning: HttpRequestBuilder =
    http("Navigate to want to update this Community Buildings schedule? page")
      .get(s"$baseUrl$redirectUrl$updateCBWarning")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Are you sure you want to update this Community Buildings schedule?"))

  val SelectUpdateCBWarningYes: HttpRequestBuilder =
    http("Select YES on update CB Warning page, that Deletes the uploaded schedule")
      .post(s"$baseUrl$redirectUrl$updateCBWarning")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToDeleteCBWarning: HttpRequestBuilder =
    http("Navigate to want to delete this Community Buildings schedule? page")
      .get(s"$baseUrl$redirectUrl$deleteCBWarning")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Do you want to delete this Community Buildings schedule?"))

  val SelectDeleteCBWarningYes: HttpRequestBuilder =
    http("Select YES on update CB Warning page, that Deletes the uploaded schedule")
      .post(s"$baseUrl$redirectUrl$deleteCBWarning")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToCBSuccessBanner: HttpRequestBuilder =
    http("Navigate to your Community Buildings Schedule upload successful banner page")
      .get(s"$baseUrl$redirectUrl$bannerCB")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Upload successful"))

  val submitScheduleUploadCB: HttpRequestBuilder =
    http("CONTINUE CB Journey from Banner Page")
      .post(s"$baseUrl$redirectUrl$bannerCB")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

}
