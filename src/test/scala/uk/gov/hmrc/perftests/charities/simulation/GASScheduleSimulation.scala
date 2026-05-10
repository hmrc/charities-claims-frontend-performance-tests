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

import io.gatling.core.Predef.{exec, scenario}
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.charities.requests.{BaseRequests, GASScheduleUploadRequests}
import uk.gov.hmrc.perftests.charities.requests.GASScheduleUploadRequests.{SelectDeleteGASWarningYes, SelectUpdateGASWarningYes, clickAttachUpdatedScheduleButtonGAS, continueFromUploadedPageGAS, getFileVerificationStatusGAS, getUpscanUploadResponseGAS, navigateToAboutGiftAidSchedule, navigateToCheckYourGASSchedule, navigateToDeleteGASWarning, navigateToGASSuccessBanner, navigateToGASUploaded, navigateToProblemWithYourGASSchedule, navigateToUpdateGASWarning, navigateToUploadGiftAidSchedule, postFileToUpscanGAS, removeGASFromUploadedPage, selectUpdateScheduleNoGAS, selectUpdateScheduleYesGAS, submitScheduleUploadGAS}
import uk.gov.hmrc.perftests.charities.requests.OrganisationRequests._
import uk.gov.hmrc.perftests.charities.requests.RepaymentRequests.{enterClaimReferenceValue, loginToAuthWizard, navigateToCheckYourRepaymentClaim, navigateToEnterClaimReferenceNumber, navigateToHaveClaimReference, selectReference, submitRepaymentClaim, _}
import uk.gov.hmrc.perftests.requests.AuthLoginRequests.{authLogInForOrg, navigateToAuth}
import io.gatling.core.action.builder.ActionBuilder

trait GASScheduleSimulation extends PerformanceTestRunner with BaseRequests {

  val uploadGASScheduleHappyPath: List[ActionBuilder] =
    List[ActionBuilder](
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
      navigateToMakeACharityClaim,
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
        navigateToMakeACharityClaim
      )

  val uploadGASScheduleErrorPage: List[ActionBuilder] =
    List[ActionBuilder](
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
      navigateToMakeACharityClaim,
      navigateToAboutGiftAidSchedule,
      navigateToUploadGiftAidSchedule,
      postFileToUpscanGAS("data/gift-aid-MasterError.ods"),
      getUpscanUploadResponseGAS
    ) ++ getFileVerificationStatusGAS ++
      List[ActionBuilder](
        continueFromUploadedPageGAS,
        navigateToProblemWithYourGASSchedule,
        navigateToDeleteGASWarning,
        SelectDeleteGASWarningYes,
        navigateToMakeACharityClaim
      )


  setup("gas-journey-1", "Gift Aid schedule upload journey with success") withActions (
    uploadGASScheduleHappyPath: _*
  )

  setup("gas-journey-2", "Gift Aid schedule upload journey with validation failed file to Errors page") withActions (
    uploadGASScheduleErrorPage: _*
  )


}
