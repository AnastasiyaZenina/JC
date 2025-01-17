package UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillInOrderForm(String name, String country, String city, String cardNumber, String month, String year) {
        driver.findElement(By.xpath("//input[@id='year']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@id='country']")).sendKeys(country);
        driver.findElement(By.xpath("//input[@id='city']")).sendKeys(city);
        driver.findElement(By.xpath("//input[@id='card']")).sendKeys(cardNumber);
        driver.findElement(By.xpath("//input[@id='month']")).sendKeys(month);
        driver.findElement(By.xpath("//input[@id='year']")).sendKeys(year);
    }

    public void submitOrder() {
        WebElement purchaseButton = driver.findElement(By.xpath("//button[text()='Purchase']"));
        purchaseButton.click();
    }
    public String getOrderDate() {
        WebElement infoElement = driver.findElement(By.xpath("//p[@class='lead text-muted ']"));
        String fullText = infoElement.getText();
        String date = fullText.substring(fullText.indexOf("Date: ") + 6).trim();
        return date;
    }
    public String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return currentDate.format(formatter);
    }
    }


