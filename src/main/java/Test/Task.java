package Test;

import Configuration.SeleniumBase;
import POM.TaskPOM;
import com.opencsv.CSVReader;
import org.testng.TestNG;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

public class Task extends SeleniumBase
{
    private TaskPOM testFlow;

    @BeforeMethod
    private void setUpObjects() {
        testFlow = new TaskPOM(driver);
    }

    /*
    * This is the First test case of Homepage
    * */
    @Test
    public void firstTask() throws InterruptedException, IOException {
       int count = 0;
        String csv_file = "./Data/HomePageData.csv";
        CSVReader reader = new CSVReader(new FileReader(csv_file));
        String[] cell = reader.readNext();
        while ((cell = reader.readNext()) != null)
        {
            String productsCategory = cell[0];
            String revenueDetails = cell[1];
            String mobileAppsNotification = cell[2];
            String storeTraffic  = cell[3];
            String mobileDevices = cell[4];
            String sellProductsMorethenOne = cell[5];
            String repeatPurchase = cell[6];
            String impulsePurchase = cell[7];
            String socialMediaFollowers  = cell[8];
            String storeName = cell[9];
            String emailID = cell[10];

            testFlow.homeScreen(productsCategory,revenueDetails,mobileAppsNotification,storeTraffic,mobileDevices,sellProductsMorethenOne,
                    repeatPurchase,impulsePurchase,socialMediaFollowers,storeName,emailID,count);
            count++;
        }
    }
}
