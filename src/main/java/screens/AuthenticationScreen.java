package screens;

import interfaces.TestHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.LoginResult;
import models.RegistrationResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AuthenticationScreen extends BaseScreen implements TestHelper {

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement titleText;
    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    MobileElement inputEmailField;
    @FindBy(id = "com.sheygam.contactapp:id/inputPassword")
    MobileElement inputPasswordField;
    @FindBy(id = "com.sheygam.contactapp:id/regBtn")
    MobileElement registrationButton;
    @FindBy(id = "com.sheygam.contactapp:id/loginBtn")
    MobileElement loginButton;

    @FindBy(id = "android:id/message")
    MobileElement errorText;

    @FindBy(id = "android:id/button1")
    MobileElement errorOkButton;


    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public AuthenticationScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }


    public AuthenticationScreen fillEmailField(String email) {
        waitForElement(inputEmailField);
        inputEmailField.sendKeys(email);
        return this;
    }

    public AuthenticationScreen fillPasswordField(String password) {
        inputPasswordField.sendKeys(password);
        return this;
    }

//    public <T extends BaseScreen> T clickLoginButton() {
//        loginButton.click();
//        if (!isElementPresent(errorText, INVALID_LOGIN_MESSAGE)) {
//            return (T) new ContactListScreen(driver);
//        } else {
//            errorOkButton.click();
//            return (T) this;
//        }
//    }


    public <T extends BaseScreen> T clickByRegistrationButton() {
        registrationButton.click();
      List<MobileElement> list = driver.findElements(By.id("android:id/alertTitle"));
        if (list.size() > 0) {
            setErrorMsg(errorText.getText());
            errorOkButton.click();
            return (T) new AuthenticationScreen(driver);
        }
        return (T) new ContactListScreen(driver);
    }

    public <T extends BaseScreen>T clickByLoginButton(){
        loginButton.click();
        List<MobileElement> list = driver.findElements(By.id("android:id/alertTitle"));
        if(list.size()>0){
            return (T)new AuthenticationScreen(driver);
        }
        else {
            return (T)new ContactListScreen(driver);
        }
    }

    public RegistrationResult clickLoginButtonUsingRegistrationResult(){
        return  clickButtonUsingRegistrationResult(loginButton);
    }
    public RegistrationResult clickRegistrationButtonUsingRegistrationResult(){
        return  clickButtonUsingRegistrationResult(registrationButton);
    }
    public RegistrationResult clickButtonUsingRegistrationResult(MobileElement button) {
        button.click();
        String msg = null;
        List<MobileElement> errorTitle = driver.findElements(By.id("android:id/alertTitle"));
        if (errorTitle.size() > 0) {
            List<MobileElement> errorMessage = driver.findElements(By.id("android:id/message"));
            if (errorMessage.size() > 0) {
                msg = errorMessage.get(0).getText();
            } else {
                msg = errorTitle.get(0).getText();
            }
            return new RegistrationResult(false, msg, null);
        } else {
            return new RegistrationResult(true, null, new ContactListScreen(driver));
        }
    }



    public boolean isErrorMessagePresent(){
        return isElementPresent(errorText,INVALID_LOGIN_MESSAGE);
    }


}



//    public <T extends BaseScreen> T clickRegistrationButton() {
//        registrationButton.click();
//        if (isElementPresent(errorText, WRONG_EMAIL_ERROR_MESSAGE)) {
//            errorOkButton.click();
//            return (T) this;
//        } else if (isElementPresent(errorText, WRONG_PASSWORD_ERROR_MESSAGE)) {
//            errorOkButton.click();
//            return (T) this;
//        } else if (isElementPresent(errorText,USER_ALREADY_EXISTS)) {
//            errorOkButton.click();
//            return (T) this;
//        } else {
//            return (T) new ContactListScreen(driver);
//        }
//
//    }

