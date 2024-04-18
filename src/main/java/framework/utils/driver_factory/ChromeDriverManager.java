package framework.utils.driver_factory;

import framework.utils.ConfigurationUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Map;

public class ChromeDriverManager extends DriverManager {

    @Override
    protected WebDriver createDriver() {
        WebDriver driver = null;
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browserName", "chrome");
        cap.setCapability("browserVersion",ConfigurationUtils.properties.getProperty("chromeVersion"));
        cap.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
        switch (ConfigurationUtils.properties.getProperty("driverType")) {
            case "REMOTE":
                if (ConfigurationUtils.properties.getProperty("selenoid").equals("true")) {
                    cap.setCapability("selenoid:options", Map.of(
                            "enableVNC", true
                    ));
                    return WebDriverManager.chromedriver()
                            .capabilities(cap)
                            .remoteAddress(SELENOID_HUB)
                            .create();
                }
                else {
                    return WebDriverManager.chromedriver()
                            .capabilities(cap)
                            .remoteAddress(GRID_HUB)
                            .create();
                }
            case "LOCAL":
                WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                return new ChromeDriver(getChromeOptions());
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
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--ignore-cerfiticate-errors");

        return options;
    }
}