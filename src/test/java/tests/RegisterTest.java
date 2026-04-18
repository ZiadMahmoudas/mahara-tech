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

    private String generateEgyptianNationalId() {
        String prefix = "305040501";
        int randomLastFive = 10000 + new Random().nextInt(90000);
        return prefix + randomLastFive;
    }
    String nationalId = generateEgyptianNationalId();

    @Test
    public void userCanRegisterWithValidData() {
        driver.get("https://maharatech.gov.eg/login/signup.php");

        RegisterPage registerPage = new RegisterPage(driver);

//        RegisterData user = new RegisterData.Builder()
//                .username("testuser12345")
//                .password("Test@123")
//                .email("kfkfjfk905@gmail.com")
//                .confirmEmail("kfkfjfk905@gmail.com")
//                .firstName("Rama")
//                .lastName("Group")
//                .city("Cairo")
//                .nationalId("30504050102599")
//                .linkedin("https://linkedin.com/in/test")
//                .countryCode("EG")
//                .university("6th of October University")
//                .faculty("Academy of Arts")
//                .build();

        String unique = String.valueOf(System.currentTimeMillis());

        RegisterData user = new RegisterData.Builder()
                .username("testuser" + unique)
                .password("Test@123")
                .email("test" + unique + "@gmail.com")
                .confirmEmail("test" + unique + "@gmail.com")
                .firstName("Rama")
                .lastName("Group")
                .city("Cairo")
                .nationalId(nationalId)
                .countryCode("EG")
                .university("6th of October University")
                .faculty("Academy of Arts")
                .build();

        registerPage.register(user);
        String currentUrl = registerPage.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        Assert.assertTrue(
                registerPage.isRegistrationConfirmationDisplayed(),
                "رسالة تأكيد التسجيل وإرسال الإيميل لم تظهر"
        );
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