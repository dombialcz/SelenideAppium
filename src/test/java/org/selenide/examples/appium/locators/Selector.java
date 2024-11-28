package org.selenide.examples.appium.locators;

import org.openqa.selenium.By;

public class Selector {
    private static final Selector instance = new Selector();

    private Selector(){}

    public static Selector getInstance() {
        return instance;
    }

    public By byText(String text) {
        return (By.xpath("//android.widget.TextView[@text='"+text+"']"));
    }

    public By byDescription(String description) {
        return (By.xpath(".//*[@content-desc='"+description+"']"));
    }

    public By byId(String id) {
        return (By.id(id));
    }
}