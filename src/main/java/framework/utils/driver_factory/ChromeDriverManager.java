package framework.utils.driver_factory;

import framework.utils.ConfigurationUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class ChromeDriverManager extends DriverManager {

    @Override
    protected WebDriver createDriver() {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        WebDriver driver = null;
        switch (ConfigurationUtils.properties.getProperty("driverType")) {
            case "REMOTE":
                try {
                    driver = new RemoteWebDriver(new URL(GRID_HUB), getChromeOptions());
                }
                catch (MalformedURLException ignore) {}
            case "LOCAL":
                driver = new ChromeDriver(getChromeOptions());
        }
        return driver;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-features=EnableEphemeralFlashPermission");
        options.addArguments("--disable-infobars");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        return options;
    }
}