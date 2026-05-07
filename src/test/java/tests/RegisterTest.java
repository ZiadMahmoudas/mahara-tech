package tests;

import DATA.RegisterData;
import Pages.RegisterPage;
import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Random;

@Listeners(TestListener.class)
public class RegisterTest extends BaseTest {

    private String generateRandomNationalId() {
        Random random = new Random();

        // لازم يبدأ بـ 3050
        String prefix = "3050";

        // نكمّل باقي الـ 14 رقم
        // 3050 = 4 digits
        // محتاجين 10 digits كمان
        StringBuilder nationalId = new StringBuilder(prefix);

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
    public void userCanRegisterWithValidData() throws InterruptedException {
        driver.get("https://maharatech.gov.eg/login/signup.php");

        RegisterPage registerPage = new RegisterPage(driver);

        RegisterData user = generateRandomUser();

        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("National ID: " + user.getNationalId());

        registerPage.register(user);

        Thread.sleep(5000);

        Assert.assertTrue(registerPage.getCurrentUrl().contains("signup"));
    }

    @Test
    public void showsErrorsWhenFieldsAreEmpty() throws InterruptedException {
        driver.get("https://maharatech.gov.eg/login/signup.php");

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.clickCreateAccount();

        Thread.sleep(5000);

        Assert.assertEquals(registerPage.getUsernameErrorMessage(), "- Missing username");
        Assert.assertEquals(registerPage.getPasswordErrorMessage(), "- Missing password");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "- Missing email address");
        Assert.assertEquals(registerPage.getConfirmEmailErrorMessage(), "- Missing email address");
        Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "- Missing given name");
        Assert.assertEquals(registerPage.getLastNameErrorMessage(), "- Missing last name");
    }
}