package stepdefs;

import framework.pages.DashboardPage;
import framework.pages.NavigationBarPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class NavigationStepdefs extends BaseStepdefs {

    public NavigationStepdefs(WebDriver driver) {
        super(driver);
    }

    @Step
    public NavigationStepdefs navigateToMessages() {
        new NavigationBarPage(driver).navigateToMessages();
        return this;
    }

    @Step
    public NavigationStepdefs navigateToForums() {
        new DashboardPage(driver).navigateToForums();
        return this;
    }

    @Step
    public NavigationStepdefs logout() {
        new NavigationBarPage(driver).clickLogoutButton();
        return this;
    }
}
