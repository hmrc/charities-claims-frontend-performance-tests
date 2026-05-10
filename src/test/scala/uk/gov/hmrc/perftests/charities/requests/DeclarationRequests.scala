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

object DeclarationRequests extends ServicesConfiguration with BaseRequests {

  val redirectToRegisterCharityFromAdjustment: HttpRequestBuilder =
    http("Redirect to Register your Charity for excepted via adjustments page redirect")
      .get(s"$baseUrl$redirectUrl$declarationAdjustment")
      .check(status.is(303))
      .check(header("Location").saveAs("RegisterPageLocation"))

  val navigateToRegisterCharityFromAdjustment: HttpRequestBuilder =
    http("Navigate to Register your Charity for excepted via adjustments page")
      .get("${RegisterPageLocation}")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Registering your charity with a regulator"))

  val selectNoContinueRegister: HttpRequestBuilder =
    http("Select No, Continue on Register your charity page")
      .post(s"$baseUrl$redirectUrl$declarationRegister")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "false")
      .check(status.is(303))
      //.check(header("Location").saveAs("AdjustmentLocation"))

  val redirectToAdjustmentsFromRegister: HttpRequestBuilder =
    http("Redirect to What adjustments made to this Claim page from Register Page")
      .get(s"$baseUrl$redirectUrl$declarationAdjustment")
      .check(status.is(303))
      .check(header("Location").saveAs("AdjustmentPageLocation"))

//  val navigateToAdjustmentsFromRegister: HttpRequestBuilder =
//    http("Navigate to What adjustments made to this Claim page from Register Page")
//      .get(s"$baseUrl$redirectUrl$declarationAdjustment")
//      .check(status.is(200))
//      .check(saveCsrfToken())
//      .check(regex("What adjustments have you made to this claim?"))

  val navigateToWhatAdjustmentsToClaim: HttpRequestBuilder =
    http("Navigate to What adjustments made to this Claim page")
      .get(s"$baseUrl$redirectUrl$declarationAdjustment")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What adjustments have you made to this claim?"))

  val enterAdjustmentText: HttpRequestBuilder =
    http("Enter Adjustment Details")
      .post(s"$baseUrl$redirectUrl$declarationAdjustment")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "Adjustments details previously overclaimed by mistake")
      .check(status.is(303))

  val navigateToDeclaration: HttpRequestBuilder =
    http("Navigate to Declaration Page")
      .get(s"$baseUrl$redirectUrl$declarationPage")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Declaration"))

  val navigateToDeclarationForUnregulated: HttpRequestBuilder =
    http("Navigate to Declaration Page")
      .get(s"$baseUrl$redirectUrl$declarationPage")
      .check(status.is(303))
      .check(saveCsrfToken())
      .check(regex("Declaration"))

  val submitClaimDeclaration: HttpRequestBuilder =
    http("Submit Claim Declaration")
      .post(s"$baseUrl$redirectUrl$declarationPage")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val navigateToClaimComplete: HttpRequestBuilder =
    http("Navigate to Claim Complete Page")
      .get(s"$baseUrl$redirectUrl$claimComplete")
      .check(status.is(200))
      .check(regex("Claim complete"))


  val pollNavigateToClaimComplete: List[ActionBuilder] =
    tryMax(10, "completePageRetry") {
      exec(navigateToClaimComplete)
        .pause(1.second)
    }.actionBuilders


  val printSubmissionSummary: HttpRequestBuilder =
    http("Navigate to Print Submission Summary page")
      .get(s"$baseUrl$redirectUrl$submissionSummary")
      .check(status.is(200))
      .check(regex("Charity repayment claim summary"))

}
