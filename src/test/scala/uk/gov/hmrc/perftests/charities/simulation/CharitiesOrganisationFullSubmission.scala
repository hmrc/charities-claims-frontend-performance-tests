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
import uk.gov.hmrc.perftests.charities.requests.DeclarationRequests.{enterAdjustmentText, navigateToDeclaration, navigateToDeclarationForUnregulated, navigateToWhatAdjustmentsToClaim, pollNavigateToClaimComplete, printSubmissionSummary, redirectToAdjustmentsFromRegister, redirectToRegisterCharityFromAdjustment, selectNoContinueRegister, submitClaimDeclaration}
import uk.gov.hmrc.perftests.charities.requests.GASDSRequests.{enterAdjustmentAmountGASDS, enterAmount1GASDS, enterAmount2GASDS, enterAmount3GASDS, enterTaxYear1GASDS, enterTaxYear2GASDS, enterTaxYear3GASDS, navigateToAboutTheGASDS, navigateToAdjustmentAmountGASDS, navigateToAmount1GASDS, navigateToAmount2GASDS, navigateToAmount3GASDS, navigateToCYAAdjustmentGASDS, navigateToCYATaxYear1GASDS, navigateToCYATaxYear2GASDS, navigateToCYATaxYear3GASDS, navigateToClaim1Added, navigateToClaim2Added, navigateToClaim3Added, navigateToFinalCYA, navigateToTaxYear1GASDS, navigateToTaxYear2GASDS, navigateToTaxYear3GASDS, selectSecondYearYes, selectThirdYearYes, submitGASDSDetails}
import uk.gov.hmrc.perftests.charities.requests.GASScheduleUploadRequests._
import uk.gov.hmrc.perftests.charities.requests.OIScheduleUploadRequests._
import uk.gov.hmrc.perftests.charities.requests.OrganisationRequests._
import uk.gov.hmrc.perftests.charities.requests.RepaymentRequests.{loginToAuthWizard, _}
import uk.gov.hmrc.perftests.requests.AuthLoginRequests.{authLogInForOrg, navigateToAuth}

trait CharitiesOrganisationFullSubmission extends PerformanceTestRunner with BaseRequests {

  val UIJourneyTOPUPRegulatorAuth: List[ActionBuilder] =
    List[ActionBuilder](
      navigateToAuth,
      authLogInForOrg,
      loginToAuthWizard,
      navigateToCharityManagementOrganisation,
      navigateToMakeACharityClaim,
      navigateToRepaymentClaimDetails,
      navigateToSelectClaimType,
      selectClaimTypeGASDSOnly,
      navigateToGASDSCheckbox,
      selectGASDSTopUP,
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
      navigateToMakeACharityClaim,
      navigateToAboutTheGASDS,
      navigateToAdjustmentAmountGASDS,
      enterAdjustmentAmountGASDS,
      navigateToCYAAdjustmentGASDS,
      navigateToTaxYear1GASDS,
      enterTaxYear1GASDS,
      navigateToAmount1GASDS,
      enterAmount1GASDS,
      navigateToCYATaxYear1GASDS,
      navigateToClaim1Added,
      selectSecondYearYes,
      navigateToTaxYear2GASDS,
      enterTaxYear2GASDS,
      navigateToAmount2GASDS,
      enterAmount2GASDS,
      navigateToCYATaxYear2GASDS,
      navigateToClaim2Added,
      selectThirdYearYes,
      navigateToTaxYear3GASDS,
      enterTaxYear3GASDS,
      navigateToAmount3GASDS,
      enterAmount3GASDS,
      navigateToCYATaxYear3GASDS,
      navigateToClaim3Added,
      navigateToFinalCYA,
      submitGASDSDetails,
      navigateToMakeACharityClaim,
      navigateToWhatAdjustmentsToClaim,
      enterAdjustmentText,
      navigateToDeclaration,
      submitClaimDeclaration
    ) ++ pollNavigateToClaimComplete ++
      List[ActionBuilder](
      printSubmissionSummary
    )

