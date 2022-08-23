package stepdefs;

import framework.model.User;
import framework.pages.DashboardPage;
import framework.pages.RegisterPage;
import framework.pages.StartupPage;
import org.openqa.selenium.WebDriver;

public class StartupStepdefs extends BaseStepdefs {

    public StartupStepdefs(WebDriver driver) {
        super(driver);
    }

    public StartupStepdefs openApp() {
        driver.get("http://200.168.0.1/index.php");
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

    public StartupStepdefs navigateToRegisterForm() {
        new StartupPage(driver).clickSignupButton();
        return this;
    }

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

    public StartupStepdefs verifySignupSuccessful() {
        new RegisterPage(driver).verifySignupSuccessful();
        return this;
    }

    public StartupStepdefs verifyLoggedInSuccessful() {
        new DashboardPage(driver).verifyLoggedInSuccessful();
        return this;
    }

    public StartupStepdefs navigateToStartupPageFromRegistrationForm() {
        new RegisterPage(driver).clickLoginButton();
        return this;
    }
}
