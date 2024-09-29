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
      RegistrationResult registrationResult = new AuthenticationScreen(driver)
                .fillEmailField(PropertiesReaderXML.getProperties("myuser",XML_DATA_FILE))
                .fillPasswordField("")
              .clickLoginButtonUsingRegistrationResult();
      if(!registrationResult.isSuccess()){
          registrationResult.getErrorMessage().equals(INVALID_LOGIN_MESSAGE);
      }else {
          ContactListScreen contactListScreen = registrationResult.getContactListScreen();
      }
        Assert.assertTrue(registrationResult.getErrorMessage().equals(INVALID_LOGIN_MESSAGE));
    }


//    @Test
//    public void loginNegativeTry(){
//       AuthenticationScreen authenticationScreen = new AuthenticationScreen(driver)
//                .fillEmailField(PropertiesReaderXML.getProperties("myuser",XML_DATA_FILE))
//                .fillPasswordField("")
//                 .clickByLoginButton();
//       Assert.assertTrue(authenticationScreen.isErrorMessagePresent());
//    }

    @Test
    public void loginNegativeInvalidEmail(){
       RegistrationResult registrationResult =  new AuthenticationScreen(driver)
                .fillEmailField(PropertiesReaderXML.getProperties("emailInvalid1",XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties("mypass",XML_DATA_FILE))
                 .clickLoginButtonUsingRegistrationResult();
        if(!registrationResult.isSuccess()){
            registrationResult.getErrorMessage().equals(INVALID_LOGIN_MESSAGE);
        }else {
            ContactListScreen contactListScreen = registrationResult.getContactListScreen();
        }
        Assert.assertTrue(registrationResult.getErrorMessage().equals(INVALID_LOGIN_MESSAGE));
    }

}
