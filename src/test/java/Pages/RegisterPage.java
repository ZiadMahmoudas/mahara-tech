package Pages;

import DATA.RegisterData;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
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
    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "id_username")
    private WebElement usernameField;

    @FindBy(id = "id_password")
    private WebElement passwordField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(id = "id_email2")
    private WebElement confirmEmailField;

    @FindBy(id = "id_firstname")
    private WebElement firstNameField;

    @FindBy(id = "id_lastname")
    private WebElement lastNameField;

    @FindBy(id = "id_city")
    private List<WebElement> cityField;

    @FindBy(id = "id_profile_field_ID")
    private WebElement profileFieldID;

    @FindBy(id = "id_profile_field_Linkedin")
    private List<WebElement> linkedinField;

    @FindBy(id = "id_country")
    private WebElement countryDropdown;

    @FindBy(id = "id_profile_field_uni")
    private WebElement universityField;

    @FindBy(id = "id_profile_field_fac")
    private WebElement facultyField;

    @FindBy(id = "id_submitbutton")
    private WebElement createAccountButton;

    @FindBy(id = "id_cancel")
    private WebElement cancelButton;

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

    private void type(WebElement element, String value) {
        WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
        visibleElement.clear();
        if (value != null) {
            visibleElement.sendKeys(value);
        }
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void enterConfirmEmail(String confirmEmail) {
        type(confirmEmailField, confirmEmail);
    }

    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
    }

    public void enterOptionalCity(String city) {
        if (!cityField.isEmpty() && city != null) {
            type(cityField.get(0), city);
        }
    }

    public void enterNationalId(String nationalId) {
        type(profileFieldID, nationalId);
    }

    public void enterLinkedIn(String linkedin) {
        if (!linkedinField.isEmpty() && linkedin != null) {
            type(linkedinField.get(0), linkedin);
        }
    }

    public void selectCountryByValue(String countryCode) {
        wait.until(ExpectedConditions.elementToBeClickable(countryDropdown));
        Select select = new Select(countryDropdown);
        select.selectByValue(countryCode);
    }

    public void selectUniversity(String university) {
        wait.until(ExpectedConditions.elementToBeClickable(universityField));
        Select select = new Select(universityField);

        try {
            select.selectByVisibleText(university);
        } catch (Exception e) {
            selectFirstRealOption(select, "university");
        }
    }

    public void selectFaculty(String faculty) {
        wait.until(driver -> new Select(facultyField).getOptions().size() > 1);
        Select select = new Select(facultyField);

        try {
            select.selectByVisibleText(faculty);
        } catch (Exception e) {
            selectFirstRealOption(select, "faculty");
        }
    }

    private void selectFirstRealOption(Select select, String fieldName) {
        List<WebElement> options = select.getOptions();

        for (int i = 1; i < options.size(); i++) {
            String text = options.get(i).getText().trim();
            String value = options.get(i).getAttribute("value");

            if (!text.isEmpty() && value != null && !value.isBlank()) {
                select.selectByIndex(i);
                System.out.println("Selected first available " + fieldName + ": " + text);
                return;
            }
        }

        throw new RuntimeException("No selectable option found for: " + fieldName);
    }

    public void clickCreateAccount() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(createAccountButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", button
        );

        try {
            button.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    public void clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
    }

    public void register(RegisterData data) {
        System.out.println("1 - username");
        enterUsername(data.getUsername());

        System.out.println("2 - password");
        enterPassword(data.getPassword());

        System.out.println("3 - email");
        enterEmail(data.getEmail());

        System.out.println("4 - confirm email");
        enterConfirmEmail(data.getConfirmEmail());

        System.out.println("5 - first name");
        enterFirstName(data.getFirstName());

        System.out.println("6 - last name");
        enterLastName(data.getLastName());

        System.out.println("7 - city");
        enterOptionalCity(data.getCity());

        System.out.println("8 - national id");
        enterNationalId(data.getNationalId());

        System.out.println("9 - linkedin");
        enterLinkedIn(data.getLinkedin());

        System.out.println("10 - country");
        selectCountryByValue(data.getCountryCode());

        System.out.println("11 - university");
        selectUniversity(data.getUniversity());

        System.out.println("12 - faculty");
        selectFaculty(data.getFaculty());

        System.out.println("13 - submit");
        clickCreateAccount();
    }

    public String getUsernameErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(usernameErrorMessage)).getText().trim();
    }

    public String getPasswordErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(passwordErrorMessage)).getText().trim();
    }

    public String getEmailErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(emailErrorMessage)).getText().trim();
    }

    public String getConfirmEmailErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(confirmEmailErrorMessage)).getText().trim();
    }

    public String getFirstNameErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(firstNameErrorMessage)).getText().trim();
    }

    public String getLastNameErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(lastNameErrorMessage)).getText().trim();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isRegistrationResultDisplayed() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(20)).until(d -> {
                String page = d.getPageSource().toLowerCase();
                String url = d.getCurrentUrl().toLowerCase();
                return !url.contains("signup.php")
                        || page.contains("confirm")
                        || page.contains("confirmation")
                        || page.contains("check your email")
                        || page.contains("complete your registration")
                        || page.contains("تأكيد")
                        || page.contains("تفعيل");
            });
        } catch (TimeoutException e) {
            return false;
        }
    }
}
