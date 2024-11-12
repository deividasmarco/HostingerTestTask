# Hostinger Test Task Automation

This repository contains an automated test script for the Hostinger test task, it simulates buying a product, selecting shipping details, entering contact information, and placing an order.

## Prerequisites

- **Java Development Kit (JDK)** - JDK 11 or later.
- **Maven** - For dependencies.
- **Google Chrome** - Ensure Chrome is installed.
- **ChromeDriver** - Ensure ChromeDriver is installed.

## Setup Instructions

1. **Clone this repository**:
   ```bash
   git clone https://github.com/deividasmarco/HostingerTestTask.git
   ```bash
   cd HostingerTestTask
2. **Run the Test**:
Open a terminal in the project directory.
Run the following Maven command to execute the test:
    ```bash
    mvn test

    Test Overview
The test automates the following steps:

Opens the sample e-commerce website.
Adds a product to the cart.
Proceeds to checkout, selects the shipping destination, and enters contact information.
Completes the order and verifies the order confirmation.
Notes
The test script is located in src/test/java/com/hostinger/automation/PurchaseTest.java.
