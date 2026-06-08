package tests;

import DATA.LoginData;
import Pages.LoginPage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class EnrollTest extends BaseTest {

    @Test(description = "Logged-in user can reach the Enrolment page for Front-End Developer")
    public void loggedInUserCanReachEnrolmentPage() throws InterruptedException {

        driver.get("https://maharatech.gov.eg/login/index.php");
        LoginPage loginPage = new LoginPage(driver);

        LoginData user = new LoginData.Builder()
                .username("yahia-elsherbini")
                .password("x}wH-jM@697%Wq)1")
                .build();

        loginPage.login(user);
        Thread.sleep(3000);

        Assert.assertFalse(
                driver.getCurrentUrl().contains("login/index.php"),
                "Login failed! Still on login page."
        );

        driver.get("https://maharatech.gov.eg/mod/page/view.php?id=16670");
        Thread.sleep(2000);

        List<WebElement> links = driver.findElements(
                By.xpath("//a[contains(text(),'Front-End Developer')]")
        );

        Assert.assertFalse(links.isEmpty(), "Front-End Developer link not found on Job Profiles page!");
        links.get(0).click();
        Thread.sleep(2000);

        String courseUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                courseUrl.contains("course") || courseUrl.contains("enrol"),
                "Did not navigate to Front-End Developer course page. URL: " + courseUrl
        );

        System.out.println("✅ Logged-in user reached course page: " + courseUrl);
    }

    @Test(description = "Logged-in user can successfully enrol in Front-End Developer course")
    public void loggedInUserCanEnrol() throws InterruptedException {

        driver.get("https://maharatech.gov.eg/login/index.php");
        LoginPage loginPage = new LoginPage(driver);

        LoginData user = new LoginData.Builder()
                .username("yahia-elsherbini")
                .password("x}wH-jM@697%Wq)1")
                .build();

        loginPage.login(user);
        Thread.sleep(3000);

        Assert.assertFalse(
                driver.getCurrentUrl().contains("login/index.php"),
                "Login failed!"
        );

        driver.get("https://maharatech.gov.eg/enrol/index.php?id=997");
        Thread.sleep(2000);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(
                currentUrl.contains("login/index.php"),
                "User was redirected to login page instead of enrolment page!"
        );

        Assert.assertTrue(
                currentUrl.contains("enrol/index.php"),
                "User did not reach enrolment page. URL: " + currentUrl
        );

        List<WebElement> enrolButtons = driver.findElements(
                By.cssSelector("input[type='submit'], .btn-enrol, button.btn-primary")
        );

        boolean canEnrol = !enrolButtons.isEmpty();
        Assert.assertTrue(canEnrol, "Enrol button not found for logged-in user!");

        System.out.println("✅ Logged-in user can enrol. Enrol button found on: " + currentUrl);
    }

    @Test(description = "Guest user is redirected to login page when trying to enrol")
    public void guestUserIsRedirectedToLogin() throws InterruptedException {

        driver.get("https://maharatech.gov.eg/enrol/index.php?id=997");
        Thread.sleep(3000);

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(
                currentUrl.contains("login/index.php") || currentUrl.contains("login"),
                "Guest was NOT redirected to login page! Current URL: " + currentUrl
        );

        System.out.println("✅ Guest user correctly redirected to login. URL: " + currentUrl);
    }


    @Test(description = "Guest can view course info but Enrol button is not accessible")
    public void guestCannotEnrolWithoutLogin() throws InterruptedException {

        driver.get("https://maharatech.gov.eg/mod/page/view.php?id=16670");
        Thread.sleep(2000);

        List<WebElement> links = driver.findElements(
                By.xpath("//a[contains(text(),'Front-End Developer')]")
        );

        if (!links.isEmpty()) {
            links.get(0).click();
            Thread.sleep(2000);
        }

        driver.get("https://maharatech.gov.eg/enrol/index.php?id=997");
        Thread.sleep(3000);

        String currentUrl = driver.getCurrentUrl();

        boolean redirectedToLogin = currentUrl.contains("login");

        Assert.assertTrue(
                redirectedToLogin,
                "Guest should be redirected to login when accessing enrol page! URL: " + currentUrl
        );

        System.out.println("✅ Guest correctly blocked from enrolment. Redirected to: " + currentUrl);
    }
}
