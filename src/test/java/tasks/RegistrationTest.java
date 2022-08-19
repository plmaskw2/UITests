package tasks;

import data_provider.DataProvider;
import framework.model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    @BeforeClass
    public void setup() {
        initializeStepdefs();
    }

    @Test
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
