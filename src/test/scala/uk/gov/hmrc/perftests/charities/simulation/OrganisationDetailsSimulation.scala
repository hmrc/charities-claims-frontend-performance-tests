/*
 * Copyright 2025 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.perftests.charities.simulation

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.charities.requests.OrganisationRequests
import uk.gov.hmrc.perftests.charities.requests.AuthLoginRequests._
import uk.gov.hmrc.perftests.charities.requests.BaseRequests

trait OrganisationDetailsSimulation extends PerformanceTestRunner with BaseRequests {

  setup("vendor-questions", "Vendor Questions Journey").withRequests(
    navigateToAuth,
    authLogInForOrg,
    loginToAuthWizard,
    navigateToReturnTaskList,
    navigateToVendorOverview,
    selectAddAnotherVendor,
    navigateToAboutTheVendor,
    navigateToWhoTheVendor,
    selectCompany,
    navigateToVendorNamePage,
    selectVendorCompanyName,
    navigateToConfirmAddress,
    ConfirmAddress,
    navigateToVendorCYA
  )
}