  val UIJourneyExceptedTrustee: List[ActionBuilder] =
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
      navigateToAboutTheGASDS,
      navigateToAdjustmentAmountGASDS,
      enterAdjustmentAmountGASDS,
      navigateToCYAAdjustmentGASDS,
      navigateToTaxYear1GASDS,
      enterTaxYear1GASDS,
      navigateToAmount1GASDS,
      enterAmount1GASDS,
      navigateToCYATaxYear1GASDS,
      navigateToClaim1Added,
      selectSecondYearYes,
      navigateToTaxYear2GASDS,
      enterTaxYear2GASDS,
      navigateToAmount2GASDS,
      enterAmount2GASDS,
      navigateToCYATaxYear2GASDS,
      navigateToClaim2Added,
      selectThirdYearYes,
      navigateToTaxYear3GASDS,
      enterTaxYear3GASDS,
      navigateToAmount3GASDS,
      enterAmount3GASDS,
      navigateToCYATaxYear3GASDS,
      navigateToClaim3Added,
      navigateToFinalCYA,
      submitGASDSDetails
    )

  val scheduleUploadAllIterations: List[ActionBuilder] =
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
      navigateToAboutTheGASDS,
      navigateToAdjustmentAmountGASDS,
      enterAdjustmentAmountGASDS,
      navigateToCYAAdjustmentGASDS,
      navigateToTaxYear1GASDS,
      enterTaxYear1GASDS,
      navigateToAmount1GASDS,
      enterAmount1GASDS,
      navigateToCYATaxYear1GASDS,
      navigateToClaim1Added,
      selectSecondYearYes,
      navigateToTaxYear2GASDS,
      enterTaxYear2GASDS,
      navigateToAmount2GASDS,
      enterAmount2GASDS,
      navigateToCYATaxYear2GASDS,
      navigateToClaim2Added,
      selectThirdYearYes,
      navigateToTaxYear3GASDS,
      enterTaxYear3GASDS,
      navigateToAmount3GASDS,
      enterAmount3GASDS,
      navigateToCYATaxYear3GASDS,
      navigateToClaim3Added,
      navigateToFinalCYA,
      submitGASDSDetails,
      navigateToAboutGiftAidSchedule,
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
        continueFromUploadedPageOI,
        navigateToCheckYourOISchedule,
        selectUpdateScheduleNoOI,
        navigateToOISuccessBanner,
        submitScheduleUploadOI,
        navigateToMakeACharityClaim,
        navigateToAboutCommunityBuildingsSchedule,
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
        continueFromUploadedPageCC,
        navigateToCheckYourCCSchedule,
        selectUpdateScheduleNoCC,
        navigateToCCSuccessBanner,
        submitScheduleUploadCC,
        navigateToMakeACharityClaim,
        redirectToRegisterCharityFromAdjustment,
        selectNoContinueRegister,
        redirectToAdjustmentsFromRegister,
        enterAdjustmentText,
        navigateToDeclarationForUnregulated,
        submitClaimDeclaration
      ) ++ pollNavigateToClaimComplete ++
      List[ActionBuilder](
        printSubmissionSummary
      )

  val scheduleUploadAllRegulated: List[ActionBuilder] =
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
      selectCorporateTrusteeYes,
      navigateToCorporateTrusteeUKAddress,
      selectCorporateTrusteeUKAddressNo,
      navigateToCorporateTrusteeDetails,
      enterCorporateTrusteeDetails,
      navigateToCheckYourOrganisationDetails,
      submitOrganisationDetails,
      navigateToMakeACharityClaim,
      navigateToAboutTheGASDS,
      navigateToAdjustmentAmountGASDS,
      enterAdjustmentAmountGASDS,
      navigateToCYAAdjustmentGASDS,
      navigateToTaxYear1GASDS,
      enterTaxYear1GASDS,
      navigateToAmount1GASDS,
      enterAmount1GASDS,
      navigateToCYATaxYear1GASDS,
      navigateToClaim1Added,
      selectSecondYearYes,
      navigateToTaxYear2GASDS,
      enterTaxYear2GASDS,
      navigateToAmount2GASDS,
      enterAmount2GASDS,
      navigateToCYATaxYear2GASDS,
      navigateToClaim2Added,
      selectThirdYearYes,
      navigateToTaxYear3GASDS,
      enterTaxYear3GASDS,
      navigateToAmount3GASDS,
      enterAmount3GASDS,
      navigateToCYATaxYear3GASDS,
      navigateToClaim3Added,
      navigateToFinalCYA,
      submitGASDSDetails,
      navigateToAboutGiftAidSchedule,
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
        continueFromUploadedPageOI,
        navigateToCheckYourOISchedule,
        selectUpdateScheduleNoOI,
        navigateToOISuccessBanner,
        submitScheduleUploadOI,
        navigateToMakeACharityClaim,
        navigateToAboutCommunityBuildingsSchedule,
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
        continueFromUploadedPageCC,
        navigateToCheckYourCCSchedule,
        selectUpdateScheduleNoCC,
        navigateToCCSuccessBanner,
        submitScheduleUploadCC,
        navigateToMakeACharityClaim,
        navigateToWhatAdjustmentsToClaim,
        enterAdjustmentText,
        navigateToDeclaration,
        submitClaimDeclaration
      ) ++ pollNavigateToClaimComplete ++
      List[ActionBuilder](
        printSubmissionSummary
      )


  setup("UI-GASDS-journey-1", "Full Submission Journey with Regulator Number and Auth Official with GASDS Top Up only, no uploads") withActions (
    UIJourneyTOPUPRegulatorAuth: _*
  )

  setup(
    "UI-form-journey-2",
    "Repayment and Organisation Journey with with Charity Excepted and Corporate Trustee"
  ) withActions (
    UIJourneyExceptedTrustee: _*
  )

  setup(
    "schedule-journey-all-iterations",
    "GASDS/GA/OI/CB/CC valid schedules only"
  ) withActions (
    scheduleUploadAllIterations: _*
  )

  setup(
    "schedule-journey-all-upload-regulated",
    "GASDS/GA/OI/CB/CC valid schedules only regulated charity"
  ) withActions (
    scheduleUploadAllRegulated: _*
    )

}
