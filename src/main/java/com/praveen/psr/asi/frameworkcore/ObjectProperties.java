/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praveen.psr.asi.frameworkcore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author temp
 */
public class ObjectProperties {

    String Screen, AppiumIdentifier, Appium_Identifier_Value, Object_Type, Object_Operation, ObjectKey;
    public static HashMap<String, ObjectProperties> ObjectRepositoryMap = new HashMap<String, ObjectProperties>();

    public String getObjectKey() {
        return ObjectKey;
    }

    public void setObjectKey(String ObjectKey) {
        this.ObjectKey = ObjectKey;
    }

    public void setScreen(String Screen) {
        this.Screen = Screen;
    }

    public void setAppiumIdentifier(String AppiumIdentifier) {
        this.AppiumIdentifier = AppiumIdentifier;
    }

    public void setAppium_Identifier_Value(String Appium_Identifier_Value) {
        this.Appium_Identifier_Value = Appium_Identifier_Value;
    }

    public void setObject_Type(String Object_Type) {
        this.Object_Type = Object_Type;
    }

    public void setObject_Operation(String Object_Operation) {
        this.Object_Operation = Object_Operation;
    }

    public String getScreen() {
        return Screen;
    }

    public String getAppiumIdentifier() {
        return AppiumIdentifier;
    }

    public String getAppium_Identifier_Value() {
        return Appium_Identifier_Value;
    }

    public String getObject_Type() {
        return Object_Type;
    }

    public String getObject_Operation() {
        return Object_Operation;
    }

}
