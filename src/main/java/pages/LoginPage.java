package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getSignUpUsernameField() {
        return driver.findElement(By.xpath("//input[@id='sign-username']"));
    }

    private WebElement getSignUpPasswordField() {
        return driver.findElement(By.xpath("//input[@id='sign-password']"));
    }

    private WebElement getSignUpButton() {
        return driver.findElement(By.xpath("//button[text()='Sign up']"));
    }

    private WebElement getLoginUsernameField() {
        return driver.findElement(By.xpath("//input[@id='loginusername']"));
    }

    private WebElement getLoginPasswordField() {
        return driver.findElement(By.xpath("//input[@id='loginpassword']"));
    }

    private WebElement getLoginButton() {
        return driver.findElement(By.xpath("//button[text()='Log in']"));
    }


    public void register(String username, String password) {
        getSignUpUsernameField().sendKeys(username);
        getSignUpPasswordField().sendKeys(password);
        getSignUpButton().click();
    }


    public void login(String username, String password) {
        getLoginUsernameField().sendKeys(username);
        getLoginPasswordField().sendKeys(password);
        getLoginButton().click();
    }
}
