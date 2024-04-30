package base;

import com.google.common.io.Files;
import framework.utils.ConfigurationUtils;
import io.qameta.allure.Attachment;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class AfterTestExecutionCallbackBase implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) {
        Object testInstance = context.getRequiredTestInstance();
        WebDriver driver = ((WebBaseTestJUnit) testInstance).driver;
        SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();
        if (context.getExecutionException().isPresent()) {
            takeScreenshot(driver);
        }
        if (ConfigurationUtils.properties.getProperty("driverType").equals("REMOTE")) {
            attachVideo(sessionId);
        }
        driver.quit();
    }

    @Attachment(value = "Video", type = "text/html")
    public static InputStream attachVideo(SessionId sessionId) {
        String htmlContent = String.format("<html><body><video width=\"320\" height=\"240\" controls><source src=\"http://192.168.0.121:5555/video/%s.mp4\" type=\"video/mp4\"></video></body></html>", sessionId);
        byte[] htmlBytes = htmlContent.getBytes(StandardCharsets.UTF_8);
        return new ByteArrayInputStream(htmlBytes);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
