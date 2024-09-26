import config.AppiumConfig;
import helpers.EmailGenerator;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.LoginResult;
import models.RegistrationResult;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class LoginTests extends AppiumConfig implements TestHelper {
    @Test
    public void loginPositive(){
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(driver);
       ContactListScreen contactListScreen = authenticationScreen
                .fillEmailField(PropertiesReaderXML.getProperties("myuser",XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties("mypass",XML_DATA_FILE))
                .clickByLoginButton();
        Assert.assertTrue(contactListScreen.isContactListPresent());
    }

    @Test
    public void loginNegativeWOPassword(){
      LoginResult loginResult = new AuthenticationScreen(driver)
                .fillEmailField(PropertiesReaderXML.getProperties("myuser",XML_DATA_FILE))
                .fillPasswordField("")
              .clickByLoginButtonUsingLoginResult();
      if(!loginResult.isSuccess()){
          loginResult.getErrorMessage().equals(INVALID_LOGIN_MESSAGE);
      }else {
          ContactListScreen contactListScreen = loginResult.getContactListScreen();
      }
    }

    @Test
    public void loginNegativeInvalidEmail(){
        LoginResult loginResult = new AuthenticationScreen(driver)
                .fillEmailField(PropertiesReaderXML.getProperties("emailInvalid1",XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties("mypass",XML_DATA_FILE))
                .clickByLoginButtonUsingLoginResult();
        if(!loginResult.isSuccess()){
            loginResult.getErrorMessage().equals(INVALID_LOGIN_MESSAGE);
        }else {
            ContactListScreen contactListScreen = loginResult.getContactListScreen();
        }
    }




}
