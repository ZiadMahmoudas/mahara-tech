package Pages;

import DATA.RegisterData;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RegisterPage {
    private WebDriver driver;
    public  RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
//    Attribute fields origin
    @FindBy(id="id_username")
    private WebElement usernameField;
    @FindBy(id = "id_password")
    private WebElement passwordField;
    @FindBy(name = "email")
    private WebElement emailField;
    @FindBy(id = "id_email2")
    private WebElement confirmEmailField;
    @FindBy(id="id_firstname")
    private WebElement firstNameField;
    @FindBy(id="id_lastname")
    private WebElement lastNameField;
    @FindBy(id = "id_city")
    private List<WebElement> cityField;
    @FindBy(id="id_profile_field_ID")
    private WebElement profileFieldID;
    @FindBy(id = "id_profile_field_Linkedin")
    private List<WebElement> LinkedinField;
    @FindBy(id = "id_country")
    private WebElement countryDropdown;
    @FindBy(id = "id_profile_field_uni")
    private WebElement universityField;
    @FindBy(id="id_profile_field_fac")
    private WebElement facultyField;
    @FindBy(id = "id_submitbutton")
    private WebElement createAccountButton;
    @FindBy(id = "id_cancel")
    private WebElement cancelButton;
//    Method to interact with the form fields original
    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }
    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }
    public void enterConfirmEmail(String confirmEmail) {
        confirmEmailField.clear();
        confirmEmailField.sendKeys(confirmEmail);
    }
    public void enterFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }
    public void enterOptionalCity(String city) {
        if (!cityField.isEmpty()) {
            cityField.get(0).clear();
            cityField.get(0).sendKeys(city);
        }
    }
    public void enterNationalId(String nationalId) {
        profileFieldID.clear();
        profileFieldID.sendKeys(nationalId);
    }
    public void enterLinkedIn(String linkedin) {
        if (!LinkedinField.isEmpty()) {
            LinkedinField.get(0).clear();
            LinkedinField.get(0).sendKeys(linkedin);
        }
    }
    public void selectCountryByValue(String country) {
        Select select = new Select(countryDropdown);
        select.selectByValue(country);
    }
    public void selectUniversity(String university) {
        Select select = new Select(universityField);
        select.selectByVisibleText(university);
    }
    public void selectFaculty(String faculty) {
        Select select = new Select(facultyField);
        select.selectByVisibleText(faculty);
    }
    public void clickCreateAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(createAccountButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", button
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", button
        );
    }
    public void clickCancel() {
        cancelButton.click();
    }
    /*  Error-handling field */
    @FindBy(id = "id_error_username")
    private WebElement usernameErrorMessage;

    @FindBy(id = "id_error_password")
    private WebElement passwordErrorMessage;

    @FindBy(id = "id_error_email")
    private WebElement emailErrorMessage;

    @FindBy(id = "id_error_email2")
    private WebElement confirmEmailErrorMessage;

    @FindBy(id = "id_error_firstname")
    private WebElement firstNameErrorMessage;

    @FindBy(id = "id_error_lastname")
    private WebElement lastNameErrorMessage;

    public void register(RegisterData data) {
        enterUsername(data.getUsername());
        enterPassword(data.getPassword());
        enterEmail(data.getEmail());
        enterConfirmEmail(data.getConfirmEmail());
        enterFirstName(data.getFirstName());
        enterLastName(data.getLastName());
        enterOptionalCity(data.getCity());
        enterNationalId(data.getNationalId());
        enterLinkedIn(data.getLinkedin());
        selectCountryByValue(data.getCountryCode());
        selectUniversity(data.getUniversity());
        selectFaculty(data.getFaculty());
        clickCreateAccount();
    }
    public String getUsernameErrorMessage() {
        return usernameErrorMessage.getText().trim();
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage.getText().trim();
    }

    public String getEmailErrorMessage() {
        return emailErrorMessage.getText().trim();
    }

    public String getConfirmEmailErrorMessage() {
        return confirmEmailErrorMessage.getText().trim();
    }

    public String getFirstNameErrorMessage() {
        return firstNameErrorMessage.getText().trim();
    }

    public String getLastNameErrorMessage() {
        return lastNameErrorMessage.getText().trim();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
