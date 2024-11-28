package org.selenide.examples.appium;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.selenide.examples.appium.data.TestData;
import org.selenide.examples.appium.screens.Cart;
import org.selenide.examples.appium.screens.Catalog;
import org.selenide.examples.appium.screens.Details;
import org.selenide.examples.appium.screens.Login;
import org.selenide.examples.appium.screens.Navigation;

public class MyDemoTest {

  @BeforeEach
  void setUp() {
    closeWebDriver();
    Configuration.browserSize = null;
    Configuration.browser = AndroidDriverMyDemoApp.class.getName();
    WebDriverRunner.addListener(new MyWebDriverListener());
    open();
  }

  @AfterEach
  public void tearDown() {
//    Navigation navigation = new Navigation();
//    navigation.burgerMenu.click();
//    navigation.resetAppMenuButton.click();
//    navigation.resetAppConfirmButton.click();
  }

  @Test
  void test_add_different_items_to_cart() {
    Catalog catalog = new Catalog();
    catalog.clickOn(Catalog.ShopItem.BIKE_LIGHT);

    Details details = new Details();
    details.addToCartButton.click();

    Navigation navigation = new Navigation();
    navigation.goTo(Navigation.MenuItem.CATALOG);

    catalog.clickOn(Catalog.ShopItem.BACKPACK);
    details.addToCartButton.click();

    details.cartBadge.click();

    Cart cart = new Cart();
    cart.sauceLabsBikeLight.shouldBe(visible);
    cart.sauceLabsBackpack.shouldBe(visible);
  }

  @Test
  void add_amount_items_to_cart() {
    Catalog.ShopItem item1 = Catalog.ShopItem.BIKE_LIGHT;

    Catalog catalog = new Catalog();
    catalog.clickOn(item1);

    Details details = new Details();
    details.addAmount(3);
    details.addToCartButton.click();
    details.cartBadge.click();

    Cart cart = new Cart();
    int quantity = cart.getItemQuantity(item1);
    assertEquals(3, quantity, "The item quantity should be 3");
  }

  @Test
  void go_to_checkout() {
    Catalog.ShopItem item1 = Catalog.ShopItem.BIKE_LIGHT;

    Navigation navigation = new Navigation();
    navigation.goTo(Navigation.MenuItem.LOGIN);

    Login login = new Login();
    login.usernameInput.sendKeys(TestData.USERNAME);
    login.passwordInput.sendKeys(TestData.PASSWORD);
    login.loginButton.click();

    navigation.goTo(Navigation.MenuItem.CATALOG);

    Catalog catalog = new Catalog();
    catalog.clickOn(item1);

    Details details = new Details();
    details.addToCartButton.click();
    details.cartBadge.click();

    Cart cart = new Cart();
    cart.proceedToCheckoutButton.click();
  }

  @Test
  void login_successfully() {
    Navigation navigation = new Navigation();
    navigation.goTo(Navigation.MenuItem.LOGIN);

    Login login = new Login();
    login.usernameInput.sendKeys(TestData.USERNAME);
    login.passwordInput.sendKeys(TestData.PASSWORD);
    login.loginButton.click();
  }

  @Test
  void login_second_attempt_goes_to_cart() {
    Navigation navigation = new Navigation();
    navigation.goTo(Navigation.MenuItem.LOGIN);

    Login login = new Login();
    login.usernameInput.sendKeys(TestData.USERNAME);
    login.passwordInput.sendKeys(TestData.PASSWORD);
    login.loginButton.click();

    navigation.goTo(Navigation.MenuItem.LOGIN);

    Cart cart = new Cart();
    cart.emptyState.shouldBe(visible);
  }

  @Test
  void logout_should_ask_confirmation() {
    Navigation navigation = new Navigation();
    navigation.goTo(Navigation.MenuItem.LOGIN);

    Login login = new Login();
    login.usernameInput.sendKeys(TestData.USERNAME);
    login.passwordInput.sendKeys(TestData.PASSWORD);

    navigation.goTo(Navigation.MenuItem.LOGOUT);
    login.logOutConfirmButton.shouldBe(visible);
    login.logOutConfirmButton.click();
    login.logOutconfirmationMessage.shouldBe(visible);
    login.logOutconfirmationMessage.shouldHave( text("You are successfully logged out."));
  }

  @Test
  void login_validation() {
    Navigation navigation = new Navigation();
    navigation.goTo(Navigation.MenuItem.LOGIN);

    Login login = new Login();
    login.usernameInput.sendKeys("invalid");
    login.loginButton.click();

    login.passwordErrorMessage
            .shouldBe(visible)
            .shouldHave( text("Password is required") );
  }

  @Test
  void login_error_state() {
    Navigation navigation = new Navigation();
    navigation.goTo(Navigation.MenuItem.LOGIN);

    Login login = new Login();
    login.usernameInput.sendKeys("invalid");
    login.passwordInput.sendKeys("invalid");
    login.loginButton.click();
    login.genericErrorMessage
            .shouldBe(visible)
            .shouldHave( text("Provided credentials do not match any user in this service.") );
  }

}

