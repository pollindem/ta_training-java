package com.epam.training.student_polina_lazareva.task2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='country-dropdown'] input")))
                .sendKeys(location, Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[data-testid='search-input']"))).sendKeys(job, Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@id='onetrust-accept-btn-handler']"))).click();
        driver.findElement(By.cssSelector("label[for='checkbox-vacancy_type-Remote-_r_0_']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@name='submit_search_box_button']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "(//div[@class='List_content__r2Dmt']//a[@class='JobCard_labelLink__cslqy'])[1]")))
                .click();

        boolean actualTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h1[data-testid='job-details-banner-title']")))
                .getText().toLowerCase().contains(job.toLowerCase());

        Assertions.assertTrue(actualTitle);
    }

    @AfterEach
    void tearDown(){
        driver.quit();
        driver = null;
    }
}
