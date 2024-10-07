package screens;

import enums.Directions;
import helpers.ContactGenerator;
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

    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement yesButton;

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
//***********Scrolling********

    public ContactListScreen scrolling(){
        if(contacts.isEmpty()){
            throw new IllegalArgumentException("The list is emtpy!");
        }
        MobileElement contact = contacts.get(contacts.size()-1);
        Rectangle rectangle = contact.getRect();
        int x = rectangle.getX()+ rectangle.getWidth()/2;
        int y = rectangle.getY()+ rectangle.getHeight()/2;
        new TouchAction<>(driver)
                .longPress(PointOption.point(x,y))
                .moveTo(PointOption.point(x,0))
                .release()
                .perform();
        return this;
    }



    private boolean isThisTheEndOfTheList(){
        String beforeScrolling = getLastContact();
        scrolling();
        String afterScrolling = getLastContact();
       return beforeScrolling.equals(afterScrolling);
    }

    private String getLastContact(){
       return rowName.get(rowName.size()-1).getText()+" "+rowPhone.get(rowPhone.size()-1).getText();
    }
    public boolean isContactAddedWithScroll(Contact contact){
        boolean result = false;
        while (!result&& !isThisTheEndOfTheList()){
            boolean nameExistance = textContains(rowName, contact.getName());
            boolean phoneExistance = textContains(rowPhone,contact.getPhone());
            result = nameExistance && phoneExistance;
        }
        if(!result){scrolling();}
        return result;
    }

    public <T>T swipeAndActOnContact(Contact contact, Directions direction){
        int index = findContactWithScroll(contact);
        if(index == -1){
            throw new IllegalStateException("Contact not found..."+contact.toString());
        }
        MobileElement contactElement = contacts.get(index);
        Rectangle rectangle = contactElement.getRect();
        int startX = rectangle.getX() + rectangle.getWidth()/3; //4
        int y = rectangle.getY()+ rectangle.getHeight()/2;
        int endX = startX + rectangle.getWidth()*6/8;
        endX = Math.min(endX, rectangle.getX()+ rectangle.getWidth()-1);
        TouchAction<?> action = new TouchAction<>(driver);
        switch (direction){
            case LEFT: action
                    .longPress(PointOption.point(endX,y))
                    .moveTo(PointOption.point(startX,y))
                    .release()
                    .perform();
            return (T) new EditContactScreen(driver);
            case RIGHT: action
                    .longPress(PointOption.point(startX,y))
                    .moveTo(PointOption.point(endX,y))
                    .release()
                    .perform();
            if (isElementPresent(yesButton,"YES")){
                yesButton.click();
            }
            return (T) this;
            default: throw new IllegalArgumentException("Invalid direction"+direction);
        }
    }

    private int findContactWithScroll(Contact contact){
        scrolling();
        int index = getContactIndex(contact);
        while (index == -1 && !isThisTheEndOfTheList()){
            scrolling();
            index = getContactIndex(contact);
        }
        return index;
    }

    private int getContactIndex(Contact contact){
        if(rowName.size() != rowPhone.size()){
            throw new IllegalStateException("Wrong size...");
        }
        for (int i = 0; i< rowName.size();i++){
            if (i >=rowPhone.size()){
                break;
            }
            if (rowName.get(i).getText().contains(contact.getName()) &&
                    rowPhone.get(i).getText().contains(contact.getPhone())){
                return i;
            }
        }
        return -1;
    }


    public ContactListScreen addContacts(int number){
        for (int i = 0;i < number;i++){
            Contact contact = ContactGenerator.createValidContact();
            ContactListScreen contactListScreen = new ContactListScreen(driver)
                    .openNewForm()
                    .fillNewContactForm(contact)
                    .createContact();
            contactListScreen.isContactAdded(contact);
        }
        return this;
    }











    }



