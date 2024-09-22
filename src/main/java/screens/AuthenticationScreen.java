package screens;

import interfaces.TestHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

import javax.swing.*;

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

    @FindBy(id = "com.sheygam.contactapp:id/message")
    MobileElement errorText;

    @FindBy(id = "com.sheygam.contactapp:id/button1")
    MobileElement errorOkButton;


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

    public <T extends BaseScreen> T clickLoginButton() {
        loginButton.click();
        if (!isElementPresent(errorText, INVALID_LOGIN_MESSAGE)) {
            return (T) new ContactListScreen(driver);
        } else {
            errorOkButton.click();
            return (T) this;
        }
    }

    public <T extends BaseScreen> T clickRegistrationButton() {
        registrationButton.click();
        if (isElementPresent(errorText, WRONG_EMAIL_ERROR_MESSAGE)) {
            errorOkButton.click();
            return (T) this;
        } else if (isElementPresent(errorText, WRONG_PASSWORD_ERROR_MESSAGE)) {
            errorOkButton.click();
            return (T) this;
        } else if (isElementPresent(errorText,USER_ALREADY_EXISTS)) {
            errorOkButton.click();
            return (T) this;
        } else {
            return (T) new ContactListScreen(driver);
        }

    }
}
