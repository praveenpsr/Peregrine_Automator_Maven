/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praveen.psr.asi.keywordconfigs.seleniumkeywords;

import com.praveen.psr.asi.consoleandUI.ASIConsole;
import com.praveen.psr.asi.customExceptions.URLloadFailedException;
import com.praveen.psr.asi.frameworkcore.ObjectProperties;
import com.praveen.psr.asi.frameworkcore.TestCase;
import com.praveen.psr.asi.frameworkcore.TestStep;
import io.appium.java_client.MobileElement;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author temp
 */
public class SeleniumDriverbasedKeywordConfigs {

    WebDriver driver;//Non static Driver declared

    public void executeSeleniumProcessStep(TestCase testCaseinstance, TestStep teststepinstance, ObjectProperties objectproperties) throws URLloadFailedException, MalformedURLException, IOException, InvocationTargetException, IllegalAccessException, Throwable {
        try {//try catch block 1- Opened
            //ASIConsole.out.println("com.maveric.asi.keywordconfigs.SeleniumDriverbasedKeywordConfigs.executeSeleniumProcessStep()");
            driver = testCaseinstance.getSeleniumWebDriver();
            String Keywordaction = teststepinstance.getActionKeyword();
            String Keywordrequiredparameter = teststepinstance.getReqParam();
            By byobject = null;
            WebElement WB = null;
            if (Keywordaction.equals("Skip")) {//Skip keyword functionality
            } else {
                switch (Keywordaction) {
                    case "Load URL": {
                        //Load URL keyword functionality
                       // ASIConsole.out.println("Load URL entered");
                        driver.manage().deleteAllCookies();
                        driver.manage().window().maximize();
                        driver.get(Keywordrequiredparameter);
                        try {
                            HttpURLConnection HUC = (HttpURLConnection) (new URL(Keywordrequiredparameter).openConnection());
                            HUC.setRequestMethod("HEAD");
                            HUC.connect();
                            //ASIConsole.out.println("Response Code : " + HUC.getResponseCode());
                            ASIConsole.out.println(testCaseinstance,"URL Loaded with Selenium on Browser!");
                        } catch (UnknownHostException UHE) {
                            throw new URLloadFailedException("Unable to Load URL! " + UHE);
                        }
                        try {//try catch block 2- Opened
                            driver.switchTo().alert().accept();
                        } catch (Exception e) {
                            //throw e;
                        }//try catch block 2- Closed
                        testCaseinstance.setDriverCurrentState(driver);
                        break;
                    }
                    case "Switch Iframe":
                    case "Switch SubIframe":
                    case "Switch DefaultFrame": {
                        switch (Keywordaction) {
                            case "Switch Iframe": {//Switch Iframe Keyword Functionality
                                //driver.switchTo().defaultContent();
                                driver.switchTo().frame(Keywordrequiredparameter);
                                testCaseinstance.setDriverCurrentState(driver);
                                break;
                            }
                            case "Switch SubIframe": {//Switch SubIframe Keyword Functionality
                                driver.switchTo().frame(Keywordrequiredparameter);
                                testCaseinstance.setDriverCurrentState(driver);
                                break;
                            }
                            case "Switch DefaultFrame": {//Switch Default Frame Keyword Functionality
                                driver.switchTo().defaultContent();
                                testCaseinstance.setDriverCurrentState(driver);
                                break;
                            }
                        }
                        break;
                    }
                    case "Accept Alert": {//Alert Accept Keyword Functionality
                        WebDriverWait wait = new WebDriverWait(driver, 2);
                        wait.until(ExpectedConditions.alertIsPresent());
                        Alert alert = driver.switchTo().alert();
                        alert.accept();
                        testCaseinstance.setDriverCurrentState(driver);
                        break;
                    }
                    default: {
                        //ASIConsole.out.println("Element Property : " + ElementProperty);
                        String ObjectProperty = objectproperties.getAppiumIdentifier();
                        String ObjectPropertyValue = objectproperties.getAppium_Identifier_Value();
                        byobject = getByObject(ObjectProperty, ObjectPropertyValue);
                        WB = VisibiltyWaitCall(driver, byobject);
                        new WebElementBasedKeywordConfigs().KeywordActionMappings(Keywordaction, WB, testCaseinstance, objectproperties, teststepinstance);
                        testCaseinstance.setDriverCurrentState(driver);
                        break;
                    }

                }
            }
        } catch (SecurityException | IllegalArgumentException | NoSuchMethodException ex) {
            throw ex;
            // Logger.getLogger(SeleniumDriverbasedKeywordConfigs.class.getName()).log(Level.SEVERE, null, ex);
        }
        //try catch block 1- Closed
        //try catch block 1- Closed

    }//End of Selenium execution

    public By getByObject(String ObjectIdentifier, String ObjectIdentifiervalue) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        By Byobject = null;
        Method ByMethod = By.class.getDeclaredMethod(ObjectIdentifier, String.class);
        //try catch block 3- Opened
        Byobject = (By) ByMethod.invoke(null, ObjectIdentifiervalue);
        //try catch block 3- Closed//try catch block 3- Closed//try catch block 3- Closed//try catch block 3- Closed//try catch block 3- Closed//try catch block 3- Closed//try catch block 3- Closed//try catch block 3- Closed
        return Byobject;
    }

    public WebElement Flentwaitcall(WebElement WB) {//Function to improve stabilty in Selenium by waiting until elements are found on the screen
        FluentWait wait = new FluentWait(WB);
        wait.withTimeout(30, TimeUnit.SECONDS);
        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
        wait.ignoring(NoSuchElementException.class);
        return WB;
    }//End of FLuentwaitcall

    public WebElement VisibiltyWaitCall(WebDriver driverinstance, By byobjectinstance) {//Function to improve stability using dynamic elements visibility
        WebDriverWait wait = new WebDriverWait(driverinstance, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byobjectinstance));
        driver = driverinstance;
        return driverinstance.findElement(byobjectinstance);
    }//End of VisibiltyWaitCall
}
