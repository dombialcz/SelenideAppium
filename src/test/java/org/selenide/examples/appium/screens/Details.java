package org.selenide.examples.appium.screens;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class Details extends BaseScreen {
    public SelenideElement name = $(selector.byText("Sace Labs Bike Light"));
    public SelenideElement price = $(selector.byDescription("product price"));
    public SelenideElement counterAmount = $(selector.byDescription("counter amount"));
    public SelenideElement addToCartButton = $(selector.byText("Add To Cart"));
    public SelenideElement counterPlusButton = $(selector.byDescription("counter plus button"));
    public SelenideElement counterMinusButton = $(selector.byDescription("counter minus button"));
    public SelenideElement cartBadge = $(selector.byDescription("cart badge"));

    public void addAmount(int amount){
        if (amount <=0) throw new RuntimeException("cannot add negative amount of items");
        while(amount-- > 1) this.counterPlusButton.click();
    }
}