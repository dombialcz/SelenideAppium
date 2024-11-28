package org.selenide.examples.appium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class MyWebDriverListener implements WebDriverListener {
  @Override
  public void beforeClick(WebElement element) {
    WebDriverListener.super.beforeClick(element);
    // Additional logic if needed
  }
}
