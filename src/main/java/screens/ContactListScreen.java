package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Contact;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

public class ContactListScreen extends BaseScreen{
    @FindBy(id = "com.sheygam.contactapp:id/add_contact_btn")
    MobileElement addButton;

    @FindBy(id= "com.sheygam.contactapp:id/emptyTxt")
    MobileElement noContactsText;

   @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
   MobileElement title;

   @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<MobileElement> rowName;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowPhone']")
    List<MobileElement> rowPhone;
    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }


    public boolean isContactListPresent(){
        return isElementPresent(title,"Contact list");
    }

    public boolean isAddButtonPresents(){
      return isElementPresent(addButton);
    }


    public AddNewContactScreen openNewForm(){
        addButton.click();
        return new AddNewContactScreen(driver);
    }

    public boolean isContactAdded(Contact contact){
        boolean nameExistance = textContains(rowName, contact.getName());
        boolean phoneExistance = textContains(rowPhone,contact.getPhone());
        return nameExistance && phoneExistance;
    }

    public boolean textContains(List<MobileElement> list,String text){
        for (MobileElement mobileElement: list){
            if(mobileElement.getText().contains(text)){return true;}
        }return false;
    }

}
