package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//input[@id='input_search']")
    private WebElement searchInput;

    private static final String SEARCH_INPUT = "//input[@id='input_search']";
    private static final String PRODUCT_CATALOGUE_BUTTON = "//span[@class='sidebar-item']";
    private static final String APPLE_STORE_BUTTON = "//ul[contains(@class,'sidebar-list')]//a[contains(@href, 'apple-store')]";
    private static final String AMOUNT_OF_PRODUCTS_IN_CART = "//div[contains(@class,'header-bottom__cart')]//div[contains(@class,'cart_count')]";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void searchByKeyword(final String keyword) {
        driver.findElement(By.xpath(SEARCH_INPUT)).sendKeys(keyword, Keys.ENTER);
    }

    public void clickOnProductCatalogButton() {
        driver.findElement(By.xpath(PRODUCT_CATALOGUE_BUTTON)).click();
    }

    public void clickOnAppleStoreButton() {
        driver.findElement(By.xpath(APPLE_STORE_BUTTON)).click();
    }

    public String getTextOfAmountProductsInCart() {
        return driver.findElement(By.xpath(AMOUNT_OF_PRODUCTS_IN_CART)).getText();
    }
}