package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends CommonLoggedOutPage {


    // Page URL Path
    private final String LOGIN_PAGE_URL = getPageUrl(PageUrlPaths.LOGIN_PAGE);

    // Locators
    private final String loginBoxLocator = "//div[@id='loginbox']";
    private final By usernameTextFileLocator = By.id("username");
    private final By passwordTextFileLocator = By.id("password");
    private final By loginButtonLocator =
            By.xpath(loginBoxLocator + "//input[contains(@class, 'btn-primary')]");
    private final By successMessageLocator =
            By.xpath(loginBoxLocator + "//div[contains(@class, 'alert-success')]");
    private final By errorMessageLocator =
            By.xpath(loginBoxLocator + "//div[contains(@class, 'alert-danger')]");

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        log.trace("new LoginPage()");
    }

    public LoginPage open(boolean verify) {
        log.debug("Open LoginPage(" + LOGIN_PAGE_URL + ")");
        openUrl(LOGIN_PAGE_URL);
        if (verify) {
            verifyLoginPage();
        }
        return this;
    }

    public LoginPage open() {
        return open(true);
    }

    public LoginPage verifyLoginPage() {
        log.debug("verifyLoginPage()");
        waitForUrlChange(LOGIN_PAGE_URL, Time.TIME_SHORTER);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }

    public boolean isUsernameTextFieldDisplayed(){
        log.debug("isUsernameTextFieldDisplayed()");
        return isWebElementDisplayed(usernameTextFileLocator);
    }

    public LoginPage typeUsername(String username) {
        log.debug("typeUsername(" + username + ")");
        Assert.assertTrue(isUsernameTextFieldDisplayed(), "Username text field is NOT displayed on Login Page");
        WebElement usernameTextField = getWebElement(usernameTextFileLocator);
        usernameTextField.getText();
        clearAndTypeTextToWebElement(usernameTextField, username);
        return this;
    }

    public String getUsername() {
        log.debug("getUsername()");
        Assert.assertTrue(isUsernameTextFieldDisplayed(), "Username text field is NOT displayed on Login Page");
        WebElement usernameTextField = getWebElement(usernameTextFileLocator);
        return getValueFromWebElement(usernameTextField);
    }

    public boolean isPasswordTextFieldDisplayed(){
        log.debug("isPasswordTextFieldDisplayed()");
        return isWebElementDisplayed(passwordTextFileLocator);
    }

    public LoginPage typePassword(String password) {
        log.debug("typePassword(" + password + ")");
        Assert.assertTrue(isPasswordTextFieldDisplayed(), "Password text field is NOT displayed on Login Page");
        WebElement passwordTextField = getWebElement(passwordTextFileLocator);
        clearAndTypeTextToWebElement(passwordTextField, password);
        return this;
    }

    public String getPassword() {
        log.debug("getPassword()");
        Assert.assertTrue(isPasswordTextFieldDisplayed(), "Password text field is NOT displayed on Login Page");
        WebElement passwordTextField = getWebElement(passwordTextFileLocator);
        return getValueFromWebElement(passwordTextField);
    }

    public boolean isLoginButtonDisplayed(){
        log.debug("isLoginButtonDisplayed()");
        return isWebElementDisplayed(loginButtonLocator);
    }

    private void clickLoginButtonNoVerify() {
        Assert.assertTrue(isLoginButtonDisplayed(), "Login button is NOT displayed on Login Page");
        WebElement loginButton = getWebElement(loginButtonLocator);
        clickOnWebElement(loginButton);
    }

    public WelcomePage clickLoginButton() {
        log.debug("clickLoginButton()");
        clickLoginButtonNoVerify();
        WelcomePage welcomePage = new WelcomePage(driver);
        return welcomePage.verifyWelcomePage();
    }

    public LoginPage clickLoginButtonNoProgress() {
        log.debug("clickLoginButtonNoProgress()");
        clickLoginButtonNoVerify();
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.verifyLoginPage();
    }

    public String getLoginButtonTitle() {
        log.debug("getLoginButtonTitle()");
        Assert.assertTrue(isLoginButtonDisplayed(), "Login button is NOT displayed on Login Page");
        WebElement loginButton = getWebElement(loginButtonLocator);
        return getValueFromWebElement(loginButton);
    }

    public boolean isSuccessMessageDisplayed() {
        log.debug("isSuccessMessageDisplayed()");
        return isWebElementDisplayed(successMessageLocator);
    }

    public String getSuccessMessage() {
        log.debug("getSuccessMessage()");
        Assert.assertTrue(isSuccessMessageDisplayed(), "Success Message is NOT present on login page");
        WebElement successMessage = getWebElement(successMessageLocator);
        return getTextFromWebElement(successMessage);
    }

    public boolean isErrorMessageDisplayed() {
        log.debug("isErrorMessageDisplayed()");
        return isWebElementDisplayed(errorMessageLocator);
    }

    public String getErrorMessage() {
        log.debug("getErrorMessage()");
        Assert.assertTrue(isErrorMessageDisplayed(), "Error Message is NOT present on login page");
        WebElement errorMessage = getWebElement(errorMessageLocator);
        return getTextFromWebElement(errorMessage);
    }

    /**
     * Login to Samsara
     *
     * @param userName {String} - Username
     * @param password {String} - Password
     * @return {WelcomePage}
     */
    public WelcomePage login(String userName, String password) {
        log.info("login(" + userName + ", " + password + ")");
        return typeUsername(userName)
                .typePassword(password)
                .clickLoginButton();
    }
}
