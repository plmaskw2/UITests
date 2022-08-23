package stepdefs;

import framework.model.Forum;
import framework.pages.CreateAForumPage;
import framework.pages.ForumPage;
import framework.pages.ForumsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class ForumStepdefs extends BaseStepdefs {

    public ForumStepdefs(WebDriver driver) {
        super(driver);
    }

    @Step
    public ForumStepdefs createNewTopic(Forum forum) {
        new ForumsPage(driver)
                .clickCreateForumButton()
                .enterTopicSubject(forum.getTopicSubject())
                .selectCategory(forum.getCategory())
                .enterForumQuestion(forum.getQuestion())
                .clickCreateForumButton();
        return this;
    }

    @Step
    public ForumStepdefs verifyNewForumCreated() {
        new CreateAForumPage(driver).verifyNewForumCreated();
        return this;
    }

    @Step
    public ForumStepdefs mavigateToViewForumsFromCreateForumView() {
        new CreateAForumPage(driver).navigateToViewForums();
        return this;
    }

    @Step
    public ForumStepdefs openForumBySubject(String subject) {
        new ForumsPage(driver).navigateToForumBySubject(subject);
        return this;
    }

    @Step
    public ForumStepdefs addReply(String subject) {
        new ForumPage(driver)
                .enterReply(subject)
                .clickSubmitReplyButton();
        return this;
    }

    @Step
    public ForumStepdefs removeReplyByText(String comment) {
        new ForumPage(driver).clickTrashButtonByComment(comment);
        return this;
    }

    @Step
    public ForumStepdefs verifyCommentIsVisible(String text) {
        new ForumPage(driver).verifyCommentIsVisible(text);
        return this;
    }

    @Step
    public ForumStepdefs verifyCommentIsNotVisible(String text) {
        new ForumPage(driver).verifyCommentIsNotVisible(text);
        return this;
    }
}
