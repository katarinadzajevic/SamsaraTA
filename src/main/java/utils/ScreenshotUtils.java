package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils extends LoggerUtils{

    private static final String screenshotPath = System.getProperty("user.dir") +
            PropertiesUtils.getScreenshotsFolder();


    private static String createScreenshotPath(String screenshotName){
        return screenshotPath + screenshotName + ".png"; //add dateTimeStamp
    }
    public static void takeScreenshot(WebDriver driver, String testName){
        log.trace("takeScreenshot(" + testName + ")");
        String pathToFile = createScreenshotPath(testName);
        if(WebDriverUtils.hasDriverQuit(driver)){
            log.warn("Screenshot for test '" + testName + "' could not be taken, driver instance has quit!");
            return;
        }
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(pathToFile);
        try {
            FileUtils.copyFile(srcFile, destFile);
            log.info("Screenshot for test '" + testName + "' is successfully saved: " + pathToFile);
        } catch (IOException e) {
            log.warn("Screenshot for test '" + testName + "' could not be saved in file '" + pathToFile +
                    "'. Message " + e.getMessage());
        }
    }
}
