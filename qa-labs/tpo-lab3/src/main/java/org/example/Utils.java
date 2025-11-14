package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class Utils {
    public static void login(WebDriver driver) {
        driver.get("https://www.fandom.com/");
        driver.findElement(By.xpath("//*[@id=\"global-sign-in-link\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"identifier\"]")).sendKeys("itmo343056");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("1july2024year");
        driver.findElement(By.xpath("/html/body/div/main/div/div[2]/div/form[1]/section/div[3]/button")).click();
    }


}
