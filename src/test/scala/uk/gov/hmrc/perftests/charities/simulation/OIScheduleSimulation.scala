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
import uk.gov.hmrc.perftests.charities.requests.OIScheduleUploadRequests.{SelectDeleteOIWarningYes, SelectUpdateOIWarningYes, clickAttachUpdatedScheduleButtonOI, continueFromUploadedPageOI, getFileVerificationStatusOI, getUpscanUploadResponseOI, navigateToAboutOtherIncomeSchedule, navigateToCheckYourOISchedule, navigateToDeleteOIWarning, navigateToOISuccessBanner, navigateToOIUploaded, navigateToProblemWithYourOISchedule, navigateToUpdateOIWarning, navigateToUploadOtherIncomeSchedule, postFileToUpscanOI, removeOIFromUploadedPage, selectUpdateScheduleNoOI, selectUpdateScheduleYesOI, submitScheduleUploadOI}
import uk.gov.hmrc.perftests.charities.requests.OrganisationRequests._
import uk.gov.hmrc.perftests.charities.requests.RepaymentRequests.{loginToAuthWizard, _}
import uk.gov.hmrc.perftests.requests.AuthLoginRequests.{authLogInForOrg, navigateToAuth}

trait OIScheduleSimulation extends PerformanceTestRunner with BaseRequests {

  val uploadOIScheduleHappyPath: List[ActionBuilder] =
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
      navigateToAboutOtherIncomeSchedule,
      navigateToUploadOtherIncomeSchedule,
      postFileToUpscanOI("data/other-income-GoodData-large.ods"),
      getUpscanUploadResponseOI,
      navigateToOIUploaded
    ) ++ getFileVerificationStatusOI ++
      List[ActionBuilder](
        continueFromUploadedPageOI,
        navigateToCheckYourOISchedule,
        selectUpdateScheduleNoOI,
        navigateToOISuccessBanner,
        submitScheduleUploadOI,
        navigateToMakeACharityClaim
      )

  val uploadOIScheduleErrorPage: List[ActionBuilder] =
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
      navigateToAboutOtherIncomeSchedule,
      navigateToUploadOtherIncomeSchedule,
      postFileToUpscanOI("data/Gift-Aid-Schedule-TEST-MasterError.ods"),
      getUpscanUploadResponseOI
    ) ++ getFileVerificationStatusOI ++
      List[ActionBuilder](
        continueFromUploadedPageOI,
        navigateToProblemWithYourOISchedule,
        navigateToDeleteOIWarning,
        SelectDeleteOIWarningYes,
        navigateToMakeACharityClaim
      )

  val uploadOIScheduleRemoveWarningIterations: List[ActionBuilder] =
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
      navigateToAboutOtherIncomeSchedule,
      navigateToUploadOtherIncomeSchedule,
      postFileToUpscanOI("data/other-income-GoodData-large.ods"),
      getUpscanUploadResponseOI,
      navigateToOIUploaded
    ) ++ getFileVerificationStatusOI ++
      List[ActionBuilder](
        removeOIFromUploadedPage,
        navigateToUploadOtherIncomeSchedule,
        postFileToUpscanOI("data/other-income-GoodData-large.ods"),
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
        postFileToUpscanOI("data/Gift-Aid-Schedule-TEST-MasterError.ods"),
        getUpscanUploadResponseOI
      ) ++ getFileVerificationStatusOI ++
      List[ActionBuilder](
        continueFromUploadedPageOI,
        navigateToProblemWithYourOISchedule,
        clickAttachUpdatedScheduleButtonOI
      )

  setup("oi-journey-1", "Other Income schedule upload journey with success") withActions (
    uploadOIScheduleHappyPath: _*
  )

  setup("oi-journey-2", "Other Income schedule upload journey with validation failed file to Errors page") withActions (
    uploadOIScheduleErrorPage: _*
  )

  setup(
    "oi-journey-3",
    "Other Income journeys removing from upload page, deleting from Warning page, reuploading OI etc"
  ) withActions (
    uploadOIScheduleRemoveWarningIterations: _*
  )

}
