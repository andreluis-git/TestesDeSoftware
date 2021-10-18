package com.testesdesoftware.testes_de_software;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class WaitForPageLoaded {
    private WebDriver _driver;

    public WaitForPageLoaded(WebDriver driver) {
        _driver = driver;
    }

    public void waitPage() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(_driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assertions.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
}
