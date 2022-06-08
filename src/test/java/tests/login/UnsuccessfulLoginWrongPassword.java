package tests.login;

import data.CommonStrings;
import data.Groups;
import data.Time;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.BaseTestClass;
import utils.DateTimeUtils;
import utils.PropertiesUtils;

@Test(groups = {Groups.REGRESSION, Groups.LOGIN})
public class UnsuccessfulLoginWrongPassword extends BaseTestClass {

    private WebDriver driver;
    private String testName = this.getClass().getName();

    private String adminUserName;
    private String adminPassword;

    @BeforeMethod
    public void setUpTest() {
        log.info("[SETUP TEST] " + testName);
        driver = setUpDriver();
        adminUserName = PropertiesUtils.getAdminUsername();
        adminPassword = PropertiesUtils.getAdminPassword() + "!";
    }

    @Test
    public void testUnsuccessfulLoginLogout() {

        String expectedLoginErrorMessage = CommonStrings.getLoginErrorMessage();

        log.info("[START TEST] " + testName);
        LoginPage loginPage = new LoginPage(driver).open();

        loginPage.typeUsername(adminUserName);
        DateTimeUtils.wait(Time.DEMONSTRATION_WAIT);

        loginPage.typePassword(adminPassword);
        DateTimeUtils.wait(Time.DEMONSTRATION_WAIT);

        loginPage = loginPage.clickLoginButtonNoProgress();

        String errorMessage = loginPage.getErrorMessage();
        DateTimeUtils.wait(Time.DEMONSTRATION_WAIT);

        Assert.assertEquals(errorMessage, expectedLoginErrorMessage, "Wrong Login Error message!");
        DateTimeUtils.wait(Time.TIME_SHORT);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        log.info("[END TEST] " + testName);
        tearDown(driver, testResult);
    }
}
