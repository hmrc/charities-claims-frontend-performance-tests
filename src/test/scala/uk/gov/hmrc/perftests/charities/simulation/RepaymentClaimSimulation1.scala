/*
 * Copyright 2025 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.perftests.charities.simulation

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.sdlt.requests.PrelimRequests.{enterPurchaserName, loginToAuthWizard, navigateToBeforeYouStart, navigateToIndividualOrBusiness, navigateToPrelimCheckYourAnswers, navigateToPurchaserName, navigateToTransactionType, selectIndividualOrBusiness, selectTransactionType, submitPreliminaryQuestions}
import uk.gov.hmrc.perftests.sdlt.requests.AddressLookupRequests.{getLookupAddress,getLookupAddressEdit,postLookupAddress,getLookupAddressConfirm}
import uk.gov.hmrc.perftests.requests.AuthLoginRequests.{authLogIn, navigateToAuth}
import uk.gov.hmrc.perftests.sdlt.requests.BaseRequests


trait FilingPrelimSimulation extends PerformanceTestRunner with BaseRequests{

  setup("preliminary-questions", "Preliminary Questions Journey").withRequests(
    navigateToAuth, authLogIn,
    loginToAuthWizard, navigateToBeforeYouStart,
    navigateToIndividualOrBusiness, selectIndividualOrBusiness,
    navigateToPurchaserName, enterPurchaserName,
    getLookupAddress,getLookupAddressEdit,postLookupAddress,getLookupAddressConfirm,
    navigateToTransactionType, selectTransactionType,
    navigateToPrelimCheckYourAnswers, submitPreliminaryQuestions

  )
}