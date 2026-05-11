/*
 * Copyright 2023 HM Revenue & Customs
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

package uk.gov.hmrc.perftests.charities.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

import java.time.LocalDate

object GASDSRequests extends ServicesConfiguration with BaseRequests {

  def currentTaxYear(): Int = {
    val today        = LocalDate.now()
    val year         = today.getYear
    val taxYearStart = LocalDate.of(year, 4, 6)

    if (today.isBefore(taxYearStart)) year
    else year + 1
  }

  val TaxYear: String               = currentTaxYear().toString
  val earliestTaxYear: String       = (currentTaxYear() - 1).toString
  val secondEarliestTaxYear: String = (currentTaxYear() - 2).toString
  val previousTaxYear: String       = (currentTaxYear() - 3).toString

  val navigateToAboutTheGASDS: HttpRequestBuilder =
    http("Navigate to About the GASDS page")
      .get(s"$baseUrl$redirectUrl$aboutTheGASDSpage")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("About Gift Aid Small Donations Scheme schedule"))

  val navigateToAdjustmentAmountGASDS: HttpRequestBuilder =
    http("Navigate to Adjustment Amount of GASDS page")
      .get(s"$baseUrl$redirectUrl$adjustmentGASDS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What is the adjustment amount for Gift Aid previously overclaimed?"))

  val amountForAll = "999999.99"

  val enterAdjustmentAmountGASDS: HttpRequestBuilder =
    http("Enter your Adjustment Amount page")
      .post(s"$baseUrl$redirectUrl$adjustmentGASDS")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("amount", amountForAll)
      .check(status.is(303))

  val navigateToCYAAdjustmentGASDS: HttpRequestBuilder =
    http("Navigate to CYA Adjustment Amount of GASDS page")
      .get(s"$baseUrl$redirectUrl$adjustmentsCYAGASDS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Check your GASDS adjustment amount"))

  val navigateToTaxYear1GASDS: HttpRequestBuilder =
    http("Navigate to which tax year GASDS page")
      .get(s"$baseUrl$redirectUrl$taxyear1GASDS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Which tax year are you claiming for?"))

  val enterTaxYear1GASDS: HttpRequestBuilder =
    http("Enter which Tax Year 1 GASDS")
      .post(s"$baseUrl$redirectUrl$taxyear1GASDS")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", TaxYear)
      .check(status.is(303))

  val navigateToAmount1GASDS: HttpRequestBuilder =
    http("Navigate to Amount of Tax Year 1 GASDS page")
      .get(s"$baseUrl$redirectUrl$amount1GASDS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What donation amount are you claiming under GASDS, in pounds?"))

  val enterAmount1GASDS: HttpRequestBuilder =
    http("Enter tax year 1 Amount")
      .post(s"$baseUrl$redirectUrl$amount1GASDS")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("amount", amountForAll)
      .check(status.is(303))

  val navigateToCYATaxYear1GASDS: HttpRequestBuilder =
    http("Navigate to CYA1 of GASDS page")
      .get(s"$baseUrl$redirectUrl$cya1GASDS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Check your claim details for tax year 1"))

  val navigateToClaim1Added: HttpRequestBuilder =
    http("Navigate to claim added 1 of GASDS page")
      .get(s"$baseUrl$redirectUrl$claimaddedGASDS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("You have added a claim for 1 tax year"))

  val selectSecondYearYes: HttpRequestBuilder =
    http("Select claim for tax year 2 as YES")
      .post(s"$baseUrl$redirectUrl$claimaddedGASDS")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToTaxYear2GASDS: HttpRequestBuilder =
    http("Navigate to which tax year 2 GASDS page")
      .get(s"$baseUrl$redirectUrl$taxyear2GASDS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Which tax year are you claiming for?"))

  val enterTaxYear2GASDS: HttpRequestBuilder =
    http("Enter which Tax Year 2 GASDS")
      .post(s"$baseUrl$redirectUrl$taxyear2GASDS")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", previousTaxYear)
      .check(status.is(303))

  val navigateToAmount2GASDS: HttpRequestBuilder =
    http("Navigate to Amount of Tax Year 2 GASDS page")
      .get(s"$baseUrl$redirectUrl$amount2GASDS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What donation amount are you claiming under GASDS, in pounds?"))

  val enterAmount2GASDS: HttpRequestBuilder =
    http("Enter tax year 2 Amount")
      .post(s"$baseUrl$redirectUrl$amount2GASDS")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("amount", amountForAll)
      .check(status.is(303))

  val navigateToCYATaxYear2GASDS: HttpRequestBuilder =
    http("Navigate to CYA2 of GASDS page")
      .get(s"$baseUrl$redirectUrl$cya2GASDS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Check your claim details for tax year 2"))

  val navigateToClaim2Added: HttpRequestBuilder =
    http("Navigate to claim added 2 of GASDS page")
      .get(s"$baseUrl$redirectUrl$claimaddedGASDS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("You have added a claim for 2 tax years"))

  val selectThirdYearYes: HttpRequestBuilder =
    http("Select claim for tax year 3 as YES")
      .post(s"$baseUrl$redirectUrl$claimaddedGASDS")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToTaxYear3GASDS: HttpRequestBuilder =
    http("Navigate to which tax year 3 GASDS page")
      .get(s"$baseUrl$redirectUrl$taxyear3GASDS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Which tax year are you claiming for?"))

  val enterTaxYear3GASDS: HttpRequestBuilder =
    http("Enter which Tax Year 3 GASDS")
      .post(s"$baseUrl$redirectUrl$taxyear3GASDS")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", earliestTaxYear)
      .check(status.is(303))

  val navigateToAmount3GASDS: HttpRequestBuilder =
    http("Navigate to Amount of Tax Year 3 GASDS page")
      .get(s"$baseUrl$redirectUrl$amount3GASDS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What donation amount are you claiming under GASDS, in pounds?"))

  val enterAmount3GASDS: HttpRequestBuilder =
    http("Enter tax year 3 Amount")
      .post(s"$baseUrl$redirectUrl$amount3GASDS")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("amount", amountForAll)
      .check(status.is(303))

  val navigateToCYATaxYear3GASDS: HttpRequestBuilder =
    http("Navigate to CYA3 of GASDS page")
      .get(s"$baseUrl$redirectUrl$cya3GASDS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Check your claim details for tax year 3"))

  val navigateToClaim3Added: HttpRequestBuilder =
    http("Navigate to claim added 3 of GASDS page")
      .get(s"$baseUrl$redirectUrl$claimaddedGASDS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("You have added a claim for 3 tax years"))

  val navigateToFinalCYA: HttpRequestBuilder =
    http("Navigate to Check your GASDS donation details")
      .get(s"$baseUrl$redirectUrl$cyaFinalGASDS")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Check your GASDS donation details"))

  val submitGASDSDetails: HttpRequestBuilder =
    http("Submit GASDS Details")
      .post(s"$baseUrl$redirectUrl$cyaFinalGASDS")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

}
