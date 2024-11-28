package org.selenide.examples.appium.screens;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;

import org.selenide.examples.appium.data.elements.Backpack;
import org.selenide.examples.appium.data.elements.BaseElement;
import org.selenide.examples.appium.data.elements.BikeLight;

public class Catalog extends BaseScreen {
    public SelenideElement header = $(selector.byText("Products"));

    public void clickOn (ShopItem shopItem) {
        String shopItemName = shopItem.getItem().getName();
        SelenideElement element = $(selector.byText(shopItemName));
        element.click();
    }

    public enum ShopItem {
        BACKPACK(new Backpack()),
        BIKE_LIGHT(new BikeLight());

        private final BaseElement item;

        ShopItem(BaseElement item) {
            this.item = item;
        }

        public BaseElement getItem() {
            return item;
        }
    }

}