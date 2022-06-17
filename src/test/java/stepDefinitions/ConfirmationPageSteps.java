package stepDefinitions;

import io.cucumber.java.en.Then;
import org.junit.Assert;

import cucumber.TestContext;
//import cucumber.api.java.en.Then;
import enums.Context;
import pageObjects.ConfirmationPage;

import java.util.Locale;

public class ConfirmationPageSteps {
    TestContext testContext;
    ConfirmationPage confirmationPage;

    public ConfirmationPageSteps(TestContext context) {
        testContext = context;
        confirmationPage = testContext.getPageObjectManager().getConfirmationPage();
    }

    @Then("^verify the order details$")
    public void verify_the_order_details() {
        String productName = (String) testContext.scenarioContext.getContext(Context.PRODUCT_NAME);
        Assert.assertTrue(confirmationPage.getProductNames().stream().map(x -> x.toUpperCase(Locale.forLanguageTag(productName))).filter(x -> x.contains((productName))).findFirst().get().length() > 0);
    }

}
