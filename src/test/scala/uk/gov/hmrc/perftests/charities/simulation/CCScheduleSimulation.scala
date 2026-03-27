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
import uk.gov.hmrc.perftests.charities.requests.CCScheduleUploadRequests.{SelectDeleteCCWarningYes, SelectUpdateCCWarningYes, clickAttachUpdatedScheduleButtonCC, continueFromUploadedPageCC, getFileVerificationStatusCC, getUpscanUploadResponseCC, navigateToAboutConnectedCharitiesSchedule, navigateToCheckYourCCSchedule, navigateToDeleteCCWarning, navigateToCCSuccessBanner, navigateToCCUploaded, navigateToProblemWithYourCCSchedule, navigateToUpdateCCWarning, navigateToUploadConnectedCharitiesSchedule, postFileToUpscanCC, removeCCFromUploadedPage, selectUpdateScheduleNoCC, selectUpdateScheduleYesCC, submitScheduleUploadCC}
import uk.gov.hmrc.perftests.charities.requests.OrganisationRequests._
import uk.gov.hmrc.perftests.charities.requests.RepaymentRequests.{loginToAuthWizard, _}
import uk.gov.hmrc.perftests.requests.AuthLoginRequests.{authLogInForOrg, navigateToAuth}

trait CCScheduleSimulation extends PerformanceTestRunner with BaseRequests {

  val uploadCCScheduleHappyPath: List[ActionBuilder] =
    List[ActionBuilder](
      navigateToAuth,
      authLogInForOrg,
      loginToAuthWizard,
      navigateToMakeACharityClaim,
      navigateToRepaymentClaimDetails,
      navigateToSelectClaimType,
      selectClaimTypeGASDS,
      navigateToGASDSNotCB,
      selectGASDSNotCBYes,
      navigateToGASDSCB,
      selectGASDSCBYes,
      navigateToGASDSOverclaimed,
      selectGASDSOverclaimedYes,
      navigateToConnectedToCharities,
      selectConnectedToCharitiesYes,
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
      navigateToAboutConnectedCharitiesSchedule,
      navigateToUploadConnectedCharitiesSchedule,
      postFileToUpscanCC("data/connected-charities-valid-large.ods"),
      getUpscanUploadResponseCC,
      navigateToCCUploaded
    ) ++ getFileVerificationStatusCC ++
      List[ActionBuilder](
        continueFromUploadedPageCC,
        navigateToCheckYourCCSchedule,
        selectUpdateScheduleNoCC,
        navigateToCCSuccessBanner,
        submitScheduleUploadCC,
        navigateToMakeACharityClaim
      )

  val uploadCCScheduleErrorPage: List[ActionBuilder] =
    List[ActionBuilder](
      navigateToAuth,
      authLogInForOrg,
      loginToAuthWizard,
      navigateToMakeACharityClaim,
      navigateToRepaymentClaimDetails,
      navigateToSelectClaimType,
      selectClaimTypeGASDS,
      navigateToGASDSNotCB,
      selectGASDSNotCBYes,
      navigateToGASDSCB,
      selectGASDSCBYes,
      navigateToGASDSOverclaimed,
      selectGASDSOverclaimedYes,
      navigateToConnectedToCharities,
      selectConnectedToCharitiesYes,
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
      navigateToAboutConnectedCharitiesSchedule,
      navigateToUploadConnectedCharitiesSchedule,
      postFileToUpscanCC("data/connected-charities-MasterError.ods"),
      getUpscanUploadResponseCC
    ) ++ getFileVerificationStatusCC ++
      List[ActionBuilder](
        continueFromUploadedPageCC,
        navigateToProblemWithYourCCSchedule,
        navigateToDeleteCCWarning,
        SelectDeleteCCWarningYes,
        navigateToMakeACharityClaim
      )

  val uploadCCScheduleRemoveWarningIterations: List[ActionBuilder] =
    List[ActionBuilder](
      navigateToAuth,
      authLogInForOrg,
      loginToAuthWizard,
      navigateToMakeACharityClaim,
      navigateToRepaymentClaimDetails,
      navigateToSelectClaimType,
      selectClaimTypeGASDS,
      navigateToGASDSNotCB,
      selectGASDSNotCBYes,
      navigateToGASDSCB,
      selectGASDSCBYes,
      navigateToGASDSOverclaimed,
      selectGASDSOverclaimedYes,
      navigateToConnectedToCharities,
      selectConnectedToCharitiesYes,
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
      navigateToAboutConnectedCharitiesSchedule,
      navigateToUploadConnectedCharitiesSchedule,
      postFileToUpscanCC("data/connected-charities-valid-large.ods"),
      getUpscanUploadResponseCC,
      navigateToCCUploaded
    ) ++ getFileVerificationStatusCC ++
      List[ActionBuilder](
        removeCCFromUploadedPage,
        navigateToUploadConnectedCharitiesSchedule,
        postFileToUpscanCC("data/connected-charities-valid-large.ods"),
        getUpscanUploadResponseCC,
        navigateToCCUploaded
      ) ++ getFileVerificationStatusCC ++
      List[ActionBuilder](
        continueFromUploadedPageCC,
        navigateToCheckYourCCSchedule,
        selectUpdateScheduleYesCC,
        navigateToUpdateCCWarning,
        SelectUpdateCCWarningYes,
        navigateToUploadConnectedCharitiesSchedule,
        postFileToUpscanCC("data/connected-charities-MasterError.ods"),
        getUpscanUploadResponseCC
      ) ++ getFileVerificationStatusCC ++
      List[ActionBuilder](
        continueFromUploadedPageCC,
        navigateToProblemWithYourCCSchedule,
        clickAttachUpdatedScheduleButtonCC
      )

  setup("cc-journey-1", "Connected Charities schedule upload journey with success") withActions (
    uploadCCScheduleHappyPath: _*
    )

  setup("cc-journey-2", "Connected Charities schedule upload journey with validation failed file to Errors page") withActions (
    uploadCCScheduleErrorPage: _*
    )

  setup(
    "cc-journey-3",
    "Connected Charities journeys removing from upload page, deleting from Warning page, reuploading CC etc"
  ) withActions (
    uploadCCScheduleRemoveWarningIterations: _*
    )

}
