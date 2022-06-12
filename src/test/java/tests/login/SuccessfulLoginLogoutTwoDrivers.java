package tests.login;

import data.CommonStrings;
import data.Groups;
import data.Time;
import objects.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PracticePage;
import pages.UsersPage;
import pages.WelcomePage;
import tests.BaseTestClass;
import utils.DateTimeUtils;
import utils.PropertiesUtils;
import utils.RestApiUtils;

@Test(groups = {Groups.REGRESSION, Groups.LOGIN})
public class SuccessfulLoginLogoutTwoDrivers extends BaseTestClass {

    private String testName = this.getClass().getName();
    private WebDriver driver1;
    private WebDriver driver2;

    private String adminUserName;
    private String adminPassword;

    @BeforeMethod
    public void setUpTest() {
        log.info("[SETUP TEST] " + testName);
        driver1 = setUpDriver();
        driver2 = setUpDriver();
        adminUserName = PropertiesUtils.getAdminUsername();
        adminPassword = PropertiesUtils.getAdminPassword();
    }

    @Test
    public void testSuccessfulLoginLogout() {

        log.info("[START TEST] " + testName);
        LoginPage loginPage1 = new LoginPage(driver1).open();
        LoginPage loginPage2 = new LoginPage(driver2).open();

        WelcomePage welcomePage1 = loginPage1.login(adminUserName, adminPassword);
        WelcomePage welcomePage2 = loginPage2.login(adminUserName, adminPassword);

        UsersPage usersPage1 = welcomePage1.clickUsersTab();
        PracticePage practicePage2 = welcomePage2.clickPracticeTab();

        Assert.fail("Test failed!");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        log.info("[END TEST] " + testName);
        tearDown(driver1, testResult, 1);
        tearDown(driver2, testResult, 2);
    }
}


