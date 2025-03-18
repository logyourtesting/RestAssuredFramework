package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class SetUp implements ITestListener {

    private static ExtentReports extentReports;
    public static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        String fileName = ExtentReportManager.getReportNameWithTimeStamp(); // ✅ Fixed typo
        String fullReportPath = System.getProperty("user.dir") + "/reports/" + fileName;
        extentReports = ExtentReportManager.getInstance(fullReportPath, "Test API Automation Report", "API Test Execution Report");
    }

    public void onFinish(ITestContext context) {
        if (extentReports != null) {
            extentReports.flush();
            System.out.println("✅ Extent Report generated successfully!");
        } else {
            System.err.println("❌ Extent Report was NOT initialized. Check ExtentReports setup.");
        }
    }


    @Override
    public void onTestStart(ITestResult result) {
        if (extentReports == null) { // ✅ Prevent NullPointerException
            System.err.println("❌ Error: ExtentReports is null. Ensure it is initialized.");
            return;
        }
        ExtentTest test = extentReports.createTest(
                "Test Name: " + result.getClass().getSimpleName() + " _ " + result.getMethod().getMethodName()
        );
        extentTestThreadLocal.set(test);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (extentTestThreadLocal.get() == null) return; // ✅ Prevent NullPointerException

        ExtentReportManager.logFailDetail(result.getThrowable().getMessage());

        // ✅ Format stack trace using ExtentReports Code Block
        String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
        ExtentReportManager.logExceptionDetails(
                MarkupHelper.createCodeBlock(stackTrace, CodeLanguage.XML).getMarkup()
        );
    }
}
