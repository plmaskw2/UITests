package base;

import framework.utils.ConfigurationUtils;
import framework.utils.driver_factory.DriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import stepdefs.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import static io.qameta.allure.Allure.addAttachment;

public abstract class WebBaseTestJUnit implements TestExecutionListener {
    protected WebDriver driver;
    protected StartupStepdefs startupStepdefs;
    protected NavigationStepdefs navigationStepdefs;
    protected MessagesStepdefs messagesStepdefs;
    protected DashboardStepdefs dashboardStepdefs;
    protected ForumStepdefs forumStepdefs;
    @SneakyThrows
    @Step("Initialize stepdefs classes")
    public void initializeStepdefs() {
        //TODO: Properties and driver initialization move to Before BaseTest class
        ConfigurationUtils.loadProperties();
        driver = DriverFactory.valueOf(ConfigurationUtils.properties.getProperty("driver")).getDriverManager().getDriver();
        startupStepdefs = new StartupStepdefs(driver);
        navigationStepdefs = new NavigationStepdefs(driver);
        messagesStepdefs = new MessagesStepdefs(driver);
        dashboardStepdefs = new DashboardStepdefs(driver);
        forumStepdefs = new ForumStepdefs(driver);
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testExecutionResult.getStatus().equals(TestExecutionResult.Status.FAILED)) {
            try {
                File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                addAttachment("Screenshot", FileUtils.openInputStream(screenshotAs));
            } catch (IOException | NoSuchSessionException e) {
            } finally {
                driver.quit();
            }
        }
    }
}
