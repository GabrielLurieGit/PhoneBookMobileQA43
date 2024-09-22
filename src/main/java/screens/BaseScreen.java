package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseScreen {
    AppiumDriver<MobileElement> driver;

    public BaseScreen(AppiumDriver<MobileElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        this.driver = driver;
    }

    public void waitForElement(MobileElement element){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isElementPresent(MobileElement element, String text){
        try {
            new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElement(element,text));
            return true;
        }catch (TimeoutException e){
            return false;

        }
    }

    public boolean isElementPresent(MobileElement element){
        try {
            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch (TimeoutException e){
            return false;

        }
    }

}
