package com.automation.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ShoppingPage extends BasePage {

    public ShoppingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "label>input[value='S']")
    private WebElement sizeSCheckbox;
    @FindBy(css = "input[value='M']")
    private WebElement sizeMCheckbox;
    @FindBy(css = "input[value='L']")
    private WebElement sizeLCheckbox;
    @FindBy(css = "input[value='XL']")
    private WebElement sizeXlCheckbox;
    @FindBy(css = "input[value='XS']")
    private WebElement sizeXsCheckbox;
    @FindBy(css = "input[value='XXL']")
    private WebElement sizeXxlCheckbox;
    @FindBy(css = "p.shelf-item__title")
    private List<WebElement> productTitle;
    @FindBy(css = ".shelf-item__buy-btn")
    private List<WebElement> addToCartButton;
    @FindBy(css = ".float-cart__close-btn")
    private WebElement closeFloatCartButton;
    @FindBy(css = ".float-cart__close-btn")
    private WebElement cartButton;

    @FindBy(css = ".val>b")
    private List<WebElement> productValue;

    @FindBy(css = ".sort>select")
    private WebElement sortProducts;

    public ShoppingPage selectMediumSize() {
        waitAndClick(sizeMCheckbox);
        return this;
    }

    @Step("select the size small")
    public ShoppingPage selectSmallSize() {
        clickJavaScript(sizeSCheckbox);
        return this;
    }

    @Step("add the product to cart")
    public CartPage selectTheProduct(String productName) {
        waitForVisibilityOf(productTitle.get(0));
        int index = 0;
        for (int i = 0; i < productTitle.size(); i++) {
            if (productTitle.get(i).getText().equals(productName)) {
                index = i;
            }
        }
        addToCartButton.get(index).click();
        return new CartPage(driver);
    }

    public ShoppingPage openHomePage() {
        driver.get(System.getProperty("url"));
        return this;
    }

    @Step("get product price value")
    public int getProductValue(int productIndex) {
        waitForVisibilityOf(productValue.get(0));
        return Integer.parseInt(productValue.get(productIndex - 1).getText());
    }

    @Step("sort the products")
    public void sortProducts(String sortType) {
        waitForVisibilityOf(sortProducts);
        Select sortSelect = new Select(sortProducts);
        sortSelect.selectByValue(sortType);

    }

    @Step("close floating cart")
    public void closeFloatingCart() {
        waitAndClick(closeFloatCartButton);
    }


}
