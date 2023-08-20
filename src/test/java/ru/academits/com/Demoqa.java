package ru.academits.com;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Demoqa {
    @Test
    public void testUntitledTestCase() throws InterruptedException {
        WebDriver driver = null;
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

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        String url = "https://demoqa.com/automation-practice-form/";
        Dimension dimension = new Dimension(1280, 720);
        assert driver != null;
        driver.manage().window().setSize(dimension);
//      driver.manage().window().fullscreen();
        driver.get(url);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='50%'");
        System.out.println("Start");

        System.out.println("Name, email");
        driver.findElement(By.id("firstName")).clear();
        driver.findElement(By.id("firstName")).sendKeys("Aleksandr");
        driver.findElement(By.id("lastName")).clear();
        driver.findElement(By.id("lastName")).sendKeys("Koroley");
        driver.findElement(By.id("userEmail")).clear();
        driver.findElement(By.id("userEmail")).sendKeys("Alexandr_Koroley@example.com");

        System.out.println("Gender");
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='genterWrapper']/div[2]/div[1]/label"))).click();
      driver.findElement(By.xpath("//*[@id='genterWrapper']/div[2]/div[1]/label")).click();

        System.out.println("Mobile Number");
        driver.findElement(By.id("userNumber")).clear();
        driver.findElement(By.id("userNumber")).sendKeys("8913999000");


        System.out.println("Date of Birth");
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.xpath("//*[@id='dateOfBirthInput']"));
        js.executeScript("arguments[0].value = '';", element);
        js.executeScript("arguments[0].value = '11 July 1982';", element);
        //js.executeScript("arguments[0].click();", element);
        Thread.sleep(3000);

        System.out.println("Subjects"); //Поле Subjects должно пропадать после написания и клика на следующем элементе?
        driver.findElement(By.id("subjectsInput")).clear();
        driver.findElement(By.id("subjectsInput")).sendKeys("name");

        System.out.println("Scroll down");
        element = driver.findElement(By.id("submit"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);

        System.out.println("Hobbies");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[contains(text(),'Sports')]")).click();

        System.out.println("Current address");
        driver.findElement(By.id("currentAddress")).clear();
        driver.findElement(By.id("currentAddress")).sendKeys("Moscow");

        System.out.println("Select State");
        driver.findElement(By.id("state")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Haryana')]")).click();
        Thread.sleep(3000);

        System.out.println("Select City");
        //driver.findElement(By.xpath("//div[contains(text(),'Select City')]")).click();
        driver.findElement(By.id("city")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[contains(text(),'Karnal')]")).click();
        Thread.sleep(1000);

        System.out.println("Скроллим вниз");
//        js.executeScript("document.body.style.zoom='50%'");
        //element = driver.findElement(By.id("submit"));
        element = driver.findElement(By.xpath("//*[@id='app']/footer/span"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='submit']"))).click();
        //driver.findElement(By.id("submit")).click();
        //driver.findElement(By.id("closeLargeModal")).click();
    }
}



