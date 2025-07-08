# API Login and Profile Balance Verification Tests

This project automates API testing of user login and profile account balance verification using **Cucumber**, **RestAssured**, and **AssertJ**.

---

## Overview

- Users login with credentials
- Fetch profile account entries
- Verify the `openingBalance` matches expected value
- Support multiple users with parameterized examples

---

## Prerequisites

- Java 11+
- Maven 3.6+
- Internet connection (for dependencies)

---

## Setup

Clone repo and build dependencies:

```bash
git clone <repo-url>
cd <project-dir>
mvn clean install
```

## Run Tests

To execute the automated API tests, use Maven from the project root directory:

```bash
mvn test
```

**This command will:**

* Compile the project

* Run all Cucumber scenarios defined in the features/ folder


**Running Specific Scenarios or Tags**
**You can also run tests selectively by specifying Cucumber tags or feature files:**
```bash
mvn test -Dcucumber.options="--tags @smoke"
mvn test -Dcucumber.options="classpath:features/login.feature"
```
