package ru.yandex.qatools.htmlelements;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.testelements.SearchArrow;
import ru.yandex.qatools.htmlelements.testelements.SearchArrowData;
import ru.yandex.qatools.htmlelements.testpages.SearchPage;

public class BlockNameTest {
	private WebDriver driver = SearchPage.mockDriver();
	
    @Test
    public void testSearchArrowInitialization() {
        SearchArrow searchArrow = new SearchArrow();
        HtmlElementLoader.populateHtmlElement(searchArrow, driver);

        assertThat("Wrapped element of search arrow should not be null",
                searchArrow.getName(), is(SearchArrowData.SEARCH_ARROW_NAME));
    }
}
