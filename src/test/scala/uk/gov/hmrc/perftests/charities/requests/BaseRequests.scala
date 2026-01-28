/*
 * Copyright 2023 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.perftests.charities.requests

import io.gatling.core.Predef._
import io.gatling.core.check.CheckBuilder
import io.gatling.core.check.regex.RegexCheckType
import io.gatling.http.Predef._
import io.gatling.http.check.header.HttpHeaderRegexCheckType
import uk.gov.hmrc.performance.conf.ServicesConfiguration


trait BaseRequests extends ServicesConfiguration {


  val baseUrl: String               = baseUrlFor("charities-claims")
  val redirectUrl: String           = "/stamp-duty-land-tax-filing"
  val beforeYouStart: String        = "/preliminary-questions/before-you-start"
  val individualOrBusiness: String  = "/preliminary-questions/who-is-making-the-purchase"
  val purchaserName: String          = "/preliminary-questions/purchaser-name"
  val transactionType: String       = "/preliminary-questions/transaction-type"
  val prelimCYA: String             = "/preliminary-questions/check-answers"
  val address: String               = "/preliminary-questions/address"


  val authLoginStub: String         = baseUrlFor("auth-login-stub")
  val authLoginStubUrl              = s"$authLoginStub/auth-login-stub/gg-sign-in"
  val CsrfPattern                   = """<input type="hidden" name="csrfToken" value="([^"]+)""""
  val lookupPattern                 = """<form method="POST" novalidate action="([^"]+)""""


  def saveCsrfToken(): CheckBuilder[RegexCheckType, String] = regex(_ => CsrfPattern).saveAs("csrfToken")

  private val lookupRegexp = "(.*)/begin"

  def saveAddressLookupUrl: CheckBuilder[HttpHeaderRegexCheckType, Response] =
    headerRegex("Location", lookupRegexp).saveAs("lookupAddressLocation")


}