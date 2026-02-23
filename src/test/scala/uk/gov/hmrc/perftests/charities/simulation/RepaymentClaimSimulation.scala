/*
 * Copyright 2025 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.perftests.charities.simulation

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.charities.requests.RepaymentRequests.{enterClaimReferenceValue, loginToAuthWizard, navigateToCheckYourRepaymentClaim, navigateToEnterClaimReferenceNumber, navigateToHaveClaimReference, navigateToMakeACharityClaim, navigateToRepaymentClaimDetails, navigateToSelectClaimType, selectClaimType, selectReference, submitRepaymentClaim}
import uk.gov.hmrc.perftests.charities.requests._
import uk.gov.hmrc.perftests.requests._
import uk.gov.hmrc.perftests.charities.requests._
import uk.gov.hmrc.perftests.requests.AuthLoginRequests.{authLogInForOrg, navigateToAuth}

trait RepaymentClaimSimulation extends PerformanceTestRunner with BaseRequests {

  setup("Repayment-Claim-Questions", "Repayment Claims Questions Journey").withRequests(
    navigateToAuth,
    authLogInForOrg,
    loginToAuthWizard,
    navigateToMakeACharityClaim,
    navigateToRepaymentClaimDetails,
    navigateToSelectClaimType,
    selectClaimType,
    navigateToHaveClaimReference,
    selectReference,
    navigateToEnterClaimReferenceNumber,
    enterClaimReferenceValue,
    navigateToCheckYourRepaymentClaim,
    submitRepaymentClaim
  )

  setup("vendor-questions", "Vendor Questions Journey when there is no vendor").withRequests(
    navigateToAuth,
    authLogIn,
    loginToAuthWizard,
    navigateToReturnTaskListWithNoVendor,
    navigateToAboutTheVendor,
    navigateToWhoTheVendor,
    selectIndividual,
    navigateToVendorNamePage,
    selectVendorFullName,
    getLookupAddress("Add address for vendor", baseUrl, redirectUrlForFiling, "/preliminary-questions/address"),
    getLookupAddressEdit,
    postLookupAddress,
    getLookupAddressConfirm,
    navigateToVendorAgent,
    selectVendorAgent,
    navigateToAgentName,
    selectAgentName,
    getLookupAddress("Add address for vendor Agent", baseUrl, redirectUrlForFiling, "/preliminary-questions/address"),
    getLookupAddressEdit,
    postLookupAddress,
    getLookupAddressConfirm,
    navigateToContactDetails,
    selectContactDetails,
    navigateToEnterContactDetails,
    addContactDetails,
    navigateToAddAgentReference,
    selectAddAgentReference,
    navigateToEnterAgentReference,
    selectEnterAgentReference,
    navigateToVendorCYA
  )
}
