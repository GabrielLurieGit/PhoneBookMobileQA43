import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class SplashScreenTest extends AppiumConfig {
    @Test
    public void firstLaunch(){
       String version = new SplashScreen(driver).getTextVersion();
        Assert.assertTrue(version.contains("Version"));
    }
}
