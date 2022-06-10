package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.en.When;
import managers.*;
import pageObjects.CheckoutPage;
import testDataTypes.*;

import java.io.*;

public class CheckoutPageSteps {
    TestContext testContext;
    CheckoutPage checkoutPage;

    public CheckoutPageSteps(TestContext context) {
        testContext = context;
        checkoutPage = testContext.getPageObjectManager().getCheckoutPage();
    }

    @When("^enter \\\"(.*)\\\" personal details on checkout page$")
    public void enter_personal_details_on_checkout_page(String customerName) throws Throwable {
        Customer customer = FileReaderManager.getInstance().getJsonDataReader().getCustomerByName(customerName);
        checkoutPage.fill_PersonalDetails(customer);
    }

    @When("^select same delivery address$")
    public void select_same_delivery_address() {
//        checkoutPage.check_ShipToDifferentAddress(false);
    }

    @When("^select payment method as \"([^\"]*)\" payment$")
    public void select_payment_method_as_payment(String arg1) {
//        checkoutPage.select_PaymentMethod("CheckPayment");
    }

    @When("^place the order$")
    public void place_the_order() {
        checkoutPage.check_TermsAndCondition(true);
        checkoutPage.clickOn_PlaceOrder();
    }
}