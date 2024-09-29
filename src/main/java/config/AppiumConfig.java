package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class AppiumConfig {
    public static AppiumDriver<MobileElement> driver;
@BeforeMethod
    public void setUp() throws MalformedURLException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName","Android");
    capabilities.setCapability("deviceName","emulator-5554"); //Pix6
    capabilities.setCapability("platformVersion","8.0");
    capabilities.setCapability("appPackage","com.sheygam.contactapp");
    capabilities.setCapability("appActivity","SplashActivity");
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"Appium");
    capabilities.setCapability(MobileCapabilityType.APP,"C:\\Users\\tocda\\Tools\\apks\\contacts-android.apk");
    driver = new AppiumDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"),capabilities);
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
