package framework.pages;

import framework.utils.WebListUtils;
import framework.utils.Timeouts;
import framework.utils.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class StartupPage extends WebPage {

    @FindBy(xpath = "//div[@class='container']")
    private WebElement pageContainer;

    @FindBy(xpath = ".//a[@href='signup.php']")
    private WebElement signupButton;

    @FindBy(xpath = ".//input[@name='login-submit']")
    private WebElement loginButton;

    @FindBy(xpath = ".//input[contains(@class, 'form-control form-control-lg')]")
    private List<WebElement> inputList;

    public StartupPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(pageContainer),this);
    }

    public StartupPage isAt() {
        webWaitUtils.waitForVisible(signupButton, Timeouts.MEDIUM);
        return this;
    }

    public StartupPage enterUsername(String username) {
        enterText(WebListUtils.getElementFromListByAttributeEquals(inputList, "id", "name"), username);
        return this;
    }

    public StartupPage enterPassword(String password) {
        enterText(WebListUtils.getElementFromListByAttributeEquals(inputList, "id", "password"), password);
        return this;
    }

    public DashboardPage clickLoginButton() {
        clickElement(loginButton);
        return new DashboardPage(driver).isAt();
    }

    public StartupPage clickSignupButton() {
        clickElement(signupButton);
        return this;
    }
}
