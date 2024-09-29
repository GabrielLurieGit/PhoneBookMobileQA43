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

    @Test
    public void splashScreenTime(){
        long expectedTime = 3000;
        SplashScreen splashScreen = new SplashScreen(driver);
        long startTime = System.currentTimeMillis();
        splashScreen.splashScreenExistance();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime-startTime;
        System.out.println(resultTime+" ms.");
        Assert.assertTrue(resultTime <= expectedTime);
    }
}
