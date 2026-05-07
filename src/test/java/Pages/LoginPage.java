package Pages;

import DATA.LoginData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(id="username")
    private WebElement usernameField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id = "loginbtn")
    private WebElement loginButton;

    // locator لرسالة الخطأ (تأكد من الـ id أو الـ class من الموقع)
    @FindBy(css = ".alert-danger, #loginerrormessage")
    private WebElement errorMsg;

    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMsg.getText();
    }

    public void login(LoginData data){
        enterUsername(data.getUsername()); // تأكد إن الـ Getter اسمه كدة في LoginData
        enterPassword(data.getPassword());
        clickLogin();
    }
}