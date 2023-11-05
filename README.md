# HRM Suite Automation with TestNG
### This project is an automation test suite for OrangeHRM, a human resource management system, using Selenium and TestNG. The suite includes positive and negative test cases for various functionalities.

## Tools and Technology Used
- IntelliJ IDEA
- Selenium Webdriver
- TestNG Framework
- Gradle
- Allure

## Prerequisites
- JDK
- Gradle

## How to run this project
- Clone this repository
- Open Terminal
- Give the following command to run Smoke Test Suite:
  ```bash
  gradle clean test -Pfilesuite="SmokeMasterSuite.xml"
  ```
- Give the following command to run the Regression Test Suite:
  ```bash
  gradle clean test -Pfilesuite="RegressionMasterSuite.xml"
  ```
- To generate Allure Report use these commands:
  ```bash
  allure generate allure-results --clean -output
  allure serve allure-results
  ```

