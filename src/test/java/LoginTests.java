import config.AppiumConfig;
import helpers.EmailGenerator;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
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
}
