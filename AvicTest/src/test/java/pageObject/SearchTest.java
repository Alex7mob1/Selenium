package pageObject;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class SearchTest extends BaseTest {

    private static final String SEARCH_KEYWORD = "iPhone 11";
    private static final String EXPECTED_SEARCH_QUERY = "query=iPhone";

    @Test
    public void checkThatUrlContainsSearchQuery() {
        getHomePage().searchByKeyword(SEARCH_KEYWORD);
        Assert.assertTrue(getDriver().getCurrentUrl().contains(EXPECTED_SEARCH_QUERY));
    }

    @Test
    public void checkElements() {
        getHomePage().searchByKeyword(SEARCH_KEYWORD);
        getHomePage().implicitWait(30);
        assertEquals(getSearchResultPage().getSearchResultCount(), 12);
    }

    @Test
    public void checkThatSearch() {
        getHomePage().searchByKeyword(SEARCH_KEYWORD);
        for (WebElement webElement : getSearchResultPage().getSearchResultList()) {
            assertTrue(webElement.getText().contains(SEARCH_KEYWORD));
        }
    }
}