package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultPage extends BasePage {

    private static final String SEARCH_RESULT_PRODUCT_LIST = "//div[@class='prod-cart__descr']";

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getSearchResultList() {
        return driver.findElements(By.xpath(SEARCH_RESULT_PRODUCT_LIST));
    }

    public int getSearchResultCount() {
        return getSearchResultList().size();
    }
}