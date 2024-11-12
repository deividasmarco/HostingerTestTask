package com.hostinger.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaseTest {
        private WebDriver driver;
        private static final Logger logger = Logger.getLogger(PurchaseTest.class.getName());

        @BeforeEach
        public void setUp() {
                System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");

                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                logger.log(Level.INFO, "Browser setup complete and maximized.");
        }

        @Test
        public void testPurchaseInitiationWithShippingAndContactDetails() {
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));
                Actions actions = new Actions(driver);

                try {
                        // Step 1: Opening the main page
                        logger.log(Level.INFO, "Opening the main page");
                        driver.get("https://lightgrey-antelope-m7vwozwl8xf7l3y2.builder-preview.com/");
                        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");

                        // Step 2: Adding product to the bag
                        logger.log(Level.INFO, "Navigating to the product page and adding product to the bag");
                        driver.get("https://lightgrey-antelope-m7vwozwl8xf7l3y2.builder-preview.com/freshly-baked-muffins-daily-product1");
                        WebElement addToBagButton = shortWait.until(ExpectedConditions.elementToBeClickable(
                                        By.cssSelector("button[data-qa='productsection-btn-addtobag']")));
                        addToBagButton.click();

                        // Step 3: Checkout
                        logger.log(Level.INFO, "Navigating to checkout");
                        WebElement checkoutButton = shortWait.until(ExpectedConditions.elementToBeClickable(
                                        By.cssSelector("button[data-qa='shoppingcart-btn-checkout']")));
                        checkoutButton.click();

                        // Step 4: Waiting for shipping options to load
                        logger.log(Level.INFO, "Waiting for shipping options to load");
                        shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.cssSelector("div[data-qa='checkout-shippingdetails-option-table']")));

                        // Step 5: Adding shipping address
                        logger.log(Level.INFO, "Entering shipping address");
                        WebElement addressInput = shortWait
                                        .until(ExpectedConditions.elementToBeClickable(By.id("input-13")));
                        addressInput.click();
                        addressInput.sendKeys("Vilniaus Tarandės RIMI paštomatas, Vaivadiškių g. 2");
                        actions.sendKeys(addressInput, Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

                        // Step 6: Selecting Omniva as the shipping method
                        logger.log(Level.INFO, "Selecting Omniva as the shipping method");
                        WebElement shippingOption = shortWait.until(ExpectedConditions.elementToBeClickable(
                                        By.cssSelector("div[data-qa='checkout-shippingdetails-option-omniva']")));
                        actions.moveToElement(shippingOption).click().perform();

                        // Step 7: Proceed to other page in which contact information will be added
                        logger.log(Level.INFO, "Proceeding to contact information page");
                        WebElement continueButton = shortWait.until(ExpectedConditions.elementToBeClickable(
                                        By.cssSelector("button[data-qa='checkout-shippingdetails-continue']")));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                                        continueButton);
                        continueButton.click();

                        // Step 8: Adding contact details, name, phone number, email and additional info
                        logger.log(Level.INFO, "Filling in contact details");
                        WebElement emailInput = shortWait
                                        .until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
                        actions.moveToElement(emailInput).click().sendKeys("deividasmar@gmail.com").perform();

                        WebElement nameInput = shortWait
                                        .until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
                        actions.moveToElement(nameInput).click().sendKeys("Deividas").perform();

                        WebElement phoneInput = shortWait
                                        .until(ExpectedConditions.visibilityOfElementLocated(By.id("phone")));
                        actions.moveToElement(phoneInput).click().sendKeys("+37068609467").perform();

                        WebElement commentInput = shortWait.until(
                                        ExpectedConditions.visibilityOfElementLocated(By.id("customFieldValue")));
                        actions.moveToElement(commentInput).click()
                                        .sendKeys("Could you add more chocolate into muffins, please :)").perform();

                        // Step 9: Making payment
                        logger.log(Level.INFO, "Making payment");
                        WebElement finalContinueButton = shortWait.until(ExpectedConditions.elementToBeClickable(
                                        By.cssSelector("button[data-qa='checkout-contactinformation-continue']")));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                                        finalContinueButton);
                        finalContinueButton.click();

                        // Step 10: Place the order
                        logger.log(Level.INFO, "Placing the order");
                        WebElement placeOrderButton = shortWait.until(ExpectedConditions.elementToBeClickable(
                                        By.cssSelector("button[data-qa='checkout-paymentmethods-placeorder']")));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                                        placeOrderButton);
                        placeOrderButton.click();

                        // Step 11: Final step pressing the 'Got it' button
                        logger.log(Level.INFO, "Clicking 'Got it' button");
                        WebElement gotItButton = longWait.until(ExpectedConditions.elementToBeClickable(
                                        By.cssSelector("button.payment-info__button")));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", gotItButton);
                        gotItButton.click();

                        logger.log(Level.INFO, "Order flow completed successfully. Test completed.");

                } catch (Exception e) {
                        logger.log(Level.SEVERE, "Test failed due to an exception.", e);
                        throw e;
                }
        }

        @AfterEach
        public void tearDown() {
                if (driver != null) {
                        driver.quit();
                        logger.log(Level.INFO, "Browser closed.");
                }
        }
}
