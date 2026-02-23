/*
 * Copyright 2023 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.perftests.charities.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

object OrganisationRequests extends ServicesConfiguration with BaseRequests {

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
    http("Navigate to individual or Business Page")
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

  val selectClaimType: HttpRequestBuilder =
    http("Select type of repayment claim Page")
      .post(s"$baseUrl$redirectUrl$repaymentClaimType")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "claimingGiftAid")
      .formParam("value", "claimingTaxDeducted")
      .check(status.is(303))
      .check(saveCsrfToken())

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
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Check your repayment claim"))

  val submitRepaymentClaim: HttpRequestBuilder =
    http("Submit Repayment Claim")
      .post(s"$baseUrl$redirectUrl$repaymentCYA")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

}
