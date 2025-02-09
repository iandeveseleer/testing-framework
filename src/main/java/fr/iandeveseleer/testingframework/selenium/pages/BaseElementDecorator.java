package fr.iandeveseleer.testingframework.selenium.pages;

import fr.iandeveseleer.testingframework.annotations.ElementName;
import fr.iandeveseleer.testingframework.selenium.pages.elements.BaseElement;
import org.junit.platform.commons.support.AnnotationSupport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;

public class BaseElementDecorator extends DefaultFieldDecorator {

    private final WebDriver webDriver;
    public BaseElementDecorator(ElementLocatorFactory pFactory, WebDriver pWebDriver) {
        super(pFactory);
        webDriver = pWebDriver;
    }

    @Override
    public Object decorate(ClassLoader pLoader, Field pField) {
        if (!BaseElement.class.isAssignableFrom(pField.getType())) {
            return super.decorate(pLoader, pField);
        }

        ElementLocator locator = factory.createLocator(pField);
        if (locator == null) {
            return null;
        }

        ElementName elementName = AnnotationUtils.getAnnotation(pField, ElementName.class);

        WebElement originalElement = proxyForLocator(pLoader, locator);
        if(elementName != null) {
            return new BaseElement(webDriver, originalElement, elementName.value());
        } else {
            return new BaseElement(webDriver, originalElement);
        }
    }
}
