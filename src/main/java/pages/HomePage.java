package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.WebDriver;

public class HomePage extends CommonLoggedInPage{

    private final String HOME_PAGE_URL = getPageUrl(PageUrlPaths.HOME_PAGE);

    //Locators


    //Constructor
    public HomePage(WebDriver driver) {
        super(driver);
        log.trace("new HomePage()");
    }

    public HomePage open(boolean verify) {
        openUrl(HOME_PAGE_URL);
        log.debug("Open HomePage(" + HOME_PAGE_URL + ")");
        if (verify) {
            verifyHomePage();
        }
        return this;
    }

    public HomePage open() {
        return open(true);
    }

    public HomePage verifyHomePage() {
        log.debug("verifyHomePage()");
        waitForUrlChange(HOME_PAGE_URL, Time.TIME_SHORTER);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }
}
