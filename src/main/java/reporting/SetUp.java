package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SetUp implements ITestListener {

    private static ExtentReports extentReports;
    public static ThreadLocal<ExtentTest> extentTestThreadLocal=new ThreadLocal<>();
    public void onStart(ITestContext context) {
        String fileName=ExtentRepostManager.getReportNameWithTimeStamp();
        String fullReportPath=System.getProperty("user.dir")+"/reports/"+fileName;
        extentReports=ExtentRepostManager.CreateExtent(fullReportPath,"Test API Automation Report","API Test Execution Report");
    }

    public void onFinish(ITestContext context) {
        if(extentReports!=null)
        {
            extentReports.flush();
            System.out.println("✅ Extent Report generated successfully!");
        }
    }

    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest("Test Name " + result.getClass().getPackageName() + " _ " + result.getMethod().getMethodName());
        extentTestThreadLocal.set(test);
    }

    public void onTestFailure(ITestResult result) {
        ExtentRepostManager.logFailDetail(result.getThrowable().getMessage());

       String stackTrace=Arrays.toString(result.getThrowable().getStackTrace());
        stackTrace=stackTrace.replaceAll(",","<br>");

        String formattedTrace = "<details>\n" +
                "  <summary>Click here to see Exceptional log</summary>\n" +
                stackTrace + "\n" + // ✅ Properly concatenate stackTrace
                "</details>";

        ExtentRepostManager.logExceptionDetails(formattedTrace);
    }

}
