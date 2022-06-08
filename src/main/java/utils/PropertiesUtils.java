package utils;

import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils extends LoggerUtils{

    private static final String propertiesPath = "common.properties";
    private static final Properties properties = loadPropertiesFile();

    public static Properties loadPropertiesFile(String filePath) {

        InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            Assert.fail("cannot load " + filePath + " file! Message: " + e.getMessage());
        }
        return properties;
    }

    private static Properties loadPropertiesFile(){
        return loadPropertiesFile(propertiesPath);
    }

    private static String getProperty(String propertyName){
        log.trace("getProperty(" + propertyName + ")");
        String result = properties.getProperty(propertyName);
        Assert.assertNotNull(result, "Cannot find property '" + propertyName + "' in " + propertiesPath + "file!");
        return result;
    }

    public static String getEnvironment(){
        return getProperty("environment");
    }

    public static String getLocalBaseUrl(){
        return getProperty("localBaseUrl");
    }

    public static String getTestBaseUrl(){
        return getProperty("testBaseUrl");
    }

    public static String getProdBaseUrl(){
        return getProperty("prodBaseUrl");
    }

    public static String getBaseUrl(){
        String environment = getEnvironment().toLowerCase();
        String baseUrl = null;
        switch (environment){
            case "local": {
                baseUrl = getLocalBaseUrl();
                break;
            }
            case "test": {
                baseUrl = getTestBaseUrl();
                break;
            }
            case "prod": {
                baseUrl = getProdBaseUrl();
                break;
            }
            default: {
                Assert.fail("Cannot get Base URL! Enviroment '" + environment + "' is not recognized");
            }
        }
    return baseUrl;
    }

    public static String getBrowser(){
        return getProperty("browser");
    }

    public static boolean getRemote(){
        return Boolean.parseBoolean(getProperty("remote"));
    }

    public static boolean getHeadless(){
        return Boolean.parseBoolean(getProperty("headless"));
    }

    public static String getHubUrl(){
        return getProperty("hubUrl");
    }

    public static String getLocale(){
        return getProperty("locale");
    }

    public static boolean getTakeScreenshot(){
        return Boolean.parseBoolean(getProperty("takeScreenshot"));
    }

    public static String getAdminUsername(){
        return getProperty("adminUsername");
    }

    public static String getAdminPassword(){
        return getProperty("adminPassword");
    }

    public static String getDefaultPassword(){
        return getProperty("defaultPassword");
    }

    public static String getRootUsername(){
        return getProperty("rootUsername");
    }

    public static String getRootPassword(){
        return getProperty("rootPassword");
    }

    public static String getDriversFolder(){
        return getProperty("driversFolders");
    }

    public static String getScreenshotsFolder(){
        return getProperty("screenshotsFolder");
    }
}
