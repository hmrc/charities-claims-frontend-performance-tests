# charities-claims-frontend-performance-tests

Performance test suite for the `DASS REPLATFORM Live Service- CHARITIES CLAIMS `, using [performance-test-runner](https://github.com/hmrc/performance-test-runner) under the hood.

## Pre-requisites

### Services

Start Mongo Docker container following instructions from the [MDTP Handbook](https://docs.tax.service.gov.uk/mdtp-handbook/documentation/developer-set-up/set-up-mongodb.html).

Start `DASS_CHARITIES_ALL` services as follows:

```bash
sm2 --start DASS_CHARITIES_ALL
```

### Logging

The default log level for all HTTP requests is set to `WARN`. Configure [logback.xml](src/test/resources/logback.xml) to update this if required.

### WARNING :warning:

Do **NOT** run a full performance test against staging from your local machine. Please [implement a new performance test job](https://docs.tax.service.gov.uk/mdtp-handbook/documentation/mdtp-test-approach/performance-testing/performance-test-a-microservice/index.html) and execute your job from the dashboard in [Performance Jenkins](https://performance.tools.staging.tax.service.gov.uk).

## Tests

### Main performance Tests summary

'CharitiesFullSubmitTimeoutFriendly' simulation file consists 3 main tests.
journeysToRun.conf setup of journeysToRun[] with 1 E2E test for 'gift aid schedule upload' then 'submitting' (journey-schedules-org).
Second Journey (journey-1-schedule-org) consists of 2 tests with 1) high load/data schedule upload and 2) Claim Submission.
Only 1 of the above should be configured to run in staging due to 20 minutes timeout threshold in Jenkins Performance Pipeline.


Run smoke test (locally) as follows:

```bash
sbt -Dperftest.runSmokeTest=true -DrunLocal=true gatling:test
```

Run full performance test (locally) as follows:

```bash
sbt -DrunLocal=true gatling:test
```

Run smoke test (staging) as follows:

```bash
sbt -Dperftest.runSmokeTest=true -DrunLocal=false gatling:test
```

## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
