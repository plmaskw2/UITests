package stepdefs;

import framework.pages.MessagesChatPage;
import framework.pages.MessagesHomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class MessagesStepdefs extends BaseStepdefs {

    public MessagesStepdefs(WebDriver driver) {
        super(driver);
    }

    @Step
    public MessagesStepdefs openChatWithUser(String username) {
        new MessagesHomePage(driver).openUserChat(username);
        return this;
    }

    @Step
    public MessagesStepdefs sentMessageToUser(String text) {
        new MessagesChatPage(driver).enterMessage(text);
        return this;
    }

    @Step
    public MessagesStepdefs verifySentMessage(String text) {
        new MessagesChatPage(driver).verifySentMessageIsVisible(text);
        return this;
    }

    @Step
    public MessagesStepdefs verifyReceivedMessage(String text) {
        new MessagesChatPage(driver).verifyReceivedMessageIsVisible(text);
        return this;
    }
}
