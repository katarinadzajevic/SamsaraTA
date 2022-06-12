package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.LoggerUtils;
import utils.PropertiesUtils;
import utils.WebDriverUtils;

import java.time.Duration;

public abstract class BasePageClass extends LoggerUtils {

    protected WebDriver driver;

    public BasePageClass(WebDriver driver) {
        Assert.assertFalse(WebDriverUtils.hasDriverQuit(driver), "Driver instance has quit");
        this.driver = driver;
    }

    protected String getPageUrl(String path) {
        log.trace("getPageUrl(" + path + ")");
        return PropertiesUtils.getBaseUrl() + path;
    }

    protected String getCurrentURL(){
        log.trace("getCurrentURL()");
        return driver.getCurrentUrl();
    }

    protected String getCurrentUrl() {
        log.trace("getCurrentUrl()");
        return driver.getCurrentUrl();
    }

    protected boolean waitForUrlChange(String url, int timeout) {
        log.trace("waitForUrlChange(" + url + ", " + timeout + "s)");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.urlContains(url));
    }

    protected void openUrl(String url) {
        log.trace("openUrl(" + url + ")");
        driver.get(url);
    }

    protected boolean waitForUrlChangeToExactUrl(String url, int timeout) {
        log.trace("waitForUrlChange(" + url + ", " + timeout + "s)");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.urlToBe(url));
    }

    protected boolean waitUntilPageIsReady(int timeout) {
        log.trace("waitUntilPageIsReady(" + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    protected WebElement getWebElement(By locator) {
        log.trace("getWebElement(" + locator + ")");
        return driver.findElement(locator);
    }

    protected WebElement getWebElement(By locator, int timeout) {
        log.trace("getWebElement(" + locator + ", " + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement getWebElement(By locator, int timeout, int pollingTime) {
        log.trace("getWebElement(" + locator + ", " + timeout + ", " + pollingTime + ")");
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);
        return wait.until(driver1 -> driver1.findElement(locator)
        );
    }

    protected WebElement waitForWebElementToBeClickable(WebElement element, int timeout) {
        log.trace("waitForWebElementToBeClickable(" + element + ", " + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected boolean isWebElementDisplayed(By locator) {
        log.trace("isWebElementDisplayed (" + locator + ")");
        try {
            WebElement webElement = getWebElement(locator);
            return webElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void typeTextToWebElement(WebElement element, String text) {
        log.trace("typeTextToWebElement(" + element + ", " + text + ")");
        element.sendKeys(text);
    }

    protected void clearAndTypeTextToWebElement(WebElement element, String text) {
        log.trace("clearAndTypeTextToWebElement(" + element + ", " + text + ")");
        element.clear();
        element.sendKeys(text);
    }

    protected String getTextFromWebElement(WebElement element){
        log.trace("getTextFromWebElement(" + element + ")");
        return element.getText();
    }

    protected String getAttributeFromWebElement(WebElement element, String attribute) {
        log.trace("getAttributeFromWebElement(" + element + ", " + attribute + ")");
        return element.getAttribute(attribute);
    }

    protected String getValueFromWebElement(WebElement element) {
        log.trace("getAttributeFromWebElement(" + element + ")");
        return getAttributeFromWebElement(element, "value");
    }

    protected String getValueFromWebElementJS(WebElement element) {
        log.trace("getAttributeFromWebElementJS(" + element + ")");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].value", element);
    }

    protected void clickOnWebElement(WebElement element) {
        log.trace("clickOnWebElement(" + element + ")");
        element.click();
    }

    protected void clickOnWebElement(WebElement element, int timeout) {
        log.trace("clickOnWebElement(" + element + ", " + timeout + ")");
        WebElement webElement = waitForWebElementToBeClickable(element, timeout);
        webElement.click();
    }

    protected void clickOnWebElementJS(WebElement element) {
        log.trace("clickOnWebElementJS(" + element + ")");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    protected WebElement waitForWebElementToBeVisible(By locator, int timeout) {
        log.trace("waitForWebElementToBeVisible(" + locator + ", " + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForWebElementToBeVisible(WebElement element, int timeout) {
        log.trace("waitForWebElementToBeVisible(" + element + ", " + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected boolean waitForWebElementToBeInvisible(By locator, int timeout) {
        log.trace("waitForWebElementToBeInvisible(" + locator + ", " + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected boolean waitForWebElementToBeInvisible(WebElement element, int timeout) {
        log.trace("waitForWebElementToBeInvisible(" + element + ", " + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected boolean isWebElementVisible(By locator, int timeout) {
        log.trace("isWebElementVisible(" + locator + ", " + timeout + ")");
        try {
            waitForWebElementToBeVisible(locator, timeout);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isWebElementVisible(WebElement element, int timeout) {
        log.trace("isWebElementVisible(" + element + ", " + timeout + ")");
        try {
            waitForWebElementToBeVisible(element, timeout);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
