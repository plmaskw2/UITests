package tasks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumPresenceTest {

    private static ThreadLocal<WebDriver> browser = new ThreadLocal<>();

    @BeforeClass
    static void setupAll() {
        WebDriverManager.getInstance(FirefoxDriver.class).driverVersion("91.12").setup();
        WebDriver driver = new ChromeDriver();
        browser.set(driver);
    }

    @Test
    public void SeleniumPresenceTest() {

        WebDriver driver = getDriver();

        driver.get("http://196.168.0.1/index.php");

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='login-submit']"))));
    }

    private static synchronized WebDriver getDriver() {
        return browser.get();
    }
}
