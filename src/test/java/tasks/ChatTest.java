package tasks;

import data_provider.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChatTest extends BaseTest {

    @BeforeClass
    public void setup(){
        initializeStepdefs();
    }

    @Test
    public void ChatTest() {
        String messageContent = DataProvider.getMessageContent();
        String messageContent2 = DataProvider.getMessageContent();

        startupStepdefs
                .openApp()
                .logInToApplication("Dawid123", "dawid123");
        navigationStepdefs
                .navigateToMessages();
        messagesStepdefs
                .openChatWithUser("Dawid321")
                .sentMessageToUser(messageContent)
                .verifySentMessage(messageContent);
        navigationStepdefs.logout();
        startupStepdefs.logInToApplication("Dawid321", "dawid321");
        navigationStepdefs
                .navigateToMessages();
        messagesStepdefs
                .openChatWithUser("Dawid123")
                .verifyReceivedMessage(messageContent)
                .sentMessageToUser(messageContent2)
                .verifySentMessage(messageContent2);
        navigationStepdefs.logout();
        startupStepdefs.logInToApplication("Dawid123", "dawid123");
        navigationStepdefs
                .navigateToMessages();
        messagesStepdefs
                .openChatWithUser("Dawid321")
                .verifyReceivedMessage(messageContent2);
    }
}
