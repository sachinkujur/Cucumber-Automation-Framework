package stepDefinitions;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.vimalselvam.cucumber.listener.Reporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import cucumber.TestContext;

import static runners.TestRunner.extent;
import static runners.TestRunner.test;
//import cucumber.api.Scenario;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;

public class Hooks {

    TestContext testContext;

    public Hooks(TestContext context) {
        testContext = context;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
//        Reporter.assignAuthor("ToolsQA - Lakshay Sharma");
        test.assignAuthor("ToolsQA - Lakshay Sharma");
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            try {
                //This takes a screenshot from the driver at save it to the specified location
                File sourcePath = ((TakesScreenshot) testContext.getWebDriverManager().getDriver()).getScreenshotAs(OutputType.FILE);

                //Building up the destination path for the screenshot to save
                //Also make sure to create a folder 'screenshots' with in the cucumber-report folder
                File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".png");

                //Copy taken screenshot from source location to destination location
                Files.copy(sourcePath, destinationPath);

                //This attach the specified screenshot to the test
//                Reporter.addScreenCaptureFromPath(destinationPath.toString());

                test.fail(MediaEntityBuilder.createScreenCaptureFromPath(destinationPath.toString()).build());

            } catch (IOException e) {
            }
        }
    }


    @After(order = 0)
    public void AfterSteps() {
        testContext.getWebDriverManager().closeDriver();
    }

}
