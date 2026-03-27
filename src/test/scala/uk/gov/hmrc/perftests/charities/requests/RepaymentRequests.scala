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
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

object RepaymentRequests extends ServicesConfiguration with BaseRequests {

  val loginToAuthWizard: HttpRequestBuilder =
    http("Login to auth wizard")
      .get(s"$baseUrl$redirectUrl")
      .check(status.is(303))
      .check(header("Location").is(s"$redirectUrl$makeACharityClaim"))

  val navigateToMakeACharityClaim: HttpRequestBuilder =
    http("Navigate to Make a Charity Repayment Claim page")
      .get(s"$baseUrl$redirectUrl$makeACharityClaim")
      .check(status.is(200))
      .check(regex("Make a charity repayment claim"))

  val navigateToRepaymentClaimDetails: HttpRequestBuilder =
    http("Navigate to Repayment Claim Details page")
      .get(s"$baseUrl$redirectUrl$repaymentClaimDetails")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Repayment claim details"))

  val navigateToSelectClaimType: HttpRequestBuilder =
    http("Select type of repayment claim Page")
      .get(s"$baseUrl$redirectUrl$repaymentClaimType")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Which type of repayment claim do you want to make?"))

  val selectClaimTypeNoGASDS: HttpRequestBuilder =
    http("Select type of repayment claim Page")
      .post(s"$baseUrl$redirectUrl$repaymentClaimType")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value[0]", "claimingGiftAid")
      .formParam("value[2]", "claimingTaxDeducted")
      .check(status.is(303))

  val selectClaimTypeGASDS: HttpRequestBuilder =
    http("Select type of repayment claim Page")
      .post(s"$baseUrl$redirectUrl$repaymentClaimType")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value[0]", "claimingGiftAid")
      .formParam("value[1]", "claimingUnderGiftAidSmallDonationsScheme")
      .formParam("value[2]", "claimingTaxDeducted")
      .check(status.is(303))

  val navigateToGASDSNotCB: HttpRequestBuilder =
    http("Navigate to Do you claim GASDS not collected in community buildings")
      .get(s"$baseUrl$redirectUrl$gasdsNotCB")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Do you want to claim a top-up payment under the Gift Aid Small Donations Scheme?"))

  val selectGASDSNotCBYes: HttpRequestBuilder =
    http("Select Yes on claim GASDS NOT collected in community buildings")
      .post(s"$baseUrl$redirectUrl$gasdsNotCB")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToGASDSCB: HttpRequestBuilder =
    http("Navigate to Do you want to claim GASDS collected in community buildings")
      .get(s"$baseUrl$redirectUrl$gasdsCB")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Do you want to claim for donations collected in community buildings?"))

  val selectGASDSCBYes: HttpRequestBuilder =
    http("Select Yes on claim GASDS collected in community buildings")
      .post(s"$baseUrl$redirectUrl$gasdsCB")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToGASDSOverclaimed: HttpRequestBuilder =
    http("Navigate to change a previous Gift Aid Small Donations Scheme claim?")
      .get(s"$baseUrl$redirectUrl$gasdsPreviousOverclaimed")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Gift Aid Small Donations Scheme claim"))

  val selectGASDSOverclaimedYes: HttpRequestBuilder =
    http("Select Yes on change a previous Gift Aid Small Donations Scheme claim?")
      .post(s"$baseUrl$redirectUrl$gasdsPreviousOverclaimed")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToConnectedToCharities: HttpRequestBuilder =
    http("Navigate to Connected charities and Community Amateur Sports Clubs")
      .get(s"$baseUrl$redirectUrl$ConnectedToCharities")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Connected charities and Community Amateur Sports Clubs"))

  val selectConnectedToCharitiesYes: HttpRequestBuilder =
    http("Select Yes on Connected charities and Community Amateur Sports Clubs")
      .post(s"$baseUrl$redirectUrl$ConnectedToCharities")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToHaveClaimReference: HttpRequestBuilder =
    http("Navigate to Do you have a claim reference number Page")
      .get(s"$baseUrl$redirectUrl$haveClaimReference")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Do you have a claim reference number?"))

  val selectReference: HttpRequestBuilder =
    http("Enter If you have a claim reference number")
      .post(s"$baseUrl$redirectUrl$haveClaimReference")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToEnterClaimReferenceNumber: HttpRequestBuilder =
    http("Navigate to Enter your claim reference number Page")
      .get(s"$baseUrl$redirectUrl$enterClaimReferenceNumber")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What is your claim reference number?"))

  val enterClaimReferenceValue: HttpRequestBuilder =
    http("Enter If you have a claim reference number")
      .post(s"$baseUrl$redirectUrl$enterClaimReferenceNumber")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "112233aaffddee44ggrr")
      .check(status.is(303))

  val navigateToCheckYourRepaymentClaim: HttpRequestBuilder =
    http("Navigate to Check Your Answers Page for Repayment Claim")
      .get(s"$baseUrl$redirectUrl$repaymentCYA")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Check your repayment claim"))

  val submitRepaymentClaim: HttpRequestBuilder =
    http("Submit Repayment Claim")
      .post(s"$baseUrl$redirectUrl$repaymentCYA")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

}
