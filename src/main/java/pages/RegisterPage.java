package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends CommonLoggedOutPage{

    private final String REGISTER_PAGE_URL = getPageUrl(PageUrlPaths.REGISTER_PAGE);

    //Constructor
    public RegisterPage(WebDriver driver) {
        super(driver);
        log.trace("new RegisterPage()");
    }

    public RegisterPage open() {
        return open(true);
    }

    public RegisterPage open(boolean verify) {
        log.debug("Open RegisterPage(" + REGISTER_PAGE_URL + ")");
        openUrl(REGISTER_PAGE_URL);
        if (verify) {
            verifyRegisterPage();
        }
        return this;
    }

    public RegisterPage verifyRegisterPage() {
        log.debug("verifyRegisterPage()");
        waitForUrlChange(REGISTER_PAGE_URL, Time.TIME_SHORTER);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }
}
