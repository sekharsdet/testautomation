package com.automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage{
    public CartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
