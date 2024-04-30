package base;

import framework.utils.ConfigurationUtils;
import framework.utils.driver_factory.DriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.IOException;

public class AfterTestExecutionCallbackBase implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) {
        Object testInstance = context.getRequiredTestInstance();
        WebDriver driver = ((WebBaseTestJUnit) testInstance).driver;
        if (context.getExecutionException().isPresent()) {
            Augmenter augmenter = new Augmenter();
            TakesScreenshot ts = (TakesScreenshot) augmenter.augment(driver);
            File getImage = ts.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(getImage, new File(System.getProperty("user.dir") + "\\screenshots\\screenshot.jpg"));
            }
            catch (IOException io) {}
            Allure.addAttachment("Screenshot", "image/jpeg","\\screenshots\\screenshot.jpg");
        }
    }
}
