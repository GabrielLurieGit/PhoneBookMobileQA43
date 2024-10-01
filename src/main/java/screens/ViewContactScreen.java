package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Contact;
import org.openqa.selenium.support.FindBy;

public class ViewContactScreen extends BaseScreen {

    public ViewContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.sheygam.contactapp:id/nameTxt")
    MobileElement nameTxt;
    @FindBy(id = "com.sheygam.contactapp:id/lastNameTxt")
    MobileElement lastNameTxt;

    @FindBy(id = "com.sheygam.contactapp:id/emailTxt")
    MobileElement emailTxt;
    @FindBy(id = "com.sheygam.contactapp:id/phoneTxt")
    MobileElement phoneTxt;
    @FindBy(id = "com.sheygam.contactapp:id/addressTxt")
    MobileElement addressTxt;
    @FindBy(id = "com.sheygam.contactapp:id/descTxt")
    MobileElement descriptionTxt;

    public Contact getContact(){
        Contact contact = new Contact(nameTxt.getText()
                ,lastNameTxt.getText()
                ,emailTxt.getText()
                ,phoneTxt.getText()
                ,addressTxt.getText()
                ,descriptionTxt.getText());
        return contact;
    }





}
