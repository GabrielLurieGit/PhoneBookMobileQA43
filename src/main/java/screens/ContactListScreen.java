package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
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

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<MobileElement> contacts;

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

    public void clickOnContact(String name, String phone) {
        String xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer' " +
                "and .//*[@resource-id='com.sheygam.contactapp:id/rowName' and contains(@text,'" + name + "')]" +
                " and .//*[@resource-id='com.sheygam.contactapp:id/rowPhone' and contains(@text,'" + phone + "')]]";
        MobileElement contactContainer = driver.findElementByXPath(xpath);
        contactContainer.click();
        }



        public EditContactScreen editMyContact(int index){
        waitForElement(addButton);
        MobileElement contact = contacts.get(0);
            Rectangle rectangle = contact.getRect();
            int xStart = rectangle.getX()+rectangle.getWidth()/8;
            int y = rectangle.getY()+rectangle.getHeight()/2;
            int xEnd = xStart+ rectangle.getWidth()*6/8;
             new TouchAction<>(driver)
                     .longPress(PointOption.point(xEnd,y))
                     .moveTo(PointOption.point(xStart,y))
                     .release()
                     .perform();
             return new EditContactScreen(driver);
        }


    public boolean isContactModified(String text,int index){
        contacts.get(index).click();
        Contact contact = new ViewContactScreen(driver).getContact();
       return contact.toString().contains(text);
    }

    }



