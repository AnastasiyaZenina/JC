package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getTotalPriceElement() {
        return driver.findElement(By.xpath("//h3[@id='totalp']"));
    }

    private WebElement getPlaceOrderButton() {
        return driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
    }

    public String getTotalPrice() {
        return getTotalPriceElement().getText();
    }

    public void placeOrder() {
        getPlaceOrderButton().click();
    }
}

