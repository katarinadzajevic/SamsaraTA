package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.WebDriver;

public class WelcomePage extends CommonLoggedInPage{

    private final String WELCOME_PAGE_URL = getPageUrl(PageUrlPaths.WELCOME_PAGE);

    public WelcomePage(WebDriver driver) {
        super(driver);
        log.trace("new WelcomePage()");
    }

    public WelcomePage open(boolean verify){
        openUrl(WELCOME_PAGE_URL);
        log.debug("Open WelcomePage(" + WELCOME_PAGE_URL + ")");
        if (verify){
            verifyWelcomePage();
        }
        return this;
    }

    public WelcomePage open(){
        return open(true);
    }

    public WelcomePage verifyWelcomePage(){
        log.debug("verifyWelcomePage()");
        waitForUrlChangeToExactUrl(WELCOME_PAGE_URL, Time.TIME_SHORTER);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }
}
