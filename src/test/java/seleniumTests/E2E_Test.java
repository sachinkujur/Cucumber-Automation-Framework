package seleniumTests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.sql.DriverManager;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class E2E_Test {
    private static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Prefme_Matrix\\OneDrive\\Documents\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://shop.demoqa.com");

        driver.navigate().to("https://shop.demoqa.com/?s=" + "dress" + "&post_type=product");

        List<WebElement> items = driver.findElements(By.className("noo-product-inner"));
        items.get(0).click();
        driver.findElement(By.cssSelector("body > div:nth-child(2) > div:nth-child(4) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > img:nth-child(1)")).click();

//        driver.findElement(By.cssSelector("select[name='attribute_pa_color']")).click();
        Select drpColor = new Select(driver.findElement(By.cssSelector("select[name='attribute_pa_color']")));
        drpColor.selectByIndex(1);

//        driver.findElement(By.cssSelector("select[name='attribute_pa_size']")).click();
        Select drpSize = new Select(driver.findElement(By.cssSelector("select[name='attribute_pa_size']")));
        drpSize.selectByIndex(1);

        WebElement addToCart = driver.findElement(By.cssSelector("button.single_add_to_cart_button"));
        addToCart.click();

        WebElement cart = driver.findElement(By.cssSelector(".cart-button"));
        cart.click();

        WebElement continueToCheckout = driver.findElement(By.cssSelector(".checkout-button.alt"));
        continueToCheckout.click();

        Thread.sleep(5000);
        WebElement firstName = driver.findElement(By.cssSelector("#billing_first_name"));
        firstName.sendKeys("Lakshay");

        WebElement lastName = driver.findElement(By.cssSelector("#billing_last_name"));
        lastName.sendKeys("Sharma");

        WebElement emailAddress = driver.findElement(By.cssSelector("#billing_email"));
        emailAddress.sendKeys("test@gmail.com");

        WebElement phone = driver.findElement(By.cssSelector("#billing_phone"));
        phone.sendKeys("07438862327");

//        WebElement countryDropDown = driver.findElement(By.xpath("//span[@title='India']"));
//        countryDropDown.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById(\"select2-billing_country-container\").click();");

//        #select2-billing_country-container
        Thread.sleep(2000);

        List<WebElement> countryList = driver.findElements(By.cssSelector("#select2-drop ul li"));
        for(WebElement country : countryList){
            if(country.getText().equals("India")) {
                country.click();
                Thread.sleep(3000);
                break;
            }
        }

        WebElement city = driver.findElement(By.cssSelector("#billing_city"));
        city.sendKeys("Delhi");

        WebElement address = driver.findElement(By.cssSelector("#billing_address_1"));
        address.sendKeys("Shalimar Bagh");

        WebElement postcode = driver.findElement(By.cssSelector("#billing_postcode"));
        postcode.sendKeys("110088");

//        WebElement shipToDifferetAddress = driver.findElement(By.cssSelector("#ship-to-different-address-checkbox"));
//        shipToDifferetAddress.click();
//        Thread.sleep(3000);

        List<WebElement> paymentMethod = driver.findElements(By.cssSelector("ul.wc_payment_methods li"));
        paymentMethod.get(0).click();
        Thread.sleep(2000);

        WebElement acceptTC = driver.findElement(By.cssSelector("input[name='terms']"));
        acceptTC.click();

        WebElement placeOrder = driver.findElement(By.cssSelector("#place_order"));
        placeOrder.submit();

        driver.quit();

    }
}
