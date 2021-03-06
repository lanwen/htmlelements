package ru.yandex.qatools.htmlelements.loader.decorator.proxyhandlers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 21.08.12
 */
public class HtmlElementListNamedProxyHandler<T extends HtmlElement> implements InvocationHandler {
    private final Class<T> htmlElementClass;
    private final ElementLocator locator;
    private final String name;

    public HtmlElementListNamedProxyHandler(Class<T> htmlElementClass, ElementLocator locator, String name) {
        this.htmlElementClass = htmlElementClass;
        this.locator = locator;
        this.name = name;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if ("toString".equals(method.getName())) {
            return name;
        }

        List<T> htmlElements = new LinkedList<T>();
        List<WebElement> elements = locator.findElements();
        for (int i = 0; i < elements.size(); i++) {
            T htmlElement = HtmlElementFactory.createHtmlElementInstance(htmlElementClass);
            WebElement element = elements.get(i);
            htmlElement.setWrappedElement(element);
            String htmlElementName = String.format("%s [%d]", name, i);
            htmlElement.setName(htmlElementName);
            PageFactory.initElements(new HtmlElementDecorator(element), htmlElement);
            htmlElements.add(htmlElement);
        }

        try {
            return method.invoke(htmlElements, objects);
        } catch (InvocationTargetException e) {
            // Unwrap the underlying exception
            throw e.getCause();
        }
    }
}
