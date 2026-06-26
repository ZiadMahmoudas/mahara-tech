package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("--- Start Execution: " + context.getName() + " ---");

        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport2.html");
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("MaharaTech Test Execution Report");
        spark.config().setTheme(Theme.DARK);
        spark.config().setEncoding("UTF-8");
        spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Project Name", "Maharatech Automation");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Framework", "Selenium + TestNG");
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        System.out.println("Started Test: " + result.getName());
        ExtentTest extentTest = extent.createTest(result.getName());
        test.set(extentTest);
        test.get().log(Status.INFO, "Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Passed Test: " + result.getName());
        test.get().log(Status.PASS, "Test passed successfully ✅");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Failed Test: " + result.getName());
        test.get().log(Status.FAIL, "Test failed ❌");
        test.get().log(Status.FAIL, "Failed Test Name: " + result.getName());

        if (result.getThrowable() != null) {
            test.get().log(Status.FAIL, result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Skipped Test: " + result.getName());
        test.get().log(Status.SKIP, "Test skipped ⚠️");

        if (result.getThrowable() != null) {
            test.get().log(Status.SKIP, result.getThrowable());
        }
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println("--- End Execution ---");
        if (extent != null) {
            extent.flush();
        }
    }
}
