/*
 * Copyright 2023 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.perftests.charities.simulation

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.charities.simulation.RepaymentClaimSimulation
import uk.gov.hmrc.perftests.charities.simulation.OrganisationDetailsSimulation

class CharitiesSimulation
    extends PerformanceTestRunner
    with RepaymentClaimSimulation
    with OrganisationDetailsSimulation {
  runSimulation()
}
