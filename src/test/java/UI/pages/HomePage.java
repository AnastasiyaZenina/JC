package UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getSignupButton() {
        return driver.findElement(By.xpath("//a[text()='Sign up']"));
    }

    private WebElement getLoginButton() {
        return driver.findElement(By.xpath("//a[@id='login2']"));
    }

    private WebElement getCartButton() {
        return driver.findElement(By.xpath("//a[@id='cartur']"));
    }


    public void clickSignup() {
        getSignupButton().click();
    }

    public void clickLogin() {
        getLoginButton().click();
    }

    public void clickCart() {
        getCartButton().click();
    }
}
