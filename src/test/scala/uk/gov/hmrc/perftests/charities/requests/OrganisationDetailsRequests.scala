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

object OrganisationDetailsRequests extends ServicesConfiguration with BaseRequests {

  val navigateToAboutTheOrg: HttpRequestBuilder =
    http("Navigate to About the Organisation page")
      .get(s"$baseUrl$redirectUrl$aboutTheOrg")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("About the organisation"))

  val navigateToCharityRegulator: HttpRequestBuilder =
    http("Navigate to name of charity regulator Page")
      .get(s"$baseUrl$redirectUrl$nameOfCharityRegulator")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What is the name of the charity regulator?"))

  val selectNotRegistered: HttpRequestBuilder =
    http("Select charity is not registered with a regulator")
      .post(s"$baseUrl$redirectUrl$nameOfCharityRegulator")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "None")
      .check(status.is(303))

  val selectScottishRegistered: HttpRequestBuilder =
    http("Select charity scottish as regulator name")
      .post(s"$baseUrl$redirectUrl$nameOfCharityRegulator")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "Scottish")
      .check(status.is(303))

  val selectEnglandWalesRegistered: HttpRequestBuilder =
    http("Select charity England and Wales as regulator name")
      .post(s"$baseUrl$redirectUrl$nameOfCharityRegulator")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "EnglandAndWales")
      .check(status.is(303))

  val navigateToWhyNotRegistered: HttpRequestBuilder =
    http("Navigate to Why charity not registered to a regulator Page")
      .get(s"$baseUrl$redirectUrl$charityNotRegistered")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Why is the charity not registered with a regulator?"))

  val selectLowIncome: HttpRequestBuilder =
    http("Select charity LowIncome")
      .post(s"$baseUrl$redirectUrl$charityNotRegistered")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "LowIncome")
      .check(status.is(303))

  val selectCharityExcepted: HttpRequestBuilder =
    http("Select charity Your Charity is Excepted")
      .post(s"$baseUrl$redirectUrl$charityNotRegistered")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "Excepted")
      .check(status.is(303))

  val selectCharityExempt: HttpRequestBuilder =
    http("Select charity Your Charity is exempt")
      .post(s"$baseUrl$redirectUrl$charityNotRegistered")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "Exempt")
      .check(status.is(303))

  val navigateToCharityExcepted: HttpRequestBuilder =
    http("Navigate to Your Charity is Excepted Page")
      .get(s"$baseUrl$redirectUrl$charityExcepted")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Your charity is excepted"))

  val navigateToCharityExceptedAgent: HttpRequestBuilder =
    http("Navigate to Charity is Excepted Page")
      .get(s"$baseUrl$redirectUrl$charityExcepted")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("The charity is excepted"))

  val navigateToCharityExempt: HttpRequestBuilder =
    http("Navigate to Your Charity is Excepted Page")
      .get(s"$baseUrl$redirectUrl$charityExempt")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Your charity is exempt"))

  val navigateToRegulatorNumber: HttpRequestBuilder =
    http("Navigate to Enter your Charity Regulator Number Page")
      .get(s"$baseUrl$redirectUrl$charityRegulatorNumber")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What is the charity regulator number?"))

  val enterRegulatorNumber: HttpRequestBuilder =
    http("Enter your regulator number page")
      .post(s"$baseUrl$redirectUrl$charityRegulatorNumber")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "44334433665577883344")
      .check(status.is(303))

  val navigateToCorporateTrustee: HttpRequestBuilder =
    http("Navigate Corporate Trustee making this claim Page")
      .get(s"$baseUrl$redirectUrl$corporateTrustee")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Is a corporate trustee making this claim?"))

  val selectCorporateTrusteeYes: HttpRequestBuilder =
    http("Select Corporate Trustee as YES")
      .post(s"$baseUrl$redirectUrl$corporateTrustee")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val selectCorporateTrusteeNo: HttpRequestBuilder =
    http("Select Corporate Trustee as NO")
      .post(s"$baseUrl$redirectUrl$corporateTrustee")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "false")
      .check(status.is(303))

  val navigateToCorporateTrusteeUKAddress: HttpRequestBuilder =
    http("Navigate Corporate Trustee UK Address Page")
      .get(s"$baseUrl$redirectUrl$corporateTrusteeAddress")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Does the corporate trustee have a UK address?"))

  val selectCorporateTrusteeUKAddressNo: HttpRequestBuilder =
    http("Select Corporate Trustee UK Address as No")
      .post(s"$baseUrl$redirectUrl$corporateTrusteeAddress")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "false")
      .check(status.is(303))

  val navigateToCorporateTrusteeDetails: HttpRequestBuilder =
    http("Navigate Corporate Trustee Details Page")
      .get(s"$baseUrl$redirectUrl$corporateTrusteeDetails")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What are the corporate trustee details?"))

  val enterCorporateTrusteeDetails: HttpRequestBuilder =
    http("Enter Corporate trustee Details")
      .post(s"$baseUrl$redirectUrl$corporateTrusteeDetails")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam(
        "nameOfCorporateTrustee",
        "Service information  region    visited  link    Charities Claims151532  Language switcher  navigation landmark    list  with 2 items  current  ENG  visited  lin"
      )
      .formParam("corporateTrusteeDaytimeTelephoneNumber", "456891234567891234567891239122")
      .check(status.is(303))

  val navigateToAuthorisedOfficialUKAddress: HttpRequestBuilder =
    http("Navigate authorised Official UK Address Page")
      .get(s"$baseUrl$redirectUrl$authorisedOfficialAddress")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Does the authorised official have a UK address?"))

  val selectAuthorisedOfficialUKAddressYes: HttpRequestBuilder =
    http("Select authorised Official UK Address as Yes")
      .post(s"$baseUrl$redirectUrl$authorisedOfficialAddress")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToAuthorisedOfficialDetails: HttpRequestBuilder =
    http("Navigate Authorised Official Details Page")
      .get(s"$baseUrl$redirectUrl$authorisedOfficialDetails")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What are the authorised official’s details?"))

  val enterAuthorisedOfficialDetails: HttpRequestBuilder =
    http("Enter Authorised Official Details")
      .post(s"$baseUrl$redirectUrl$authorisedOfficialDetails")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("title", "Miss")
      .formParam("firstName", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
      .formParam("lastName", "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb")
      .formParam("phoneNumber", "456891234563456789123456789122")
      .formParam("postcode", "AA1 1AA")
      .check(status.is(303))

  val navigateToSendPaymentToAgent: HttpRequestBuilder =
    http("Navigate to 'Who should HMRC send payment to' Page")
      .get(s"$baseUrl$redirectUrl$sendPaymentToAgent")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Who should HMRC send payment to?"))

  val selectAgentNomineeAgent: HttpRequestBuilder =
    http("Select Agent/Nominee")
      .post(s"$baseUrl$redirectUrl$sendPaymentToAgent")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "taxAgent")
      .check(status.is(303))

  val selectCASCAgent: HttpRequestBuilder =
    http("Select Charity/CASC")
      .post(s"$baseUrl$redirectUrl$sendPaymentToAgent")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "business")
      .check(status.is(303))

  val navigateToTelephoneAgent: HttpRequestBuilder =
    http("Navigate to 'agent's telephone number' Page")
      .get(s"$baseUrl$redirectUrl$telephoneAgent")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What is your telephone number?"))

  val enterTelephoneAgent: HttpRequestBuilder =
    http("Enter telephone number of agent")
      .post(s"$baseUrl$redirectUrl$telephoneAgent")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "456891234567891234567891239122")
      .check(status.is(303))

  val navigateToUKAddressAgent: HttpRequestBuilder =
    http("Navigate to 'Do you have a UK Address' agents Page")
      .get(s"$baseUrl$redirectUrl$UKAddressAgent")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Do you have a UK address?"))

  val selectUKAddressYesAgent: HttpRequestBuilder =
    http("Select YES for Agent's UK Address")
      .post(s"$baseUrl$redirectUrl$UKAddressAgent")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val navigateToPostcodeAgent: HttpRequestBuilder =
    http("Navigate to enter Agent's Postcode Page")
      .get(s"$baseUrl$redirectUrl$postcodeAgent")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What is your postcode?"))

  val enterPostcodeAgent: HttpRequestBuilder =
    http("Enter Agent's Postcode")
      .post(s"$baseUrl$redirectUrl$postcodeAgent")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "AA1 1AA")
      .check(status.is(303))

  val navigateToCheckYourOrganisationDetails: HttpRequestBuilder =
    http("Navigate Check your organisation details Page")
      .get(s"$baseUrl$redirectUrl$organisationCYA")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Check your organisation details"))

  val navigateToCheckYourOrganisationDetailsAgent: HttpRequestBuilder =
    http("Navigate Check your organisation details Page")
      .get(s"$baseUrl$redirectUrl$organisationCYA")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Check the organisation details"))

  val submitOrganisationDetails: HttpRequestBuilder =
    http("Submit Organisation Details")
      .post(s"$baseUrl$redirectUrl$organisationCYA")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

}
