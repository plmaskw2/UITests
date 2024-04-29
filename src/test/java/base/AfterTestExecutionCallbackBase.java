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
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.function.Supplier;

public class AfterTestExecutionCallbackBase implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) {
        WebDriver driver = DriverFactory.valueOf(ConfigurationUtils.properties.getProperty("driver")).getDriverManager().getDriver();
        if (context.getExecutionException().isPresent()) {
            takeScreenshot(driver);
        }
    }

    private void takeScreenshot(WebDriver driver) {
        if (driver instanceof TakesScreenshot) {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addByteAttachmentAsync("Screenshot", "image/png", "png", (Supplier<byte[]>) new ByteArrayInputStream(screenshotBytes));
        }
    }
}
