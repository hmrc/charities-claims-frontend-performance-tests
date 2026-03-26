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
import uk.gov.hmrc.perftests.charities.requests.CBScheduleUploadRequests.{SelectDeleteCBWarningYes, SelectUpdateCBWarningYes, clickAttachUpdatedScheduleButtonCB, continueFromUploadedPageCB, getFileVerificationStatusCB, getUpscanUploadResponseCB, navigateToAboutCommunityBuildingsSchedule, navigateToCBSuccessBanner, navigateToCBUploaded, navigateToCheckYourCBSchedule, navigateToDeleteCBWarning, navigateToProblemWithYourCBSchedule, navigateToUpdateCBWarning, navigateToUploadCommunityBuildingsSchedule, postFileToUpscanCB, removeCBFromUploadedPage, selectUpdateScheduleNoCB, selectUpdateScheduleYesCB, submitScheduleUploadCB}
import uk.gov.hmrc.perftests.charities.requests.OrganisationRequests._
import uk.gov.hmrc.perftests.charities.requests.RepaymentRequests.{loginToAuthWizard, _}
import uk.gov.hmrc.perftests.requests.AuthLoginRequests.{authLogInForOrg, navigateToAuth}

trait CharitiesJourneyScheduleSimulation extends PerformanceTestRunner with BaseRequests {

  val uploadCBScheduleHappyPath: List[ActionBuilder] =
    List[ActionBuilder](
      navigateToAuth,
      authLogInForOrg,
      loginToAuthWizard,
      navigateToMakeACharityClaim,
      navigateToRepaymentClaimDetails,
      navigateToSelectClaimType,
      selectClaimTypeGASDS,
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
      navigateToAboutCommunityBuildingsSchedule,
      navigateToUploadCommunityBuildingsSchedule,
      postFileToUpscanCB("data/other-income-GoodData-large.ods"),
      getUpscanUploadResponseCB,
      navigateToCBUploaded
    ) ++ getFileVerificationStatusCB ++
      List[ActionBuilder](
        continueFromUploadedPageCB,
        navigateToCheckYourCBSchedule,
        selectUpdateScheduleNoCB,
        navigateToCBSuccessBanner,
        submitScheduleUploadCB,
        navigateToMakeACharityClaim
      )

  val uploadCBScheduleErrorPage: List[ActionBuilder] =
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
      navigateToAboutCommunityBuildingsSchedule,
      navigateToUploadCommunityBuildingsSchedule,
      postFileToUpscanCB("data/Gift-Aid-Schedule-TEST-MasterError.ods"),
      getUpscanUploadResponseCB
    ) ++ getFileVerificationStatusCB ++
      List[ActionBuilder](
        continueFromUploadedPageCB,
        navigateToProblemWithYourCBSchedule,
        navigateToDeleteCBWarning,
        SelectDeleteCBWarningYes,
        navigateToMakeACharityClaim
      )

  val uploadCBScheduleRemoveWarningIterations: List[ActionBuilder] =
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
      navigateToAboutCommunityBuildingsSchedule,
      navigateToUploadCommunityBuildingsSchedule,
      postFileToUpscanCB("data/other-income-GoodData-large.ods"),
      getUpscanUploadResponseCB,
      navigateToCBUploaded
    ) ++ getFileVerificationStatusCB ++
      List[ActionBuilder](
        removeCBFromUploadedPage,
        navigateToUploadCommunityBuildingsSchedule,
        postFileToUpscanCB("data/other-income-GoodData-large.ods"),
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
        postFileToUpscanCB("data/Gift-Aid-Schedule-TEST-MasterError.ods"),
        getUpscanUploadResponseCB
      ) ++ getFileVerificationStatusCB ++
      List[ActionBuilder](
        continueFromUploadedPageCB,
        navigateToProblemWithYourCBSchedule,
        clickAttachUpdatedScheduleButtonCB
      )

  setup("cb-journey-1", "Community Buildings schedule upload journey with success") withActions (
    uploadCBScheduleHappyPath: _*
    )

  setup("cb-journey-2", "Community Buildings schedule upload journey with validation failed file to Errors page") withActions (
    uploadCBScheduleErrorPage: _*
    )

  setup(
    "cb-journey-3",
    "Community Buildings journeys removing from upload page, deleting from Warning page, reuploading CB etc"
  ) withActions (
    uploadCBScheduleRemoveWarningIterations: _*
    )

}
