package Util;

import java.io.IOException;
import Configuration.SeleniumBase;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends SeleniumBase implements ITestListener {
	public static ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public Listeners() throws IOException {
	}

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		//extentTest.get().log(Status.PASS, "Test Passed");
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " PASSED"+"</b>";
		Markup m= MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().pass(m);
	}

	public void onTestFailure(ITestResult result) {
		String failureLogg="TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);
		extentTest.get().fail(result.getThrowable());
		String testMethodName = result.getMethod().getMethodName();


		try {
			//extentTest.get().addScreenCaptureFromPath(takeScreenshotAtEndOfTest(testMethodName));
			extentTest.get().log(Status.FAIL,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshotAtEndOfTest(testMethodName)).build());

		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {

		}
	}

	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>";
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTest.get().skip(m);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		extentTest.get().log(Status.PASS, "*** Test failed but within percentage % " + result.getMethod().getMethodName());
		String FailedSuccessPre ="*** Test failed but within percentage % " + result.getMethod().getMethodName() +"***";
		Markup m = MarkupHelper.createLabel(FailedSuccessPre, ExtentColor.INDIGO);
		extentTest.get().log(Status.INFO, m);
	}

	public void onStart(ITestContext context) {
//		extentTest.get().log(Status.FAIL, "*** TEST CASE STARTED ***");
////		String Start ="*** TEST CASE STARTED ***";
////		Markup m = MarkupHelper.createLabel(Start, ExtentColor.BLUE);
////		extentTest.get().log(Status.INFO, m);
	}

	public void onFinish(ITestContext context) {
		String End ="*** TEST CASE ENDING ***";
		Markup m = MarkupHelper.createLabel(End, ExtentColor.CYAN);
		extentTest.get().log(Status.INFO, m);
		extent.flush();
	}
}
