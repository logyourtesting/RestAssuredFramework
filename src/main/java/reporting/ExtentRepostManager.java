package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.http.Header;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExtentRepostManager {

    private static ExtentReports extent;

    public static ExtentReports CreateExtent(String fileName, String reportName, String title) {
        if (extent == null) { // Ensure singleton pattern (only one instance)
            extent = new ExtentReports();
        }
        ExtentSparkReporter extentReports = new ExtentSparkReporter(fileName);
        extentReports.config().setReportName(reportName);
        extentReports.config().setDocumentTitle(title);
        extentReports.config().setTheme(Theme.DARK);
        extentReports.config().setEncoding("utf-8");

        extent.attachReporter(extentReports);
        return extent;


    }

    public static String getReportNameWithTimeStamp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");

        // Use LocalDateTime instead of LocalDate to include time
        LocalDateTime localDateTime = LocalDateTime.now();
        String formattedTime = dateTimeFormatter.format(localDateTime);
        return "TestReport_" + formattedTime + ".html";
    }

    public static void logPassDetail(String log) {
        SetUp.extentTestThreadLocal.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
    }

    public static void logFailDetail(String log) {
        SetUp.extentTestThreadLocal.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
    }

    public static void logExceptionDetails(String log) {
        SetUp.extentTestThreadLocal.get().fail(log);
    }

public static void logInfoDetail(String log) {
    SetUp.extentTestThreadLocal.get().info(MarkupHelper.createLabel(log, ExtentColor.CYAN));
}

public static void logSkipDetail(String log) {
    SetUp.extentTestThreadLocal.get().skip(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
}


    public static void logJSON(String JSON) {
        SetUp.extentTestThreadLocal.get().skip(MarkupHelper.createCodeBlock(JSON, CodeLanguage.JSON));
    }


    public static void logHeaders(List<Header> headerList) {
         String [][] arrayHeader= headerList.stream().map(header -> new String [] {header.getName(),header.getValue()}).toArray(String [][]::new);
        SetUp.extentTestThreadLocal.get().skip(MarkupHelper.createTable(arrayHeader));
    }


}
