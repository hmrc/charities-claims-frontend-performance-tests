/*
 * Copyright 2025 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.perftests.charities.simulation

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.charities.requests.*



trait RepaymentClaimSimulation extends PerformanceTestRunner with BaseRequests{

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