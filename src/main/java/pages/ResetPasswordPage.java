package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage extends CommonLoggedOutPage{

    private final String RESET_PASSWORD_PAGE_URL = getPageUrl(PageUrlPaths.RESET_PASSWORD_PAGE);

    //Constructor
    public ResetPasswordPage(WebDriver driver) {
        super(driver);
        log.trace("new ResetPasswordPage()");
    }

    public ResetPasswordPage open() {
        return open(true);
    }

    public ResetPasswordPage open(boolean verify) {
        log.debug("Open ResetPasswordPage(" + RESET_PASSWORD_PAGE_URL + ")");
        openUrl(RESET_PASSWORD_PAGE_URL);
        if (verify) {
            verifyResetPasswordPage();
        }
        return this;
    }

    public ResetPasswordPage verifyResetPasswordPage() {
        log.debug("verifyResetPasswordPage()");
        waitForUrlChange(RESET_PASSWORD_PAGE_URL, Time.TIME_SHORTER);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }
}
