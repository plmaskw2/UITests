package stepdefs;

import framework.model.User;
import framework.pages.DashboardPage;
import framework.pages.RegisterPage;
import framework.pages.StartupPage;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.util.Properties;

public class StartupStepdefs extends BaseStepdefs {

    Properties properties = new Properties();

    @SneakyThrows
    public StartupStepdefs(WebDriver driver) {
        super(driver);
        properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + "configuration.properties"));
    }

    @Step("User open application")
    public StartupStepdefs openApp() {
        driver.get(properties.getProperty("url"));
        new StartupPage(driver).isAt();
        return this;
    }

    @Step("User log in to application")
    public StartupStepdefs logInToApplication(String username, String password) {
        new StartupPage(driver)
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
        return this;
    }

    @Step("User navigates to register form")
    public StartupStepdefs navigateToRegisterForm() {
        new StartupPage(driver).clickSignupButton();
        return this;
    }

    @Step("User register new user {user.userName}")
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

    @Step("User verifies that he is signed up successfully")
    public StartupStepdefs verifySignupSuccessful() {
        new RegisterPage(driver).verifySignupSuccessful();
        return this;
    }

    @Step("user verifies that he is logged in successfully")
    public StartupStepdefs verifyLoggedInSuccessful() {
        new DashboardPage(driver).verifyLoggedInSuccessful();
        return this;
    }

    @Step("User navigates to startup page")
    public StartupStepdefs navigateToStartupPageFromRegistrationForm() {
        new RegisterPage(driver).clickLoginButton();
        return this;
    }
}
