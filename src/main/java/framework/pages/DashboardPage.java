package framework.pages;

import framework.utils.Timeouts;
import framework.utils.WebPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class DashboardPage extends WebPage {

    @FindBy(xpath = "//div[@class='container-fluid']")
    private WebElement pageContainer;

    @FindBy(xpath = "//div[@id='content'][@style='display: block;']")
    private WebElement contentLocator;

    @FindBy(xpath = "//h2[.='DASHBOARD']")
    private WebElement dashboard;

    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(pageContainer),this);
    }

    public DashboardPage isAt() {
        webWaitUtils.waitForVisible(contentLocator, Timeouts.LOW);
        return this;
    }

    public DashboardPage verifyLoggedInSuccessful() {
        Assertions
                .assertThat(isDisplayed(dashboard))
                .as("User has logged in successfully")
                .isTrue();
        return this;
    }
}
