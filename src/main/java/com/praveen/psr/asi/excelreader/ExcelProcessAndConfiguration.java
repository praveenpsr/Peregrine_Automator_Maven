/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praveen.psr.asi.excelreader;

import com.praveen.psr.asi.consoleandUI.ASIConsole;
import com.praveen.psr.asi.frameworkcore.DriverManager;
import com.praveen.psr.asi.frameworkcore.TestCase;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author temp
 */
public class ExcelProcessAndConfiguration {

    public static String InputExcelPath;
    public static String TestCaseSheetName = "TC";
    public static String ObjectsRepositorySheetName = "Objects";
    public static String TestStepsSheetName = "Test Steps";
    public static String TestStepModulesSheetName = "TestStepModules";
    public static String ConfigurationSheet;
    public static String ScreenshotPath;
    public static String ReportPath;
    public static String ScreensnapPrefix;
    public static TestCase[][] TestNGDataProviderInput;
    public static FileInputStream CentralFileInputStream;
    public static XSSFWorkbook GlobalWorkBoookObject;
    public static String ExcelType;
    public static HashMap<String, String> CentralConfigurationsheetData;
    public static HashMap<String, DriverManager> DriverManagerObjects = new HashMap<String, DriverManager>();

    public static HashMap<String, String> getConfigurationSheetMetaData(String inputSheet) {
        String data[][] = null;
        HashMap<String, String> configMap = new HashMap<String, String>();
        data = ExcelReaderDesignPOIRowFormat.getSheetData(inputSheet);
        for (int i = 0; i < data.length; i++) {
            configMap.put(data[i][0], data[i][1]);
        }
        return configMap;
    }

    public static String getDriverManagerObjects(String driverkeyfromTC) throws ClassNotFoundException, NoSuchMethodException, MalformedURLException {

        String data[][] = null;
        data = ExcelReaderDesignPOIRowFormat.getSheetData(ExcelProcessAndConfiguration.CentralConfigurationsheetData.get("DriverConfigurationSheet"));

        for (int k = 1; k < data.length; k++) {
            if (data[k][0].equals(driverkeyfromTC)) {
                DriverManager dp = new DriverManager();
                dp.setAutomationTechnology_1(data[k][1]);
                if (data[k][1].equals("Selenium")) {
                    dp.setSystemParamters_2(data[k][5]);
                }
                dp.setElementType_3(data[k][3]);
                dp.setDriverConstructor_4(data[k][2]);
                dp.setDriverURL_5(data[k][4]);
                dp.setDriverCaps_6(CapsMapping(k));
                dp.setAppiumDevicelockstatus(false);
                dp.setDriverkey(driverkeyfromTC);
                DriverManagerObjects.put(data[k][0], dp);
            }
        }
        return driverkeyfromTC;

    }

    public static HashMap<String, Object> CapsMapping(int k) throws ClassNotFoundException, NoSuchMethodException, MalformedURLException {
        String data[][] = null;
        data = ExcelReaderDesignPOIRowFormat.getSheetData(ExcelProcessAndConfiguration.CentralConfigurationsheetData.get("DriverConfigurationSheet"));
        HashMap<String, Object> CapsObject = new HashMap<String, Object>();
        for (int j = 6; j < data[k].length; j++) {
            CapsObject.put(data[0][j], data[k][j]);

        }
        return CapsObject;
    }

    private static void loadExcelConfiguration() throws ClassNotFoundException, NoSuchMethodException {

        try {
            CentralFileInputStream = new FileInputStream(InputExcelPath);
            GlobalWorkBoookObject = new XSSFWorkbook(CentralFileInputStream);
            CentralConfigurationsheetData = getConfigurationSheetMetaData(ConfigurationSheet);
            ASIConsole.out.println("Input Excel WorkBook Loaded");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelProcessAndConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelProcessAndConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void loadROWformatExcelData() {
        ExcelReaderDesignPOIRowFormat.LoadObjectRepository();
        ExcelReaderDesignPOIRowFormat.LoadTestStepModules();
        ExcelReaderDesignPOIRowFormat.loadTestCaseObjects();

    }

    private static void loadPOMformatExcelData() throws IOException, ClassNotFoundException, NoSuchMethodException {
        ExcelReaderDesignPOIPOMFormat.testCaseReader();

    }

    private static void completeExcelDataLoad() {
        try {
            CentralFileInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ExcelProcessAndConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void loadExcelData() throws IOException, ClassNotFoundException, NoSuchMethodException {
        try {
            loadExcelConfiguration();
            if (ExcelType.equals("Excel - Linear ROW Format")) {
                loadROWformatExcelData();
            } else {
                loadPOMformatExcelData();
            }
        } finally {
            completeExcelDataLoad();
        }
    }
}
