package screenactions;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenUntil {
    private AppiumDriver driver;


    public ScreenUntil(AppiumDriver driver) {
        this.driver = driver;
    }


    public void takeStreenShot(String methodName){
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String time = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
        File file = new File("screenshots/"+methodName+"_"+time+".png");
        try {
            FileUtils.copyFile(scrFile,file);
        }catch (IOException e){
            throw  new RuntimeException(e);
        }

    }
}
