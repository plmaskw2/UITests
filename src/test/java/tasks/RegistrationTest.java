package tasks;

import base.BaseTest;
import data_provider.DataProvider;
import framework.model.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Regression tests")
@Feature("Registration test")
public class RegistrationTest extends BaseTest {

    @BeforeClass
    public void setup() {
        initializeStepdefs();
    }

    @Test
    @Description("Registration test")
    public void registrationTest() {
        User newUser = DataProvider.getNewUser();

        startupStepdefs
                .openApp()
                .navigateToRegisterForm()
                .registerUser(newUser)
                .verifySignupSuccessful()
                .navigateToStartupPageFromRegistrationForm()
                .logInToApplication(newUser.getUserName(), newUser.getPassword())
                .verifyLoggedInSuccessful();
    }
}
