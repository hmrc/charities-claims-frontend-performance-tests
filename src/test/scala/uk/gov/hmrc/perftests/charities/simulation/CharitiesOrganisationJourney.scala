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
import uk.gov.hmrc.perftests.charities.requests.CBScheduleUploadRequests._
import uk.gov.hmrc.perftests.charities.requests.CCScheduleUploadRequests._
import uk.gov.hmrc.perftests.charities.requests.GASScheduleUploadRequests._
import uk.gov.hmrc.perftests.charities.requests.OIScheduleUploadRequests._
import uk.gov.hmrc.perftests.charities.requests.OrganisationDetailsRequests._
import uk.gov.hmrc.perftests.charities.requests.RepaymentRequests.{loginToAuthWizard, _}
import uk.gov.hmrc.perftests.requests.AuthLoginRequests.{authLogInForOrg, navigateToAuth}

trait CharitiesOrganisationJourney extends PerformanceTestRunner with BaseRequests {

  val UIJourneyRegulatorAuth: List[ActionBuilder] =
    List[ActionBuilder](
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

  val uploadScheduleExhaustive: List[ActionBuilder] =
    List[ActionBuilder](
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
      postFileToUpscanGAS("data/gift-aid-valid-large.ods"),
      getUpscanUploadResponseGAS,
      navigateToGASUploaded
    ) ++ getFileVerificationStatusGAS ++
      List[ActionBuilder](
        removeGASFromUploadedPage,
        navigateToUploadGiftAidSchedule,
        postFileToUpscanGAS("data/gift-aid-valid-large.ods"),
        getUpscanUploadResponseGAS,
        navigateToGASUploaded
      ) ++ getFileVerificationStatusGAS ++
      List[ActionBuilder](
        continueFromUploadedPageGAS,
        navigateToCheckYourGASSchedule,
        selectUpdateScheduleYesGAS,
        navigateToUpdateGASWarning,
        SelectUpdateGASWarningYes,
        navigateToUploadGiftAidSchedule,
        postFileToUpscanGAS("data/gift-aid-MasterError.ods"),
        getUpscanUploadResponseGAS
      ) ++ getFileVerificationStatusGAS ++
      List[ActionBuilder](
        continueFromUploadedPageGAS,
        navigateToProblemWithYourGASSchedule,
        clickAttachUpdatedScheduleButtonGAS,
        navigateToUploadGiftAidSchedule,
        postFileToUpscanGAS("data/gift-aid-valid-large.ods"),
        getUpscanUploadResponseGAS,
        navigateToGASUploaded
      ) ++ getFileVerificationStatusGAS ++
      List[ActionBuilder](
        continueFromUploadedPageGAS,
        navigateToCheckYourGASSchedule,
        selectUpdateScheduleNoGAS,
        navigateToGASSuccessBanner,
        submitScheduleUploadGAS,
        navigateToMakeACharityClaim,
        navigateToAboutOtherIncomeSchedule,
        navigateToUploadOtherIncomeSchedule,
        postFileToUpscanOI("data/other-income-valid-large.ods"),
        getUpscanUploadResponseOI,
        navigateToOIUploaded
      ) ++ getFileVerificationStatusOI ++
      List[ActionBuilder](
        removeOIFromUploadedPage,
        navigateToUploadOtherIncomeSchedule,
        postFileToUpscanOI("data/other-income-valid-large.ods"),
        getUpscanUploadResponseOI,
        navigateToOIUploaded
      ) ++ getFileVerificationStatusOI ++
      List[ActionBuilder](
        continueFromUploadedPageOI,
        navigateToCheckYourOISchedule,
        selectUpdateScheduleYesOI,
        navigateToUpdateOIWarning,
        SelectUpdateOIWarningYes,
        navigateToUploadOtherIncomeSchedule,
        postFileToUpscanOI("data/other-income-MasterError.ods"),
        getUpscanUploadResponseOI
      ) ++ getFileVerificationStatusOI ++
      List[ActionBuilder](
        continueFromUploadedPageOI,
        navigateToProblemWithYourOISchedule,
        clickAttachUpdatedScheduleButtonOI,
        navigateToUploadOtherIncomeSchedule,
        postFileToUpscanOI("data/other-income-valid-large.ods"),
        getUpscanUploadResponseOI,
        navigateToOIUploaded
      ) ++ getFileVerificationStatusOI ++
      List[ActionBuilder](
        continueFromUploadedPageOI,
        navigateToCheckYourOISchedule,
        selectUpdateScheduleNoOI,
        navigateToOISuccessBanner,
        submitScheduleUploadOI,
        navigateToMakeACharityClaim,
        navigateToAboutCommunityBuildingsSchedule,
        navigateToUploadCommunityBuildingsSchedule,
        postFileToUpscanCB("data/community-buildings-valid-large.ods"),
        getUpscanUploadResponseCB,
        navigateToCBUploaded
      ) ++ getFileVerificationStatusCB ++
      List[ActionBuilder](
        removeCBFromUploadedPage,
        navigateToUploadCommunityBuildingsSchedule,
        postFileToUpscanCB("data/community-buildings-valid-large.ods"),
        getUpscanUploadResponseCB,
        navigateToCBUploaded
      ) ++ getFileVerificationStatusCB ++
      List[ActionBuilder](
        continueFromUploadedPageCB,
        navigateToCheckYourCBSchedule,
        selectUpdateScheduleYesCB,
        navigateToUpdateCBWarning,
        SelectUpdateCBWarningYes,
        navigateToUploadCommunityBuildingsSchedule,
        postFileToUpscanCB("data/community-buildings-MasterError.ods"),
        getUpscanUploadResponseCB
      ) ++ getFileVerificationStatusCB ++
      List[ActionBuilder](
        continueFromUploadedPageCB,
        navigateToProblemWithYourCBSchedule,
        clickAttachUpdatedScheduleButtonCB,
        navigateToUploadCommunityBuildingsSchedule,
        postFileToUpscanCB("data/community-buildings-MasterError.ods"),
        getUpscanUploadResponseCB
      ) ++ getFileVerificationStatusCB ++
      List[ActionBuilder](
        continueFromUploadedPageCB,
        navigateToProblemWithYourCBSchedule,
        navigateToDeleteCBWarning,
        SelectDeleteCBWarningYes,
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
        clickAttachUpdatedScheduleButtonCC,
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

  setup("UI-form-journey-1", "Repayment and Organisation Journey with Regulator Number and Auth Official") withActions (
    UIJourneyRegulatorAuth: _*
  )

  setup(
    "upload-Schedule-Exhaustive",
    "GA/OI/CB/CC schedules validated and failed validation uploads, deletion, reuploads, warnings"
  ) withActions (
    uploadScheduleExhaustive: _*
  )

}
