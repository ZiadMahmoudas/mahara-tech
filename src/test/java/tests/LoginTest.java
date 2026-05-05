package tests;

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
    public void userCannotLoginWithInvalidCredentials(){
        driver.get("https://maharatech.gov.eg/login/index.php");
        LoginPage loginPage = new LoginPage(driver);
        LoginData user = new LoginData.Builder().email("yahia-elsherbini").password("x}wH-jM@697%Wq)1").build();
        loginPage.login(user);
    }
    @Test
    public void userCannotLoginWithEmptyFields() {
        driver.get("https://maharatech.gov.eg/login/index.php");

        LoginPage loginPage = new LoginPage(driver);

        LoginData user = new LoginData.Builder()
                .email("")
                .password("")
                .build();

        loginPage.login(user);
    }

    }
