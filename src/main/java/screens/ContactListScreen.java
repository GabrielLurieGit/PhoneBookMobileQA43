package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ContactListScreen extends BaseScreen{
    @FindBy(id = "com.sheygam.contactapp:id/add_contact_btn")
    MobileElement addButton;

    @FindBy(id= "com.sheygam.contactapp:id/emptyTxt")
    MobileElement noContactsText;

   @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
   MobileElement title;
    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }


    public boolean isContactListPresent(){
        return isElementPresent(title,"Contact list");
    }

    public boolean isAddButtonPresents(){
      return isElementPresent(addButton);
    }

}
