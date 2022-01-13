package Helper;

import Util.Listeners;
import com.opencsv.CSVReader;
import org.codehaus.plexus.interpolation.multi.DelimiterSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import javax.rmi.CORBA.Util;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class GenricHelper
{
    public static WebDriver driver = null;
    public void sendKeys(WebElement element, String value) throws InterruptedException {
        new WebDriverWait(driver, 15).
                until(ExpectedConditions.visibilityOf(element));
        Thread.sleep(1000);
        element.sendKeys(value);
    }

    public void Click(WebElement element) throws InterruptedException {
        new WebDriverWait(driver, 15).
                until(ExpectedConditions.visibilityOf(element));
        Thread.sleep(5000);
        element.click();
    }

    public void ScrollclickOn(WebElement element,WebElement element1,WebElement element2) throws InterruptedException {
        Actions action = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        Thread.sleep(1000);
        action.moveToElement(element1).click().build().perform();
        action.doubleClick(element2).build().perform();
    }

    public void directClick(WebElement element,WebElement element2) throws InterruptedException {
        Actions action = new Actions(driver);
        Thread.sleep(1000);
        action.moveToElement(element).click().build().perform();
        action.doubleClick(element2).build().perform();
    }

    public void ColorSelect(WebElement element,String value) throws InterruptedException
    {
        String color ="#03a9f4";

        Actions action = new Actions(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        WebElement element1 = driver.findElement(By.xpath("//div[@title='" +value+ "']"));
        action.click(element1).build().perform();
        Thread.sleep(10000);
    }

    public void clickOn(WebElement element)
    {
        new WebDriverWait(driver, 30).
                until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void iFrame(int number)
    {
        driver.switchTo().frame(number);
    }
    public void clickOn1(List<WebElement> elements , String Value) throws InterruptedException {
        Thread.sleep(6000);
        for (WebElement list :elements)
        {
           String lis =  list.getText();
            if(lis.equals(Value))
            {
                JavascriptExecutor executor1 = (JavascriptExecutor) driver;
                executor1.executeScript("arguments[0].click();", list);
            }
        }
    }


    public boolean isDisplayed(WebElement element) {
        new WebDriverWait(driver, 15).
                until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }

    public void isElementDisplayed(WebElement element, String elementName) {
        if (element.isDisplayed())
            Reporter.log(elementName + " is  Displayed");
        else
            Reporter.log(elementName + " is not Dispalyed");
    }

    public void selectByIndex(String option, int i) {
        WebElement dropDownMenuSeleniumObj = driver.findElement(By.xpath(option));
        Select dpMenu = new Select(dropDownMenuSeleniumObj);
        dpMenu.selectByIndex(i);
    }

    public void fileDownload(String value,String filename) throws InterruptedException {
         Thread.sleep(20000);
        File file = new File(value);
        File[] downloadlist = file.listFiles();
        for (File all : downloadlist) {
            if(all.getName().equalsIgnoreCase(filename))
            {
                System.out.println("FileDownload");
            }
        }
    }


    // Action Classes
    public void clickOnElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).click().perform();
    }

    public void rightClickOnElement(WebElement element) {
        Actions act = new Actions(driver);
        act.contextClick(element).perform();
    }

    public void doubleClickOnElement(WebElement element) {
        Actions act = new Actions(driver);
        act.doubleClick(element).perform();
    }

    public void dragAndDropAction(WebElement from, WebElement to) {
        Actions act = new Actions(driver);
        act.dragAndDrop(from, to).perform();
    }

    public void scrollTo(int number)
    {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0,"+number+")", "");
    }


    public void scrolldown(int n) {
        for (int i = 0; i <= n; i++) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy(0,200)");
        }
    }

    public static void scrollToElementText(String txt) throws InterruptedException {

        WebElement element = driver.findElement(By.xpath("//*[contains(text(),'" + txt + "')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000);
    }

    public void clickAndClick(String value, String txt, String plus, String value1, String value2, String value3, String save) throws InterruptedException {

        WebElement YesORnO = driver.findElement(By.id("submenu"));
        Select select = new Select(YesORnO);
        List<WebElement> options = select.getOptions();
        int fullList = options.size();

        for (WebElement webElement : options) {
            String text = webElement.getText();
            select.selectByVisibleText(value);
        }

        String plusbutton = "Plus";
        if (plusbutton.equalsIgnoreCase(plus)) {
            driver.findElement(By.xpath("/html/body/app-root/app-default-layout/div/div[3]/div[2]/main/app-add-module-details/div/div[2]/div/div[1]/form/div[2]/div[1]/span")).click();
            driver.findElement(By.xpath("html/body/app-root/app-default-layout/div/div[3]/div[2]/main/app-add-module-details/div/div[2]/div/div[1]/form/div[2]/div[2]/" + txt + "/div/div[1]/div/input")).sendKeys(value1);
            driver.findElement(By.xpath("/html/body/app-root/app-default-layout/div/div[3]/div[2]/main/app-add-module-details/div/div[2]/div/div[1]/form/div[2]/div[2]/" + txt + "/div/div[2]/div/input")).sendKeys(value2);
            driver.findElement(By.xpath("/html/body/app-root/app-default-layout/div/div[3]/div[2]/main/app-add-module-details/div/div[2]/div/div[1]/form/div[2]/div[2]/" + txt + "/div/div[3]/div/div/input")).sendKeys(value3);

        } else {
            driver.findElement(By.xpath("html/body/app-root/app-default-layout/div/div[3]/div[2]/main/app-add-module-details/div/div[2]/div/div[1]/form/div[2]/div[2]/" + txt + "/div/div[1]/div/input")).sendKeys(value1);
            driver.findElement(By.xpath("/html/body/app-root/app-default-layout/div/div[3]/div[2]/main/app-add-module-details/div/div[2]/div/div[1]/form/div[2]/div[2]/" + txt + "/div/div[2]/div/input")).sendKeys(value2);
            driver.findElement(By.xpath("/html/body/app-root/app-default-layout/div/div[3]/div[2]/main/app-add-module-details/div/div[2]/div/div[1]/form/div[2]/div[2]/" + txt + "/div/div[3]/div/div/input")).sendKeys(value3);
        }
        Thread.sleep(2000);
        String savebutton = "Save";
        if (savebutton.equalsIgnoreCase(save)) {
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[text()='Save']")).click();
            Thread.sleep(2000);
        } else {
            System.out.println("its not working");
        }
    }


    public void Uploadimages(WebElement element, String imagefiles) throws InterruptedException, AWTException {
        Thread.sleep(2000);
        Actions action = new Actions(driver);
        action.moveToElement(element).click().build().perform();
        Thread.sleep(2000);
        StringSelection imageselection = new StringSelection(imagefiles);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imageselection, null);
        Thread.sleep(2000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
    }

    public void DrapdownSelect(WebElement element, String value1) throws InterruptedException, IOException, AWTException {

       // WebElement module = driver.findElement(By.id(value));
        Select select = new Select(element);
        List<WebElement> options = select.getOptions();
        int fullList = options.size();
        for (WebElement webElement : options) {
            String text = webElement.getText();
            select.selectByVisibleText(value1);
        }
    }

    public void DynamicDrapdownSelect(WebElement element, WebElement element1) throws InterruptedException, IOException, AWTException {

        element.click();
        element1.click();
    }

    public void AddingData() throws InterruptedException, IOException, AWTException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/app-root/app-default-layout/div/div[3]/div[1]/app-leftbar/div[1]/ul/li[12]/a/span[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='Add New Module']")).click();
        Thread.sleep(2000);

        String csv_file = "./Data/MasterData.csv";

        CSVReader reader = new CSVReader(new FileReader(csv_file));
        String[] cell = reader.readNext();
        while ((cell = reader.readNext()) != null) {

            String modname = cell[0];
            String ModuleTranslationPath = cell[1];
            String Order = cell[2];
            String Icon = cell[3];
            String EnterModule = cell[4];
            String SubMenuName = cell[5];
            String SubMenuPath = cell[6];
            String ComponentTranslationPath = cell[7];
            String plusbutton = cell[8];
            String xpathtxt = cell[9];

            String MainModuleEntering = "EnterMainModule";

            if (MainModuleEntering.equalsIgnoreCase(EnterModule)) {

                Thread.sleep(1000);
                driver.findElement(By.id("ModuleName")).sendKeys(modname);

                driver.findElement(By.id("OrderNo")).sendKeys(Order);

                driver.findElement(By.id("ModuleTranslationPath")).sendKeys(ModuleTranslationPath);

                Thread.sleep(1000);
                WebElement Document = driver.findElement(By.id("document"));
                Uploadimages(Document, Icon);
                Thread.sleep(1000);

            } else {
            }

            WebElement YesORnO = driver.findElement(By.id("submenu"));
            Select select = new Select(YesORnO);
            List<WebElement> options = select.getOptions();
            int fullList = options.size();

            for (WebElement webElement : options) {
                String text = webElement.getText();
                select.selectByVisibleText("Yes");
            }

            driver.findElement(By.xpath("html/body/app-root/app-default-layout/div/div[3]/div[2]/main/app-add-module-details/div/div[2]/div/div[1]/form/div[2]/div[2]/" + xpathtxt + "/div/div[1]/div/input")).sendKeys(SubMenuName);
            driver.findElement(By.xpath("/html/body/app-root/app-default-layout/div/div[3]/div[2]/main/app-add-module-details/div/div[2]/div/div[1]/form/div[2]/div[2]/" + xpathtxt + "/div/div[2]/div/input")).sendKeys(SubMenuPath);
            driver.findElement(By.xpath("/html/body/app-root/app-default-layout/div/div[3]/div[2]/main/app-add-module-details/div/div[2]/div/div[1]/form/div[2]/div[2]/" + xpathtxt + "/div/div[3]/div/div/input")).sendKeys(ComponentTranslationPath);

            String Plus = "ClickPlus";

            if (Plus.equalsIgnoreCase(plusbutton)) {
                driver.findElement(By.xpath("/html/body/app-root/app-default-layout/div/div[3]/div[2]/main/app-add-module-details/div/div[2]/div/div[1]/form/div[2]/div[1]/span")).click();
            } else {
                driver.findElement(By.xpath("//button[text()='Save']")).click();
                reader.readNext();
                Thread.sleep(2000);
            }

        }
    }

    public void AssignData() throws InterruptedException, IOException, AWTException {

        driver.findElement(By.xpath("//span[text()=' System Security']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='Assign User Module']")).click();
        Thread.sleep(2000);

        String csv_file = "./Data/AssignMasterData.csv";

        CSVReader reader = new CSVReader(new FileReader(csv_file));
        String[] cell = reader.readNext();
        while ((cell = reader.readNext()) != null) {

            String AssignModules = cell[0];
            String Designation = cell[1];
         /*   String UserName = cell[2];
            String SelectSubMenuPath = cell[3];*/


            WebElement AssignmodulePath = driver.findElement(By.id("module"));
            WebElement AssignDesignation = driver.findElement(By.id("Designation"));


       //     WebElement SelectingUser = driver.findElement(By.xpath("//div[text()='" + UserName + "']"));


            Thread.sleep(2000);
            DrapdownSelect(AssignmodulePath,AssignModules);
            Thread.sleep(2000);

            Thread.sleep(2000);
            DrapdownSelect(AssignDesignation,Designation);
            Thread.sleep(2000);

            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"USer\"]/div/div[1]/span")).click();

            List<WebElement> options= driver.findElements(By.xpath("//li[@class='multiselect-item-checkbox ng-star-inserted']"));

            System.out.println(options);


        }
    }
}
