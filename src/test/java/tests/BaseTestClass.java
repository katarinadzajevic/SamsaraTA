package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import utils.LoggerUtils;
import utils.PropertiesUtils;
import utils.ScreenshotUtils;
import utils.WebDriverUtils;

public abstract class BaseTestClass extends LoggerUtils {

    protected WebDriver setUpDriver(){
        log.debug("setUpDriver()");
        return WebDriverUtils.setUpDriver();
    }

    protected void quitDriver(WebDriver driver){
        log.debug("quitDriver()");
        WebDriverUtils.quitDriver(driver);
    }

    private void ifFailed(WebDriver driver, ITestResult testResult){
        if (testResult.getStatus() == ITestResult.FAILURE){
            if(PropertiesUtils.getTakeScreenshot()){
                String testName = testResult.getTestClass().getName();
                ScreenshotUtils.takeScreenshot(driver, testName);
            }
        }
    }

    protected void tearDown(WebDriver driver, ITestResult testResult){
        String testName = testResult.getTestClass().getName();
        log.debug("tearDown(" + testName + ")");
        try {
            ifFailed(driver, testResult);
        } catch (AssertionError | Exception e){
            log.error("Exception occurred in tearDown(" + testName + ")! Message: " + e.getMessage());
        } finally {
            quitDriver(driver);
        }
    }
}
