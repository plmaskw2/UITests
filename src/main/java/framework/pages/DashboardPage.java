package framework.pages;

import framework.utils.Timeouts;
import framework.utils.WebPage;
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

    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(pageContainer),this);
    }

    public DashboardPage isAt() {
        webWaitUtils.waitForVisible(contentLocator, Timeouts.LOW);
        return this;
    }
}
