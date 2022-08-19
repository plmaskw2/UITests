package stepdefs;

import framework.pages.NavigationBarPage;
import org.openqa.selenium.WebDriver;

public class NavigationStepdefs extends BaseStepdefs {

    public NavigationStepdefs(WebDriver driver) {
        super(driver);
    }

    public NavigationStepdefs navigateToMessages() {
        new NavigationBarPage(driver).navigateToMessages();
        return this;
    }

    public NavigationStepdefs logout() {
        new NavigationBarPage(driver).clickLogoutButton();
        return this;
    }
}
