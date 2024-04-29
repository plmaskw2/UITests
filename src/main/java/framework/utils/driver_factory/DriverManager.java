package framework.utils.driver_factory;

import framework.utils.ConfigurationUtils;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class DriverManager {

    protected final String SELENOID_HUB = ConfigurationUtils.properties.getProperty("selenoidHub");
    protected final String GRID_HUB = ConfigurationUtils.properties.getProperty("gridHub");
    protected ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
    protected abstract WebDriver createDriver();

    public void quitDriver() {
        if (null != drivers.get()) {
            try {
                drivers.get().quit();
                drivers.remove();
            } catch (Exception e) {}
        }
    }

    public WebDriver getDriver() {
        if (drivers.get() == null) {
            drivers.set(this.createDriver());
        }
        drivers.get().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        return drivers.get();
    }
}
