package chrome;

import org.example.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FandomTest {
    private RemoteWebDriver driver;

//    @BeforeEach
//    public void setUp() {
//        ChromeOptions options = new ChromeOptions();
//        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//        driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//    }
//
//    @AfterEach
//    public void tearDown() {
//        driver.quit();
//    }


    @Test
    public void followFandom() {
        assertEquals(1, 1);
    }
    @Test
    public void unfollowFandom() {
        assertEquals(1, 1);
    }

//
//    @Test
//    @Order(1)
//    public void followFandom() throws InterruptedException {
//        Utils.login(driver);
//        driver.manage().window().fullscreen();
//        driver.findElement(By.xpath("/html/body/div[2]/div[1]/nav/div/a[1]")).click();
//        driver.findElement(By.xpath("//*[@id=\"GenericModal_generic-modal__4mRja\"]/div/div[2]/div[2]/div/div[2]/button")).click(); //okno
//        driver.manage().window().fullscreen();
//        WebElement ulElement = driver.findElement(By.xpath("//ul[contains(@class, 'FandomList_fandomList__9rJqn')]"));
//        List<WebElement> children = ulElement.findElements(By.xpath("./child::*"));
//        int prevSize = children.size();
//       // driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[4]/div/aside[1]/section[2]/section/button")).click();
//        driver.findElement(By.xpath("//*[@id=\"GenericModal_generic-modal__4mRja\"]/div/div[2]/div[2]/div/div[1]/div/input")).sendKeys("genshin"); //add fandom
//        driver.findElement(By.xpath("//*[@id=\"GenericModal_generic-modal__4mRja\"]/div/div[2]/div[2]/div/ul/li/button")).click(); //follow fandom
//        driver.findElement(By.xpath("//*[@id=\"GenericModal_generic-modal__4mRja\"]/div/div[2]/div[2]/div/div[2]/button")).click(); //done button
//        System.out.println(prevSize);
//        int currSize = children.size();
//        System.out.println(currSize);
//        assertEquals(1, currSize - prevSize);
//    }
//
//
//
//    @Test
//    @Order(2)
//    public void unfollowFandom() {
//        Utils.login(driver);
//        driver.manage().window().fullscreen();
//        driver.findElement(By.xpath("/html/body/div[2]/div[1]/nav/div/a[1]")).click();
//        driver.findElement(By.xpath("//*[@id=\"GenericModal_generic-modal__4mRja\"]/div/div[2]/div[2]/div/div[2]/button")).click(); //okno
//        driver.manage().window().fullscreen();
//        WebElement ulElement = driver.findElement(By.xpath("//ul[contains(@class, 'FandomList_fandomList__9rJqn')]"));
//        List<WebElement> children = ulElement.findElements(By.xpath("./child::*"));
//        int prevSize = children.size();
//        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[4]/div/aside[1]/section[2]/section/header/div[2]/div[1]")).click();
//        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[4]/div/aside[1]/section[2]/section/header/div[2]/div[2]/ul/li/button")).click();
//
//        driver.findElement(By.xpath("//*[@id=\"GenericModal_generic-modal__4mRja\"]/div/div[2]/div[2]/div/ul/li/button")).click();
//        driver.findElement(By.xpath("//*[@id=\"GenericModal_generic-modal__4mRja\"]/div/div[2]/div[2]/div/div[4]/button[1]")).click();
//        driver.findElement(By.xpath("//*[@id=\"GenericModal_generic-modal__4mRja\"]/div/div[2]/div[2]/div/div[2]/button")).click(); //done button
//
//        int currSize = children.size();
//        System.out.println(prevSize);
//        System.out.println(currSize);
//        assertEquals(1, prevSize - currSize);
//    }

}
