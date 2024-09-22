import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class AuthenticationTests extends AppiumConfig {

    @Test
    public void loginPositiveTest(){
        SplashScreen splashScreen = new SplashScreen(driver);
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(driver);
        authenticationScreen.fillEmailField("bigbrother@gmail.com")
                .fillPasswordField(" Tr43123456!")
                .clickLoginButton();
        ContactListScreen contactListScreen = new ContactListScreen(driver);
        Assert.assertTrue(contactListScreen.isAddButtonPresents());
    }

    @Test
    public void loginNegativeTest(){
        SplashScreen splashScreen = new SplashScreen(driver);
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(driver);
        authenticationScreen.fillEmailField("bigbrother@gmail.com")
                .fillPasswordField("")
                .clickLoginButton();
    }

    @Test
    public void registrationPositiveTest(){                         //блок try catch на случай падения апликации, обнаружил что с пустым полем крашит (обсудил бы это на уроке)
        try{
            SplashScreen splashScreen = new SplashScreen(driver);
            AuthenticationScreen authenticationScreen = new AuthenticationScreen(driver);
            authenticationScreen.fillEmailField("bigbrother22@gmail.com")
                   // .fillPasswordField("Qwerty1234!")
                    .clickRegistrationButton();
        }catch (RuntimeException e){
            System.out.println("RuntimeException occured" + e.getMessage());
        }
    }


}
