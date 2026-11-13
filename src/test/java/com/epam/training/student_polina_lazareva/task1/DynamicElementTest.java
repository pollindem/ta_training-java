package com.epam.training.student_polina_lazareva.task1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicElementTest {

    @Test
    public void testHiddenElement() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//a[@href=\"/dynamic_loading\"]")).click();
        driver.findElement(By.xpath("//a[@href=\"/dynamic_loading/1\"]")).click();

        driver.findElement(By.cssSelector("#start button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement finishElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("finish"))
        );

        Assertions.assertEquals("Hello World!", finishElement.getText());
        driver.quit();

        driver = new FirefoxDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//a[@href=\"/dynamic_loading\"]")).click();
        driver.findElement(By.xpath("//a[@href=\"/dynamic_loading/1\"]")).click();

        driver.findElement(By.cssSelector("#start button")).click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        finishElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("finish"))
        );

        Assertions.assertEquals("Hello World!", finishElement.getText());
        driver.quit();
    }
}
