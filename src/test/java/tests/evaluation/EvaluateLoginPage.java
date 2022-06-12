package tests.evaluation;

import data.Groups;
import data.Time;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import tests.BaseTestClass;
import utils.DateTimeUtils;

@Test(groups = {Groups.EVALUATION})
public class EvaluateLoginPage extends BaseTestClass {

    private WebDriver driver;
    private String testName = this.getClass().getName();

    @BeforeMethod
    public void setUpTest() {
        log.info("[SETUP TEST] " + testName);
        driver = setUpDriver();
    }

    @Test
    public void testEvaluateLoginPage() {

        log.info("[START TEST] " + testName);
        LoginPage loginPage = new LoginPage(driver).open();
        DateTimeUtils.wait(Time.DEMONSTRATION_WAIT);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.isUsernameTextFieldDisplayed(), "Username text Field is NOT displayed on Login page");
        softAssert.assertTrue(loginPage.isPasswordTextFieldDisplayed(), "Password text Field is NOT displayed on Login page");
        softAssert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login Button is NOT displayed on Login page");
        softAssert.assertTrue(loginPage.isCreateAccountLinkDisplayed(), "Create Account Link is NOT displayed on Login page");
        softAssert.assertTrue(loginPage.isResetPasswordLinkDisplayed(), "Reset Password Link is NOT displayed on Login page");
        softAssert.assertAll("At least one Web Element is not present on Login Page! Locator(s) change?");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        log.info("[END TEST] " + testName);
        tearDown(driver, testResult);
    }
}
