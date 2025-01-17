package UI.tests;

import UI.pages.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UITest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private Faker faker;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\79531\\Desktop\\JavaCode\\src\\main\\java\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        faker = new Faker();
    }
    @Test
    public void testDemoblaze() {
        //С регистрацией и авторизацией и  произошел затык,не работает то одна, то другая,
        // точнее как-открывается форма,а поля не заполняются в одной из них
        //это чередуется по какой то причине, я при этом ничего не меняю
        //следовательно,дальше авторизации тест я не смогла прогнать
        //поэтому начиная с 3 пункта и дальше говнокод написан наугад,
        // не без помощи чата гпт конечно же (каюсь,но не сильно)
        // 1. Зарегистрироваться
        String username = faker.name().username();
        String password = faker.internet().password();

        driver.get("https://www.demoblaze.com/");
        homePage.clickSignup();
        loginPage.register(username, password);
        confirmAlert();

        // 2. Залогиниться
        homePage.clickLogin();
        loginPage.login(username, password);
        confirmAlert();

        // 3. Добавить в корзину по одному гаджету из каждой категории
        productPage.addProductToCart("Phones", "Nokia lumia 1520");
        confirmAlert();
        driver.navigate().back();
        productPage.addProductToCart("Laptops", "Sony vaio i5");
        confirmAlert();
        driver.navigate().back();
        productPage.addProductToCart("Monitors", "ASUS Full HD");

        //4.Сравнить цену из общего списка и с карточки товара
        //здесь ничего нет,потмоу что я тупо не заметила этот пункт и стала писать дальше

        // 5. Перейти в корзину и убедиться, что общая цена посчитана верно
        homePage.clickCart();
        double phonePrice = productPage.getProductPrice("Nokia lumia 1520");
        double laptopPrice = productPage.getProductPrice("Sony vaio i5");
        double monitorPrice = productPage.getProductPrice("ASUS Full HD");
        double expectedTotal = phonePrice + laptopPrice + monitorPrice;
        String total = cartPage.getTotalPrice();
        double actualTotal = Double.parseDouble(total.replace("$", "").trim());
        assertEquals(expectedTotal, actualTotal, "Общая цена в корзине неверна!");

        // 6. Оформить заказ
        cartPage.placeOrder();
        checkoutPage.fillInOrderForm("John", "Canada", "Toronto", "12340000567809876", "10", "28");
        checkoutPage.submitOrder();

        //7.Убедиться что дата в конце заказа совпадает с датой в системе
        String orderDate = checkoutPage.getOrderDate();
        String currentDate = checkoutPage.getCurrentDate();
        assertEquals(currentDate, orderDate, "Дата заказа не совпадает с текущей датой!");
    }

    private void confirmAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

