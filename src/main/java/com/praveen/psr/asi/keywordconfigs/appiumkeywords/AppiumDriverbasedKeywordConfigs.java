/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praveen.psr.asi.keywordconfigs.appiumkeywords;

import com.praveen.psr.asi.customExceptions.URLloadFailedException;
import com.praveen.psr.asi.frameworkcore.ObjectProperties;
import com.praveen.psr.asi.frameworkcore.TestCase;
import com.praveen.psr.asi.frameworkcore.TestStep;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author temp
 */
public class AppiumDriverbasedKeywordConfigs {

    AppiumDriver<? extends WebElement> driver;//Non static Driver declared

    public void executeAppiumProcess(TestCase testCaseinstance, TestStep teststepinstance, ObjectProperties objectproperties) throws URLloadFailedException, MalformedURLException, IOException, InvocationTargetException, IllegalAccessException, Throwable {
        try {//try catch block 1- Opened
            //ASIConsole.out.println("com.maveric.asi.keywordconfigs.AppiumDriverbasedKeywordConfigs.executeAppiumProcess()");
            driver = testCaseinstance.getAppiumDriver();
            String Keywordaction = teststepinstance.getActionKeyword();
            String Keywordrequiredparameter = teststepinstance.getReqParam();
            By byobject = null;
            MobileElement MB = null;
            if (Keywordaction.equals("Skip")) {//Skip keyword functionality
            } else {
                switch (Keywordaction) {
                   
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
                        MB = VisibiltyWaitCall(driver, byobject);
                        new MobileElementBasedKeywordConfigs().KeywordActionMappings(Keywordaction, MB, testCaseinstance, objectproperties, teststepinstance);
                        testCaseinstance.setDriverCurrentState(driver);
                        break;
                    }

                }
            }
        } catch (SecurityException | IllegalArgumentException | NoSuchMethodException ex) {
            throw ex;
            // Logger.getLogger(AppiumDriverbasedKeywordConfigs.class.getName()).log(Level.SEVERE, null, ex);
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

    public MobileElement VisibiltyWaitCall(AppiumDriver<?> driverinstance, By byobjectinstance) {//Function to improve stability using dynamic elements visibility
        WebDriverWait wait = new WebDriverWait(driverinstance, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byobjectinstance));
        driver = driverinstance;
        return (MobileElement) driverinstance.findElement(byobjectinstance);
    }//End of VisibiltyWaitCall
}
