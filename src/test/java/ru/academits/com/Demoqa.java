package ru.academits.com;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.awt.*;
import java.io.File;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Demoqa {
    public static WebDriver driver = null;

    @BeforeEach
    public void beforetest(){
        String browser = System.getProperty("browser");

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        String url = "https://demoqa.com/automation-practice-form/";
        assert driver != null;
        driver.get(url);
        driver.manage().window().fullscreen();


    }
    @Test
    public void testUntitledTestCase() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        System.out.println("Start");
        System.out.println("Name, email");
        driver.findElement(By.id("firstName")).clear();
        driver.findElement(By.id("firstName")).sendKeys("Aleksandr");
        driver.findElement(By.id("lastName")).clear();
        driver.findElement(By.id("lastName")).sendKeys("Koroley");
        driver.findElement(By.id("userEmail")).clear();
        driver.findElement(By.id("userEmail")).sendKeys("Alexandr_Koroley@example.com");

        System.out.println("Gender");
        driver.findElement(By.xpath("//*[@id='genterWrapper']/div[2]/div[1]/label")).click();

        System.out.println("Mobile Number");
        driver.findElement(By.id("userNumber")).clear();
        driver.findElement(By.id("userNumber")).sendKeys("8913999000");

        System.out.println("Date of Birth");
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.xpath("//*[@id='dateOfBirthInput']"));
        js.executeScript("arguments[0].value = '';", element);
        js.executeScript("arguments[0].value = '11 July 1982';", element);
        Thread.sleep(3000);

//        System.out.println("Subjects");
//        driver.findElement(By.id("subjectsInput")).clear();
//        driver.findElement(By.id("subjectsInput")).sendKeys("name");

        System.out.println("Scroll down");
        element = driver.findElement(By.id("submit"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);

        System.out.println("Hobbies");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[contains(text(),'Sports')]")).click();

        System.out.println("Current address");
        driver.findElement(By.id("currentAddress")).clear();
        driver.findElement(By.id("currentAddress")).sendKeys("Moscow");

        System.out.println("Picture");
        File picture = new File("C://Users/User/IdeaProjects/Lection_5 Selenium/FotoForDemoqa.jpeg");
        WebElement pathInput = driver.findElement(By.xpath("//*[@id='uploadPicture']"));
        pathInput.sendKeys(picture.getAbsolutePath());

        System.out.println("Select State");
        driver.findElement(By.id("state")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Haryana')]")).click();
        Thread.sleep(3000);

        System.out.println("Select City");
        driver.findElement(By.id("city")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[contains(text(),'Karnal')]")).click();
        Thread.sleep(1000);

        System.out.println("Scroll down");
        js.executeScript("document.body.style.zoom='50%'");
        driver.findElement(By.xpath("//*[@id='submit']"));
        js.executeScript("arguments[0].click();", element);

//        driver.findElement(By.xpath("//*[@id='example-modal-sizes-title-lg']")).getText(),("Thanks for submitting the form");
//        String actualText = element.getText();
//        String expectedText = "Thanks for submitting the form";
//        expectedText = expectedText.trim();
        assertEquals(driver.findElement(By.xpath("//*[@id='example-modal-sizes-title-lg']")).getText(),("Thanks for submitting the form"));
        Thread.sleep(3000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#closeLargeModal")));
//        assertTrue(icon.isDisplayed());
//        WebElement icon = driver.findElement(By.cssSelector("#closeLargeModal"));
//        assertTrue(icon.isDisplayed());
//           WebElement closeModal = driver.findElement(By.cssSelector("#closeLargeModal"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", closeModal);
//        driver.findElement(By.cssSelector("#closeLargeModal")).click();

//        ((JavascriptExecutor) driver).executeScript("document.getElementById('closeLargeModal').style.display = 'none';");
//        closeModal.click();

        System.out.println("Test passed");

    }

    @AfterEach
    public void aftertest() {
        if (driver != null) {
            driver.quit();
        }
    }
}



