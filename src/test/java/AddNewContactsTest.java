import config.AppiumConfig;
import enums.ContactField;
import helpers.ContactGenerator;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.Contact;
import models.RegistrationResult;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class AddNewContactsTest extends AppiumConfig implements TestHelper {

    @Test
    public void addNewContactPositive() {
        Contact contact = ContactGenerator.createValidContact();
        new SplashScreen(driver).switchAuthenticationScreen()
                .fillEmailField(PropertiesReaderXML.getProperties("myuser",XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties("mypass",XML_DATA_FILE))
                .clickLoginButtonUsingRegistrationResult();
        ContactListScreen contactListScreen = new ContactListScreen(driver);
        contactListScreen
                .openNewForm()
                .fillNewContactForm(contact)
                .createContact();
        Assert.assertTrue(contactListScreen.isContactAdded(contact));
    }


    @Test
    public void AddNewContactNegative(){
        Contact contact = ContactGenerator.createInvalidContact("sasdsad", ContactField.EMAIL);
        new SplashScreen(driver).switchAuthenticationScreen()
                .fillEmailField(PropertiesReaderXML.getProperties("myuser",XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties("mypass",XML_DATA_FILE))
                .clickLoginButtonUsingRegistrationResult();
        AddNewContactScreen addNewContactScreen = new ContactListScreen(driver)
                .openNewForm()
                .fillNewContactForm(contact)
                .createContact();
        Assert.assertTrue(addNewContactScreen.isTheErrorPresent("email"));
    }


    @Test
    public void AddNewContactNegativeUsingRegistrationResult(){
        Contact contact = ContactGenerator.createInvalidContact("sasdsad", ContactField.EMAIL);
        new SplashScreen(driver).switchAuthenticationScreen()
                .fillEmailField(PropertiesReaderXML.getProperties("myuser",XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties("mypass",XML_DATA_FILE))
                .clickLoginButtonUsingRegistrationResult();
        ContactListScreen contactListScreen = new ContactListScreen(driver);
             RegistrationResult registrationResult = contactListScreen.openNewForm()
                .fillNewContactForm(contact)
                .clickCreateBtnUsingRegistrationResult();
             if (!registrationResult.isSuccess()){
                 registrationResult.getErrorMessage();
             }else {
                 contactListScreen = registrationResult.getContactListScreen();
             }
             Assert.assertTrue(registrationResult.getErrorMessage().contains("must be a well-formed email"));
    }


    @Test
    public void addNewContactApproach2() throws InterruptedException {
        Contact contact = ContactGenerator.createValidContact();
        new SplashScreen(driver).switchAuthenticationScreen()
                .fillEmailField(PropertiesReaderXML.getProperties("myuser",XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties("mypass",XML_DATA_FILE))
                .clickLoginButtonUsingRegistrationResult();
        RegistrationResult registrationResult = new ContactListScreen(driver)
                .openNewForm()
                .fillNewContactForm(contact)
                .clickCreateButton();
        ContactListScreen contactListScreen = registrationResult.getContactListScreen();
        contactListScreen.clickOnContact(contact.getName(),contact.getPhone());
        Thread.sleep(3000);
    }
}