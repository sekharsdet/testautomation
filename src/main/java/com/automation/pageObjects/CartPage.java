package com.automation.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {
    @FindBy(css = ".bag__quantity")
    private WebElement productQuantity;

    @FindBy(css = ".sub-price__val")
    private WebElement subTotalValue;

    @FindBy(css = ".buy-btn")
    private WebElement checkoutButton;

    @FindBy(css = ".shelf-item__del")
    private WebElement removeTheProduct;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("get the count of products added to cart")
    public int getProductQuantity() {
        waitForVisibilityOf(productQuantity);
        return Integer.parseInt(productQuantity.getText());
    }

    @Step("retrieve the sub total value")
    public String getSubTotal() {
        waitForVisibilityOf(subTotalValue);
        return subTotalValue.getText();
    }

    @Step("checkout the products")
    public void checkoutTheProducts() {
        try {
            waitAndClick(checkoutButton);
        } catch (UnhandledAlertException e) {
            e.printStackTrace();
        }
    }

    @Step("get checkout value details")
    public String getCheckoutDetails() {
        return getAlertText();
    }

    @Step("remove the product from cart")
    public void removeTheProduct() {
        waitAndClick(removeTheProduct);
    }
}
