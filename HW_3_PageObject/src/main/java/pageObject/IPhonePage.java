package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.id;

public class IPhonePage extends BasePage {

    private static final String ADD_TO_CART_BUTTON = "//div[@class='btns-cart-holder']//a[contains(@class,'btn--orange')]";
    private static final String CONTINUE_TO_SHOPPING_BUTTON = "";
    private static final String ADD_TO_CART_POPUP = "js_cart";

    public IPhonePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnAddToCartButton() {
        driver.findElements(By.xpath(ADD_TO_CART_BUTTON)).get(0).click();
    }

    public void clickOnContinueShoppingButton() {
        driver.findElement(By.xpath(CONTINUE_TO_SHOPPING_BUTTON)).click();
    }

    public By getAddToCartPopup() {
        return id(ADD_TO_CART_POPUP);
    }

}