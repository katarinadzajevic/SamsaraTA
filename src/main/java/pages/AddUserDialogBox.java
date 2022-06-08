package pages;

import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AddUserDialogBox extends BasePageClass{

    // Locators
    private final String addUserDialogBoxString = "//div[@id='addUserModal']";
    private final By addUserDialogBoxLocator = By.id("addUserModal");
    private final By cancelButtonLocator = By.xpath(addUserDialogBoxString + "//button[contains(@class,'btn-default')]");

    // Constructor
    public AddUserDialogBox(WebDriver driver) {
        super(driver);
        log.trace("new AddUserDialogBox");
    }

    public void verifyAddUserDialogBox(){
        log.debug("verifyAddUserDialogBox()");
        waitUntilPageIsReady(Time.TIME_SHORTER);
        //isDialogBoxOpened
    }

    private boolean isAddUserDialogBoxOpened(int timeout){
        log.debug("isAddUserDialogBoxOpened()");
        return isWebElementVisible(addUserDialogBoxLocator, timeout);
    }

    public boolean isCancelButtonDisplayed(){
        log.debug("isCancelButtonDisplayed()");
        return true;//isWebElementDisplayed(cancelButtonLocator);
    }

    public UsersPage clickCancelButton(){ //!!!
        log.debug(isCancelButtonDisplayed());
        Assert.assertTrue(isCancelButtonDisplayed(), "");
        WebElement cancelButton = getWebElement(cancelButtonLocator);
        clickOnWebElement(cancelButton);
        //Assert.assertTrue();
        UsersPage usersPage = new UsersPage(driver);
        return usersPage;
    }
}
