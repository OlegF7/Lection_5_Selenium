package ru.academits;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.time.Duration;

import org.testng.Assert;

import static org.junit.Assert.assertEquals;

import org.assertj.core.api.SoftAssertions;

public class Demoqa {

    public static WebDriver driver = null;

    @Before
    public void beforeTest() {

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
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().fullscreen();
    }

    @After
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testUntitledTest() throws InterruptedException {
        SoftAssertions softAssert = new SoftAssertions();
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
        WebElement dateOfBirthInput = driver.findElement(By.xpath("//*[@id='dateOfBirthInput']"));
        dateOfBirthInput.click();
        WebElement SelectMonth = driver.findElement(By.xpath("//*[@id='dateOfBirth']/descendant::select[1]/option[3]"));
        SelectMonth.click();
        WebElement SelectYear = driver.findElement(By.xpath("//*[@id='dateOfBirth']/descendant::select[2]/option[79]"));
        SelectYear.click();
        WebElement SelectDate = driver.findElement(By.xpath("//*[@id='dateOfBirth']/descendant::div[29]"));
        SelectDate.click();


        System.out.println("Scroll down");
        WebElement element = driver.findElement(By.id("submit"));
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
        Thread.sleep(3000);

        assertEquals(driver.findElement(By.xpath("//*[@id='example-modal-sizes-title-lg']")).getText().trim(), ("Thanks for submitting the form"));
        Thread.sleep(3000);

        softAssert.assertThat(driver.findElement(By.xpath("//*[@class='table-responsive']/descendant::table/tbody/tr[1]/td[2]")).getText()).isEqualTo("Aleksandr Koroley");
        softAssert.assertThat(driver.findElement(By.xpath("//*[@class='table-responsive']/descendant::table/tbody/tr[2]/td[2]")).getText()).isEqualTo("Alexandr_Koroley@example.com");
        softAssert.assertThat(driver.findElement(By.xpath("//*[@class='table-responsive']/descendant::table/tbody/tr[3]/td[2]")).getText()).isEqualTo("Male");
        softAssert.assertThat(driver.findElement(By.xpath("//*[@class='table-responsive']/descendant::table/tbody/tr[4]/td[2]")).getText()).isEqualTo("8913999000");
        softAssert.assertThat(driver.findElement(By.xpath("//*[@class='table-responsive']/descendant::table/tbody/tr[5]/td[2]")).getText()).isEqualTo("02 March,1978");
        softAssert.assertThat(driver.findElement(By.xpath("//*[@class='table-responsive']/descendant::table/tbody/tr[7]/td[2]")).getText()).isEqualTo("Sports");
        softAssert.assertThat(driver.findElement(By.xpath("//*[@class='table-responsive']/descendant::table/tbody/tr[8]/td[2]")).getText()).isEqualTo("FotoForDemoqa.jpeg");
        softAssert.assertThat(driver.findElement(By.xpath("//*[@class='table-responsive']/descendant::table/tbody/tr[9]/td[2]")).getText()).isEqualTo("Moscow");
        softAssert.assertThat(driver.findElement(By.xpath("//*[@class='table-responsive']/descendant::table/tbody/tr[10]/td[2]")).getText()).isEqualTo("Haryana Karnal");
        softAssert.assertAll();
        System.out.println("Test passed");
    }
}



