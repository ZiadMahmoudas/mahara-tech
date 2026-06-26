<div align="center">

<p>
  <img src="docs/assets/rowad-misr-digital.png" alt="رواد مصر الرقمية" width="170" />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="docs/assets/itc-colleague.png" alt="ITC Colleague" width="170" />
</p>

# MaharaTech Automation Testing Project

### Final Automation Testing Project for MaharaTech Platform
### مشروع اختبار آلي لمنصة مهارة تك ضمن أعمال مبادرة رواد مصر الرقمية و ITC Colleague

<p>
  <img src="https://img.shields.io/badge/Java-17-red?style=for-the-badge&logo=openjdk" alt="Java 17" />
  <img src="https://img.shields.io/badge/Selenium-WebDriver-43B02A?style=for-the-badge&logo=selenium&logoColor=white" alt="Selenium" />
  <img src="https://img.shields.io/badge/TestNG-Testing-orange?style=for-the-badge" alt="TestNG" />
  <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven" />
  <img src="https://img.shields.io/badge/ExtentReports-Report-2D3748?style=for-the-badge" alt="ExtentReports" />
</p>

</div>

---

## 📌 Project Overview

This repository contains an automated testing framework for the **MaharaTech** learning platform.  
The project is built using **Java, Selenium WebDriver, TestNG, Maven, Page Object Model (POM), and ExtentReports** to automate core user scenarios and generate a professional HTML execution report.

يحتوي هذا المستودع على إطار عمل لاختبارات آلية لمنصة **مهارة تك** التعليمية.  
تم بناء المشروع باستخدام **Java و Selenium WebDriver و TestNG و Maven و Page Object Model و ExtentReports** بهدف اختبار أهم السيناريوهات الأساسية وتوليد تقرير احترافي بنتائج التنفيذ.

---

## 🎯 Main Objectives

- Automate the main user journeys on MaharaTech.
- Validate login, registration, and enrolment workflows.
- Apply a clean and maintainable **Page Object Model** structure.
- Generate detailed execution reports using **ExtentReports**.
- Keep test execution easy and scalable with **Maven** and **TestNG**.

---

## 🧪 Covered Test Scenarios

| Module | Covered Scenarios |
|---|---|
| Login | Invalid credentials, empty fields, Google login flow |
| Registration | Valid registration data, required field validation |
| Enrolment | Logged-in user enrolment, guest redirect to login |
| Reporting | Generate HTML execution report after test run |

---

## 🛠️ Tech Stack

| Tool | Purpose |
|---|---|
| **Java 17** | Programming language |
| **Selenium WebDriver** | Browser automation |
| **TestNG** | Test execution and assertions |
| **Maven** | Build and dependency management |
| **ExtentReports** | HTML execution reporting |
| **Page Object Model** | Maintainable test architecture |

---

## 📂 Project Structure

```text
mahara-tech/
├── src/
│   ├── main/java/
│   └── test/java/
│       ├── base/          # WebDriver setup and teardown
│       ├── DATA/          # Test data builders
│       ├── listeners/     # ExtentReports listener
│       ├── Pages/         # Page Object classes
│       └── tests/         # TestNG test classes
├── docs/assets/           # README images and initiative logos
├── test-output/           # Generated reports after execution
├── pom.xml                # Maven dependencies and plugins
├── testng.xml             # TestNG suite configuration
└── README.md