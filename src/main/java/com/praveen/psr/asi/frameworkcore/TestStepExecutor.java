/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praveen.psr.asi.frameworkcore;

import com.praveen.psr.asi.consoleandUI.ASIConsole;
import com.praveen.psr.asi.keywordconfigs.ToolLevelStepControlKeywordConfigs;
import com.praveen.psr.asi.customExceptions.TextMismatchException;
import com.praveen.psr.asi.customExceptions.URLloadFailedException;
import com.praveen.psr.asi.excelreader.ExcelProcessAndConfiguration;
import com.praveen.psr.asi.frameworkcore.TestStep;
import com.praveen.psr.asi.keywordconfigs.appiumkeywords.AppiumDriverbasedKeywordConfigs;
import com.praveen.psr.asi.keywordconfigs.seleniumkeywords.SeleniumDriverbasedKeywordConfigs;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author temp
 */
public class TestStepExecutor {

    public static ExtentTest loggerX;

    public String getScreenshot(WebDriver driver, String TestCaseName, String foldergroup) throws Exception {
        //below line is just to append the date format with the screenshot name to avoid duplicate names 
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = ExcelProcessAndConfiguration.ScreenshotPath + "/" + foldergroup + "/" + ExcelProcessAndConfiguration.ScreensnapPrefix + "_" + TestCaseName + "_" + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        //Returns the captured file path
        return destination;
    }

    public String getScreenshotAppium(AppiumDriver<?> driver, String TestCaseName, String foldergroup) throws Exception {
        //below line is just to append the date format with the screenshot name to avoid duplicate names 
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = ExcelProcessAndConfiguration.ScreenshotPath + "/" + foldergroup + "/" + ExcelProcessAndConfiguration.ScreensnapPrefix + "_" + TestCaseName + "_" + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        //Returns the captured file path
        return destination;
    }

    public void executeTestSteps(TestCase TCOBJ, ArrayList<TestStep> TSList) throws TextMismatchException, Exception, IOException, Throwable {

        for (int i = 0; i < TSList.size(); i++) {
            ASIConsole.out.println(TCOBJ, "Step No : " + TCOBJ.StepNumber + " started");
            executeTestStep(TSList.get(i), TCOBJ, TCOBJ.StepNumber);
        }
    }

    public void executeTestCase(TestCase TCOBJ) throws TextMismatchException, Exception, Throwable {
        //ASIConsole.out.println(TCOBJ.getTestCaseNumber() + " has started on thread : " + Thread.currentThread().getId());
        // ASIConsole.out.println("Test Case : "+TCOBJ.getTestCaseNumber()+" : Test Steps : "+TCOBJ.getTestSteps());
        executeTestSteps(TCOBJ, TCOBJ.getTestSteps());

    }

    public void executeTestStep(TestStep TSOBJ, TestCase TCOBJ, int stepnumber) throws TextMismatchException, URLloadFailedException, IOException, Throwable {
        ObjectProperties OBJProp = ObjectProperties.ObjectRepositoryMap.get(TSOBJ.getObjKey());

        Method ToolLevelStepControlMethod;
        try {
            ToolLevelStepControlMethod = ToolLevelStepControlKeywordConfigs.class.getDeclaredMethod(TSOBJ.getActionKeyword());
            ToolLevelStepControlMethod.invoke(new ToolLevelStepControlKeywordConfigs(TCOBJ, TSOBJ.getReqParam(), new TestStepExecutor()));

        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            //ASIConsole.out.println("Some Exception Thrown"+ex.getStackTrace());
            throw ex.getCause();
//Logger.getLogger(TestStepExecutor.class.getName()).log(Level.SEVERE, null, ex);
            //   if(TSOBJ.getObjKey().equals("Object3"))
            //  throw new NullPointerException();
        } catch (NoSuchMethodException NSME) {
            /*TCOBJ.getLogger().log(LogStatus.PASS, TCOBJ.getTestCaseNumber() + "(Thread-" + Thread.currentThread().getId() + ") :: Step - " + TCOBJ.StepNumber + " : " + OBJProp.getObjectKey() + " | identifier : " + OBJProp.getAppiumIdentifier() + " | identifier value : " + OBJProp.getAppium_Identifier_Value() + " | action : " + TSOBJ.getActionKeyword());
            if (OBJProp.getObjectKey().equals("Object4")) {
                throw new TextMismatchException("Text is Mismatch!");
            }*/
            //ASIConsole.out.println("TC Number" + TCOBJ.getTestCaseNumber());
            // ASIConsole.out.println("Thread-" + Thread.currentThread().getId());
            //ASIConsole.out.println("Step - " + TCOBJ.StepNumber);
            //ASIConsole.out.println("Action Keyword : " + TSOBJ.getActionKeyword());
            String objkey = null;
            String objidentifier = null;
            String objidentifiervalue = null;

            if (OBJProp == null) {
                objkey = "No Object Mapped";
                objidentifier = "No Object Mapped";
                objidentifiervalue = "No Object Mapped";
            } else {
                objkey = OBJProp.getObjectKey();
                objidentifier = OBJProp.getAppiumIdentifier();
                objidentifiervalue = OBJProp.getAppium_Identifier_Value();
            }

            TCOBJ.setCurrentExecutingStepDetails("<h5>" + TCOBJ.getTestCaseNumber() + "(Thread-" + Thread.currentThread().getId() + ") :: Step - " + TCOBJ.StepNumber + "</h5> | Object : <p style=\"font-weight: bold\">" + objkey + "</p> | identifier : <p style=\"font-weight: bold\">" + objidentifier + "</p> | identifier value : <p style=\"font-weight: bold\">" + objidentifiervalue + "</p> | action : <p style=\"font-weight: bold\">" + TSOBJ.getActionKeyword() + "</p> | action parameters : <p style=\"font-weight: bold\">" + TSOBJ.getReqParam() + "</p>");

            String screenshotPath = "";
            if (TCOBJ.getAutomationTechnology().equals("Selenium")) {
                new SeleniumDriverbasedKeywordConfigs().executeSeleniumProcessStep(TCOBJ, TSOBJ, OBJProp);
                screenshotPath = new TestStepExecutor().getScreenshot(TCOBJ.getSeleniumWebDriver(), TCOBJ.getTestCaseNumber(), TCOBJ.getTestCaseNumber() + "\\PASS");
            } else {
                new AppiumDriverbasedKeywordConfigs().executeAppiumProcess(TCOBJ, TSOBJ, OBJProp);
                screenshotPath = new TestStepExecutor().getScreenshotAppium(TCOBJ.getAppiumDriver(), TCOBJ.getTestCaseNumber(), TCOBJ.getTestCaseNumber() + "\\PASS");
            }
            TCOBJ.getLogger().log(LogStatus.PASS, TCOBJ.getCurrentExecutingStepDetails() + "<h5 style=\"color: #00ff00\"> PASS </h5>\n" + TCOBJ.getLogger().addScreenCapture(screenshotPath));
            //ASIConsole.out.println(TCOBJ.getTestCaseNumber() + "(Thread-" + Thread.currentThread().getId() + ") :: Step - " + TCOBJ.StepNumber + " : " + OBJProp.getObjectKey() + " | identifier : " + OBJProp.getAppiumIdentifier() + " | identifier value : " + OBJProp.getAppium_Identifier_Value() + " | action : " + TSOBJ.getActionKeyword());

            ASIConsole.out.println(TCOBJ, "Step No : " + TCOBJ.StepNumber + " completed");
            ++TCOBJ.StepNumber;

            // 
        }

    }
}
