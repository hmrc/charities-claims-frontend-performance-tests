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

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.charities.requests.OrganisationRequests
import uk.gov.hmrc.perftests.charities.requests.AuthLoginRequests._
import uk.gov.hmrc.perftests.charities.requests.BaseRequests

trait OrganisationDetailsSimulation extends PerformanceTestRunner with BaseRequests {

//  setup("vendor-questions", "Vendor Questions Journey").withRequests(
//    navigateToAuth,
//    authLogInForOrg,
//    loginToAuthWizard,
//    navigateToReturnTaskList,
//    navigateToVendorOverview,
//    selectAddAnotherVendor,
//    navigateToAboutTheVendor,
//    navigateToWhoTheVendor,
//    selectCompany,
//    navigateToVendorNamePage,
//    selectVendorCompanyName,
//    navigateToConfirmAddress,
//    ConfirmAddress,
//    navigateToVendorCYA
//  )
}
