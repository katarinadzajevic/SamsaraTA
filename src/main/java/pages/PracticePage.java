package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.WebDriver;

public class PracticePage extends CommonLoggedInPage{

    private final String PRACTICE_PAGE_URL = getPageUrl(PageUrlPaths.PRACTICE_PAGE);

    //Constructor
    public PracticePage(WebDriver driver) {
        super(driver);
        log.trace("new PracticePage()");
    }

    public PracticePage open() {
        return open(true);
    }

    public PracticePage open(boolean verify) {
        log.debug("Open PracticePage(" + PRACTICE_PAGE_URL + ")");
        openUrl(PRACTICE_PAGE_URL);
        if (verify) {
            verifyPracticePage();
        }
        return this;
    }

    public PracticePage verifyPracticePage() {
        log.debug("verifyPracticePage()");
        waitForUrlChange(PRACTICE_PAGE_URL, Time.TIME_SHORTER);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }
}
