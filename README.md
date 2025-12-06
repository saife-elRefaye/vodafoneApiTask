# API Automation Suite

This project is an **API Testing Framework** built using **Java**, **TestNG**, and **Allure Reports** for generating interactive test reports. It is designed to automate API testing with a clean and scalable architecture.

---

## 📂 Project Structure
```
ApiAutomationTask/
│
├── src/
│   ├── main/java/com/api/utilities/
│   │   ├── APIConstants.java        # Holds API constants like base URLs
│   │   ├── ConfigLoader.java        # Loads configuration properties
│   │   └── JsonUtils.java           # Utility for JSON parsing
│   │
│   └── test/java/com/api/tests/
│       ├── AuthTests.java           # Authentication-related test cases
│       └── ProductTests.java        # Product-related test cases
│
├── resources/
│   ├── config.properties            # Global configuration (Base URL, credentials)
│   └── testdata/
│       ├── loginData.json           # Test data for login
│       └── productData.json         # Test data for products
│
├── testng.xml                       # TestNG suite configuration
├── pom.xml                          # Maven dependencies and build configuration
└── allure-results/                  # Allure report results
```

---

## ✅ Features
- **Modular Design**: Utilities for configuration and JSON handling.
- **Data-Driven Testing**: Test data stored in JSON files.
- **Reporting**: Integrated with **Allure** for rich, interactive reports.
- **Scalable**: Easy to add new test cases and endpoints.

---

## ⚙️ Prerequisites
- **Java 11+**
- **Maven 3+**
- **TestNG**
- **Allure Commandline** (for generating reports)

---

## 🚀 How to Run
1. **Install dependencies**
   ```bash
   mvn clean install
   ```

2. **Run tests using TestNG**
   ```bash
   mvn test
   ```

3. **Generate Allure Report**
   ```bash
   allure serve allure-results
   ```

---

## 🧪 Test Execution
- The `testng.xml` file defines the test suite and includes Allure listeners:
  ```xml
  <listeners>
      <listener class-name="io.qameta.allure.testng.AllureTestNg"/>
  </listeners>
  ```

---

## 📊 Reporting
After running tests, generate the report:
```bash
allure serve allure-results
```
This will open an interactive HTML report in your browser.

---

## 💡 Best Practices
- Keep test data separate from test logic.
- Use `ConfigLoader` for environment-specific configurations.
- Add new test classes under `com.api.tests` and update `testng.xml`.
