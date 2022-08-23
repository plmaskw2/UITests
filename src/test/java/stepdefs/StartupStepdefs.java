package stepdefs;

import framework.model.User;
import framework.pages.DashboardPage;
import framework.pages.RegisterPage;
import framework.pages.StartupPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class StartupStepdefs extends BaseStepdefs {

    public StartupStepdefs(WebDriver driver) {
        super(driver);
    }

    @Step
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

    @Step
    public StartupStepdefs navigateToRegisterForm() {
        new StartupPage(driver).clickSignupButton();
        return this;
    }

    @Step
    public StartupStepdefs registerUser(User user) {
        new RegisterPage(driver)
                .enterUsername(user.getUserName())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .enterConfirmation(user.getPassword())
                .enterFirstName(user.getFirstName())
                .enterLastName(user.getLastName())
                .enterHeadline(user.getHeadline())
                .enterDescription(user.getAboutYourself())
                .uploadAvatar(user.getAvatarPath())
                .clickSignupButton();
        return this;
    }

    @Step
    public StartupStepdefs verifySignupSuccessful() {
        new RegisterPage(driver).verifySignupSuccessful();
        return this;
    }

    @Step
    public StartupStepdefs verifyLoggedInSuccessful() {
        new DashboardPage(driver).verifyLoggedInSuccessful();
        return this;
    }

    @Step
    public StartupStepdefs navigateToStartupPageFromRegistrationForm() {
        new RegisterPage(driver).clickLoginButton();
        return this;
    }
}
