package framework.pages;

import framework.utils.Timeouts;
import framework.utils.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class StartupPage extends WebPage {

    @FindBy(xpath = "//div[@class='container']")
    private WebElement pageContainer;

    @FindBy(xpath = "//a[@href='signup.php']")
    private WebElement signupButton;



    public StartupPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(pageContainer),this);
    }

    public StartupPage isAt() {
        webWaitUtils.waitForVisible(signupButton, Timeouts.MEDIUM);
        return this;
    }

}
