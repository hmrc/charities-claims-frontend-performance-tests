/*
 * Copyright 2025 HM Revenue & Customs
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

package uk.gov.hmrc.perftests.charities.simulation

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.charities.requests.BaseRequests
import uk.gov.hmrc.perftests.charities.requests.OrganisationDetailsRequests.{enterAuthorisedOfficialDetails, enterCorporateTrusteeDetails, enterRegulatorNumber, navigateToAboutTheOrg, navigateToAuthorisedOfficialDetails, navigateToAuthorisedOfficialUKAddress, navigateToCharityExcepted, navigateToCharityRegulator, navigateToCheckYourOrganisationDetails, navigateToCorporateTrustee, navigateToCorporateTrusteeDetails, navigateToCorporateTrusteeUKAddress, navigateToRegulatorNumber, navigateToWhyNotRegistered, selectAuthorisedOfficialUKAddressYes, selectCharityExcepted, selectCorporateTrusteeNo, selectCorporateTrusteeUKAddressNo, selectCorporateTrusteeYes, selectNotRegistered, selectScottishRegistered, submitOrganisationDetails}
import uk.gov.hmrc.perftests.charities.requests.RepaymentRequests.{enterClaimReferenceValue, loginToAuthWizard, navigateToCharityManagementOrganisation, navigateToCheckYourRepaymentClaim, navigateToEnterClaimReferenceNumber, navigateToGASDSCheckbox, navigateToGASDSOverclaimed, navigateToHaveClaimReference, navigateToMakeACharityClaim, navigateToRepaymentClaimDetails, navigateToSelectClaimType, selectClaimTypeGASDS, selectClaimTypeNoGASDS, selectGASDSAllYes, selectGASDSOverclaimedYes, selectReference, submitRepaymentClaim}
import uk.gov.hmrc.perftests.requests.AuthLoginRequests.{authLogInForOrg, navigateToAuth}

trait OrganisationDetailsSimulation extends PerformanceTestRunner with BaseRequests {

  setup("organisation-journey-1", "Organisation Questions Journey with No GASDS, excepted and corporate trustee")
    .withRequests(
      navigateToAuth,
      authLogInForOrg,
      loginToAuthWizard,
      navigateToCharityManagementOrganisation,
      navigateToMakeACharityClaim,
      navigateToRepaymentClaimDetails,
      navigateToSelectClaimType,
      selectClaimTypeNoGASDS,
      navigateToHaveClaimReference,
      selectReference,
      navigateToEnterClaimReferenceNumber,
      enterClaimReferenceValue,
      navigateToCheckYourRepaymentClaim,
      submitRepaymentClaim,
      navigateToMakeACharityClaim,
      navigateToAboutTheOrg,
      navigateToCharityRegulator,
      selectNotRegistered,
      navigateToWhyNotRegistered,
      selectCharityExcepted,
      navigateToCharityExcepted,
      navigateToCorporateTrustee,
      selectCorporateTrusteeYes,
      navigateToCorporateTrusteeUKAddress,
      selectCorporateTrusteeUKAddressNo,
      navigateToCorporateTrusteeDetails,
      enterCorporateTrusteeDetails,
      navigateToCheckYourOrganisationDetails,
      submitOrganisationDetails,
      navigateToMakeACharityClaim
    )

  setup(
    "organisation-journey-2",
    "Organisation Journey with GASDS, scottish regulator Number and Authorised Official"
  ).withRequests(
    navigateToAuth,
    authLogInForOrg,
    loginToAuthWizard,
    navigateToCharityManagementOrganisation,
    navigateToMakeACharityClaim,
    navigateToRepaymentClaimDetails,
    navigateToSelectClaimType,
    selectClaimTypeGASDS,
    navigateToGASDSCheckbox,
    selectGASDSAllYes,
    navigateToGASDSOverclaimed,
    selectGASDSOverclaimedYes,
    navigateToHaveClaimReference,
    selectReference,
    navigateToEnterClaimReferenceNumber,
    enterClaimReferenceValue,
    navigateToCheckYourRepaymentClaim,
    submitRepaymentClaim,
    navigateToMakeACharityClaim,
    navigateToAboutTheOrg,
    navigateToCharityRegulator,
    selectScottishRegistered,
    navigateToRegulatorNumber,
    enterRegulatorNumber,
    navigateToCorporateTrustee,
    selectCorporateTrusteeNo,
    navigateToAuthorisedOfficialUKAddress,
    selectAuthorisedOfficialUKAddressYes,
    navigateToAuthorisedOfficialDetails,
    enterAuthorisedOfficialDetails,
    navigateToCheckYourOrganisationDetails,
    submitOrganisationDetails,
    navigateToMakeACharityClaim
  )
}
