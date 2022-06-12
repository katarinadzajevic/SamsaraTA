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

    private void ifFailed(WebDriver driver, ITestResult testResult, int session){
        if (testResult.getStatus() == ITestResult.FAILURE){
            if(PropertiesUtils.getTakeScreenshot()){
                String testName = testResult.getTestClass().getName();
                String screenshotName = testName;
                if (session > 0){
                    screenshotName = screenshotName + "." + session;
                }
                ScreenshotUtils.takeScreenshot(driver, screenshotName);
            }
        }
    }

    protected void tearDown(WebDriver driver, ITestResult testResult, int session){
        String testName = testResult.getTestClass().getName();
        session = Math.abs(session);
        String sessionName = testName;
        if(session > 0){
            sessionName = sessionName + "." + session;
        }
        log.debug("tearDown(" + sessionName + ")");
        try {
            ifFailed(driver, testResult, session);
        } catch (AssertionError | Exception e){
            log.error("Exception occurred in tearDown(" + sessionName + ")! Message: " + e.getMessage());
        } finally {
            quitDriver(driver);
        }
    }

    protected void tearDown(WebDriver driver, ITestResult testResult){
        tearDown(driver, testResult, 0);
    }

    protected void tearDown(WebDriver driver){
        log.debug("tearDown()");
            quitDriver(driver);
    }
}
