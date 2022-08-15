package tasks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SeleniumPresenceTest {

    private static ThreadLocal<WebDriver> browser = new ThreadLocal<>();

    @BeforeClass
    static void setupAll() {
        WebDriverManager.getInstance(ChromeDriver.class).driverVersion("104.0.5112.79").setup();
        WebDriver driver = new ChromeDriver();
        browser.set(driver);
    }

    @Test
    public void SeleniumPresenceTest() {

        WebDriver driver = getDriver();

        driver.get("http://127.0.0.1/login.php");

    }

    private static synchronized WebDriver getDriver() {
        return browser.get();
    }
}
