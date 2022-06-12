package data;

public final class ApiCalls {

    public static final String CHECK_IF_USER_EXISTS = "/api/users/exists";
    public static final String GET_USER = "/api/users/findByUsername";

    public static String createCheckIfUsersExistsApiCall(String username) {
        return CHECK_IF_USER_EXISTS + username;
    }
    public static String createGetUserApiCall(String username) {
        return GET_USER + username;
    }
}
