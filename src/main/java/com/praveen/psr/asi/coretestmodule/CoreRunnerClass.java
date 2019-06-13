/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praveen.psr.asi.coretestmodule;

import com.praveen.psr.asi.excelreader.ExcelProcessAndConfiguration;
import java.io.IOException;
import org.testng.TestNG;

/**
 *
 * @author temp
 */
public class CoreRunnerClass {
//The Invocation method

    public void runCases() throws IOException, ClassNotFoundException, NoSuchMethodException {
        //CoreTestNGTestClass.dataproviderthreadcount = 5;
        //ExcelProcessAndConfiguration.ExcelType = "ROWFormat";
        ExcelProcessAndConfiguration.loadExcelData();
        TestNG testNG = new TestNG();
        Class[] classes1 = {CoreTestNGTestClass.class};
        testNG.setTestClasses(classes1);
        //testNG.setXmlSuites(suites);
        testNG.run();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException {
        new CoreRunnerClass().runCases();
    }
}
