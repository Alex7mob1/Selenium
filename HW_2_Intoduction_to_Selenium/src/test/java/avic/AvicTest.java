package avic;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.Keys.ENTER;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AvicTest {

    private WebDriver driver;
    private int elementsOnSearchPage = 1;
    private int cameras = 3;
    private String expectedPrice = "9199";

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://avic.ua/");
    }

    @Test(priority = 1)
    public void checkThatUrlContainsSearchWord() {
        driver.findElement(xpath("//input[@id='input_search']")).sendKeys("nikon");
        driver.findElement(xpath("//button[@class='button-reset search-btn']")).click();
        assertTrue(driver.getCurrentUrl().contains("query=nikon"));
    }

    @Test(priority = 2)
    public void checkElementsAmountOnSearchPage() {
        driver.findElement(xpath("//input[@id='input_search']")).sendKeys("nikon", ENTER);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(xpath("//li[@class='page-item'][last()]")).click();
        List<WebElement> elementsList = driver.findElements(xpath("//div[@class='prod-cart__descr']"));
        int actualElementsSize = elementsList.size();
        assertEquals(actualElementsSize, elementsOnSearchPage);
    }

    @Test(priority = 3)
    public void checkThatSearchResultsContainsSearchWord() {
        driver.findElement(xpath("//input[@id='input_search']")).sendKeys("nikon", ENTER);
        List<WebElement> elementList = driver.findElements(xpath("//div[@class='prod-cart__descr']"));
        for (WebElement webElement : elementList) {
            assertTrue(webElement.getText().contains("Nikon"));
        }
    }

    @Test(priority = 4)
    public void checkAddToCart() {
        driver.findElement(xpath("//span[@class='sidebar-item']")).click();
        driver.findElement(xpath("//ul[contains(@class,'sidebar-list')]//a[contains(@href, 'foto-video')]")).click();
        driver.findElement(xpath("//img[@alt='Фотокамери']")).click();
        new WebDriverWait(driver, 90).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        driver.findElement(xpath("//a[@class='prod-cart__buy'][contains(@data-ecomm-cart,' Canon EOS 5D Mark IV body')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js_cart")));
        driver.findElement(xpath("//span[@class='js_plus btn-count btn-count--plus ']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js_cart")));
        driver.findElement(xpath("//span[@class='js_plus btn-count btn-count--plus ']")).click();
        String actualProductsCountInCart =
                driver.findElement(xpath("//div[contains(@class,'header-bottom__cart')]//div[contains(@class,'cart_count')]")).getText();
        int actualProductsCountInCartInt = Integer.parseInt(actualProductsCountInCart);
        assertEquals(actualProductsCountInCartInt, cameras);
    }

    @Test(priority = 5)
    public void checkChoseWithStandards() {

        driver.findElement(xpath("//span[@class='sidebar-item']")).click();
        driver.findElement(xpath("//ul[contains(@class,'sidebar-list')]//a[contains(@href, 'gadzhetyi1')]")).click();
        driver.findElement(xpath("//img[contains(@src, 'smart-home')]")).click();
        driver.findElement(xpath("//label[@for='fltr-1']")).click();
        driver.findElement(xpath("//label[@for='fltr-badge-6']")).click();
        driver.findElement(xpath("//input[contains(@class,'max')]")).click();
        driver.findElement(xpath("//input[contains(@class,'max')]")).clear();
        driver.findElement(xpath("//input[contains(@class,'max')]")).sendKeys("10000", ENTER);
        driver.findElement(xpath("//span[@class='prod-cart__article']")).click();
        driver.findElement(xpath("//a[contains(text(),'Купити в 1 клік')]")).click();
        driver.findElement(xpath("//button[@data-callback='onOneClickOrderSubmit']")).click();

        String price = driver.findElement(xpath("//div[@class='new-prise']//span[@data-product-price='9199']")).getText();
        assertEquals(price, expectedPrice);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}