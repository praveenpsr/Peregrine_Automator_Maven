/*
 File Desc : This File is used to configure the keywords used to drive the script.
 Author / Created By : Praveen P.S.R.
 Created On : 12/10/2016
 Reviewed By : Sridhar K
 Last Modified By : Praveen P.S.R.
 Last Modified On : 10/11/2016
 */
package com.praveen.psr.asi.keywordconfigs.seleniumkeywords;

import com.praveen.psr.asi.consoleandUI.ASIConsole;
import com.praveen.psr.asi.frameworkcore.ObjectProperties;
import com.praveen.psr.asi.frameworkcore.TestCase;
import com.praveen.psr.asi.frameworkcore.TestStep;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebElementBasedKeywordConfigs {

    //Function that holds the execution logics of different keywords defined
    public void KeywordActionMappings(String Keywordaction, WebElement WB, TestCase testcaseinstance, ObjectProperties objectproperty, TestStep teststepinstance) throws Throwable {
        String ElementAction = teststepinstance.getActionKeyword();
        String ActionValue = teststepinstance.getReqParam();
        WebDriver driver = testcaseinstance.getSeleniumWebDriver();
        switch (Keywordaction) {//Switch case opened
            case "MoveScrollBar": {//Mouse Scroll Bar move Keyword Functionality
                if (testcaseinstance.getTestCaseDescription().equals("safari")) {//CHANGE IT TO THE BROWSER NAME
                    ASIConsole.out.println(testcaseinstance,"Mouse Scrolling is unsuppported in Safari. Action Skipped");
                } else {
                    String[] offsetsplit = ActionValue.split(",");
                    Actions action = new Actions(driver);
                    action.clickAndHold(WB).moveByOffset(Integer.parseInt(offsetsplit[0]), Integer.parseInt(offsetsplit[1])).release().build().perform();
                    testcaseinstance.setDriverCurrentState(driver);
                }
                break;
            }
            case "MouseClickHold": {//Mouse Click and Hold Keyword Functionality
                if (testcaseinstance.getTestCaseDescription().equals("safari")) {//CHANGE IT TO THE BROWSER NAME
                    ASIConsole.out.println(testcaseinstance,"Mouse Click and Hold unsuppported in Safari. Action Skipped");
                } else {
                    Actions action = new Actions(driver);
                    action.clickAndHold(WB).build().perform();
                    testcaseinstance.setDriverCurrentState(driver);
                }
                break;
            }
            case "MouseHover": {//Mouse Hover Keyword Functionality
                if (testcaseinstance.getTestCaseDescription().equals("safari")) {//CHANGE IT TO THE BROWSER NAME
                    ASIConsole.out.println(testcaseinstance,"Mouse Hover is unsuppported in Safari. Action Skipped");
                } else {
                    Actions action = new Actions(driver);
                    action.moveToElement(WB);
                    action.click();
                    action.perform();
                    testcaseinstance.setDriverCurrentState(driver);
                }
                break;
            }
            case "Dropdown Select": {//Drop down Keyword Functionality
                new Select(WB).selectByVisibleText(ActionValue);
                break;
            }
            default: {
                Method[] AllWebElementMethods = WebElement.class.getDeclaredMethods();
                for (Method AllWebElementMethod : AllWebElementMethods) {
                    if (AllWebElementMethod.getName().equals(ElementAction)) {
                        try {//try catch block 4- Opened
                            if (AllWebElementMethod.getParameterCount() == 0) {
                                AllWebElementMethod.invoke(WB);
                            } else {
                                if (ElementAction.equals("sendKeys")) {
                                    WB.sendKeys(ActionValue);
                                } else {
                                    AllWebElementMethod.invoke(WB, ActionValue);

                                }
                            }
                            break;
                        } catch (IllegalAccessException e) {
                            throw e;
                            //Logger.getLogger(WebElementBasedKeywordConfigs.class.getName()).log(Level.SEVERE, null, e);
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
}//End of Class
