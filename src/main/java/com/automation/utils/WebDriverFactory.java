package com.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    public static WebDriver init(String browser, String os) {
        return Browser.valueOf(browser.toUpperCase()).getDriver(os);
    }

    enum Browser {
        CHROME {
            public WebDriver getDriver(String os) {
                WebDriverManager manager = ChromeDriverManager.getInstance(DriverManagerType.CHROME);
                manager.setup();
                return new ChromeDriver();
            }
        },
        FIREFOX {
            public WebDriver getDriver(String os) {
                WebDriverManager manager = FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX);
                manager.setup();
                return new FirefoxDriver();
            }
        };

        public abstract WebDriver getDriver(String os);
    }
}