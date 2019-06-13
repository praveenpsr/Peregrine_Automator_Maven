package com.praveen.psr.asi.excelreader;

import static com.praveen.psr.asi.excelreader.ExcelProcessAndConfiguration.DriverManagerObjects;
import static com.praveen.psr.asi.excelreader.ExcelReaderDesignPOIPOMFormat.formatCellDataToString;
import com.praveen.psr.asi.frameworkcore.TestCase;
import com.praveen.psr.asi.frameworkcore.ObjectProperties;
import com.praveen.psr.asi.frameworkcore.TestStep;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelReaderDesignPOIRowFormat {

    //public static HashMap<String, ArrayList<ObjectProperties>> testObjectsMap = new HashMap<String, ArrayList<ObjectProperties>>();
    public static void LoadObjectRepository() {
        String[][] sheetData = getSheetData(ExcelProcessAndConfiguration.CentralConfigurationsheetData.get("ObjectRepository"));
        String objKey = null;

        for (int i = 0; i < sheetData.length; i++) {
            objKey = sheetData[i][0];//Setting the Hashmap key
            ObjectProperties OBJP = new ObjectProperties();
            //Adding the ObjectProperties
            OBJP.setObjectKey(sheetData[i][0]);
            OBJP.setScreen(sheetData[i][1]);
            OBJP.setAppiumIdentifier(sheetData[i][2]);
            OBJP.setAppium_Identifier_Value(sheetData[i][3]);
            OBJP.setObject_Type(sheetData[i][4]);
            OBJP.setObject_Operation(sheetData[i][5]);
            //Adding the OBJproperties to map's key
            ObjectProperties.ObjectRepositoryMap.put(objKey, OBJP);
        }

    }

    public static void mapStepWithModules(String keyModuleName, String valueGenericTestStepID) {

        if (TestStep.TestModulesMap.get(keyModuleName) == null) {
            ArrayList<String> TestStepsKeys = new ArrayList<String>();
            TestStepsKeys.add(valueGenericTestStepID);
            TestStep.TestModulesMap.put(keyModuleName, TestStepsKeys);
        } else {
            (TestStep.TestModulesMap.get(keyModuleName)).add(valueGenericTestStepID);
        }

    }

    public static void LoadTestStepModules() {
        String[][] sheetData = getSheetData(ExcelProcessAndConfiguration.CentralConfigurationsheetData.get("TestStepModuleSheet"));
        String objKey = null;

        for (int i = 0; i < sheetData.length; i++) {
            objKey = sheetData[i][1];//Setting the Hashmap key
            TestStep TSOBJ = new TestStep();
            //Adding the ObjectProperties
            TSOBJ.setObjKey(sheetData[i][2]);
            TSOBJ.setActionKeyword(sheetData[i][3]);
            TSOBJ.setReqParam(sheetData[i][4]);
            //Adding the OBJproperties to map's key
            TestStep.GenericTestStepsMap.put(objKey, TSOBJ);
            //Adding the TestSteps to Module and adding the data to Map
            mapStepWithModules(sheetData[i][0], objKey);

        }

    }

    public static String[][] getSheetData(String dataSheetName) {

        String[][] data = null;

        try {
            XSSFSheet sheet = ExcelProcessAndConfiguration.GlobalWorkBoookObject.getSheet(dataSheetName);

            // get the number of rows
            int rowCount = sheet.getLastRowNum();

            // get the number of columns
            int columnCount = sheet.getRow(0).getLastCellNum();
            data = new String[rowCount][columnCount];

            // loop through the rows
            for (int i = 1; i < rowCount + 1; i++) {
                try {
                    XSSFRow row = sheet.getRow(i);
                    for (int j = 0; j < columnCount; j++) { // loop through the columns
                        try {
                            String cellValue = "";
                            try {
                                DataFormatter formatter = new DataFormatter();
                                cellValue = formatter.formatCellValue(row.getCell(j));
                            } catch (NullPointerException e) {

                            }

                            data[i - 1][j] = cellValue; // add to the data array
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
        }
        return data;

    }

    public static ArrayList<TestStep> getTestSteps(String TestCaseNumber) {

        //String[][] data = null ;
        ArrayList<TestStep> teststepslist = new ArrayList<TestStep>();

        try {
            XSSFSheet sheet = ExcelProcessAndConfiguration.GlobalWorkBoookObject.getSheet(ExcelProcessAndConfiguration.CentralConfigurationsheetData.get("TestStepSheet"));

            // get the number of rows
            int rowCount = sheet.getLastRowNum();

            // get the number of columns
            //int columnCount = sheet.getRow(0).getLastCellNum();
            //data = new String[rowCount][columnCount];
            for (int i = 1; i < rowCount + 1; i++) {
                try {
                    XSSFRow row = sheet.getRow(i);
                    try {

                        DataFormatter formatter = new DataFormatter();
                        if (TestCaseNumber.equals((formatter.formatCellValue(row.getCell(0))))) {

                            TestStep X = new TestStep();
                            X.setMappedTCnumber((formatter.formatCellValue(row.getCell(0))));
                            X.setStepNo((formatter.formatCellValue(row.getCell(1))));
                            X.setObjKey((formatter.formatCellValue(row.getCell(2))));
                            X.setActionKeyword((formatter.formatCellValue(row.getCell(3))));
                            X.setReqParam((formatter.formatCellValue(row.getCell(4))));
                            teststepslist.add(X);
                        } else {
                            continue;
                        }

                    } catch (NullPointerException e) {

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return teststepslist;
    }

    public static void loadTestCaseObjects() {

        ArrayList<TestCase> TestNGInputList = new ArrayList<TestCase>();
        try {
            XSSFSheet sheet = ExcelProcessAndConfiguration.GlobalWorkBoookObject.getSheet(ExcelProcessAndConfiguration.CentralConfigurationsheetData.get("TestCaseSheet"));

            // get the number of rows
            int rowCount = sheet.getLastRowNum();

            // loop through the rows
            for (int i = 1; i < rowCount + 1; i++) {
                try {
                    XSSFRow row = sheet.getRow(i);
                    if ((formatCellDataToString(row.getCell(3))).equals("Yes")) {
                        int TCexecutions = Integer.parseInt((formatCellDataToString(row.getCell(2))));
                        for (int k = 1; k <= TCexecutions; k++) {
                            try {
                                TestCase tc = new TestCase();
                                if (TCexecutions > 1) {
                                    tc.setTestCaseNumber((formatCellDataToString(row.getCell(0))) + "(instance-" + k + ")");
                                } else {
                                    tc.setTestCaseNumber((formatCellDataToString(row.getCell(0))));
                                }
                                tc.setTestCaseDescription((formatCellDataToString(row.getCell(1))));
                                tc.setNumOfExecutions((formatCellDataToString(row.getCell(2))));
                                tc.setTcTrigger((formatCellDataToString(row.getCell(3))));
                                String driverkey = formatCellDataToString(row.getCell(4));
                                if (DriverManagerObjects.containsKey(driverkey) == true) {
                                    tc.setDriverManagerKey(driverkey);
                                } else {
                                    tc.setDriverManagerKey(ExcelProcessAndConfiguration.getDriverManagerObjects(driverkey));
                                }
                                tc.setAutomationTechnology(ExcelProcessAndConfiguration.DriverManagerObjects.get(driverkey).getAutomationTechnology());
                                tc.setElementType(ExcelProcessAndConfiguration.DriverManagerObjects.get(driverkey).getElementType());
                                tc.setTestSteps(getTestSteps((formatCellDataToString(row.getCell(0)))));
                                TestNGInputList.add(tc);

                            } catch (NullPointerException e) {

                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        TestCase[][] TestCaseTestNGINput = new TestCase[TestNGInputList.size()][1];
        for (int i = 0; i < TestCaseTestNGINput.length; i++) {
            TestCaseTestNGINput[i][0] = TestNGInputList.get(i);
        }
        TestNGInputList.clear();
        TestNGInputList = null;
        ExcelProcessAndConfiguration.TestNGDataProviderInput = TestCaseTestNGINput;

    }

}
