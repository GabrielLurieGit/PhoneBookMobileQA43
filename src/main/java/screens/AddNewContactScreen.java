package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Contact;
import models.RegistrationResult;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddNewContactScreen extends BaseScreen{

    @FindBy(id = "com.sheygam.contactapp:id/inputName")
    MobileElement inputNameField;
    @FindBy(id = "com.sheygam.contactapp:id/inputLastName")
    MobileElement inputLastNameField;
    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    MobileElement inputEmailField;
    @FindBy(id = "com.sheygam.contactapp:id/inputPhone")
    MobileElement inputPhoneField;
    @FindBy(id = "com.sheygam.contactapp:id/inputAddress")
    MobileElement inputAddressField;
    @FindBy(id = "com.sheygam.contactapp:id/inputDesc")
    MobileElement inputDescriptionField;
    @FindBy(id = "com.sheygam.contactapp:id/createBtn")
    MobileElement createButton;

    @FindBy(id = "android:id/message")
    MobileElement errorAlert;

    public AddNewContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }


    public AddNewContactScreen fillNewContactForm(Contact contact){
        inputNameField.sendKeys(contact.getName());
        driver.hideKeyboard();
        inputLastNameField.sendKeys(contact.getLastName());
        driver.hideKeyboard();
        inputEmailField.sendKeys(contact.getEmail());
        driver.hideKeyboard();
        inputPhoneField.sendKeys(contact.getPhone());
        driver.hideKeyboard();
        inputAddressField.sendKeys(contact.getAddress());
        driver.hideKeyboard();
        inputDescriptionField.sendKeys(contact.getDescription());
        driver.hideKeyboard();
        return new AddNewContactScreen(driver);
    }


    public <T extends BaseScreen> T createContact(){
        createButton.click();
        List<MobileElement> list = driver.findElements(By.id("android:id/alertTitle"));
        if(list.size() >0){
            return (T)new AddNewContactScreen(driver);
        }
        else {
            return (T) new ContactListScreen(driver);
        }
    }


    public boolean isTheErrorPresent(String text){
        waitForElement(errorAlert);
        System.out.println("ALERT: "+errorAlert.getText());
        return errorAlert.getText().contains(text);
    }
    public RegistrationResult clickCreateBtnUsingRegistrationResult(){
        createButton.click();
        String msg = null;
        List<MobileElement> errorTitle = driver.findElements(By.id("android:id/alertTitle"));
        if (errorTitle.size()>0){
            List<MobileElement> errorMessage = driver.findElements(By.id("android:id/message"));
            if (errorMessage.size()>0){
                msg = errorMessage.get(0).getText();
            }else {
                msg= errorTitle.get(0).getText();
            }
            return new RegistrationResult(false,msg,null);
        }
        return new RegistrationResult(true,null,new ContactListScreen(driver));
    }



}
