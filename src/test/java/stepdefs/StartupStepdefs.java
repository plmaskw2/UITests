package stepdefs;

import framework.pages.StartupPage;
import org.openqa.selenium.WebDriver;

public class StartupStepdefs extends BaseStepdefs {

    public StartupStepdefs(WebDriver driver) {
        super(driver);
    }

    public StartupStepdefs openApp() {
        driver.get("http://localhost/index.php");
        new StartupPage(driver).isAt();
        return this;
    }

    public StartupStepdefs logInToApplication(String username, String password) {
        new StartupPage(driver)
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
        return this;
    }

    public StartupStepdefs navigateToRegisterForm(String username, String password) {
        new StartupPage(driver)
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
        return this;
    }
}
