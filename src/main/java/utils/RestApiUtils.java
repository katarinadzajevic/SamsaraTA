package utils;

import com.google.gson.Gson;
import data.ApiCalls;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import objects.User;
import org.testng.Assert;

import java.util.Locale;

public class RestApiUtils extends LoggerUtils {

    private static final String BASE_URL = PropertiesUtils.getBaseUrl();
    private static final String adminUser = PropertiesUtils.getAdminUsername();
    private static final String adminPassword = PropertiesUtils.getAdminPassword();

    public static Response checkIfUserExistsApiCall(String username, String authUser, String authPass){

        String apiCall = BASE_URL + ApiCalls.createCheckIfUsersExistsApiCall(username);
        log.info("API CALL " + apiCall);
        Response response = null;
        try {
            response = RestAssured.given().auth().basic(authUser, authPass)
                    .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                    .when().get(apiCall);
        }
        catch (Exception e){
            Assert.fail("Exception in checkIfUserExists " + username + ") Api Call: " + e.getMessage());
        }
        return response;
    }

    public static boolean checkIfUserExists(String username, String authUser, String userPass) {
        log.trace("checkIfUserExists(" + username + ")");
        Response response = checkIfUserExistsApiCall(username, authUser, userPass);
        int status = response.getStatusCode();
        String responseBody = response.getBody().asString();
        Assert.assertEquals(status, 200, "Wrong Response Status code in checkIfUserExists" + username +
                ") Api Call! Response body: " + responseBody);
        String result = responseBody.toLowerCase();
        if (!(result == "true" || result == "false")) {
            Assert.fail("Cannot convert response " + responseBody + " to boolean value");
        }
            return Boolean.parseBoolean(responseBody);
    }

    public static boolean checkIfUserExists(String username){
        return checkIfUserExists(username, adminUser, adminPassword);
    }

    public static Response getUserApiCall(String username, String authUser, String authPass){
        String apiCall = BASE_URL + ApiCalls.createGetUserApiCall(username);
        Response response = null;
        try {
            response = RestAssured.given().auth().basic(authUser, authPass)
                    .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                    .when().get(apiCall);
        }
        catch (Exception e){
            Assert.fail("Exception in getUserApiCall " + username + ") Api Call: " + e.getMessage());
        }
        return response;
    }

    public static String getUserJsonFormat(String username, String authUser, String authPass) {
        log.trace("getUserJsonFormat(" + username + ")");
        Assert.assertTrue(checkIfUserExists(username, authUser, authPass), "User: '" + username + "' doesn't exist!");
        Response response = checkIfUserExistsApiCall(username, authUser, authPass);
        int status = response.getStatusCode();
        String responseBody = response.getBody().asString();
        Assert.assertEquals(status, 200, "Wrong Response Status code in checkIfUserExists" + username +
                ") Api Call! Response body: " + responseBody);
        return "";
    }

    public static User getUser(String username, String authUser, String authPass){
        log.debug("getUser(" + username + ")");
        String json = getUserJsonFormat(username, authUser, authPass);
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }

    public static User getUser(String username){
        return getUser(username, adminUser, adminPassword);
    }
}
