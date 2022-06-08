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
import pages.WelcomePage;
import tests.BaseTestClass;
import utils.DateTimeUtils;
import utils.PropertiesUtils;

import javax.jws.soap.SOAPBinding;

@Test(groups = {Groups.REGRESSION, Groups.SANITY, Groups.LOGIN})
public class SuccessfulLoginLogout extends BaseTestClass {

    private WebDriver driver;
    private String testName = this.getClass().getName();

    private String adminUserName;
    private String adminPassword;

    @BeforeMethod
    public void setUpTest() {
        log.info("[SETUP TEST] " + testName);
        driver = setUpDriver();
        adminUserName = PropertiesUtils.getAdminUsername();
        adminPassword = PropertiesUtils.getAdminPassword();
    }

    @Test
    public void demoTest(){
        User user1 = User.createNewUniqueUser("kaca");
        User user2 = User.createNewUniqueUser("drasko");

        log.info(user1);
        log.info(user2);
    }

    @Test
    public void testSuccessfulLoginLogout() {

        String expectedLogoutSuccessMessage = CommonStrings.getLogoutSuccessMessage();

        log.info("[START TEST] " + testName);
        LoginPage loginPage = new LoginPage(driver).open();

        Assert.assertFalse(loginPage.isSuccessMessageDisplayed(),"Success Message should NOT be displayed!");
        Assert.assertFalse(loginPage.isErrorMessageDisplayed(),"Error Message should NOT be displayed!");

        loginPage.typeUsername(adminUserName);
        DateTimeUtils.wait(Time.DEMONSTRATION_WAIT);

        loginPage.typePassword(adminPassword);
        DateTimeUtils.wait(Time.DEMONSTRATION_WAIT);
        WelcomePage welcomePage = loginPage.clickLoginButton();

        DateTimeUtils.wait(Time.DEMONSTRATION_WAIT);
        loginPage = welcomePage.clickLogoutLink();
        DateTimeUtils.wait(Time.DEMONSTRATION_WAIT);

        String successMessage = loginPage.getSuccessMessage();
        Assert.assertEquals(successMessage, expectedLogoutSuccessMessage, "Wrong logout success message!");
        DateTimeUtils.wait(Time.TIME_SHORT);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        log.info("[END TEST] " + testName);
        tearDown(driver, testResult);
    }
}


