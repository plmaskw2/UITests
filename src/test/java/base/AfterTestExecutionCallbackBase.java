package base;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AfterTestExecutionCallbackBase implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) {
        Object testInstance = context.getRequiredTestInstance();
        WebDriver driver = ((WebBaseTestJUnit) testInstance).driver;
        if (context.getExecutionException().isPresent()) {
            takeScreenshot(driver);
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
