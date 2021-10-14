package com.automation.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public WebElement waitForVisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public Boolean waitForInVisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void clickJavaScript(WebElement element) {
        this.executeJavaScript("arguments[0].click();", element);
    }

    protected Object executeJavaScript(String script, Object... objects) {
        return ((JavascriptExecutor) this.driver).executeScript(script, objects);
    }


    protected void waitAndClick(WebElement element) {
        waitForVisibilityOf(element);
        element.click();
    }
}
