package com.praveen.psr.asi.coretestmodule;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.praveen.psr.asi.consoleandUI.ASIConsole;
import com.praveen.psr.asi.consoleandUI.ASI_UI;
import com.praveen.psr.asi.consoleandUI.ConfigurationLoader;
import com.praveen.psr.asi.excelreader.ExcelProcessAndConfiguration;
import com.praveen.psr.asi.frameworkcore.TestCase;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.praveen.psr.asi.frameworkcore.TestStepExecutor;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.TestRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

/**
 *
 * @author temp
 */
public class CoreTestNGTestClass {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    public static int dataproviderthreadcount;
    static ExtentReports ExtentReportOBJ;
    ExtentTest extentTestOBJ;
    int counter;
    public static boolean Userforcequitstatus = false;

    public static String commondate;

    @BeforeClass
    public void TestNGConnfiguationBeforeRun(ITestContext context) throws IOException {
        context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(dataproviderthreadcount);
        context.getCurrentXmlTest().getSuite().setPreserveOrder("false");
        // context.getCurrentXmlTest().getSuite().setListeners((new ArrayList<>().add("ASIListener")));
        ASIConsole.out.println("Test will execute at " + dataproviderthreadcount + " parallel speeds !");
    }

    @BeforeTest
    public void BeforeAllTests(ITestContext ITC) throws IOException, URISyntaxException {
        TestRunner runner = (TestRunner) ITC;
        runner.setOutputDirectory(ExcelProcessAndConfiguration.ReportPath + "\\TestNG-Report");
        ExtentReportOBJ = new ExtentReports(ExcelProcessAndConfiguration.ReportPath + "\\Extent-Report\\" + ExcelProcessAndConfiguration.ScreensnapPrefix + "_" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + ".html", true);
        //extent.addSystemInfo("Environment","Environment Name")

        ExtentReportOBJ.loadConfig(new File(ConfigurationLoader.default_directory + "\\extent-config.xml"));
        ASIConsole.out.println("Report Configurations set sucessfully!");
    }

    @Test(dataProvider = "TestCaseData")
    public void executeTestCase(TestCase TestCaseInstance) throws InterruptedException, FileNotFoundException, Exception, Throwable {
        {

            extentTestOBJ = ExtentReportOBJ.startTest(TestCaseInstance.getTestCaseNumber(), TestCaseInstance.getTestCaseDescription());
            TestCaseInstance.setLogger(extentTestOBJ);

            if (Userforcequitstatus == false) {
                ASIConsole.out.println("Test Case " + TestCaseInstance.getTestCaseNumber() + " has started on thread - " + Thread.currentThread().getId());
                new TestStepExecutor().executeTestCase(TestCaseInstance);
            } else {
                throw new SkipException("TestCase has been skipped as part of Force quit user action!");
            }
            //ExtentReportOBJ.endTest(TestCaseInstance.getLogger());

        }
    }

