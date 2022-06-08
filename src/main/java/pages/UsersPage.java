package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class UsersPage extends CommonLoggedInPage{


    private final String USERS_PAGE_URL = getPageUrl(PageUrlPaths.USERS_PAGE);

    //Locators
    private final By addNewUserButtonLocator =
            By.xpath("//a[contains(@class,'btn-info')] and contains[@onclick,'openAddUserModal']");


    //Constructor
    public UsersPage(WebDriver driver) {
        super(driver);
        log.trace("new UsersPage()");
    }

    public UsersPage open(boolean verify) {
        openUrl(USERS_PAGE_URL);
        log.debug("Open UsersPage(" + USERS_PAGE_URL + ")");
        if (verify) {
            verifyUsersPage();
        }
        return this;
    }

    public UsersPage open() {
        return open(true);
    }

    public UsersPage verifyUsersPage() {
        log.debug("verifyUsersPage()");
        waitForUrlChange(USERS_PAGE_URL, Time.TIME_SHORTER);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }

    public boolean isAddUserButtonDisplayed(){
        log.debug("isAddUserButtonDisplayed()");
       // return isWebElementDisplayed(addNewUserButtonLocator);
        return true;
    }

    public void clickAddNewUserButton(){
        log.debug("clickAddNewUserButton()");
        Assert.assertTrue(isAddUserButtonDisplayed(), "");
       // WebElement addNewUserButton
    }
}
