package framework.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }
    public WebDriver initDriver(DriverType driverType){
        WebDriver webDriver;
        switch (driverType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito", "-lang=en-GB");
                webDriver = new ChromeDriver(chromeOptions);
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
                webDriver.manage().window().maximize();
                webDriver.manage().deleteAllCookies();
                tlDriver.set(webDriver);
                break;
        }
        switch (driverType) {
            case FIREFOX:
                webDriver = new FirefoxDriver();
                tlDriver.set(webDriver);
                break;
        }
        return getDriver();
    }
}
