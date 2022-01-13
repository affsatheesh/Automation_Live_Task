package Util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ExtentReporterNG
{
	static ExtentReports extent;
	public static String OSName = System.getProperty("os.name");
	public static String OSArchitecture = System.getProperty("os.arch");
	public static String OSVersion = System.getProperty("os.version");
	public static String OSBit = System.getProperty("sun.arch.data.model");

	public static ExtentReports getReportObject() throws IOException {
		Date d = new Date();
		//String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
		String path =System.getProperty("user.dir")+"\\reports\\index.html";
		//String path = System.getProperty("user.dir")+"\\reports\\"+fileName;
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Vajro Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

	//	reporter.loadXMLConfig(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\"+"extent-config.xml"));
		extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Satheesh Kumar");
		extent.setSystemInfo("Browser", "Chorme Browser");
		extent.setSystemInfo("OS", OSName);
		extent.setSystemInfo("OS Version", OSVersion);
		extent.setSystemInfo("OS Architecture", OSArchitecture);
		extent.setSystemInfo("OS Bit", OSBit);
		extent.setSystemInfo("JAVA Version", System.getProperty("java.version"));
		extent.setSystemInfo("OS Bit", OSBit);
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setEncoding("utf-8");
		reporter.config().setProtocol(Protocol.HTTPS);
		return extent;
	}
}