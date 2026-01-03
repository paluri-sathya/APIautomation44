package core;

import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentReport;

import com.aventstack.extentreports.Status;

import java.lang.reflect.Method;

public abstract class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {

        String reportPath = System.getProperty("user.dir")
                + "/reports/API_Execution_Report.html";

        ExtentReport.init(reportPath);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        ExtentReport.createTest(method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentReport.test().log(Status.PASS, "Test Passed");

        } else if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReport.test().fail(result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentReport.test().log(Status.SKIP, "Test Skipped");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        ExtentReport.flush();
    }
}
