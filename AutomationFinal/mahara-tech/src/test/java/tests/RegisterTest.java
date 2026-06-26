package tests;

import DATA.RegisterData;
import Pages.RegisterPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class RegisterTest extends BaseTest {

    private String generateRandomNationalId() {
        Random random = new Random();
        StringBuilder nationalId = new StringBuilder("3050");

        for (int i = 0; i < 10; i++) {
            nationalId.append(random.nextInt(10));
        }

        return nationalId.toString();
    }

    private RegisterData generateRandomUser() {
        String unique = String.valueOf(System.currentTimeMillis());

        return new RegisterData.Builder()
                .username("testuser" + unique)
                .password("Test@123")
                .email("test" + unique + "@gmail.com")
                .confirmEmail("test" + unique + "@gmail.com")
                .firstName("Rama")
                .lastName("Group")
                .city("Cairo")
                .nationalId(generateRandomNationalId())
                .linkedin("https://linkedin.com/in/test" + unique)
                .countryCode("EG")
                .university("6th of October University")
                .faculty("Academy of Arts")
                .build();
    }

    @Test
    public void userCanRegisterWithValidData() {
        driver.get("https://maharatech.gov.eg/login/signup.php");

        RegisterPage registerPage = new RegisterPage(driver);
        RegisterData user = generateRandomUser();

        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("National ID: " + user.getNationalId());

        registerPage.register(user);

        Assert.assertTrue(
                registerPage.isRegistrationResultDisplayed(),
                "Registration result/confirmation message was not displayed. Current URL: " + registerPage.getCurrentUrl()
        );
    }

    @Test
    public void showsErrorsWhenFieldsAreEmpty() {
        driver.get("https://maharatech.gov.eg/login/signup.php");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickCreateAccount();

        Assert.assertTrue(
                registerPage.getUsernameErrorMessage().contains("Missing username"),
                "Username error message was not displayed correctly"
        );

        Assert.assertTrue(
                registerPage.getPasswordErrorMessage().contains("Missing password"),
                "Password error message was not displayed correctly"
        );

        Assert.assertTrue(
                registerPage.getEmailErrorMessage().contains("Missing email address"),
                "Email error message was not displayed correctly"
        );

        Assert.assertTrue(
                registerPage.getConfirmEmailErrorMessage().contains("Missing email address"),
                "Confirm email error message was not displayed correctly"
        );

        Assert.assertTrue(
                registerPage.getFirstNameErrorMessage().contains("Missing given name"),
                "First name error message was not displayed correctly"
        );

        Assert.assertTrue(
                registerPage.getLastNameErrorMessage().contains("Missing last name"),
                "Last name error message was not displayed correctly"
        );
    }
}
