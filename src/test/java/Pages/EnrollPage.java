package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnrollPage {

    private WebDriver driver;

    public EnrollPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(linkText = "Job Profiles")
    private WebElement jobProfilesLink;

    @FindBy(xpath = "//h3[contains(text(),'Front-End Developer') or contains(text(),'Front End Developer')]/ancestor::div[contains(@class,'card') or contains(@class,'coursebox')]")
    private WebElement frontEndCard;

    @FindBy(xpath = "//a[contains(., 'Front-End Developer') and not(contains(@class,'nav'))]")
    private WebElement frontEndLink;

    @FindBy(css = "button.btn-enrol, input[value='Enrol me'], a[href*='enrol'], .enrol_me_button, button[data-action='enrolme']")
    private WebElement enrolButton;

    @FindBy(css = "input[type='submit'][value='Enrol me'], button.btn-primary")
    private WebElement confirmEnrolButton;

    @FindBy(css = ".page-header-headings h1, h1.h2, .coursename")
    private WebElement courseTitle;

    @FindBy(css = ".btn-continue, a[href*='course/view'], .continuebutton, .btn-primary[href*='course']")
    private WebElement continueButton;

    @FindBy(css = ".page-header-headings h1, #page-header h1")
    private WebElement pageHeading;



    public void goToJobProfiles() {
        jobProfilesLink.click();
    }

    public void clickFrontEndDeveloper() {
        frontEndLink.click();
    }

    public void clickEnrolMe() {
        enrolButton.click();
    }

    public void confirmEnrolment() {
        confirmEnrolButton.click();
    }

    public String getPageHeading() {
        return pageHeading.getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isOnEnrolmentPage() {
        return driver.getCurrentUrl().contains("enrol/index.php") ||
               driver.getCurrentUrl().contains("course/view.php");
    }

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("login/index.php");
    }
}
