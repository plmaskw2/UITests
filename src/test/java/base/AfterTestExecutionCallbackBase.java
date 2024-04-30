package base;

import framework.utils.ConfigurationUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;

public class AfterTestExecutionCallbackBase implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) {
        Object testInstance = context.getRequiredTestInstance();
        WebDriver driver = ((WebBaseTestJUnit) testInstance).driver;
        if (context.getExecutionException().isPresent()) {
            takeScreenshot(driver);
        }
        driver.quit();
        if (ConfigurationUtils.properties.getProperty("driverType").equals("REMOTE")) {
            attachVideo(driver);
        }
    }

    @Attachment(value = "Video", type = "video/mp4")
    public static byte[] attachVideo(WebDriver driver) {
        byte[] byteArr = null;
        try {
            byteArr = IOUtils.toByteArray(new FileInputStream("http://192.168.0.121:5555/video/%s.mp4".formatted(((RemoteWebDriver) driver).getSessionId().toString())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArr;
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
