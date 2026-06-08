package tests;

import DATA.LoginData;
import Pages.LoginPage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class LoginTest extends BaseTest {

    @Test
    public void userCannotLoginWithInvalidCredentials() {
        driver.get("https://maharatech.gov.eg/login/index.php");
        LoginPage loginPage = new LoginPage(driver);

        LoginData user = new LoginData.Builder()
                .username("yahia-elsherbini")
                .password("wrong-password")
                .build();

        loginPage.login(user);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("login/index.php"),
                "User should stay on login page when credentials are invalid. Current URL: " + driver.getCurrentUrl()
        );
    }

    @Test
    public void userCannotLoginWithEmptyFields() {
        driver.get("https://maharatech.gov.eg/login/index.php");
        LoginPage loginPage = new LoginPage(driver);

        LoginData user = new LoginData.Builder()
                .username("")
                .password("")
                .build();

        loginPage.login(user);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("login/index.php"),
                "Expected URL to contain login/index.php but found: " + driver.getCurrentUrl()
        );
    }

    @Test
    public void userCanLoginWithGoogleAccount() {
        driver.get("https://maharatech.gov.eg/login/index.php");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div.potentialidp a[title='Google']")
        )).click();

        try {
            List<WebElement> accounts = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath("//div[@data-email] | //div[contains(@data-identifier,'@')]")
                    )
            );

            if (!accounts.isEmpty()) {
                accounts.get(0).click();
            }

        } catch (Exception e) {
            System.out.println("Google account chooser did not appear, maybe account is already selected.");
        }

        try {
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(
                            "//span[text()='Continue']/ancestor::button" +
                                    " | //span[text()='Allow']/ancestor::button" +
                                    " | //span[text()='متابعة']/ancestor::button" +
                                    " | //span[text()='السماح']/ancestor::button"
                    )
            ));

            continueButton.click();

        } catch (Exception e) {
            System.out.println("Google consent button did not appear, maybe already approved before.");
        }

        wait.until(ExpectedConditions.urlContains("maharatech.gov.eg"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("maharatech.gov.eg"),
                "Google login did not redirect back to MaharaTech. Current URL: " + driver.getCurrentUrl()
        );
    }
}