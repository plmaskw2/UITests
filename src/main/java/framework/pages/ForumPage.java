package framework.pages;

import framework.utils.Timeouts;
import framework.utils.WebPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class ForumPage extends WebPage {

    @FindBy(xpath = "//div[@class='container']")
    private WebElement pageContainer;

    @FindBy(xpath = ".//input[@value='Submit reply']")
    private WebElement submitReplyButton;

    @FindBy(xpath = ".//textarea[@name='reply-content']")
    private WebElement replyContentField;

    @FindBy(xpath = ".//div[@class='row']//p")
    private List<WebElement> commentsList;

    public ForumPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(pageContainer),this);
    }

    public ForumPage isAt() {
        webWaitUtils.waitForVisible(submitReplyButton, Timeouts.MEDIUM);
        return this;
    }

    public ForumPage enterReply(String text) {
        enterText(replyContentField, text);
        return this;
    }

    public ForumPage clickSubmitReplyButton() {
        clickElement(submitReplyButton);
        return new ForumPage(driver).isAt();
    }

    public ForumPage verifyCommentIsVisible(String text) {
        Assertions
                .assertThat(commentsList.stream().anyMatch(webElement -> webElement.getText().equals(text)))
                .as("User has logged in successfully")
                .isTrue();
        return this;
    }
}
