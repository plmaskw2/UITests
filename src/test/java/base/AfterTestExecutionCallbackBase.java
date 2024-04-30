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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class AfterTestExecutionCallbackBase implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) {
        WebDriver driver = DriverFactory.valueOf(ConfigurationUtils.properties.getProperty("driver")).getDriverManager().getDriver();
        if (context.getExecutionException().isPresent()) {
            Augmenter augmenter = new Augmenter();
            TakesScreenshot ts = (TakesScreenshot) augmenter.augment(driver);
            byte[] screenshotBytes = ts.getScreenshotAs(OutputType.BYTES);
            String screenshotAsBase64 = Base64.getEncoder().encodeToString(screenshotBytes);
            Allure.addAttachment("Screenshot", "image/png", screenshotAsBase64);

        }
    }

    @Attachment(value = "HTML attachment", type = "text/html")
    private File getScreenshot(WebDriver driver) {
        String image = null;
        Augmenter augmenter = new Augmenter();
        TakesScreenshot ts = (TakesScreenshot) augmenter.augment(driver);
        File src = ts.getScreenshotAs(OutputType.FILE);
        try {
            image = System.getProperty("user.dir") + "\\screenshots\\" + "screenshot.png";
            FileUtils.copyFile(src, new File(image));
        }
        catch (IOException ex) {
            ex.getStackTrace();
        }
        return src;
    }
}
