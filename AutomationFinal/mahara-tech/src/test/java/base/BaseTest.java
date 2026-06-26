package base;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        System.out.println("===== SETUP STARTED: " + getClass().getSimpleName() + " =====");

        File localDriver = new File("drivers/chromedriver.exe");
        if (localDriver.exists()) {
            System.setProperty("webdriver.chrome.driver", localDriver.getAbsolutePath());
            System.out.println("===== USING LOCAL CHROMEDRIVER: " + localDriver.getAbsolutePath() + " =====");
        } else {
            System.out.println("===== NO LOCAL CHROMEDRIVER FOUND =====");
            System.out.println("===== Selenium will try Selenium Manager now. If it hangs here, add chromedriver.exe inside /drivers =====");
        }

        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");
        options.addArguments("user-data-dir=D:/selenium-profiles/mahara-google");
        options.addArguments("profile-directory=Default");

        System.out.println("===== BEFORE CHROME DRIVER: " + getClass().getSimpleName() + " =====");
        driver = new ChromeDriver(options);
        System.out.println("===== AFTER CHROME DRIVER: " + getClass().getSimpleName() + " =====");

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        System.out.println("===== SETUP FINISHED: " + getClass().getSimpleName() + " =====");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        System.out.println("===== TEARDOWN STARTED: " + getClass().getSimpleName() + " =====");

        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }

        System.out.println("===== TEARDOWN FINISHED: " + getClass().getSimpleName() + " =====");
    }
}
