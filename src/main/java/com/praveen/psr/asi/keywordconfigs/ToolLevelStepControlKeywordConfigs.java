/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praveen.psr.asi.keywordconfigs;

import com.praveen.psr.asi.consoleandUI.ASIConsole;
import com.praveen.psr.asi.customExceptions.TextMismatchException;
import com.praveen.psr.asi.customExceptions.URLloadFailedException;
import com.praveen.psr.asi.frameworkcore.TestCase;
import com.praveen.psr.asi.frameworkcore.TestStep;
import com.praveen.psr.asi.frameworkcore.TestStepExecutor;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author temp
 */
public class ToolLevelStepControlKeywordConfigs {

    public ToolLevelStepControlKeywordConfigs(TestCase tcobj, String modulename, TestStepExecutor executorinstance) {
        this.ExecutorInstance = executorinstance;
        this.ModuleName = modulename;
        this.TCOBJ = tcobj;
    }

    public TestCase getTCOBJ() {
        return TCOBJ;
    }

    public void setTCOBJ(TestCase TCOBJ) {
        this.TCOBJ = TCOBJ;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String ModuleName) {
        this.ModuleName = ModuleName;
    }

    public TestStepExecutor getExecutorInstance() {
        return ExecutorInstance;
    }

    public void setExecutorInstance(TestStepExecutor ExecutorInstance) {
        this.ExecutorInstance = ExecutorInstance;
    }

    TestCase TCOBJ;
    String ModuleName;
    TestStepExecutor ExecutorInstance;

    public void validateText() {
        // ASIConsole.out.println("Method HIT");
    }

    public void validateSorting() {

    }

    public void executeTestStepModule() throws TextMismatchException, URLloadFailedException, IOException, Throwable {
        //ASIConsole.out.println("Entered");
        ASIConsole.out.println(TCOBJ,this.ModuleName + " Step Module Invoked");
        for (String genericteststepkey : TestStep.TestModulesMap.get(this.ModuleName)) {
            this.ExecutorInstance.executeTestStep(TestStep.GenericTestStepsMap.get(genericteststepkey), this.TCOBJ, this.TCOBJ.StepNumber);
        }
    }
}
