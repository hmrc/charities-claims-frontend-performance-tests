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

import io.gatling.core.action.builder.ActionBuilder
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.charities.requests.BaseRequests
import uk.gov.hmrc.perftests.charities.requests.GASScheduleUploadRequests._
import uk.gov.hmrc.perftests.charities.requests.OrganisationRequests._
import uk.gov.hmrc.perftests.charities.requests.RepaymentRequests.{loginToAuthWizard, _}
import uk.gov.hmrc.perftests.requests.AuthLoginRequests.{authLogInForOrg, navigateToAuth}

trait CharitiesJourneyScheduleSimulation extends PerformanceTestRunner with BaseRequests {

  val uploadGASScheduleHappyPath: List[ActionBuilder] =
    List[ActionBuilder](
      navigateToAuth,
      authLogInForOrg,
      loginToAuthWizard,
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
      navigateToMakeACharityClaim,
      navigateToAboutGiftAidSchedule,
      navigateToUploadGiftAidSchedule,
      postFileToUpscan("data/Gift-Aid-Schedule-Excel-GoodData-large2.ods"),
      getUpscanUploadResponse,
      navigateToGASUploaded
    ) ++ getFileVerificationStatus ++
      List[ActionBuilder](
        continueFromUploadedPage,
        navigateToCheckYourGASSchedule,
        selectUpdateScheduleNo,
        navigateToGASSuccessBanner,
        submitScheduleUpload,
        navigateToMakeACharityClaim
      )

  val uploadGASScheduleErrorPage: List[ActionBuilder] =
    List[ActionBuilder](
      navigateToAuth,
      authLogInForOrg,
      loginToAuthWizard,
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
      navigateToMakeACharityClaim,
      navigateToAboutGiftAidSchedule,
      navigateToUploadGiftAidSchedule,
      postFileToUpscan("data/Gift-Aid-Schedule-TEST-MasterError.ods"),
      getUpscanUploadResponse
    ) ++ getFileVerificationStatus ++
      List[ActionBuilder](
        continueFromUploadedPage,
        navigateToProblemWithYourGASSchedule,
        navigateToDeleteGASWarning,
        SelectDeleteGASWarningYes,
        navigateToMakeACharityClaim
      )

  val uploadGASScheduleRemoveWarningIterations: List[ActionBuilder] =
    List[ActionBuilder](
      navigateToAuth,
      authLogInForOrg,
      loginToAuthWizard,
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
      navigateToMakeACharityClaim,
      navigateToAboutGiftAidSchedule,
      navigateToUploadGiftAidSchedule,
      postFileToUpscan("data/Gift-Aid-Schedule-Excel-GoodData-large2.ods"),
      getUpscanUploadResponse,
      navigateToGASUploaded
    ) ++ getFileVerificationStatus ++
      List[ActionBuilder](
        removeGASFromUploadedPage,
        navigateToUploadGiftAidSchedule,
        postFileToUpscan("data/Gift-Aid-Schedule-Excel-GoodData-large2.ods"),
        getUpscanUploadResponse,
        navigateToGASUploaded
      ) ++ getFileVerificationStatus ++
      List[ActionBuilder](
        continueFromUploadedPage,
        navigateToCheckYourGASSchedule,
        selectUpdateScheduleYes,
        navigateToUpdateGASWarning,
        SelectUpdateGASWarningYes,
        navigateToUploadGiftAidSchedule,
        postFileToUpscan("data/Gift-Aid-Schedule-TEST-MasterError.ods"),
        getUpscanUploadResponse
      ) ++ getFileVerificationStatus ++
      List[ActionBuilder](
        continueFromUploadedPage,
        navigateToProblemWithYourGASSchedule,
        clickAttachUpdatedScheduleButton
      )

  setup("gas-journey-1", "Gift Aid schedule upload journey with success") withActions (
    uploadGASScheduleHappyPath: _*
  )

  setup("gas-journey-2", "Gift Aid schedule upload journey with validation failed file to Errors page") withActions (
    uploadGASScheduleErrorPage: _*
  )

  setup(
    "gas-journey-3",
    "GAS journeys removing from upload page, deleting from Warning page, reuploading GAS etc"
  ) withActions (
    uploadGASScheduleRemoveWarningIterations: _*
  )

}
