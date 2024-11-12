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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseTest {
        private WebDriver driver;

        @BeforeEach
        public void setUp() {
                System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*");

                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
        }

        @Test
        public void testPurchaseInitiationWithShippingAndContactDetails() {
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

                WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));

                Actions actions = new Actions(driver);

                try {
                        // Step 1: Opening the main page
                        driver.get("https://lightgrey-antelope-m7vwozwl8xf7l3y2.builder-preview.com/");
                        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");

                        // Step 2: Going to product page and adding product to the bag
                        driver.get("https://lightgrey-antelope-m7vwozwl8xf7l3y2.builder-preview.com/freshly-baked-muffins-daily-product1");
                        WebElement addToBagButton = shortWait.until(ExpectedConditions.elementToBeClickable(
                                        By.cssSelector("button[data-qa='productsection-btn-addtobag']")));
                        addToBagButton.click();

                        // Step 3: Going to checkout
                        WebElement checkoutButton = shortWait.until(ExpectedConditions.elementToBeClickable(
                                        By.cssSelector("button[data-qa='shoppingcart-btn-checkout']")));
                        checkoutButton.click();

                        // Step 4: Waitting for Shipping Options
                        shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.cssSelector("div[data-qa='checkout-shippingdetails-option-table']")));

                        // Step 5: Adding shipping address
                        WebElement addressInput = shortWait
                                        .until(ExpectedConditions.elementToBeClickable(By.id("input-13")));
                        addressInput.click();
                        addressInput.sendKeys("Vilniaus Tarandės RIMI paštomatas, Vaivadiškių g. 2");
                        actions.sendKeys(addressInput, Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

                        // Step 6: Selecting shipping method (Omniva)
                        WebElement shippingOption = shortWait.until(ExpectedConditions.elementToBeClickable(
                                        By.cssSelector("div[data-qa='checkout-shippingdetails-option-omniva']")));
                        actions.moveToElement(shippingOption).click().perform();

                        // Step 7: Next page opened which is contact information page
                        WebElement continueButton = shortWait.until(ExpectedConditions.elementToBeClickable(
                                        By.cssSelector("button[data-qa='checkout-shippingdetails-continue']")));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                                        continueButton);
                        continueButton.click();

                        // Step 8: Adding contact, name, phone number and additional information about
                        // the order
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

                        WebElement finalContinueButton = shortWait.until(ExpectedConditions.elementToBeClickable(
                                        By.cssSelector("button[data-qa='checkout-contactinformation-continue']")));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                                        finalContinueButton);
                        finalContinueButton.click();

                        WebElement placeOrderButton = shortWait.until(ExpectedConditions.elementToBeClickable(
                                        By.cssSelector("button[data-qa='checkout-paymentmethods-placeorder']")));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                                        placeOrderButton);
                        placeOrderButton.click();

                        WebElement gotItButton = longWait.until(ExpectedConditions
                                        .elementToBeClickable(By.cssSelector("button.payment-info__button")));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", gotItButton);
                        gotItButton.click();

                        // Step 12: Verifying that the message about order is there and closing the
                        // browser
                        WebElement confirmationMessage = longWait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.xpath("//*[contains(text(),'Thank you for your order')]")));
                        assertTrue(confirmationMessage.isDisplayed(),
                                        "Order confirmation message should be displayed.");

                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        if (driver != null) {
                                driver.quit();
                        }
                }
        }

        @AfterEach
        public void tearDown() {
                if (driver != null) {
                        driver.quit();
                }
        }
}
