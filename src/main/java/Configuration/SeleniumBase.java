package Configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import Util.MyScreenRecorder;

public class SeleniumBase
{
    public static Properties properties = null;
    public static WebDriver driver =null ;
    private static Logger Logg = LogManager.getLogger(SeleniumBase.class.getName());

    public Properties loadpropertiesfile() throws IOException {
        FileInputStream fileinputstream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\App.properties");
        properties = new Properties();
        properties.load(fileinputstream);
        return properties;
    }

    @BeforeSuite
    public void launchbrowser() throws Exception {
        loadpropertiesfile();
        String browser = properties.getProperty("Browser");
        String vajroSite = properties.getProperty("Vajrosite");

        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
           // MyScreenRecorder.startRecording("First Test");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            Logg.info("Open the Browser");
            driver.get(vajroSite);
            Logg.info("Open the Test URL " + vajroSite );
        }
        else if(browser.equalsIgnoreCase("Headless"))
        {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
            driver = new ChromeDriver(chromeOptions);
        }

        else if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
    }
        @AfterSuite
        public void teardown() throws Exception {
        //    MyScreenRecorder.stopRecording();
            driver.quit();
        }


    public String takeScreenshotAtEndOfTest(String testname) throws IOException
    {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir")+ "/reports/" + testname + ".png";
        FileUtils.copyFile(scrFile, new File(currentDir));
        return currentDir;
    }
}

/*
| Helpers ===============>

WebDriverManager.chromedriver().browserVersion("77.0.3865.40").setup();
ChromeOptions options = new ChromeOptions();
options.addArguments("start-maximized");
options.addArguments("enable-automation");
options.addArguments("--no-sandbox");
options.addArguments("--disable-infobars");
options.addArguments("--disable-dev-shm-usage");
options.addArguments("--disable-browser-side-navigation");
options.addArguments("--disable-gpu");
*/