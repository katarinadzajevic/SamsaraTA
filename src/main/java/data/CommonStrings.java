package data;

import org.testng.Assert;
import utils.PropertiesUtils;

import java.util.Properties;

public final class CommonStrings {

    public static final String localeFile = "locale_" + PropertiesUtils.getLocale() + ".loc";
    public static final String localePath = "\\locale\\" + localeFile;

    private static final Properties locale = PropertiesUtils.loadPropertiesFile(localePath);

    private static String getLocaleString(String title){
        String text = locale.getProperty(title);
        Assert.assertNotNull(text, "String " + title + " doesn't exist in file " + localeFile + " !");
        return text;
    }

    public  static String getLoginErrorMessage(){
        return getLocaleString("LOGIN_ERROR_MESSAGE");
    }

    // Login Page
    public  static String getLogoutSuccessMessage(){
        return getLocaleString("LOGOUT_SUCCESS_MESSAGE");
    }
}
