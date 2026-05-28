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
import uk.gov.hmrc.perftests.requests.AuthLoginRequests.{authLogInForOrg, navigateToAuth}

trait CharitiesFullSubmitTimeoutFriendly extends PerformanceTestRunner with BaseRequests {


  val schedule3UploadsSubmissions: List[ActionBuilder] =
    List[ActionBuilder](
      navigateToAuth,
      authLogInForOrg,
      loginToAuthWizard,
      navigateToCharityManagementOrganisation,
      navigateToMakeACharityClaim,
      navigateToRepaymentClaimDetails,
      navigateToSelectClaimType,
      selectClaimTypeGACB,
      navigateToGASDSCheckbox,
      selectGASDScbTopup,
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
        navigateToAboutCommunityBuildingsSchedule,
        navigateToUploadCommunityBuildingsSchedule,
        postFileToUpscanCB("data/community-buildings-valid-large.ods"),
        getUpscanUploadResponseCB,
        navigateToCBUploaded
      ) ++ getFileVerificationStatusCB ++
      List[ActionBuilder](
        continueFromUploadedPageCB,
        navigateToCheckYourCBSchedule,
        selectUpdateScheduleNoCB,
        navigateToCBSuccessBanner,
        submitScheduleUploadCB,
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
    "schedule-journey-org-timeout-friendly",
    "GASDS/GA/OI/CB valid schedules only"
  ) withActions (
    schedule3UploadsSubmissions: _*
  )
}
