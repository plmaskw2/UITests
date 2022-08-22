package tasks;

import data_provider.DataProvider;
import framework.model.Forum;
import framework.model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ForumInteractionsTest extends BaseTest {

    User user;

    @BeforeClass
    public void setup() {
        initializeStepdefs();
    }

    @BeforeMethod
    public void prerequisites() {
        user = DataProvider.getNewUser();

        startupStepdefs
                .openApp()
                .navigateToRegisterForm()
                .registerUser(user)
                .verifySignupSuccessful();
    }

    @Test
    public void forumInteractionsTest() {
        Forum forum = DataProvider.getNewForum();
        String comment = DataProvider.getMessageContent();

        startupStepdefs
                .openApp()
                .logInToApplication(user.getUserName(), user.getPassword());
        navigationStepdefs.navigateToForums();
        forumStepdefs
                .createNewTopic(forum)
                .verifyNewForumCreated()
                .mavigateToViewForumsFromCreateForumView()
                .openForumBySubject(forum.getTopicSubject())
                .addReply(comment)
                .verifyCommentIsVisible(comment);
    }
}
