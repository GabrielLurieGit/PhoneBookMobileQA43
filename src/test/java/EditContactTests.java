import config.AppiumConfig;
import config.InternetManager;
import enums.ContactField;
import enums.Directions;
import helpers.ContactGenerator;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.EditContactScreen;
import screens.SplashScreen;

import java.io.IOException;

public class EditContactTests extends AppiumConfig implements TestHelper {
    @Test
    public void editContactTestPositive(){
        InternetManager internetManager = new InternetManager();

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



    @Test
    public void addContactAndCheckTheList(){
        Contact contact = ContactGenerator.createValidContact();
        new SplashScreen(driver).switchAuthenticationScreen()
                .fillEmailField(PropertiesReaderXML.getProperties("myuser",XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties("mypass",XML_DATA_FILE))
                .clickLoginButtonUsingRegistrationResult();
        ContactListScreen contactListScreen= new ContactListScreen(driver)
                .openNewForm()
                .fillNewContactForm(contact)
                .createContact();
        contactListScreen.isContactAddedWithScroll(contact);
    }

    @Test
    public void editeOrRemoveContact() throws IOException, InterruptedException {
        InternetManager internetManager = new InternetManager();
        Contact contact = ContactGenerator.createValidContact();
        new SplashScreen(driver).switchAuthenticationScreen()
                .fillEmailField(PropertiesReaderXML.getProperties("myuser",XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties("mypass",XML_DATA_FILE))
                .clickLoginButtonUsingRegistrationResult();
        ContactListScreen contactListScreen= new ContactListScreen(driver)
                .openNewForm()
                .fillNewContactForm(contact)
                .createContact();
        internetManager.cpuUsage();
        internetManager.memoryMonitoring();
        contactListScreen.swipeAndActOnContact(contact, Directions.RIGHT);
     // editContactScreen.editField(ContactField.EMAIL,"modifidField@gmail.com");
    }

}
