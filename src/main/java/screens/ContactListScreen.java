package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactListScreen extends BaseScreen{
    @FindBy(id = "com.sheygam.contactapp:id/add_contact_btn")
    MobileElement addButton;
    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }


    public boolean isAddButtonPresents(){
      return isElementPresent(addButton);
    }

}
