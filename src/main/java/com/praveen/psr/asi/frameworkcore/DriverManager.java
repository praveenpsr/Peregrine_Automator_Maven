/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praveen.psr.asi.frameworkcore;

import com.praveen.psr.asi.consoleandUI.ASIConsole;
import com.praveen.psr.asi.customExceptions.AppiumDriverLockedException;
import io.appium.java_client.AppiumDriver;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author temp
 */
public class DriverManager {

    private URL DriverURL;
    private Capabilities DriverCaps;
    private Constructor DriverConstructor;
    private String AutomationTechnology;
    private String SystemParamters;
    private String Driverkey;

    private Class ElementType;
    public boolean AppiumDevicelockstatus;

    static final HashMap<String, String> DriverClassReference;
    static final HashMap<String, String> ElementTypes;
    static final HashMap<String, String> SystemProperties;

    public void setAppiumDevicelockstatus(boolean AppiumDevicelockstatus) {
        this.AppiumDevicelockstatus = AppiumDevicelockstatus;
    }

    public String getDriverkey() {
        return Driverkey;
    }

    public void setDriverkey(String Driverkey) {
        this.Driverkey = Driverkey;
    }

    static {
        SystemProperties = new HashMap<String, String>();
        SystemProperties.put("ChromeDriver", "webdriver.chrome.driver");

        ElementTypes = new HashMap<String, String>();
        ElementTypes.put("WindowsElement", "io.appium.java_client.windows.WindowsElement");
        ElementTypes.put("AndroidElement", "io.appium.java_client.android.AndroidElement");
        ElementTypes.put("IOSElement", "io.appium.java_client.ios.IOSElement");
        ElementTypes.put("RemoteWebElement", "org.openqa.selenium.remote.RemoteWebElement");
        ElementTypes.put("MobileElement", "io.appium.java_client.MobileElement");

        DriverClassReference = new HashMap<String, String>();
        DriverClassReference.put("AppiumDriver", "io.appium.java_client.AppiumDriver");
        DriverClassReference.put("AndroidDriver", "io.appium.java_client.android.AndroidDriver");
        DriverClassReference.put("IOSDriver", "io.appium.java_client.ios.IOSDriver");
        DriverClassReference.put("WindowsDriver", "io.appium.java_client.windows.WindowsDriver");
        DriverClassReference.put("ChromeDriver", "org.openqa.selenium.chrome.ChromeDriver");
        DriverClassReference.put("EdgeDriver", "org.openqa.selenium.edge.EdgeDriver");
        DriverClassReference.put("FirefoxDriver", "org.openqa.selenium.firefox.FirefoxDriver");
        DriverClassReference.put("InternetExplorerDriver", "org.openqa.selenium.ie.InternetExplorerDriver");
        DriverClassReference.put("OperaDriver", "org.openqa.selenium.opera.OperaDriver");
        DriverClassReference.put("RemoteWebDriver", "org.openqa.selenium.remote.RemoteWebDriver");
        DriverClassReference.put("SafariDriver", "org.openqa.selenium.safari.SafariDriver");
        DriverClassReference.put("EventFiringWebDriver", "org.openqa.selenium.support.events.EventFiringWebDriver");
    }

    public Class getElementType() {
        return ElementType;
    }

    public synchronized WebDriver getSeleniumDriver() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            return (WebDriver) this.DriverConstructor.newInstance(null);
        } catch (IllegalArgumentException ex) {
            Object[] objarr = {this.DriverURL, this.DriverCaps};
            return (WebDriver) this.DriverConstructor.newInstance(objarr);
        }
    }

    private synchronized <T extends WebElement> AppiumDriver<T> getAppiumDriverwithType(Class<T> Type) throws IllegalAccessException, IllegalArgumentException, InstantiationException, Throwable {
        Object[] objarr = {this.DriverURL, this.DriverCaps};
        try {
            return (AppiumDriver<T>) this.DriverConstructor.newInstance(objarr);
        } catch (InvocationTargetException IT) {
            throw IT.getCause();
        }
    }

    public synchronized <T extends WebElement> AppiumDriver<T> getAppiumDriver() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, InterruptedException, AppiumDriverLockedException, Throwable {
        return getAppiumDriverwithType(this.ElementType);
    }

    public void setElementType_3(String ElementString) throws ClassNotFoundException {
        this.ElementType = Class.forName(ElementTypes.get(ElementString));;
    }
    
    public String getSystemParamters() {
        return SystemParamters;
    }

    public void setSystemParamters_2(String SystemParamters) {
        this.SystemParamters = SystemParamters;
    }

    static HashMap<String, DriverManager> driverlist = new HashMap<String, DriverManager>();

    public String getAutomationTechnology() {
        return AutomationTechnology;
    }

    public void setAutomationTechnology_1(String AutomationTechnology) {
        this.AutomationTechnology = AutomationTechnology;
    }

    public URL getDriverURL() {
        return DriverURL;
    }

    public void setDriverURL_5(String URLString) throws MalformedURLException {
        if (URLString == null || URLString.equals("")) {
            this.DriverURL = null;
        } else {
            this.DriverURL = new URL(URLString);
        }
    }

    public Capabilities getDriverCaps() {
        return DriverCaps;
    }

    public void setDriverCaps_6(HashMap<String, Object> CapsMapping) {
        DesiredCapabilities caps = new DesiredCapabilities();
        for (Map.Entry<String, Object> entry : CapsMapping.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            try {
                int val = Integer.parseInt(value.toString());
                caps.setCapability(key, val);
            } catch (NumberFormatException Ne) {
                caps.setCapability(key, value.toString());
            }

        }
        this.DriverCaps = (Capabilities) caps;
    }

    public Constructor getDriverConstructor() {
        return DriverConstructor;
    }

    private void setSystemProperty(String Drivertype) {
        System.setProperty(SystemProperties.get(Drivertype), this.SystemParamters);
    }

    public void setDriverConstructor_4(String ConstructorString) throws ClassNotFoundException, NoSuchMethodException {
        Class X = Class.forName(DriverClassReference.get(ConstructorString));;
        Constructor ConsObject = null;

        if (this.AutomationTechnology.equals("Selenium")) {
            try {
                setSystemProperty(ConstructorString);
                ConsObject = X.getConstructor(null);
            } catch (NoSuchMethodException ex) {
                ConsObject = X.getConstructor(URL.class, Capabilities.class);
            } catch (SecurityException ex) {
                Logger.getLogger(DriverManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (this.AutomationTechnology.equals("Appium")) {

            ConsObject = X.getConstructor(URL.class, Capabilities.class);
        }
        this.DriverConstructor = ConsObject;
    }

}
