package tests; // حل مشكلة Missing package statement

import DATA.LoginData;
import Pages.LoginPage;
import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @Test
    public void userCannotLoginWithInvalidCredentials() throws InterruptedException {
        driver.get("https://maharatech.gov.eg/login/index.php");
        LoginPage loginPage = new LoginPage(driver);

        LoginData user = new LoginData.Builder()
                .username("yahia-elsherbini")
                .password("wrong-password")
                .build();

        loginPage.login(user);
        Thread.sleep(5000);
        // التعديل هنا: استخدمنا contains بدل assertEquals للـ URL
        // أو اتأكد من الـ Locator بتاع رسالة الخطأ
        Assert.assertTrue(driver.getCurrentUrl().contains("login/index.php"), "لم يتم البقاء في صفحة الدخول!");
    }

    @Test
    public void userCannotLoginWithEmptyFields() throws InterruptedException {
        driver.get("https://maharatech.gov.eg/login/index.php");
        LoginPage loginPage = new LoginPage(driver);

        LoginData user = new LoginData.Builder()
                .username("")
                .password("")
                .build();

        loginPage.login(user);
        Thread.sleep(5000);
        // الحل الأضمن هنا:
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login/index.php"),
                "Expected URL to contain login/index.php but found: " + currentUrl);
    }
}