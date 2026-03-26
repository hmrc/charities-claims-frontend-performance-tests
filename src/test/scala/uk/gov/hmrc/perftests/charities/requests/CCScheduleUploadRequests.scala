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

object CCScheduleUploadRequests extends ServicesConfiguration with BaseRequests {

  val loginToAuthWizard: HttpRequestBuilder =
    http("Login to auth wizard")
      .get(s"$baseUrl$redirectUrl")
      .check(status.is(303))
      .check(header("Location").is(s"$redirectUrl$makeACharityClaim"))

  val navigateToAboutConnectedCharitiesSchedule: HttpRequestBuilder =
    http("Navigate to About the Connected Charities Schedule page")
      .get(s"$baseUrl$redirectUrl$aboutTheCC")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("About Connected Charities schedule"))

  val navigateToUploadConnectedCharitiesSchedule: HttpRequestBuilder =
    http("Navigate to Upload your Connected Charities schedule Page")
      .get(s"$baseUrl$redirectUrl$uploadCC")
      .check(status.is(200))
      .check(regex("Upload a Connected Charities schedule"))
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

  def postFileToUpscanCC(fileName: String): HttpRequestBuilder =
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

  val getUpscanUploadResponseCC: HttpRequestBuilder =
    http(s"Upscan upload redirect")
      .get("#{UpscanUploadResponse}")
      .check(status.in(303))

  val navigateToCCUploaded: HttpRequestBuilder =
    http("Navigate to your Connected Charities Schedule upload page")
      .get(s"$baseUrl$redirectUrl$uploadedCCPage")
      .check(status.is(200))
      .check(saveCsrfToken())

  val resetUploadStatusCC: ActionBuilder =
    exec(s => s.remove("uploadStatus")).actionBuilders.head

  val pollUntilUploadedCC: List[ActionBuilder] =
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
            .get(s"$baseUrl$redirectUrl$uploadedCCPage")
            .check(status.is(200))
            .check(regex("""<strong[^>]*>\s*([\s\S]*?)\s*</strong>""").saveAs("uploadStatusRaw"))
            .check(saveCsrfToken())
            .check(regex("Your Connected Charities schedule upload"))
        )
        .exec { session =>
          val cleaned = session("uploadStatusRaw").asOption[String].map(_.trim).getOrElse("")
          session
            .remove("uploadStatusRaw")
            .set("uploadStatus", cleaned)
        }
    ).actionBuilders

  val getFileVerificationStatusCC: List[ActionBuilder] =
    resetUploadStatusCC :: pollUntilUploadedCC

  val continueFromUploadedPageCC: HttpRequestBuilder =
    http("CONTINUE from your CONNECTED CHARITIES Upload Page")
      .post(s"$baseUrl$redirectUrl$uploadedCCPage")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))
      .check(header("Location").saveAs("nextPageURL"))

  val removeCCFromUploadedPage: HttpRequestBuilder =
    http("Navigate to remove Schedule from Uploaded CC Page that DELETES the schedule upload")
      .get(s"$baseUrl$redirectUrl$removeCCFromUploaded")
      .check(status.is(303))

  val navigateToCheckYourCCSchedule: HttpRequestBuilder =
    http("Navigate to check your Connected Charities Schedule page")
      .get(s"$baseUrl" + "#{nextPageURL}")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Check your Connected Charities schedule"))

  val navigateToProblemWithYourCCSchedule: HttpRequestBuilder =
    http("Navigate to Problem with your Connected Charities Schedule page")
      .get(s"$baseUrl" + "#{nextPageURL}")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("There is a problem with the data in your Connected Charities schedule"))

  val clickAttachUpdatedScheduleButtonCC: HttpRequestBuilder =
    http(
      "Click on Attach an updated Connected Charities Schedule Button on Problem page, that Deletes the uploaded schedule"
    )
      .post(s"$baseUrl$redirectUrl$checkCCProblem")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val selectUpdateScheduleNoCC: HttpRequestBuilder =
    http("Select Update Schedule as NO on check your CC page")
      .post(s"$baseUrl$redirectUrl$checkCCSuccess")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "false")
      .check(status.is(303))

  val selectUpdateScheduleYesCC: HttpRequestBuilder =
    http("Select Update Schedule as YES on check your CC page")
      .post(s"$baseUrl$redirectUrl$checkCCSuccess")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToUpdateCCWarning: HttpRequestBuilder =
    http("Navigate to want to update this Connected Charities schedule? page")
      .get(s"$baseUrl$redirectUrl$updateCCWarning")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Are you sure you want to update this Connected Charities schedule?"))

  val SelectUpdateCCWarningYes: HttpRequestBuilder =
    http("Select YES on update CC Warning page, that Deletes the uploaded schedule")
      .post(s"$baseUrl$redirectUrl$updateCCWarning")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToDeleteCCWarning: HttpRequestBuilder =
    http("Navigate to want to delete this Connected Charities schedule? page")
      .get(s"$baseUrl$redirectUrl$deleteCCWarning")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Do you want to delete this Connected Charities schedule?"))

  val SelectDeleteCCWarningYes: HttpRequestBuilder =
    http("Select YES on update CC Warning page, that Deletes the uploaded schedule")
      .post(s"$baseUrl$redirectUrl$deleteCCWarning")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToCCSuccessBanner: HttpRequestBuilder =
    http("Navigate to your Connected Charities Schedule upload successful banner page")
      .get(s"$baseUrl$redirectUrl$bannerCC")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Upload successful"))

  val submitScheduleUploadCC: HttpRequestBuilder =
    http("CONTINUE CC Journey from Banner Page")
      .post(s"$baseUrl$redirectUrl$bannerCC")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

}
