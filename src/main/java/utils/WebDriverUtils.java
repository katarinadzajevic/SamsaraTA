package utils;

import data.Time;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class WebDriverUtils extends LoggerUtils {

    public static WebDriver setUpDriver(){
        WebDriver driver = null;

        String browser = PropertiesUtils.getBrowser();
        boolean remote = PropertiesUtils.getRemote();
        boolean headless = PropertiesUtils.getHeadless();
        String hubUrl = PropertiesUtils.getHubUrl();
        String driversFolder = PropertiesUtils.getDriversFolder();
        String pathDriverChrome = driversFolder + "chromedriver.exe";
        String pathDriverFirefox = driversFolder + "geckodriver.exe";
        String pathDriverEdge = driversFolder + "msedgedriver.exe";

        String sRemote = "";
        if (remote){
            sRemote = "Remote";
        }

        log.debug("setUp" + sRemote + "Driver(" + browser + ", Is Headless: " + headless + ")");

        try {
            switch (browser) {
                case "chrome": {
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(headless);
                    options.addArguments("--windows-size=1600x900");
                    if (remote) {
                        RemoteWebDriver remoteDriver = new RemoteWebDriver(new URL(hubUrl), options);
                        remoteDriver.setFileDetector(new LocalFileDetector());  //upload file
                        driver = remoteDriver;
                    } else {
                        System.setProperty("webdriver.chrome.driver", pathDriverChrome);
                        driver = new ChromeDriver(options);
                    }
                    break;
                }
                case "firefox":{
                    FirefoxOptions options  = new FirefoxOptions();
                    options.setHeadless(headless);
                    options.addArguments("--windows-size=1600x900");
                    if(remote){
                        RemoteWebDriver remoteDriver = new RemoteWebDriver(new URL(hubUrl), options);
                        remoteDriver.setFileDetector(new LocalFileDetector());
                        driver = remoteDriver;
                    } else {
                        System.setProperty("webdriver.gecko.driver", pathDriverFirefox);
                        driver = new FirefoxDriver(options);
                    }
                    break;
                }
                case "edge":{
                    EdgeOptions options = new EdgeOptions();
                    options.setHeadless(headless);
                    options.addArguments("--windows-size=1600x900");
                    if(remote){
                        RemoteWebDriver remoteDriver = new RemoteWebDriver(new URL(hubUrl), options);
                        remoteDriver.setFileDetector(new LocalFileDetector());
                        driver = remoteDriver;
                    } else {
                        System.setProperty("webdriver.edge.driver", pathDriverEdge);
                        driver = new EdgeDriver(options);
                    }
                    break;
                }
                default:{
                    Assert.fail("Cannot create driver! Browser '" + browser + "' is not recognized");
                }
            }
        }catch (MalformedURLException e){
            Assert.fail("Cannot create driver! Path to browser '" + browser + "' driver is not correct!");
        }

        // Default TimeOuts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Time.IMPLICIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Time.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Time.ASYNC_SCRIPT_TIMEOUT));

        // Maximise Browser
        driver.manage().window().maximize();

        return driver;
    }

    public static SessionId getSessionId(WebDriver driver){
        return ((RemoteWebDriver) driver).getSessionId();
    }

    public static boolean hasDriverQuit(WebDriver driver){
        if(driver != null){
           return getSessionId(driver) == null;
        } else {
            return true;
        }
    }

    public static void quitDriver(WebDriver driver){
        if(!hasDriverQuit(driver)){
            driver.quit();
        }
    }
}
