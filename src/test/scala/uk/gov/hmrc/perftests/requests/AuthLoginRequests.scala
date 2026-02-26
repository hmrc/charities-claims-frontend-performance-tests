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

package uk.gov.hmrc.perftests.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.perftests.charities.requests.{BaseRequests, OrganisationRequests, RepaymentRequests}

object AuthLoginRequests extends ServicesConfiguration with BaseRequests {

  lazy val navigateToAuth: HttpRequestBuilder =
    http("Auth wizard")
      .get(OrganisationRequests.authLoginStubUrl)
      .check(saveCsrfToken())
      .check(status.is(200))
      .check(regex("Authority Wizard").exists)

  def authLogInForOrg: HttpRequestBuilder =
    http("Login as an GG sign-in")
      .post(OrganisationRequests.authLoginStubUrl)
      .formParam("redirectionUrl", redirectUrl)
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("credentialStrength", "strong")
      .formParam("authorityId", "")
      .formParam("confidenceLevel", "50")
      .formParam("affinityGroup", "Organisation")
      .formParam("enrolment[0].name", "HMRC-CHAR-ORG")
      .formParam("enrolment[0].taxIdentifier[0].name", "CHARID")
      .formParam("enrolment[0].taxIdentifier[0].value", "123456")
      .formParam("enrolment[0].state", "Activated")
      .check(status.is(303))
      .check(header("Location").is(redirectUrl))

  def authLogInForAgent: HttpRequestBuilder =
    http("Login as an Org")
      .post(OrganisationRequests.authLoginStubUrl)
      .formParam("redirectionUrl", redirectUrl)
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("credentialStrength", "strong")
      .formParam("credentialRole", "User")
      .formParam("authorityId", "")
      .formParam("confidenceLevel", "50")
      .formParam("affinityGroup", "Organisation")
      .formParam("enrolment[0].name", "HMRC-CHAR-AGENT")
      .formParam("enrolment[0].taxIdentifier[0].name", "AGENTCHARID")
      .formParam("enrolment[0].taxIdentifier[0].value", "123")
      .formParam("enrolment[0].state", "Activated")
      .check(status.is(303))
      .check(header("Location").is(redirectUrl))
      .check(status.saveAs("statusCode"))

  def loginToService(
    name: String,
    baseUrl: String,
    redirectUrl: String,
    expectedLocation: String
  ): HttpRequestBuilder =
    http(name)
      .get(s"$baseUrl$redirectUrl")
      .check(status.is(303))
      .check(header("Location").is(expectedLocation))

}
