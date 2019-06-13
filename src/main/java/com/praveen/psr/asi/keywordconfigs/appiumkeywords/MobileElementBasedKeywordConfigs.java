/*
 File Desc : This File is used to configure the keywords used to drive the script.
 Author / Created By : Praveen P.S.R.
 Created On : 12/10/2016
 Reviewed By : Sridhar K
 Last Modified By : Praveen P.S.R.
 Last Modified On : 10/11/2016
 */
package com.praveen.psr.asi.keywordconfigs.appiumkeywords;

import com.praveen.psr.asi.customExceptions.ButtonEnableException;
import com.praveen.psr.asi.frameworkcore.ObjectProperties;
import com.praveen.psr.asi.frameworkcore.TestCase;
import com.praveen.psr.asi.frameworkcore.TestStep;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class MobileElementBasedKeywordConfigs {

    //Function that holds the execution logics of different keywords defined
    public void KeywordActionMappings(String Keywordaction, MobileElement MB, TestCase testcaseinstance, ObjectProperties objectproperty, TestStep teststepinstance) throws Throwable {

        String ElementAction = teststepinstance.getActionKeyword();
        String ActionValue = teststepinstance.getReqParam();
        AppiumDriver<?> driver = testcaseinstance.getAppiumDriver();
        switch (Keywordaction) {//Switch case opened
           
            case "Check If Enabled": {//Drop down Keyword Functionality
                if (MB.isEnabled() == false) {
                    throw new ButtonEnableException("The Button is Disabled");
                }
                break;
            }
            case "Check If Disabled": {//Drop down Keyword Functionality
                if (MB.isEnabled() == true) {
                    throw new ButtonEnableException("The Button is Enabled");
                }
                break;
            }
            default: {
                Method[] AllWebElementMethods = getSuperClassMethods(testcaseinstance.getElementType());
                for (Method AllWebElementMethod : AllWebElementMethods) {
                    if (AllWebElementMethod.getName().equals(ElementAction)) {
                        try {//try catch block 4- Opened
                            if (AllWebElementMethod.getParameterCount() == 0) {
                                AllWebElementMethod.invoke(MB);
                            } else {
                                if (ElementAction.equals("sendKeys")) {
                                    MB.sendKeys(ActionValue);
                                } else {
                                    AllWebElementMethod.invoke(MB, ActionValue);

                                }
                            }
                            break;
                        } catch (IllegalAccessException e) {
                            throw e;
                            //Logger.getLogger(MobileElementBasedKeywordConfigs.class.getName()).log(Level.SEVERE, null, e);
                        } catch (InvocationTargetException ex) {
                            throw ex.getCause();
                        }
                        //try catch block 4- Closed//try catch block 4- Closed
                        //try catch block 4- Closed//try catch block 4- Closed
                    }
                }
            }
        }//Switch case closed
    }//End of Keyword action mappings

    public void recursiveloopmethods(Class Sample, Set<Method> Methods) {
        try {
            Method[] Methodarr = Sample.getMethods();
            if (Methodarr.length == 0) {
            } else {
                for (int i = 0; i < Methodarr.length; i++) {
                    Methods.add(Methodarr[i]);
                }
            }
            recursiveloopmethods(Sample.getSuperclass(), Methods);
        } catch (NullPointerException Ne) {
        }
    }

    public Set<Method> getMethodSet(Class BaseClass) {
        Set<Method> listofMethods = new HashSet<Method>();
        recursiveloopmethods(BaseClass, listofMethods);
        return listofMethods;
    }

    public Method[] getSuperClassMethods(Class Classname) {
        Set<Method> XY = getMethodSet(Classname);
        Method[] returnMethods = new Method[XY.size()];
        int i = 0;
        for (Iterator<Method> iterator = XY.iterator(); iterator.hasNext();) {
            Method next = iterator.next();
            returnMethods[i] = next;
            ++i;
        }
        return returnMethods;
    }
}//End of Class
