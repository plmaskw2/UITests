package tasks;

import data_provider.DataProvider;
import framework.model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatFunctionalityTest extends BaseTest {

    User user1;
    User user2;

    @BeforeClass
    public void setup(){
        initializeStepdefs();
    }

    @BeforeMethod
    public void prerequisites() {
        user1 = DataProvider.getNewUser();
        user2 = DataProvider.getNewUser();

        startupStepdefs
                .openApp()
                .navigateToRegisterForm()
                .registerUser(user1)
                .verifySignupSuccessful()
                .registerUser(user2)
                .verifySignupSuccessful();
    }

    @Test
    public void chatTest() {
        String messageContent = DataProvider.getMessageContent();
        String messageContent2 = DataProvider.getMessageContent();

        startupStepdefs
                .openApp()
                .logInToApplication(user1.getUserName(), user1.getPassword());
        navigationStepdefs.navigateToMessages();
        messagesStepdefs
                .openChatWithUser(user2.getUserName())
                .sentMessageToUser(messageContent)
                .verifySentMessage(messageContent);
        navigationStepdefs.logout();
        startupStepdefs.logInToApplication(user2.getUserName(), user2.getPassword());
        navigationStepdefs.navigateToMessages();
        messagesStepdefs
                .openChatWithUser(user1.getUserName())
                .verifyReceivedMessage(messageContent)
                .sentMessageToUser(messageContent2)
                .verifySentMessage(messageContent2);
        navigationStepdefs.logout();
        startupStepdefs.logInToApplication(user1.getUserName(), user1.getPassword());
        navigationStepdefs.navigateToMessages();
        messagesStepdefs
                .openChatWithUser(user2.getUserName())
                .verifyReceivedMessage(messageContent2);
    }
}
