package org.selenide.examples.appium.screens;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;


public class Login extends BaseScreen {
    public SelenideElement usernameInput = $(selector.byDescription("Username input field"));
    public SelenideElement passwordInput = $(selector.byDescription("Password input field"));
    public SelenideElement loginButton = $(selector.byDescription("Login button"));
    public SelenideElement logOutConfirmButton = $(selector.byId("android:id/button1"));
    public SelenideElement logOutconfirmationMessage = $(selector.byId("android:id/alertTitle"));

    public SelenideElement passwordErrorMessage = $x("//android.view.ViewGroup[@content-desc='Password-error-message']/android.widget.TextView");
    public SelenideElement genericErrorMessage = $x("//android.view.ViewGroup[@content-desc='generic-error-message']//android.widget.TextView");
}