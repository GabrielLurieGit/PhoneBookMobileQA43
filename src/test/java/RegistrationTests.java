import config.AppiumConfig;
import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import io.appium.java_client.appmanagement.ApplicationState;
import models.RegistrationResult;
import org.openqa.selenium.html5.ApplicationCache;
import org.testng.Assert;
import org.testng.annotations.Test;
import screenactions.ScreenUntil;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class RegistrationTests extends AppiumConfig {



//    @Test
//    public void registrationPositiveTest() {
//      ContactListScreen contactListScreen =  new SplashScreen(driver).switchAuthenticationScreen()
//                .fillEmailField(EmailGenerator.generateEmail(5,5,3))
//                .fillPasswordField(PasswordStringGenerator.generateRandomPassword())
//                .clickByRegistrationButton();
//      Assert.assertTrue(contactListScreen.isContactListPresent());
//    }

    @Test
    public void registrationPositiveTest() {
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(driver);
               ContactListScreen contactListScreen = authenticationScreen.fillEmailField(EmailGenerator.generateEmail(5,5,3))
                .fillPasswordField(PasswordStringGenerator.generateRandomPassword())
                .clickByRegistrationButton();
        Assert.assertTrue(contactListScreen.isContactListPresent());
    }



@Test
    public void wrongEmailRegistration(){
        try {
            new SplashScreen(driver).switchAuthenticationScreen()
                    .fillEmailField("lalala")
                    .fillPasswordField(PasswordStringGenerator.generateRandomPassword())
                    .clickByRegistrationButton();
        }catch (RuntimeException exception){
            Assert.assertTrue(exception.getMessage().contains("username"));
        }

    }


    @Test
    public void regWOPassword(){
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(driver);
     RegistrationResult result = authenticationScreen.fillEmailField(EmailGenerator.generateEmail(5,5,3))
                .fillPasswordField("123")
                .clickByRegistrationButtonUsingRegistrationResult();
     if(!result.isSuccess()){
         result.getErrorMessage().contains("least 8");
     }else {
         ContactListScreen contactListScreen = result.getContactListScreen();
     }
    }


    @Test()
    public void regWOPasswordUsingAppState(){
        AuthenticationScreen authenticationScreen=new AuthenticationScreen(driver);
       ApplicationState appState = driver.queryAppState("com.sheygam.contactapp");
        System.out.println("APP STATE: "+ appState.toString());
        RegistrationResult result = authenticationScreen
                .fillEmailField(EmailGenerator.generateEmail(5,5,3))
                .fillPasswordField("")
                .clickByRegistrationButtonUsingRegistrationResult();
        System.out.println("RESULT MESSAGE: "+ result.getErrorMessage());
        ScreenUntil screenUntil = new ScreenUntil(driver);
        screenUntil.takeStreenShot("RegistrationIssue");


        if (appState == ApplicationState.RUNNING_IN_FOREGROUND){
            System.out.println("Running....");
        }
        else {
            System.out.println("Not....");
        }
        Assert.assertTrue(result.getErrorMessage().contains("Contact App has stopped"));
    }
}














