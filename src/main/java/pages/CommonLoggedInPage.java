package pages;

import data.PageUrlPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonLoggedInPage extends BasePageClass {

    // Locators
    private final String headerLocatorString = "//header[@id='headContainer']";
    private final By homeTabLocator = By.xpath(headerLocatorString + "//a[@href='" + PageUrlPaths.HOME_PAGE + "']");
    private final By usersTabLocator = By.xpath(headerLocatorString + "//a[@href='" + PageUrlPaths.USERS_PAGE + "']");
    private final By heroesTabLocator = By.xpath(headerLocatorString + "//a[@href='" + PageUrlPaths.HEROES_PAGE + "']");
    private final By apiTabLocator = By.xpath(headerLocatorString + "//a[@href='" + PageUrlPaths.API_PAGE + "']");
    private final By galleryTabLocator = By.xpath(headerLocatorString + "//a[@href='" + PageUrlPaths.GALLERY_PAGE + "']");
    private final By practiceTabLocator = By.xpath(headerLocatorString + "//a[@href='" + PageUrlPaths.PRACTICE_PAGE + "']");
    private final By adminTabLocator = By.xpath(headerLocatorString + "//a[@href='" + PageUrlPaths.ADMIN_PAGE + "']");
    private final By profileLinkLocator = By.xpath("//a[@href='" + PageUrlPaths.PROFILE_PAGE + "']");
    private final By logoutLinkLocator = By.xpath(headerLocatorString + "//a[contains(@href, 'logoutForm.submit')]");

    public CommonLoggedInPage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickHomeTab() {
        log.debug("clickHomeTab()");
        WebElement homeTab = getWebElement(homeTabLocator);
        clickOnWebElement(homeTab);
        HomePage homePage = new HomePage(driver);
        return homePage.verifyHomePage();
    }

    public UsersPage clickUsersTab() {
        log.debug("clickUsersTab()");
        WebElement usersTab = getWebElement(usersTabLocator);
        clickOnWebElement(usersTab);
        UsersPage usersPage = new UsersPage(driver);
        return usersPage.verifyUsersPage();
    }

    public HeroesPage clickHeroesTab() {
        log.debug("clickHeroesTab()");
        WebElement heroesTab = getWebElement(heroesTabLocator);
        clickOnWebElement(heroesTab);
        HeroesPage heroesPage = new HeroesPage(driver);
        return heroesPage.verifyHeroesPage();
    }

    public ApiPage clickApiTab() {
        log.debug("clickApiTab()");
        WebElement apiTab = getWebElement(apiTabLocator);
        clickOnWebElement(apiTab);
        ApiPage apiPage = new ApiPage(driver);
        return apiPage.verifyApiPage();
    }

    public GalleryPage clickGalleryTab() {
        log.debug("clickGalleryTab()");
        WebElement galleryTab = getWebElement(galleryTabLocator);
        clickOnWebElement(galleryTab);
        GalleryPage galleryPage = new GalleryPage(driver);
        return galleryPage.verifyGalleryPage();
    }

    public PracticePage clickPracticeTab() {
        log.debug("clickPracticeTab()");
        WebElement practiceTab = getWebElement(practiceTabLocator);
        clickOnWebElement(practiceTab);
        PracticePage practicePage = new PracticePage(driver);
        return practicePage.verifyPracticePage();
    }

    public AdminPage clickAdminTab() {
        log.debug("clickAdminTab()");
        WebElement adminTab = getWebElement(adminTabLocator);
        clickOnWebElement(adminTab);
        AdminPage adminPage = new AdminPage(driver);
        return adminPage.verifyAdminPage();
    }

    public ProfilePage clickProfileLink() {
        log.debug("clickProfileLink()");
        WebElement profileLink = getWebElement(profileLinkLocator);
        clickOnWebElement(profileLink);
        ProfilePage profilePage = new ProfilePage(driver);
        return profilePage.verifyProfilePage();
    }

    public LoginPage clickLogoutLink() {
        log.debug("clickLogoutLink()");
        WebElement logoutLink = getWebElement(logoutLinkLocator);
        clickOnWebElement(logoutLink);
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.verifyLoginPage();
    }
}
