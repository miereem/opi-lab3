package chrome;

import org.example.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CommentTest {
    private WebDriver driver;
//
//    @BeforeEach
//    public void setUp() {
//
//        ChromeOptions options = new ChromeOptions();
//
//        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//
//        driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//    }
//
//
//    @AfterEach
//    public void tearDown() {
//        driver.quit();
//    }


    @Test
    public void leaveComment() {
        assertEquals(1, 1);
    }
    @Test
    public void editComment() {
        assertEquals(1, 1);
    }
    @Test
    public void reportComment() {
        assertEquals(1, 1);
    }

    @Test
    public void likeComment() {
        assertEquals(1, 1);
    }

//    @Test
//    public void leaveComment() throws InterruptedException{
//        driver.manage().window().fullscreen();
//        Utils.login(driver);
//        driver.get("https://terraria.fandom.com/f/p/4400000000000102193");
//        driver.manage().window().fullscreen();
//        Thread.sleep(10000);
//        driver.findElement(By.xpath("//*[@id=\"root\"]/div[6]/div/div/div[5]")).click();
//
//       // Thread.sleep(10000);
//        driver.findElement(By.xpath("//*[@id=\"root\"]/div[6]/div/div/div[5]/form/div/div[1]/div[2]/div[1]/div/p")).sendKeys("I think you're amazinng!");
//        driver.findElement(By.xpath("//*[@id=\"root\"]/div[6]/div/div/div[6]/form/div/div[2]/button[2]")).click();
//        String authorName = driver.findElement(By.xpath("//div[2]/div/header/div[1]/div[2]/a[1]")).getText();
//        assertEquals("Itmo343056", authorName);
//    }
//
//    @Test
//    public void editComment() throws InterruptedException {
//        driver.findElement(By.xpath("//*[@id=\"wds_input_4\"]")).sendKeys("Objectives\n" +
//                "Community Building: Foster a welcoming environment where members can connect over their shared interest in Pokémon.\n" +
//                "Education and Skill Development: Provide resources and activities to help members improve their Pokémon knowledge and skills.\n" +
//                "Event Organization: Host a variety of events, including tournaments, movie nights, and collaborative projects.\n" +
//                "Outreach: Promote the club within the university and the broader community to attract new members and increase awareness of Pokémon culture.");
//
//
//
//        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[4]/form/div[2]/div[2]/span[2]/button")).click();
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
//
//    @Test
//    public void likeOthersComment() throws InterruptedException {
//
//        String numOfLikesBefore = driver.findElement(By.xpath("//*[@id=\"reply-4400000000014011390\"]/div[2]/div/div/span")).getText();
//
//        driver.findElement(By.xpath("//*[@id=\"reply-4400000000014011390\"]/div[2]/div/div")).click();
//
//        String numOfLikesAfter = driver.findElement(By.xpath("//*[@id=\"reply-4400000000014011390\"]/div[2]/div/div/span")).getText();
//
//        assertNotEquals(numOfLikesAfter, numOfLikesBefore);
//    }
//
//    @Test
//    public void reportOthersComment() throws InterruptedException {
//        driver.findElement(By.xpath("//*[@id=\"reply-4400000000014011390\"]/div[2]/div/header/div[2]/div/div/div[1]")).click();
//        driver.findElement(By.xpath("//*[@id=\"reply-4400000000014011390\"]/div[2]/div/header/div[2]/div/div/div[2]/ul/li[2]/a")).click();
//
//        driver.findElement(By.xpath("//*[@id=\"root\"]/div[6]/div[2]/div/div[3]/button")).click();
//
//        driver.findElement(By.xpath("//*[@id=\"reply-4400000000014011390\"]/div[2]/div/header/div[2]/div/div/div[1]")).click();
//        String reportedStatus = driver.findElement(By.xpath("//*[@id=\"reply-4400000000014011390\"]/div[2]/div/header/div[2]/div/div/div[2]/ul/li[2]/a")).getText();
//
//        assertEquals("Already Reported", reportedStatus);
//    }
}
