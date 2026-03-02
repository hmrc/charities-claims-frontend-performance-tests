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
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

import scala.concurrent.duration.DurationInt

object GASScheduleDebugRequests extends ServicesConfiguration with BaseRequests {


  val navigateToGASUploaded: HttpRequestBuilder =
    http("Navigate to your Gift Aid Schedule upload page")
      .get(s"$baseUrl$redirectUrl$uploadedGASPage")
      .check(status.is(200))
      .check(saveCsrfToken())


  val resetUploadStatus: ActionBuilder =
    exec { s => s.remove("uploadStatus") }.actionBuilders.head

  val pollUntilUploaded: List[ActionBuilder] =
    asLongAsDuring(
      session =>
        session("uploadStatus").asOption[String] match {
          case Some(status) => !status.equalsIgnoreCase("UPLOADED")
          case None         => true
        },
      5.minutes,
      exitASAP = true
    )(
      pause(5.second)
        .exec(
          http("Get the file verification/validation status on your Gift Aid Schedule Upload Page")
            .get(s"$baseUrl$redirectUrl$uploadedGASPage")
            .check(status.is(200))
            .check(regex("""<strong[^>]*>\s*([\s\S]*?)\s*</strong>""").saveAs("uploadStatusRaw"))
            .check(saveCsrfToken())
            .check(regex("Your Gift Aid schedule upload"))
        )
        .exec { session =>
          val cleaned = session("uploadStatusRaw").asOption[String].map(_.trim).getOrElse("")
          session
            .remove("uploadStatusRaw")
            .set("uploadStatus", cleaned)
        }
    ).actionBuilders


  val getFileVerificationStatus: List[ActionBuilder] =
    resetUploadStatus :: pollUntilUploaded


  val continueFromUploadedPage: HttpRequestBuilder =
    http("CONTINUE from your GIFT AID Upload Page")
      .post(s"$baseUrl$redirectUrl$uploadedGASPage")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))
      .check(header("Location").saveAs("nextPageURL"))



  // sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss

  val finalPostReq: ActionBuilder =
    http("Final POST")
      .post(s"$baseUrl$redirectUrl$uploadedGASPage")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))
      .check(header("Location").saveAs("nextPageURL"))

  // 2) SessionHook ActionBuilder (created via exec(function) then extracted)
  val finalPostDebugHook: ActionBuilder =
    exec { s =>
      println("nextpage URL = " + s("nextPageURL").asOption[String])
      s
    }.actionBuilders.head   // this head is the underlying SessionHookBuilder (an ActionBuilder)

  // 3) Your required list
  val actionspost: List[ActionBuilder] = List(finalPostReq, finalPostDebugHook)

// sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss

  val finalGetReq: ActionBuilder =
    http("Final GET")
      .get(s"$baseUrl" + "#{nextPageURL}")
      .check(status.saveAs("st"))
      .check(currentLocation.saveAs("landedUrlget"))

  // 2) SessionHook ActionBuilder (created via exec(function) then extracted)
  val finalGetDebugHook: ActionBuilder =
    exec { s =>
      println(s"""FinalGET status=${s("st").asOption[Int]} location=${s("landedUrlget").asOption[String]}""")
      s
    }.actionBuilders.head   // this head is the underlying SessionHookBuilder (an ActionBuilder)

  // 3) Your required list
  val actions: List[ActionBuilder] = List(finalGetReq, finalGetDebugHook)


  // sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss

  val flushHttpCacheAB: ActionBuilder =
    exec(flushHttpCache).actionBuilders.head

  val flushSessionCookiesAB: ActionBuilder =
    exec(flushSessionCookies).actionBuilders.head

  val flushCookieJarAB: ActionBuilder =
    exec(flushCookieJar).actionBuilders.head

  // What you asked for: a method that returns List[ActionBuilder]
  val flushAll: List[ActionBuilder] =
    List(flushHttpCacheAB, flushSessionCookiesAB, flushCookieJarAB)



}
