package interfaces;

public interface TestHelper {
public static final String WRONG_PASSWORD_ERROR_MESSAGE = "{password= At least 8 characters; Must contain at least 1 uppercase letter," +
        " 1 lowercase letter, and 1 number; Can contain special characters [@$#^&*!]}";
public static final String WRONG_EMAIL_ERROR_MESSAGE = "{username=must be a well-formed email address}";
public static final String INVALID_LOGIN_MESSAGE = "Login or Password incorrect";
public static final String USER_ALREADY_EXISTS= "User already exists";
public static final String XML_DATA_FILE = "src/main/resources/data.xml";
public static final String MY_USER = "myuser";
public static final String MY_PASSWORD = "mypass";
public static final String INVALID_PASS = "passwordInvalid1";
public static final String INVALID_PASS2 = "passwordInvalid2";

public static final String INVALID_EMAIL = "emailInvalid1";

}
