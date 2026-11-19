package com.epam.training.student_polina_lazareva.task1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicElementTest {

    private WebDriver driver;

    @BeforeEach
    public void browserSetup(){
        driver = new ChromeDriver();
    }

    @Test
    public void testHiddenElement() {
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//a[@href='/dynamic_loading']")).click();
        driver.findElement(By.xpath("//a[@href='/dynamic_loading/1']")).click();

        driver.findElement(By.cssSelector("#start button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement finishElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("finish"))
        );

        Assertions.assertEquals("Hello World!", finishElement.getText());
    }

    @AfterEach
    void tearDown(){
        driver.quit();
        driver = null;
    }
}
