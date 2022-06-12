package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HomePage extends CommonLoggedInPage{

    private final String HOME_PAGE_URL = getPageUrl(PageUrlPaths.HOME_PAGE);

    //Locators
    private final By pageTitleLocator = By.xpath("//div[@class='my-jumbotron']/h1)");

    //Constructor
    public HomePage(WebDriver driver) {
        super(driver);
        log.trace("new HomePage()");
    }

    public HomePage open() {
        return open(true);
    }

    public HomePage open(boolean verify) {
        log.debug("Open HomePage(" + HOME_PAGE_URL + ")");
        openUrl(HOME_PAGE_URL);
        if (verify) {
            verifyHomePage();
        }
        return this;
    }

    public HomePage verifyHomePage() {
        log.debug("verifyHomePage()");
        waitForUrlChange(HOME_PAGE_URL, Time.TIME_SHORTER);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }

    public boolean isPageTitleDisplayed(){
        log.debug("isPageTitleDisplayed()");
        return isWebElementDisplayed(pageTitleLocator);
    }

    public String getPageTitle(){
        log.debug("getPageTitle()");
        Assert.assertTrue(isPageTitleDisplayed(), "Page Title is NOT displayed on Navigation Bar!");
        WebElement pageTitle = getWebElement(pageTitleLocator);
        return getTextFromWebElement(pageTitle);
    }
}
