package stepdefs;

import framework.model.Forum;
import framework.pages.CreateAForumPage;
import framework.pages.DashboardPage;
import framework.pages.ForumPage;
import framework.pages.ForumsPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

public class ForumStepdefs extends BaseStepdefs {

    public ForumStepdefs(WebDriver driver) {
        super(driver);
    }

    public ForumStepdefs createNewTopic(Forum forum) {
        new ForumsPage(driver)
                .clickCreateForumButton()
                .enterTopicSubject(forum.getTopicSubject())
                .selectCategory(forum.getCategory())
                .enterForumQuestion(forum.getQuestion())
                .clickCreateForumButton();
        return this;
    }

    public ForumStepdefs verifyNewForumCreated() {
        new CreateAForumPage(driver).verifyNewForumCreated();
        return this;
    }

    public ForumStepdefs mavigateToViewForumsFromCreateForumView() {
        new CreateAForumPage(driver).navigateToViewForums();
        return this;
    }

    public ForumStepdefs openForumBySubject(String subject) {
        new ForumsPage(driver).navigateToForumBySubject(subject);
        return this;
    }

    public ForumStepdefs addReply(String subject) {
        new ForumPage(driver)
                .enterReply(subject)
                .clickSubmitReplyButton();
        return this;
    }

    public ForumStepdefs verifyCommentIsVisible(String text) {
        new ForumPage(driver).verifyCommentIsVisible(text);
        return this;
    }
}
