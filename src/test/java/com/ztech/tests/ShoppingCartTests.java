package com.ztech.tests;

import com.automation.pageObjects.CartPage;
import com.automation.pageObjects.ShoppingPage;
import com.ztech.basetest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Feature("Add products to cart and checkout")
public class ShoppingCartTests extends BaseTest {

    @DataProvider
    public Object[][] singleProduct() {
        return new Object[][]{
                {"Cat Tee Black T-Shirt"}
        };
    }

    @DataProvider
    public Object[][] multipleProducts() {
        return new Object[][]{
                {"Cat Tee Black T-Shirt", "Crazy Monkey Black T-Shirt"}
        };
    }

    @DataProvider
    public Object[][] descending() {
        return new Object[][]{
                {"highestprice"}
        };
    }

    @DataProvider
    public Object[][] ascending() {
        return new Object[][]{
                {"lowestprice"}
        };
    }


    @Test(dataProvider = "singleProduct")
    @Description("add a small size product and verify the count of products")
    public void addProductsToCart(String productName) {
        CartPage cartPage = addSmallSizeProductToCart(productName);
        int addedProductQuantity = cartPage.getProductQuantity();
        assertThat("product count did not match", addedProductQuantity, is(1));
    }

    @Test(dataProvider = "singleProduct")
    @Description("should add a product to cart and remove it")
    public void addProductToCartAndRemove(String productName) {
        CartPage cartPage = addSmallSizeProductToCart(productName);
        cartPage.removeTheProduct();
        int addedProductQuantity = cartPage.getProductQuantity();
        assertThat("product count did not match", addedProductQuantity, is(0));
    }

    @Test(dataProvider = "singleProduct")
    @Description("should match subtotal with checkout price value")
    public void verifyProductCheckoutPriceValue(String productName) {
        CartPage cartPage = addSmallSizeProductToCart("Cat Tee Black T-Shirt");
        String subTotal = cartPage.getSubTotal();
        cartPage.checkoutTheProducts();
        String details = cartPage.getCheckoutDetails();
        assertThat("checkout price value did not match", "Checkout - Subtotal: " + subTotal,
                is(details));
    }

    @Test(dataProvider = "ascending")
    @Description("able to sort the product in ascending order")
    public void sortTheProductsLowestToHighest(String sortType) {
        ShoppingPage shoppingPage = new ShoppingPage(getDriver()).
                openHomePage();
        shoppingPage.sortProducts(sortType);
        int firstProductValue = shoppingPage.getProductValue(1);
        int secondProductValue = shoppingPage.getProductValue(2);
        assertThat("products are not sorted", firstProductValue, Matchers.greaterThanOrEqualTo(secondProductValue));
    }

    @Test(dataProvider = "descending")
    @Description("able to sort the product in descending order")
    public void sortTheProductsHighestToLowest(String sortType) {
        ShoppingPage shoppingPage = new ShoppingPage(getDriver()).
                openHomePage();
        shoppingPage.sortProducts(sortType);
        int firstProductValue = shoppingPage.getProductValue(1);
        int secondProductValue = shoppingPage.getProductValue(2);
        assertThat("products are not sorted", secondProductValue, Matchers.greaterThanOrEqualTo(firstProductValue));
    }

    @Test(dataProvider = "multipleProducts")
    @Description("able to close float cart and should add multiple products to cart")
    public void addMultipleTheProducts(String firstProductName, String secondProductName) {
        ShoppingPage shoppingPage = new ShoppingPage(getDriver()).
                openHomePage().
                selectSmallSize();
        shoppingPage.selectTheProduct(firstProductName);
        shoppingPage.closeFloatingCart();
        CartPage cartPage = shoppingPage.selectTheProduct(secondProductName);
        int addedProductQuantity = cartPage.getProductQuantity();
        assertThat("product are not more than two", addedProductQuantity, is(2));
    }

    CartPage addSmallSizeProductToCart(String productName) {
        return new ShoppingPage(getDriver()).
                openHomePage().
                selectSmallSize().
                selectTheProduct(productName);
    }

}