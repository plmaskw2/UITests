package base;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;

public class Listener implements TestLifecycleListener {

    @Override
    public void beforeTestStop(TestResult result) {

        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {
            if (driver != null)
                Allure.addAttachment(result.getName(), new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }
}
