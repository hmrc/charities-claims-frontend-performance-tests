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
import uk.gov.hmrc.perftests.charities.requests.DeclarationRequests._
import uk.gov.hmrc.perftests.charities.requests.GASDSRequests._
import uk.gov.hmrc.perftests.charities.requests.GASScheduleUploadRequests._
import uk.gov.hmrc.perftests.charities.requests.OIScheduleUploadRequests._
import uk.gov.hmrc.perftests.charities.requests.OrganisationDetailsRequests._
import uk.gov.hmrc.perftests.charities.requests.RepaymentRequests.{loginToAuthWizard, _}
import uk.gov.hmrc.perftests.requests.AuthLoginRequests.{authLogInForAgent, authLogInForOrg, navigateToAuth}

trait CharitiesAgentFullSubmission extends PerformanceTestRunner with BaseRequests {

  val AgentUIJourneyTOPUPRegulatorAuth: List[ActionBuilder] =
    List[ActionBuilder](
      navigateToAuth,
      authLogInForAgent,
      loginToAuthWizard,
      navigateToRepaymentClaimDetails,
      navigateToHMRCCharityRefAgent,
      enterHMRCReferenceAgent,
      navigateToCharityNameAgent,
      enterCharityNameAgent,
      navigateToSelectClaimType,
      selectClaimTypeGASDSOnly,
      navigateToGASDSCheckbox,
      selectGASDSTopUP,
      navigateToGASDSOverclaimed,
      selectGASDSOverclaimedYes,
      navigateToHaveClaimReferenceAgent,
      selectReference,
      navigateToEnterClaimReferenceNumberAgent,
      enterClaimReferenceValue,
      navigateToCheckYourRepaymentClaimAgent,
      submitRepaymentClaim,
      navigateToMakeACharityClaim,
      navigateToAboutTheOrg,
      navigateToCharityRegulator,
      selectEnglandWalesRegistered,
      navigateToRegulatorNumber,
      enterRegulatorNumber,
      navigateToSendPaymentToAgent,
      selectAgentNomineeAgent,
      navigateToTelephoneAgent,
      enterTelephoneAgent,
      navigateToUKAddressAgent,
      selectUKAddressYesAgent,
      navigateToPostcodeAgent,
      enterPostcodeAgent,
      navigateToCheckYourOrganisationDetailsAgent,
      submitOrganisationDetails,
      navigateToMakeACharityClaim,
      navigateToAboutTheGASDS,
      navigateToAdjustmentAmountGASDS,
      enterAdjustmentAmountGASDS,
      navigateToCYAAdjustmentGASDSAgent,
      navigateToTaxYear1GASDSAgent,
      enterTaxYear1GASDS,
      navigateToAmount1GASDSAgent,
      enterAmount1GASDS,
      navigateToCYATaxYear1GASDSAgent,
      navigateToClaim1Added,
      selectSecondYearYes,
      navigateToTaxYear2GASDSAgent,
      enterTaxYear2GASDS,
      navigateToAmount2GASDSAgent,
      enterAmount2GASDS,
      navigateToCYATaxYear2GASDSAgent,
      navigateToClaim2Added,
      selectThirdYearYes,
      navigateToTaxYear3GASDSAgent,
      enterTaxYear3GASDS,
      navigateToAmount3GASDSAgent,
      enterAmount3GASDS,
      navigateToCYATaxYear3GASDSAgent,
      navigateToClaim3Added,
      navigateToFinalCYAAgent,
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

  val AgentScheduleUploadAllIterations: List[ActionBuilder] =
    List[ActionBuilder](
      navigateToAuth,
      authLogInForAgent,
      loginToAuthWizard,
      navigateToRepaymentClaimDetails,
      navigateToHMRCCharityRefAgent,
      enterHMRCReferenceAgent,
      navigateToCharityNameAgent,
      enterCharityNameAgent,
      navigateToSelectClaimType,
      selectClaimTypeGASDS,
      navigateToGASDSCheckbox,
      selectGASDSAllYes,
      navigateToGASDSOverclaimed,
      selectGASDSOverclaimedYes,
      navigateToHaveClaimReferenceAgent,
      selectReference,
      navigateToEnterClaimReferenceNumberAgent,
      enterClaimReferenceValue,
      navigateToCheckYourRepaymentClaimAgent,
      submitRepaymentClaim,
      navigateToMakeACharityClaim,
      navigateToAboutTheOrg,
      navigateToCharityRegulator,
      selectNotRegistered,
      navigateToWhyNotRegistered,
      selectCharityExcepted,
      navigateToCharityExceptedAgent,
      navigateToSendPaymentToAgent,
      selectCASCAgent,
      navigateToTelephoneAgent,
      enterTelephoneAgent,
      navigateToUKAddressAgent,
      selectUKAddressYesAgent,
      navigateToPostcodeAgent,
      enterPostcodeAgent,
      navigateToCheckYourOrganisationDetailsAgent,
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
        navigateToCheckYourGASScheduleAgent,
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
        navigateToCheckYourOIScheduleAgent,
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
        continueFromUploadedPageCB,
        navigateToCheckYourCBScheduleAgent,
        selectUpdateScheduleNoCB,
        navigateToCBSuccessBanner,
        submitScheduleUploadCB,
        navigateToMakeACharityClaim,
        navigateToAboutConnectedCharitiesSchedule,
        navigateToUploadConnectedCharitiesSchedule,
        postFileToUpscanCC("data/connected-charities-valid-large.ods"),
        getUpscanUploadResponseCC,
        navigateToCCUploaded
      ) ++ getFileVerificationStatusCC ++
      List[ActionBuilder](
        continueFromUploadedPageCC,
        navigateToCheckYourCCScheduleAgent,
        selectUpdateScheduleNoCC,
        navigateToCCSuccessBanner,
        submitScheduleUploadCC,
        navigateToMakeACharityClaim,
        redirectToRegisterCharityFromAdjustment,
        selectNoContinueRegister,
        navigateToWhatAdjustmentsToClaim,
        enterAdjustmentText,
        navigateToDeclaration,
        submitClaimDeclaration
      ) ++ pollNavigateToClaimComplete ++
      List[ActionBuilder](
        printSubmissionSummary
      )

  setup(
    "Agent-UI-GASDS-journey-1",
    "Agent's full Submission Journey with Regulator Number and Auth Official with GASDS Top Up only, no uploads"
  ) withActions (
    AgentUIJourneyTOPUPRegulatorAuth: _*
  )

  setup(
    "Agent-schedule-journey-all-iterations",
    "Agent's GASDS/GA/OI/CB/CC valid schedules only"
  ) withActions (
    AgentScheduleUploadAllIterations: _*
  )
}
