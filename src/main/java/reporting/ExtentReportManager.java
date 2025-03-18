package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.http.Header;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExtentReportManager { // ✅ Fixed Typo in Class Name

    private static ExtentReports extent;

    private ExtentReportManager() {} // ✅ Private Constructor to Prevent Instantiation

    // ✅ Singleton Implementation
    public static ExtentReports getInstance(String fileName, String reportName, String title) {
        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
            sparkReporter.config().setReportName(reportName);
            sparkReporter.config().setDocumentTitle(title);
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setEncoding("utf-8");
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    // ✅ Generates Unique Report Name with Timestamp
    public static String getReportNameWithTimeStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        return "TestReport_" + LocalDateTime.now().format(formatter) + ".html";
    }

    // ✅ Thread-Safe Logging Methods with Null Checks
    private static boolean isTestActive() {
        return SetUp.extentTestThreadLocal.get() != null;
    }

    public static void logPassDetail(String log) {
        if (isTestActive()) {
            SetUp.extentTestThreadLocal.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
        }
    }

    public static void logFailDetail(String log) {
        if (isTestActive()) {
            SetUp.extentTestThreadLocal.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
        }
    }

    public static void logExceptionDetails(String log) {
        if (isTestActive()) {
            SetUp.extentTestThreadLocal.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
        }
    }

    public static void logInfoDetail(String log) {
        if (isTestActive()) {
            SetUp.extentTestThreadLocal.get().info(MarkupHelper.createLabel(log, ExtentColor.CYAN));
        }
    }

    public static void logSkipDetail(String log) {
        if (isTestActive()) {
            SetUp.extentTestThreadLocal.get().skip(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
        }
    }

    public static void logJSON(String json) {
        if (isTestActive()) {
            SetUp.extentTestThreadLocal.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
        }
    }

    public static void logHeaders(List<Header> headerList) {
        if (isTestActive()) {
            String[][] arrayHeader = headerList.stream()
                    .map(header -> new String[]{header.getName(), header.getValue()})
                    .toArray(String[][]::new);
            SetUp.extentTestThreadLocal.get().info(MarkupHelper.createTable(arrayHeader));
        }
    }
}
