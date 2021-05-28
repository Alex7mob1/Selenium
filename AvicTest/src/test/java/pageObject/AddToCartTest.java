package pageObject;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class AddToCartTest extends BaseTest {

    private static final String NUMBER = "1";

    @Test
    public void checkAddToCart(){
        getHomePage().clickOnProductCatalogButton();
        getHomePage().clickOnAppleStoreButton();
        getAppleStorePage().clickOnIPhoneButton();
        getIPhonePage().waitForPageLoadingComplete(30);
        getIPhonePage().clickOnAddToCartButton();
        getIPhonePage().waitForVisibilityOfElement(30, getIPhonePage().getAddToCartPopup());
        getIPhonePage().clickOnContinueShoppingButton();
        assertEquals(getHomePage().getTextOfAmountProductsInCart(),NUMBER);
    }
}