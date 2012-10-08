package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.WebElement;

/**
 * Represents hyperlink.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 */
public class Link extends TypifiedElement {
    public Link(WebElement wrappedElement) {
        super(wrappedElement);
    }

    /**
     * Clicks the link.
     */
    public void click() {
        getWrappedElement().click();
    }

    /**
     * Retrieves reference from "href" tag.
     * @return Reference associated with hyperlink.
     */
    public String getReference() {
        return getWrappedElement().getAttribute("href");
    }

    /**
     * Returns text of the link.
     */
    public String getText() {
        return getWrappedElement().getText();
    }
}





