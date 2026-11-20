package com.epam.training.student_polina_lazareva.task2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JobSearchTest {

    private WebDriver driver;

    @BeforeEach
    public void browserSetup(){
        driver = new ChromeDriver();
    }

    @Test
    public void testRemoteJavaJobSearch(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        final String location = "All available countries";
        final String job = "Java";

        driver.get("https://www.epam.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();
        driver.findElement(By.linkText("Careers")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//div[contains(@class,'pinned-button')]//span[contains(.,'Start Your Search Here')]")))
                        .click();
        //wait.until(ExpectedConditions.urlContains("careers.epam.com/"));
        //driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='country-dropdown'] input")))
                .sendKeys(location, Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[data-testid='search-input']"))).sendKeys(job, Keys.ENTER);
        //driver.findElement(By.xpath("//label[contains(.,'Remote')]")).click();
        //driver.findElement(By.cssSelector("#anchor-list button[name='submit_search_box_button']")).click();


    }

    @AfterEach
    void tearDown(){
        driver.quit();
        driver = null;
    }
}
