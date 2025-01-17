package UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

    public class ProductPage {
        private WebDriver driver;

        public ProductPage(WebDriver driver) {
            this.driver = driver;
        }

        public void clickCategory(String category) {
            driver.findElement(By.xpath("//a[contains(text(),'" + category + "')]")).click();
        }

        public void clickProduct(String productName) {
            driver.findElement(By.xpath("//a[text()='" + productName + "']")).click();
        }

        public void addToCart() {
            driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]")).click();
        }

        public double getProductPrice(String productName) {
            String priceText = driver.findElement(By.xpath("//a[text()='" + productName + "']/following-sibling::h2")).getText();
            return Double.parseDouble(priceText.replace("$", "").trim());
        }
        public double getPriceFromList(String productName) {
            String priceText = driver.findElement(By.xpath("//a[text()='" + productName + "']/following-sibling::h2")).getText();
            return Double.parseDouble(priceText.replace("$", "").trim());
        }
        public void addProductToCart(String category, String product) {
            clickCategory(category);
            clickProduct(product);
            addToCart();
        }
    }


