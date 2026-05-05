package Pages;

import DATA.LoginData;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Collection;

public class LoginPage {
    private WebDriver driver;
public LoginPage(WebDriver driver){
    this.driver = driver;
    PageFactory.initElements(this.driver, this);
}
//    Attribute fields origin
    @FindBy(id="username")
    private WebElement usernameField;
    @FindBy(id="password")
    private  WebElement passwordField;
    @FindBy(id = "loginbtn")
    WebElement loginButton;
    // //    Method to interact with the form fields original
    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }
    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    // Normal login click
    public void clickLogin() {
        loginButton.click();
    }
    public void clickCreateAccount() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", loginButton);
    }
    public void login(LoginData data){
    enterUsername(data.getUsername());
    enterPassword(data.getPassword());
    clickLogin();
    }

}
