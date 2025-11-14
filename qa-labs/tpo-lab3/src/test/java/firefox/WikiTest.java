package firefox;

import org.example.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WikiTest {
    private RemoteWebDriver driver;

    @BeforeEach
    public void setUp() {

        FirefoxOptions options = new FirefoxOptions();

        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void startWiki() throws InterruptedException {
        Utils.login(driver);
        driver.manage().window().fullscreen();

        driver.findElement(By.xpath("/html/body/div[2]/div[1]/nav/div/a[7]")).click();
        driver.findElement(By.xpath("//*[@id=\"wds_input_1\"]")).sendKeys("itmo pokemon club");
        driver.findElement(By.xpath("//*[@id=\"wds_input_2\"]")).sendKeys("itmopokemonclubbbbb");
        driver.manage().window().fullscreen();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"create-new-wiki\"]/div/div[2]/button")).click();

        driver.findElement(By.xpath("//*[@id=\"wds_input_4\"]")).sendKeys("The University Pokémon Club is a vibrant and inclusive community dedicated to all things Pokémon. Whether you’re a seasoned trainer, a competitive battler, a collector, or just a casual fan of the franchise, the club offers something for everyone. The club provides a space for students to share their passion for Pokémon, participate in various events, and form lasting friendships.");


        driver.findElement(By.xpath("//*[@id=\"create-new-wiki\"]/div/div[2]/div[2]")).click();
        WebElement dropdown = driver.findElement(By.xpath("//div[@class='fandom-select__control css-13cymwt-control']"));

        dropdown.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'fandom-select__option')][1]")));
        firstOption.click();


        //to be reviewed
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"create-new-wiki\"]/div/div[2]/div[4]/button[2]")).click();

        Thread.sleep(10000);

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"create-new-wiki\"]/div/div[1]/div[2]/button"))).click();

        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://lhoi.fandom.com/wiki/itmopokemonclubbbbb_Wiki", currentUrl);

    }

    @Test
    public void editWiki() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"wds_input_4\"]")).sendKeys("Objectives\n" +
                "Community Building: Foster a welcoming environment where members can connect over their shared interest in Pokémon.\n" +
                "Education and Skill Development: Provide resources and activities to help members improve their Pokémon knowledge and skills.\n" +
                "Event Organization: Host a variety of events, including tournaments, movie nights, and collaborative projects.\n" +
                "Outreach: Promote the club within the university and the broader community to attract new members and increase awareness of Pokémon culture.");



        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[4]/form/div[2]/div[2]/span[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/section/form/div/div/p/a")).click();

        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/section/form/div/section/div/label[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/section/form/div/div/button")).click();


        Thread.sleep(1000);

        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://community.fandom.com/wiki/Special:Search?query=lord+of+rings&scope=internal&contentType=&ns%5B0%5D=4&ns%5B1%5D=12&ns%5B2%5D=110&ns%5B3%5D=112&ns%5B4%5D=118&ns%5B5%5D=500&ns%5B6%5D=502&ns%5B7%5D=2900", currentUrl);
    }

    @Test
    public void deleteWiki() throws InterruptedException {
        Utils.login(driver);
        driver.manage().window().fullscreen();

        driver.findElement(By.xpath("//*[@id=\"p-views\"]/div/div[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"p-cactions\"]/ul/li[6]")).click();

        driver.findElement(By.xpath("//*[@id=\"wpConfirmB\"]")).click();

        Thread.sleep(1000);

        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://dfvd.fandom.com/wiki/Dfvd_Wiki?action=delete", currentUrl);
    }
}
