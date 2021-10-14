package com.automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingPage extends BasePage {

    public ShoppingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[value='S']")
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

    public ShoppingPage selectMediumSize() {
        waitAndClick(sizeMCheckbox);
        return this;
    }

    public ShoppingPage selectSmallSize() {
        waitAndClick(sizeSCheckbox);
        return this;
    }

    public ShoppingPage selectTheProduct(String productName){
        int index=0;
        for(int i=0;i< productTitle.size();i++){
            if(productTitle.get(i).getText().equals(productName)){
                index=i;
            }
        }
        addToCartButton.get(index).click();
        return this;
    }

    public ShoppingPage openHomePage() {
        driver.get(System.getProperty("url"));
        return this;
    }

}
