# Stock Market Tool

> This is a web-app for Stock Market. It allows to buy and sell some shares on the (fake) stock market.

___

- [Architecture](#architecture)
- [Stack](#stack)
- [Run Locally](#run-locally)
    - [Pre-requisites](#pre-requisites)
    - [Start the app Locally](#start-the-app-locally)
    - [Run the tests](#run-the-tests)
- [AWS Deployment](#aws-deployment)
- [Limitations](#limitations)
___


## Architecture

**Front-end**

Angular UI exposing 3 main panels:
- Dashboard  (Portfolio + Trade history)
- Buy / Sell Shares
- Top up / Withdraw Cash

**Back-end**

Springboot REST API exposing the main features:
- Get portfolio details
- Buy stock
- Sell stock
- Get stock details


## Stack

**Back-end**

- Java
- SpringBoot
- Swagger
- Gradle
- JUnit + Mockito for unit testing

**Front-end**

- Angular CLI
- Angular 7
- Node
- Typescript
- Bootstrap
- Karma + Jasmine for unit testing
- Protractor + Jasmine for integration testing


## Run Locally

### Pre-requisites

To build and run the app locally, you need to have installed:
- Java 8+
- Gradle
- Node 8+
- Npm 6+
- Angular CLI

### Start the app Locally

**Back-end**

To start the app (using mocked data):
```bash
$ SPRING_PROFILES_ACTIVE=local ./gradlew bootRun
```

To start the app (using alphavantage data):
```bash
$ SPRING_PROFILES_ACTIVE=dev ./gradlew bootRun
```

Then access the API at: [http://localhost:8080/api/swagger-ui.html](http://localhost:8080/api/swagger-ui.html)


**Front-end**

To start the client:
```bash
$ ng serve
```

Then access the app at: [http://localhost:4200/](http://localhost:4200/)

### Run the tests

**Back-end**

```bash
$ ./gradlew test
```

**Front-end**

```bash
$ ng test
```


## AWS Deployment

**Back-end**

Package the app:
```bash
$ ./gradlew bootWar
```

Deploy on AWS using `Elastic Beanstalk`. 

Just upload the `.war` file present in `/build/libs/` folder.

**Front-end**

Package the app:
```bash
$ ng build --prod
```

Deploy on AWS using `S3 static website`.

Just upload the `/client-app/dist/client-app/` folder content.


## Limitations

No validation is done on the front-end, instead all validations are happening in the back-end.

The UI, even responsive, is not fully user-friendly: no auto completion on symbol typed in...

The Quote received from AlphaVantage is limited to only 3 stocks and returns only the day value.

The tests are limited and only present to showcase how testing could be done. They only cover basic scenarios.