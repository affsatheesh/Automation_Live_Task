package POM;

import Helper.GenricHelper;
import Util.Listeners;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class TaskPOM extends GenricHelper
{
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    private static final Logger Logg = LogManager.getLogger(TaskPOM.class.getName());

    public TaskPOM(WebDriver driver) {
        GenricHelper.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath ="//div[@class='ChoiceContent-sc-__sc-m4g23g-0 ImOCO']")
    List<WebElement> ProductCategory;

    @FindBy(xpath ="//input[@name='organization']")
    WebElement StoreName;

    @FindBy(xpath = "//input[@name='email']")
    WebElement EmailID;

    @FindBy(xpath = "//*[text()='Download Report']")
    WebElement Download;

    public void homeScreen(String productsCategory,String revenueDetails,String mobileAppsNotification,String storeTraffic,
                           String mobileDevices,
                           String sellProductsMorethenOne,String repeatPurchase,String impulsePurchase,String socialMediaFollowers,
                           String storeName,
                           String emailID,int count) throws InterruptedException, IOException {

        Listeners.test.log(Status.PASS,"Enter the HomeScreen");

        scrollTo(500);
        if(count == 0)
        {
            driver.switchTo().frame(0);
            Logg.info("Switching to Frame");
            Listeners.test.log(Status.PASS, "Switching to Frame");
        }
        clickOn1(ProductCategory,productsCategory);
        Listeners.test.log(Status.PASS, "Enter the Products Category Details : " +productsCategory);
        Logg.info("Enter the Products Category Details");

        clickOn1(ProductCategory,revenueDetails);
        Listeners.test.log(Status.PASS, "Enter the RevenueDetails : " + revenueDetails);
        Logg.info("Enter the RevenueDetails");

        clickOn1(ProductCategory,mobileAppsNotification);
        Listeners.test.log(Status.PASS, "Enter the Mobile Apps Notification Details : "+ mobileAppsNotification);
        Logg.info("Enter the Mobile Apps Notification Details");

        clickOn1(ProductCategory,storeTraffic);
        Listeners.test.log(Status.PASS, "Enter the Store Traffic Details : " +storeTraffic);
        Logg.info("Enter the Store Traffic Details");

        clickOn1(ProductCategory,mobileDevices);
        Listeners.test.log(Status.PASS, "Enter the Mobile Devices Details : " + mobileDevices);
        Logg.info("Enter the Mobile Devices Details");

        clickOn1(ProductCategory,sellProductsMorethenOne);
        Listeners.test.log(Status.PASS, "Enter the Sell Products More then one Details : "+ sellProductsMorethenOne);
        Logg.info("Enter the Sell Products More then one Details");

        clickOn1(ProductCategory,repeatPurchase);
        Listeners.test.log(Status.PASS, "Enter the Repeat purchase Details : "+ repeatPurchase);
        Logg.info("Enter the Repeat purchase Details");

        clickOn1(ProductCategory,impulsePurchase);
        Listeners.test.log(Status.PASS, "Enter the impulse Purchase Details : " + impulsePurchase);
        Logg.info("Enter the impulse Purchase Details");

        clickOn1(ProductCategory,socialMediaFollowers);
        Listeners.test.log(Status.PASS, "Enter the socialMediaFollowers Details : " + socialMediaFollowers);
        Logg.info("Enter the socialMediaFollowers Details");

        sendKeys(StoreName,storeName+ Keys.ENTER);
        Listeners.test.log(Status.PASS, "Enter the StoreName Details : " + storeName);
        Logg.info("Enter the StoreName Details");

        sendKeys(EmailID,emailID+Keys.CONTROL+Keys.ENTER);
        Listeners.test.log(Status.PASS, "Enter the Email ID Details : " + emailID);
        Logg.info("Enter the Email ID Details");

        Thread.sleep(5000);
        clickOn(Download);
        Listeners.test.log(Status.PASS, "Survey PDF Document Downloaded ");
    }
}