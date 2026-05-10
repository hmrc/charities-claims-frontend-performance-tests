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
import io.gatling.core.check.CheckBuilder
import io.gatling.core.check.regex.RegexCheckType
import io.gatling.http.Predef._
import io.gatling.http.check.header.HttpHeaderRegexCheckType
import uk.gov.hmrc.performance.conf.ServicesConfiguration

trait BaseRequests extends ServicesConfiguration {

  val baseUrl: String                   = baseUrlFor("charities-claims")
  val redirectUrl: String               = "/charities-claims"

  val baseUrlManagement: String                   = baseUrlFor("charities-management")
  val redirectUrlManagement: String               = "/charities-management"

  val charityManagementOrganisation: String               = "/manage-charity-repayment-claim"

  val charityRefAgent: String         = "/enter-charities-reference-number"
  val charityNameAgent: String         = "/enter-the-charity-name"

  val makeACharityClaim: String         = "/make-a-charity-repayment-claim"
  val repaymentClaimDetails: String     = "/repayment-claim-details"
  val repaymentClaimType: String        = "/select-repayment-claim-type"
  val repaymentClaimTypeGASDS: String        = "/select-gift-aid-small-donations-scheme-claim-type"

  val gasdsPreviousOverclaimed: String  = "/change-previous-gift-aid-small-donations-scheme-claim"

  val haveClaimReference: String        = "/claim-reference-number-check"
  val enterClaimReferenceNumber: String = "/enter-claim-reference-number"
  val repaymentCYA: String              = "/check-your-repayment-claim"

  val aboutTheOrg: String               = "/about-the-organisation"
  val nameOfCharityRegulator: String    = "/name-of-charity-regulator"
  val charityNotRegistered: String      = "/charity-not-registered"
  val charityExcepted: String           = "/charity-excepted"
  val charityExempt: String             = "/charity-exempt"
  val charityRegulatorNumber: String    = "/charity-regulator-number"
  val corporateTrustee: String          = "/corporate-trustee-claim"
  val corporateTrusteeAddress: String   = "/corporate-trustee-address"
  val corporateTrusteeDetails: String   = "/corporate-trustee-details"
  val authorisedOfficialAddress: String = "/authorised-official-address"
  val authorisedOfficialDetails: String = "/authorised-official-details"
  val organisationCYA: String           = "/check-your-organisation-details"

  val aboutTheGASDSpage: String           = "/about-gift-aid-small-donations-scheme"
  val adjustmentGASDS: String             = "/adjustment-for-gift-aid-small-donations-scheme-overclaimed"
  val adjustmentsCYAGASDS: String       = "/check-gift-aid-small-donations-scheme-adjustment-amount"
  val taxyear1GASDS: String = "/which-tax-year-are-you-claiming-for/1"
  val amount1GASDS: String       = "/donation-amount-you-are-claiming/1"
  val cya1GASDS: String       = "/check-claim-details-for-tax-year/1"
  val claimaddedGASDS: String      = "/claim-added-for-tax-year"
  val taxyear2GASDS: String = "/which-tax-year-are-you-claiming-for/2"
  val amount2GASDS: String       = "/donation-amount-you-are-claiming/2"
  val cya2GASDS: String       = "/check-claim-details-for-tax-year/2"
  val taxyear3GASDS: String = "/which-tax-year-are-you-claiming-for/3"
  val amount3GASDS: String       = "/donation-amount-you-are-claiming/3"
  val cya3GASDS: String       = "/check-claim-details-for-tax-year/3"
  val cyaFinalGASDS: String       = "/check-your-gift-aid-small-donations-scheme-donation-details"

  val aboutTheGAS: String           = "/about-gift-aid-schedule"
  val uploadGAS: String             = "/upload-gift-aid-schedule"
  val uploadedGASPage: String       = "/your-gift-aid-schedule-upload"
  val removeGASFromUploaded: String = "/your-gift-aid-schedule-upload/remove"
  val checkGASSuccess: String       = "/check-your-gift-aid-schedule"
  val checkGASProblem: String       = "/problem-with-gift-aid-schedule"
  val updateGASWarning: String      = "/update-gift-aid-schedule"
  val deleteGASWarning: String      = "/delete-gift-aid-schedule"
  val bannerGAS: String             = "/gift-aid-schedule-upload-successful"

  val aboutTheOI: String           = "/about-other-income-schedule"
  val uploadOI: String             = "/upload-other-income-schedule"
  val uploadedOIPage: String       = "/your-other-income-schedule-upload"
  val removeOIFromUploaded: String = "/your-other-income-schedule-upload/remove"
  val checkOISuccess: String       = "/check-your-other-income-schedule"
  val checkOIProblem: String       = "/problem-with-other-income-schedule"
  val updateOIWarning: String      = "/update-other-income-schedule"
  val deleteOIWarning: String      = "/delete-other-income-schedule"
  val bannerOI: String             = "/other-income-schedule-upload-successful"

  val aboutTheCB: String           = "/about-community-buildings-schedule"
  val uploadCB: String             = "/upload-community-buildings-schedule"
  val uploadedCBPage: String       = "/your-community-buildings-schedule-upload"
  val removeCBFromUploaded: String = "/your-community-buildings-schedule-upload/remove"
  val checkCBSuccess: String       = "/check-your-community-buildings-schedule"
  val checkCBProblem: String       = "/problem-with-community-buildings-schedule"
  val updateCBWarning: String      = "/update-community-buildings-schedule"
  val deleteCBWarning: String      = "/delete-gasds-community-buildings-schedule"
  val bannerCB: String             = "/community-buildings-schedule-upload-successful"

  val aboutTheCC: String           = "/about-connected-charities-schedule"
  val uploadCC: String             = "/upload-connected-charities-schedule"
  val uploadedCCPage: String       = "/your-connected-charities-schedule-upload"
  val removeCCFromUploaded: String = "/your-connected-charities-schedule-upload/remove"
  val checkCCSuccess: String       = "/check-your-connected-charities-schedule"
  val checkCCProblem: String       = "/problem-with-connected-charities-schedule"
  val updateCCWarning: String      = "/update-connected-charities-schedule"
  val deleteCCWarning: String      = "/delete-gasds-connected-charities-schedule"
  val bannerCC: String             = "/connected-charities-schedule-upload-successful"


  val declarationRegister: String       = "/registering-your-charity-with-a-regulator"
  val declarationAdjustment: String       = "/adjustments-to-this-claim"
  val declarationPage: String      = "/declaration"
  val claimComplete: String      = "/claim-complete"
  val submissionSummary: String             = "/charity-repayment-claim-summary"

  val transactionType: String = "/preliminary-questions/transaction-type"
  val address: String         = "/preliminary-questions/address"

  val authLoginStub: String = baseUrlFor("auth-login-stub")
  val authLoginStubUrl      = s"$authLoginStub/auth-login-stub/gg-sign-in"
  val CsrfPattern           = """<input type="hidden" name="csrfToken" value="([^"]+)""""
  val lookupPattern         = """<form method="POST" novalidate action="([^"]+)""""

  def saveCsrfToken(): CheckBuilder[RegexCheckType, String] = regex(_ => CsrfPattern).saveAs("csrfToken")

  private val lookupRegexp = "(.*)/begin"

  def saveAddressLookupUrl: CheckBuilder[HttpHeaderRegexCheckType, Response] =
    headerRegex("Location", lookupRegexp).saveAs("lookupAddressLocation")

}
