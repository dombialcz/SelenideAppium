package org.selenide.examples.appium.screens;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.appium.SelenideAppium.$x;

import com.codeborne.selenide.SelenideElement;


public class Cart extends BaseScreen {
    public SelenideElement sauceLabsBikeLight = $(selector.byText("Sauce Labs Bike Light"));
    public SelenideElement sauceLabsBackpack = $(selector.byText("Sauce Labs Backpack"));
    public SelenideElement emptyState = $(selector.byText("No Items"));
    public SelenideElement proceedToCheckoutButton = $(selector.byDescription("Proceed To Checkout button"));

    public static int getItemQuantity(Catalog.ShopItem item) {
        String itemName = item.getItem().getName();
        SelenideElement quantityElement = $x("//android.widget.TextView[@text='" + itemName + "']/following-sibling::android.view.ViewGroup[@content-desc='counter amount']//android.widget.TextView");

        return Integer.parseInt(quantityElement.text());
    }
}