package org.selenide.examples.appium.screens;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.appium.SelenideAppium.$x;

import com.codeborne.selenide.SelenideElement;

public class Navigation extends BaseScreen {
    public SelenideElement burgerMenu =  $(selector.byDescription("open menu"));
    public SelenideElement resetAppConfirmButton = $(selector.byText("RESET APP"));
    public SelenideElement resetAppMenuButton = $x("//android.view.ViewGroup[@content-desc=\"menu item reset app\"]");

    // helper methods
    public void goTo(MenuItem menuItem) {
        this.burgerMenu.click();
        $(selector.byDescription(menuItem.getDescription())).click();
    }

    public void resetApp(){
        this.burgerMenu.click();
        this.goTo(MenuItem.RESET_APP_STATE);
        this.resetAppConfirmButton.click();
    }

    // Enum for menu items
    public enum MenuItem {
        CATALOG("menu item catalog"),
        WEBVIEW("menu item webview"),
        LOGIN("menu item log in"),
        LOGOUT("menu item log out"),
        RESET_APP_STATE("menu item reset app");

        private final String description;

        MenuItem(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }
    }
}

