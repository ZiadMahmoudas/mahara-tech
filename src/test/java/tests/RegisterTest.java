package tests;

import DATA.RegisterData;
import Pages.RegisterPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test
    public void userCanRegisterWithValidData() {
        driver.get("https://maharatech.gov.eg/login/signup.php");

        RegisterPage registerPage = new RegisterPage(driver);

        RegisterData user = new RegisterData.Builder()
                .username("testuser123")
                .password("Test@123")
                .email("test@gmail.com")
                .confirmEmail("test@gmail.com")
                .firstName("Rama")
                .lastName("Group")
                .city("Cairo")
                .nationalId("12345678901234")
                .linkedin("https://linkedin.com/in/test")
                .countryCode("EG")
                .university("6th of October University")
                .faculty("Academy of Arts")
                .build();

        registerPage.register(user);
        Assert.assertTrue(registerPage.getCurrentUrl().contains("signup") == false);

    }
    @Test
    public void showsErrorsWhenFieldsAreEmpty() {
        driver.get("https://maharatech.gov.eg/login/signup.php");
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.clickCreateAccount();

        Assert.assertEquals(registerPage.getUsernameErrorMessage(), "- Missing username");
        Assert.assertEquals(registerPage.getPasswordErrorMessage(), "- Missing password");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "- Missing email address");
        Assert.assertEquals(registerPage.getConfirmEmailErrorMessage(), "- Missing email address");
        Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "- Missing given name");
        Assert.assertEquals(registerPage.getLastNameErrorMessage(), "- Missing last name");
    }
}