    @AfterMethod
    public void AfterEachTest(ITestResult result) throws Exception, Throwable {
        TestCase TC = (TestCase) result.getParameters()[0];
        ASIConsole.out.println("Test Case " + TC.getTestCaseNumber() + " is complete!");
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                ASIConsole.out.println(TC, "Failed!");
                //Logger.getLogger(CoreTestNGTestClass.class.getName()).log(Level.SEVERE, null, result.getThrowable());
                String screenshotPath = "";
                boolean drivernull = false;
                //result.getThrowable().printStackTrace();
                if (TC.getAutomationTechnology().equals("Selenium")) {
                    if (TC.getSeleniumWebDriver() != null) {
                        screenshotPath = new TestStepExecutor().getScreenshot(TC.getSeleniumWebDriver(), TC.getTestCaseNumber(), TC.getTestCaseNumber()+"\\FAIL");
                        TC.getLogger().log(LogStatus.FAIL, TC.getCurrentExecutingStepDetails() + "\n" + "<h5 style=\"color: #ff0000\" >FAILED : </h5><p style=\"color: #ff0000\">" + result.getThrowable() + "</p>" + TC.getLogger().addScreenCapture(screenshotPath), result.getThrowable());
                    } else {
                        drivernull = true;
                        TC.getLogger().log(LogStatus.FAIL, TC.getCurrentExecutingStepDetails() + "\n" + "<h5 style=\"color: #ff0000\" >FAILED : </h5><p style=\"color: #ff0000\">" + result.getThrowable() + "</p>", result.getThrowable());
                    }
                } else {
                    if (TC.getAppiumDriver() != null) {
                        screenshotPath = new TestStepExecutor().getScreenshotAppium(TC.getAppiumDriver(), TC.getTestCaseNumber(), TC.getTestCaseNumber()+"\\FAIL");
                        TC.getLogger().log(LogStatus.FAIL, TC.getCurrentExecutingStepDetails() + "\n" + "<h5 style=\"color: #ff0000\" >FAILED : </h5><p style=\"color: #ff0000\">" + result.getThrowable() + "</p>" + TC.getLogger().addScreenCapture(screenshotPath), result.getThrowable());
                    } else {
                        drivernull = true;
                        TC.getLogger().log(LogStatus.FAIL, TC.getCurrentExecutingStepDetails() + "\n" + "<h5 style=\"color: #ff0000\" >FAILED : </h5><p style=\"color: #ff0000\">" + result.getThrowable() + "</p>", result.getThrowable());
                    }
                }
                destroycreateddriverobjects(TC, drivernull);
                break;
            case ITestResult.SKIP:
                ASIConsole.out.println(TC, "Skipped!");
                TC.getLogger().log(LogStatus.SKIP, "This Test Case has been skipped as part of User Force Quit Action!");
                //TC.getLogger().log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
                break;
            case ITestResult.SUCCESS:
                ASIConsole.out.println(TC, "Passed!");
                destroycreateddriverobjects(TC, false);
                //TC.getLogger().log(LogStatus.PASS, "Test Case " + extentTestOBJ.getDescription() + " Success is " + result.getName());
                break;
            default:
                break;
        }

        ExtentReportOBJ.endTest(TC.getLogger());
        ++counter;
        if (ConfigurationLoader.UserInteractionparameter == 1) {
            ASI_UI.addcompletionprogress(counter);
        }
    }

    public void destroycreateddriverobjects(TestCase TC, boolean drivernull) throws Throwable {
        if (TC.getAutomationTechnology().equals("Selenium")) {
            try {
                if (drivernull == false) {
                    TC.getSeleniumWebDriver().close();
                }
                ASIConsole.out.println("Selenium Driver Closed for testcase " + TC.getTestCaseNumber());
            } catch (NullPointerException Ne) {
            }
        } else {
            if (drivernull == false) {
                TC.getAppiumDriver().quit();
            }
            ASIConsole.out.println("Appium App Closed for testcase " + TC.getTestCaseNumber());
            ExcelProcessAndConfiguration.DriverManagerObjects.get(TC.getDriverManagerKey()).setAppiumDevicelockstatus(false);
        }
    }

    /**
     * Here DataProvider returning value on the basis of test method name
     *
     * @param m
     * @return
     *
     */
    @DataProvider(name = "TestCaseData", parallel = true)
    public Object[][] getData(Method m) {
        //return ExcelInputSheet.getTestCaseObjects();
        if (ConfigurationLoader.UserInteractionparameter == 1) {
            ASI_UI.setMaxlimit2progress(ExcelProcessAndConfiguration.TestNGDataProviderInput.length);
        }
        return ExcelProcessAndConfiguration.TestNGDataProviderInput;
    }

    @AfterTest
    public void afterallTests() throws IOException, InterruptedException {
        ExtentReportOBJ.flush();
        ASIConsole.out.println("All TestCase result data has been flushed to Extent Reports");
        ExtentReportOBJ.close();
        ASIConsole.out.println("Extent report Generated sucessfully");
        if (ConfigurationLoader.UserInteractionparameter == 1) {
            ASI_UI.EndAllTests();
        }
        ASIConsole.writeLogData();
    }

}
