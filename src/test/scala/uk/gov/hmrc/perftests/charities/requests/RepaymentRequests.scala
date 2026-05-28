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
      .get(s"$baseUrlManagement$redirectUrlManagement")
      .check(status.is(303))
      .check(header("Location").is(s"$redirectUrlManagement$charityManagementURL"))

  val navigateToCharityManagementOrganisation: HttpRequestBuilder =
    http("Navigate to Make a Charity Repayment Claim page")
      .get(s"$baseUrl$redirectUrl$makeACharityClaim")
      .check(status.is(200))
      .check(regex("Make a charity repayment claim"))

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

  val navigateToHMRCCharityRefAgent: HttpRequestBuilder =
    http("Navigate to HMRC Charity Reference Page for Agent")
      .get(s"$baseUrl$redirectUrl$charityRefAgent")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What is the HMRC charities reference number?"))

  val enterHMRCReferenceAgent: HttpRequestBuilder =
    http("Enter HMRC Charity reference for AGENT")
      .post(s"$baseUrl$redirectUrl$charityRefAgent")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "A1")
      .check(status.is(303))

  val navigateToCharityNameAgent: HttpRequestBuilder =
    http("Navigate to Charity Name for Agent")
      .get(s"$baseUrl$redirectUrl$charityNameAgent")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What is the name of the charity or Community Amateur Sports Club \\(CASC\\)?"))

  val enterCharityNameAgent: HttpRequestBuilder =
    http("Enter Charity name for AGENT")
      .post(s"$baseUrl$redirectUrl$charityNameAgent")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "Charity Name for A1")
      .check(status.is(303))

  val navigateToSelectClaimType: HttpRequestBuilder =
    http("Select type of repayment claim Page")
      .get(s"$baseUrl$redirectUrl$repaymentClaimType")
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

  val selectClaimTypeGAGASDS: HttpRequestBuilder =
    http("Select type of repayment claim Page")
      .post(s"$baseUrl$redirectUrl$repaymentClaimType")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value[0]", "claimingGiftAid")
      .formParam("value[1]", "claimingUnderGiftAidSmallDonationsScheme")
      .check(status.is(303))

  val selectClaimTypeGASDSOnly: HttpRequestBuilder =
    http("Select type of repayment claim Page")
      .post(s"$baseUrl$redirectUrl$repaymentClaimType")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value[1]", "claimingUnderGiftAidSmallDonationsScheme")
      .check(status.is(303))

  val navigateToGASDSCheckbox: HttpRequestBuilder =
    http("Navigate to GASDS Checkbox (GASDS) details page")
      .get(s"$baseUrl$redirectUrl$repaymentClaimTypeGASDS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Gift Aid Small Donations Scheme \\(GASDS\\) details"))

  val selectGASDSAllYes: HttpRequestBuilder =
    http("Select Yes on claim GASDS for Top-up/Community buildings/Connected Charity")
      .post(s"$baseUrl$redirectUrl$repaymentClaimTypeGASDS")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value[0]", "topUp")
      .formParam("value[2]", "communityBuildings")
      .formParam("value[3]", "connectedCharity")
      .check(status.is(303))

  val selectGASDScbTopup: HttpRequestBuilder =
    http("Select Yes on claim GASDS for Top-up/Community buildings/Connected Charity")
      .post(s"$baseUrl$redirectUrl$repaymentClaimTypeGASDS")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value[0]", "topUp")
      .formParam("value[2]", "communityBuildings")
      .check(status.is(303))

  val selectGASDSTopUP: HttpRequestBuilder =
    http("Select Yes on claim GASDS for Top-up only")
      .post(s"$baseUrl$redirectUrl$repaymentClaimTypeGASDS")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value[0]", "topUp")
      .check(status.is(303))

  val navigateToGASDSOverclaimed: HttpRequestBuilder =
    http("Navigate to change a previous Gift Aid Small Donations Scheme claim?")
      .get(s"$baseUrl$redirectUrl$gasdsPreviousOverclaimed")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Gift Aid Small Donations Scheme \\(GASDS\\) details"))

  val selectGASDSOverclaimedYes: HttpRequestBuilder =
    http("Select Yes on change a previous Gift Aid Small Donations Scheme claim?")
      .post(s"$baseUrl$redirectUrl$gasdsPreviousOverclaimed")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToHaveClaimReference: HttpRequestBuilder =
    http("Navigate to Do you have a claim reference number Page")
      .get(s"$baseUrl$redirectUrl$haveClaimReference")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Do you have a claim reference number?"))

  val navigateToHaveClaimReferenceAgent: HttpRequestBuilder =
    http("Navigate to Agent's Charity have a claim reference number Page")
      .get(s"$baseUrl$redirectUrl$haveClaimReference")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Does the charity have a claim reference number?"))

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

  val navigateToEnterClaimReferenceNumberAgent: HttpRequestBuilder =
    http("Navigate to Agent's Enter charity's claim reference number Page")
      .get(s"$baseUrl$redirectUrl$enterClaimReferenceNumber")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What is the charity’s claim reference number?"))

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

  val navigateToCheckYourRepaymentClaimAgent: HttpRequestBuilder =
    http("Navigate to Agent's CYA Page for Repayment Claim")
      .get(s"$baseUrl$redirectUrl$repaymentCYA")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Check repayment claim details"))

  val submitRepaymentClaim: HttpRequestBuilder =
    http("Submit Repayment Claim")
      .post(s"$baseUrl$redirectUrl$repaymentCYA")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

}
