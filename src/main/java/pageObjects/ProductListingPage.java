package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import selenium.Wait;

public class ProductListingPage {
    WebDriver driver;

    public ProductListingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using = "button.single_add_to_cart_button")
    private WebElement btn_AddToCart;

    @FindAll(@FindBy(how = How.CLASS_NAME, using = "product-one-thumb"))
    private List<WebElement> prd_List;

    @FindBy(how = How.CSS, using = "select[name='attribute_pa_color']")
    private WebElement select_Color;

    @FindBy(how = How.CSS, using = "select[name='attribute_pa_size']")
    private WebElement select_Size;

    public void clickOn_AddToCart() {
        Select drp_Color = new Select(select_Color);
        drp_Color.selectByIndex(1);

        Select drp_Size = new Select(select_Size);
        drp_Size.selectByIndex(1);
        btn_AddToCart.click();
        Wait.untilJqueryIsDone(driver);
    }

    public void select_Product(int productNumber) {
        prd_List.get(productNumber).click();
    }

    public String getProductName(int productNumber) {
        return prd_List.get(productNumber).findElement(By.xpath("//body[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/h3[1]")).getText();
    }

}

