package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.WebDriver;

public class HeroesPage extends CommonLoggedInPage {

    private final String HEROES_PAGE_URL = getPageUrl(PageUrlPaths.HEROES_PAGE);

    //Constructor
    public HeroesPage(WebDriver driver) {
        super(driver);
        log.trace("new HeroesPage()");
    }

    public HeroesPage open(boolean verify) {
        openUrl(HEROES_PAGE_URL);
        log.debug("Open HeroesPage(" + HEROES_PAGE_URL + ")");
        if (verify) {
            verifyHeroesPage();
        }
        return this;
    }

    public HeroesPage open() {
        return open(true);
    }

    public HeroesPage verifyHeroesPage() {
        log.debug("verifyHeroesPage()");
        waitForUrlChange(HEROES_PAGE_URL, Time.TIME_SHORTER);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }
}
