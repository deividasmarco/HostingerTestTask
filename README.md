# Hostinger Test Task Automation

This repository is for automated test that simulates buying a product, selecting shipping details, entering contact information, and placing an order.
This repository is for automated test that simulates buying a product, selecting shipping details, entering contact information, and placing an order.

## Prerequisites

- **Java Development Kit (JDK)** - JDK 11 or later.
- **Maven** - For dependencies.
- **Google Chrome** - Ensure Chrome is installed.
- **ChromeDriver** - Ensure ChromeDriver is installed.

## Setup Instructions

1. **Clone this repository**:
   ```bash
   git clone https://github.com/deividasmarco/HostingerTestTask.git
    ```
   Then change directory to HostingerTestTask
    ```
   Then change directory to HostingerTestTask
   ```bash
   cd HostingerTestTask


2. **Run the Test**:
Open a terminal in the project directory.
Run the Maven command to execute the test:
Run the Maven command to execute the test:
    ```bash
    mvn test

# Test Overview

# Test Overview

The test automates the following steps:
- Opens the website.
- Adds a product to the cart.
- Proceeds to checkout.
- Selects the shipping destination and shipping option.
- Enters contact information.
- Completes the order.
- Verifies that an order confirmation message is displayed.
- Note: The test script is located in src/test/java/com/hostinger/automation/PurchaseTest.java.
- Opens the website.
- Adds a product to the cart.
- Proceeds to checkout.
- Selects the shipping destination and shipping option.
- Enters contact information.
- Completes the order.
- Verifies that an order confirmation message is displayed.
- Note: The test script is located in src/test/java/com/hostinger/automation/PurchaseTest.java.

# Note on Shipping Destination
- The test assumes that "Lithuania" is pre-selected as the default shipping destination.
- The pre-selection behavior can be inconsistent due to session state, cookies, or test environment.
- If "Lithuania" is not pre-selected, this may cause test instability.