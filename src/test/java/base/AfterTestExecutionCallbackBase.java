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

import java.io.File;
import java.io.IOException;

public class AfterTestExecutionCallbackBase implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) {
        WebDriver driver = DriverFactory.valueOf(ConfigurationUtils.properties.getProperty("driver")).getDriverManager().getDriver();
        if (context.getExecutionException().isPresent()) {
            try {
                Allure.addAttachment("Screenshot", FileUtils.openInputStream(getScreenshot(driver)));
            }
            catch (IOException ignore) {}
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private File getScreenshot(WebDriver driver) {
        return ((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
    }
}
