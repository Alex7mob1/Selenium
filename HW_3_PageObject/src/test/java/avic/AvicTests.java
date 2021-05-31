package avic;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AvicTests {

    WebDriver driver;

    @Test(priority = 1)
    public void checkThatUrlContainsSearchQuery(){
        driver.findElement(By.xpath("//input[@id='input_search']")).clear();
        driver.findElement(By.xpath("//input[@id='input_search']")).sendKeys("iPhone 11", Keys.ENTER);
        Assert.assertTrue(driver.getCurrentUrl().contains("query=iPhone"));
    }
}
