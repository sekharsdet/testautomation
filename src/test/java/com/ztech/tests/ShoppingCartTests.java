package com.ztech.tests;

import com.automation.pageObjects.ShoppingPage;
import com.ztech.basetest.BaseTest;
import org.testng.annotations.Test;

public class ShoppingCartTests extends BaseTest {

    @Test
    public void addProductsToCart(){
     new ShoppingPage(getDriver()).openHomePage().selectSmallSize().selectTheProduct("Cat Tee Black T-Shirt");
    }
}
