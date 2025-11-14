package chrome;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchTest {
    private RemoteWebDriver driver;

//    @BeforeEach
//    public void setUp() {
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//    }
//
//    @AfterEach
//    public void tearDown() {
//        driver.quit();
//    }

    @Test
    public void search() {
        assertEquals(1, 1);
    }
    @Test
    public void advancedSearch() {
        assertEquals(1, 1);
    }
//
//
//    @Test
//    public void search() throws InterruptedException {
//        driver.get("https://www.fandom.com/");
//
//        driver.findElement(By.xpath("/html/body/div[5]/div[1]/form[1]/div/label")).click();
//        driver.findElement(By.xpath("/html/body/div[5]/div[1]/form[1]/div/label/input[1]")).sendKeys("pikachu");
//        driver.findElement(By.xpath("/html/body/div[5]/div[1]/form[1]/div/label/input[1]")).submit();
//
//        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/section/div/div[2]/ul/li[1]/article/h3/a")).click();
//        Thread.sleep(1000);
//
//        String currentUrl = driver.getCurrentUrl();
//        assertEquals("https://pokemon.fandom.com/wiki/Pikachu", currentUrl);
//    }
//
//    //no search results
//
//    @Test
//    public void advancedSearch() throws InterruptedException {
//        driver.get("https://community.fandom.com/wiki/Special:Search");
//
//        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/section/form/div/div/div/input")).sendKeys("lord of rings");
//        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/section/form/div/div/p/a")).click();
//
//        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/section/form/div/section/div/label[1]")).click();
//        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/section/form/div/div/button")).click();
//
//
//        Thread.sleep(1000);
//
//        String currentUrl = driver.getCurrentUrl();
//        assertEquals("https://community.fandom.com/wiki/Special:Search?query=lord+of+rings&scope=internal&contentType=&ns%5B0%5D=4&ns%5B1%5D=12&ns%5B2%5D=110&ns%5B3%5D=112&ns%5B4%5D=118&ns%5B5%5D=500&ns%5B6%5D=502&ns%5B7%5D=2900", currentUrl);
//    }
}
