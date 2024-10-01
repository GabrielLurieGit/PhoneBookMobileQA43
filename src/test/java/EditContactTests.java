import config.AppiumConfig;
import enums.ContactField;
import helpers.ContactGenerator;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class EditContactTests extends AppiumConfig implements TestHelper {
    @Test
    public void editContactTestPositive(){
        String modifiedFieldValue = "mynewemail@gmail.com";
        Contact contact = ContactGenerator.createValidContact();
        new SplashScreen(driver).switchAuthenticationScreen()
                .fillEmailField(PropertiesReaderXML.getProperties("myuser",XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties("mypass",XML_DATA_FILE))
                .clickLoginButtonUsingRegistrationResult();
        ContactListScreen contactListScreen= new ContactListScreen(driver)
                .openNewForm()
                .fillNewContactForm(contact)
                .createContact();
      Assert.assertTrue(contactListScreen
              .editMyContact(0)
              .editField(ContactField.EMAIL,modifiedFieldValue)
              .clickUpdateButton()
              .isContactModified(modifiedFieldValue,0));
    }

}
