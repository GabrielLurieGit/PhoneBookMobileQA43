package screens;

import enums.ContactField;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class EditContactScreen extends BaseScreen{
    public EditContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
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

    @FindBy(id = "com.sheygam.contactapp:id/updateBtn")
    MobileElement updateButton;

    public EditContactScreen editField(ContactField field,String newValue){
        MobileElement element = getFieldElement(field);
        element.clear();
        element.sendKeys(newValue);
        return this;
    }

    public ContactListScreen clickUpdateButton(){
        updateButton.click();
        return new ContactListScreen(driver);
    }

    private MobileElement getFieldElement(ContactField field){
        switch (field){
            case NAME: return inputNameField;
            case LAST_NAME: return inputLastNameField;
            case EMAIL:return inputEmailField;
            case PHONE: return inputPhoneField;
            case ADDRESS: return inputAddressField;
            case DESCRIPTION: return inputDescriptionField;
            default: throw new IllegalArgumentException();
        }

    }



}
