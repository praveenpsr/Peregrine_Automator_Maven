package com.praveen.psr.asi.frameworkcore;

import com.praveen.psr.asi.consoleandUI.ASIConsole;
import com.praveen.psr.asi.customExceptions.AppiumDriverLockedException;
import com.praveen.psr.asi.excelreader.ExcelProcessAndConfiguration;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;

public class TestCase {

    private String testCaseNumber;
    private String testCaseDescription;
    private String numOfExecutions;
    private String tcTrigger;
    private String DriverManagerKey;

    public String getAutomationTechnology() {
        return AutomationTechnology;
    }

    public void setAutomationTechnology(String AutomationTechnology) {
        this.AutomationTechnology = AutomationTechnology;
    }

    public Class getElementType() {
        return ElementType;
    }

    public void setElementType(Class ElementType) {
        this.ElementType = ElementType;
    }
    private String AutomationTechnology;
    private Class ElementType;

    private ArrayList<TestStep> testSteps;
    private WebDriver SeleniumWebDriver;
    private AppiumDriver<?> AppiumDriver;
    private boolean driverinitiationstatus = false;

    public int StepNumber = 1;
    private ExtentTest logger;
    private String currentExecutingStepDetails;

    public String getCurrentExecutingStepDetails() {
        return currentExecutingStepDetails;
    }

    public void setCurrentExecutingStepDetails(String currentExecutingStepDetails) {
        this.currentExecutingStepDetails = currentExecutingStepDetails;
    }

    public String getDriverManagerKey() {
        return DriverManagerKey;
    }

    public void setDriverManagerKey(String DriverManagerKey) {
        this.DriverManagerKey = DriverManagerKey;
    }

    public synchronized WebDriver getSeleniumWebDriver() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.driverinitiationstatus == false) {
            //System.setProperty("webdriver.chrome.SeleniumWebDriver", "D:\\AppiumAutomationStandalone\\ChromeDriver\\chromedriver.exe");
            //this.SeleniumWebDriver = new ChromeDriver();
            /*  DriverManager DMOBJ = new DriverManager();
            DMOBJ.setAutomationTechnology_1("Selenium");
            DMOBJ.setSystemParamters_2("D:\\AppiumAutomationStandalone\\ChromeDriver\\chromedriver.exe");
            DMOBJ.setDriverConstructor_4("ChromeDriver");*/
            this.SeleniumWebDriver = ExcelProcessAndConfiguration.DriverManagerObjects.get(this.DriverManagerKey).getSeleniumDriver();
            driverinitiationstatus = true;
            return SeleniumWebDriver;
        } else {
            return SeleniumWebDriver;
        }
    }

    public synchronized AppiumDriver<?> getAppiumDriver() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, MalformedURLException, ClassNotFoundException, NoSuchMethodException, InterruptedException, AppiumDriverLockedException, Throwable {
        if (this.driverinitiationstatus == false) {
            //System.setProperty("webdriver.chrome.SeleniumWebDriver", "D:\\AppiumAutomationStandalone\\ChromeDriver\\chromedriver.exe");
            //this.SeleniumWebDriver = new ChromeDriver();
            /*HashMap<String, Object> caps = new HashMap<String, Object>();
            caps.put("deviceName", "Galaxy Nexus API 22");
            caps.put("udid", "emulator-5554"); //DeviceId from "adb devices" command
            caps.put("platformName", "Android");
            caps.put("platformVersion", "5.1");
            caps.put("skipUnlock", "true");
            caps.put("appPackage", "com.citi.mobile.sg.dit");
            caps.put("appActivity", "com.citi.mobile.pt3.starter.authentication.view.activity.LoginActivity");
            caps.put("autoGrantPermissions", "true");
            caps.put("androidInstallTimeout", 15000);
            caps.put("noReset", "false");
            DriverManager driverprop = new DriverManager();
            driverprop.setAutomationTechnology_1("Appium");
            driverprop.setElementType_3("MobileElement");
            driverprop.setDriverConstructor_4("AndroidDriver");
            driverprop.setDriverURL_5("http://192.168.43.126:4723/wd/hub");
            driverprop.setDriverCaps_6(caps);*/
            driverinitiationstatus = true;
            DriverManager sharedappiumdriver = ExcelProcessAndConfiguration.DriverManagerObjects.get(this.DriverManagerKey);
            if (sharedappiumdriver.AppiumDevicelockstatus == true) {
                ASIConsole.out.println("Another Test Case is currently using this Device, waiting for completion..");
                for (int i = 0; i < 200; i++) {
                    Thread.sleep(3000);
                    if (sharedappiumdriver.AppiumDevicelockstatus == false) {
                        sharedappiumdriver.AppiumDevicelockstatus = true;
                        ASIConsole.out.println("Driver is released by the previous  testcase ,creating an instance for the current..");
                        this.AppiumDriver = sharedappiumdriver.getAppiumDriver();
                        ASIConsole.out.println("Instance created for the TC!");
                        break;
                    }
                    if (sharedappiumdriver.AppiumDevicelockstatus == false) {
                        throw new AppiumDriverLockedException(this.DriverManagerKey + " is locked for 20 minutes ,Failed to provide Driver instance to testcase");
                    }
                }
            } else {
                sharedappiumdriver.AppiumDevicelockstatus = true;
                this.AppiumDriver = sharedappiumdriver.getAppiumDriver();
            }
            return AppiumDriver;
        } else {
            return AppiumDriver;
        }
    }

    public void setAppiumDriver(AppiumDriver<?> AppiumDriver) {
        this.AppiumDriver = AppiumDriver;
    }

    public void setDriverCurrentState(WebDriver driver) {
        this.SeleniumWebDriver = driver;
    }

    public ExtentTest getLogger() {
        return logger;
    }

    public void setLogger(ExtentTest logger) {
        this.logger = logger;
    }

    public String getTestCaseNumber() {
        return testCaseNumber;
    }

    public void setTestCaseNumber(String testCaseNumber) {
        this.testCaseNumber = testCaseNumber;
    }

    public String getTestCaseDescription() {
        return testCaseDescription;
    }

    public void setTestCaseDescription(String testCaseDescription) {
        this.testCaseDescription = testCaseDescription;
    }

    public String getNumOfExecutions() {
        return numOfExecutions;
    }

    public void setNumOfExecutions(String numOfExecutions) {
        this.numOfExecutions = numOfExecutions;
    }

    public String getTcTrigger() {
        return tcTrigger;
    }

    public void setTcTrigger(String tcTrigger) {
        this.tcTrigger = tcTrigger;
    }

    public ArrayList<TestStep> getTestSteps() {
        return testSteps;
    }

    public void setTestSteps(ArrayList<TestStep> testSteps) {
        this.testSteps = testSteps;
    }

}
