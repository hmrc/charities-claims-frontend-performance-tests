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
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

import scala.concurrent.duration.DurationInt
import scala.util.matching.Regex

object GASScheduleUploadRequests extends ServicesConfiguration with BaseRequests {

  val loginToAuthWizard: HttpRequestBuilder =
    http("Login to auth wizard")
      .get(s"$baseUrl$redirectUrl")
      .check(status.is(303))
      .check(header("Location").is(s"$redirectUrl$makeACharityClaim"))

  val navigateToAboutGiftAidSchedule: HttpRequestBuilder =
    http("Navigate to About the Gift Aid Schedule page")
      .get(s"$baseUrl$redirectUrl$aboutTheGAS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("About Gift Aid schedule"))

  val navigateToUploadGiftAidSchedule: HttpRequestBuilder =
    http("Navigate to Upload your gift aid schedule Page")
      .get(s"$baseUrl$redirectUrl$uploadGAS")
      .check(status.is(200))
      .check(regex("Upload a Gift Aid schedule"))
      .check(regex("""<form[^>]*action="([^"]+)"""").saveAs("uploadUrl"))
      .check(regex("""name="success_action_redirect"\s+value="([^"]+)"""").saveAs("successActionRedirect"))
      .check(regex("""name="x-amz-credential"\s+value="([^"]+)"""").saveAs("xAmzCredential"))
      .check(regex("""name="x-amz-meta-upscan-initiate-response"\s+value="([^"]+)"""").saveAs("xAmzMetaUpscanInitiateResponse"))
      .check(regex("""name="x-amz-meta-original-filename"\s+value="([^"]+)"""").saveAs("xAmzMetaOriginalFilename"))
      .check(regex("""name="x-amz-algorithm"\s+value="([^"]+)"""").saveAs("xAmzAlgorithm"))
      .check(regex("""name="x-amz-signature"\s+value="([^"]+)"""").saveAs("xAmzSignature"))
      .check(regex("""name="error_action_redirect"\s+value="([^"]+)"""").saveAs("errorActionRedirect"))
      .check(regex("""name="x-amz-meta-session-id"\s+value="([^"]+)"""").saveAs("xAmzMetaSessionId"))
      .check(regex("""name="x-amz-meta-callback-url"\s+value="([^"]+)"""").saveAs("xAmzMetaCallbackURL"))
      .check(regex("""name="x-amz-date"\s+value="([^"]+)"""").saveAs("xAmzDate"))
      .check(regex("""name="x-amz-meta-upscan-initiate-received"\s+value="([^"]+)"""").saveAs("xAmzMetaUpscanInitiateReceived"))
      .check(regex("""name="x-amz-meta-request-id"\s+value="([^"]+)"""").saveAs("xAmzMetaRequestId"))
      .check(regex("""name="key"\s+value="([^"]+)"""").saveAs("key"))
      .check(regex("""name="acl"\s+value="([^"]+)"""").saveAs("acl"))
      .check(regex("""name="x-amz-meta-consuming-service"\s+value="([^"]+)"""").saveAs("xAmzMetaConsumingService"))
      .check(regex("""name="policy"\s+value="([^"]+)"""").saveAs("policy"))

  def postFileToUpscan(fileName: String): HttpRequestBuilder =
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

  val getUpscanUploadResponse: HttpRequestBuilder =
    http(s"Upscan upload redirect")
      .get("#{UpscanUploadResponse}")
      .check(status.in(303))

  val navigateToGASUploaded: HttpRequestBuilder =
    http("Navigate to your Gift Aid Schedule upload page")
      .get(s"$baseUrl$redirectUrl$uploadedGASPage")
      .check(status.is(200))


  val getFileVerificationStatus: List[ActionBuilder] =
    asLongAsDuring(session =>
      session("uploadStatus").asOption[String] match {
        case Some(status) => !status.equalsIgnoreCase("UPLOADED")
        case None => true
      },
      5.minutes
    )(
      pause(3.second)
        .exec(
          http("Get the file verification/validation status on your Gift Aid Schedule Upload Page")
            .get(s"$baseUrl$redirectUrl$uploadedGASPage")
            .check(status.is(200))
            .check(regex("""<strong[^>]*>\s*([\s\S]*?)\s*</strong>""").saveAs("uploadStatus"))
            .check(saveCsrfToken())
            .check(regex("Your Gift Aid schedule upload"))
        )
        .exec(session =>
          session.set(
            "uploadStatus",
            session("uploadStatus").as[String].trim
          )
        )
        ).actionBuilders

  val continueFromUploadedPage: HttpRequestBuilder =
    http("CONTINUE from your GIFT AID Upload Page")
      .post(s"$baseUrl$redirectUrl$uploadedGASPage")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))
      .check(header("Location").saveAs("nextPageURL"))

  val removeGASFromUploadedPage: HttpRequestBuilder =
    http("Navigate to remove Schedule from Uploaded GAS Page that DELETES the schedule upload")
      .get(s"$baseUrl$redirectUrl$removeGASFromUploaded")
      .check(status.is(303))


  val navigateToCheckYourGASSchedule: HttpRequestBuilder =
    http("Navigate to check your Gift Aid Schedule page")
      //.get(s"$baseUrl$redirectUrl$checkGASSuccess")
      .get(s"$baseUrl"+"#{nextPageURL}")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Check your Gift Aid schedule"))

  val navigateToProblemWithYourGASSchedule: HttpRequestBuilder =
    http("Navigate to Problem with your Gift Aid Schedule page")
      //.get(s"$baseUrl$redirectUrl$checkGASProblem")
      .get(s"$baseUrl"+"#{nextPageURL}")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("There is a problem with the data in your Gift Aid schedule"))

  val clickAttachUpdatedScheduleButton: HttpRequestBuilder =
    http("Click on Attach an updated Gift Aid Schedule Button on Problem page, that Deletes the uploaded schedule")
      .post(s"$baseUrl$redirectUrl$checkGASProblem")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val selectUpdateScheduleNo: HttpRequestBuilder =
    http("Select Update Schedule as NO on check your GAS page")
      .post(s"$baseUrl$redirectUrl$checkGASSuccess")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "false")
      .check(status.is(303))

  val selectUpdateScheduleYes: HttpRequestBuilder =
    http("Select Update Schedule as YES on check your GAS page")
      .post(s"$baseUrl$redirectUrl$checkGASSuccess")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToUpdateGASWarning: HttpRequestBuilder =
    http("Navigate to want to update this Gift Aid schedule? page")
      .get(s"$baseUrl$redirectUrl$updateGASWarning")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Are you sure you want to update this Gift Aid schedule?"))

  val SelectUpdateGASWarningYes: HttpRequestBuilder =
    http("Select YES on update GAS Warning page, that Deletes the uploaded schedule")
      .post(s"$baseUrl$redirectUrl$updateGASWarning")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToDeleteGASWarning: HttpRequestBuilder =
    http("Navigate to want to delete this Gift Aid schedule? page")
      .get(s"$baseUrl$redirectUrl$deleteGASWarning")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Do you want to delete this Gift Aid schedule?"))

  val SelectDeleteGASWarningYes: HttpRequestBuilder =
    http("Select YES on update GAS Warning page, that Deletes the uploaded schedule")
      .post(s"$baseUrl$redirectUrl$deleteGASWarning")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToGASSuccessBanner: HttpRequestBuilder =
    http("Navigate to your Gift Aid Schedule upload successful banner page")
      .get(s"$baseUrl$redirectUrl$bannerGAS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Upload successful"))

  val submitScheduleUpload: HttpRequestBuilder =
    http("CONTINUE GAS Journey from Banner Page")
      .post(s"$baseUrl$redirectUrl$bannerGAS")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))










}
