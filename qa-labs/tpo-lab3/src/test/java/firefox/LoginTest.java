package firefox;

import org.example.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void login() throws InterruptedException {
        Utils.login(driver);
        Thread.sleep(1000);
        String currentUrl = driver.getCurrentUrl();

        assertEquals("https://www.fandom.com/", currentUrl);
    }

    @Test
    public void loginWrongCredentials() throws InterruptedException {
        driver.get("https://www.fandom.com/");
        driver.findElement(By.xpath("//*[@id=\"global-sign-in-link\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"identifier\"]")).sendKeys("itmo343057");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("1july2024yeaar");
        driver.findElement(By.xpath("/html/body/div/main/div/div[2]/div/form[1]/section/div[3]/button")).click();

        String currentUrl = driver.getCurrentUrl();
        assertNotEquals("https://www.fandom.com/", currentUrl);
    }

    @Test
    public void logout() {
        Utils.login(driver);
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div/div[2]/div[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"global-navigation-logout-form\"]/button")).click();
        boolean registrationButtonsVisible = driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]")).isDisplayed();
        assertTrue(registrationButtonsVisible);
    }
}
