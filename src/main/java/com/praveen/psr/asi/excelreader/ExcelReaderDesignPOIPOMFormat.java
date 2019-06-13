package com.praveen.psr.asi.excelreader;

import static com.praveen.psr.asi.excelreader.ExcelProcessAndConfiguration.DriverManagerObjects;
import static com.praveen.psr.asi.excelreader.ExcelProcessAndConfiguration.GlobalWorkBoookObject;
import com.praveen.psr.asi.frameworkcore.ObjectProperties;
import com.praveen.psr.asi.frameworkcore.TestCase;
import com.praveen.psr.asi.frameworkcore.TestStep;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;

public class ExcelReaderDesignPOIPOMFormat {

    public static TestCase[][] testCaseReader() throws IOException, ClassNotFoundException, NoSuchMethodException {

        XSSFSheet sheet1 = GlobalWorkBoookObject.getSheet(ExcelProcessAndConfiguration.CentralConfigurationsheetData.get("TestCaseSheet"));
        XSSFSheet sheet2 = GlobalWorkBoookObject.getSheet(ExcelProcessAndConfiguration.CentralConfigurationsheetData.get("TestFlowSheet"));
        int rowlength = sheet1.getLastRowNum();
        ArrayList<TestCase> TestNGInputList = new ArrayList<TestCase>();

        for (int i = 1; i <= rowlength; i++) {//Test case start
            XSSFRow row = sheet1.getRow(i);
            if (formatCellDataToString(row.getCell(3)).equals("Yes")) {
                int TCexecutions = Integer.parseInt((formatCellDataToString(row.getCell(2))));
                for (int k = 1; k <= TCexecutions; k++) {

                    TestCase tc = new TestCase();
                    ArrayList<TestStep> CreatedList = new ArrayList<TestStep>();
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
                    for (int j = 1; j <= sheet2.getLastRowNum(); j++) {

                        if (formatCellDataToString(sheet1.getRow(i).getCell(0)).equals(formatCellDataToString(sheet2.getRow(j).getCell(0)))) {

                            String ObjectSheetName = formatCellDataToString(sheet2.getRow(j).getCell(2));
                            String testFlowSheet = formatCellDataToString(sheet2.getRow(j).getCell(3));
                            String TDidentifier = formatCellDataToString(sheet2.getRow(j).getCell(4));
                            getTDRepo(ObjectSheetName, testFlowSheet, TDidentifier, CreatedList);
                            //ASIConsole.out.println("Print : "+CreatedList.size());

                        }

                    }
                    /*ASIConsole.out.println("CreatedList" + Arrays.toString(CreatedList.toArray()));
                    for (TestStep ts : CreatedList) {
                        ASIConsole.out.println("obj key " + ts.getObjKey());
                        ASIConsole.out.println("Action keyword " + ts.getActionKeyword());
                        ASIConsole.out.println("req param " + ts.getReqParam());
                    }*/

                    tc.setTestSteps(CreatedList);

                    TestNGInputList.add(tc);
                }
            }
        }

        TestCase[][] TestCaseTestNGINput = new TestCase[TestNGInputList.size()][1];
        for (int i = 0; i < TestCaseTestNGINput.length; i++) {
            TestCaseTestNGINput[i][0] = TestNGInputList.get(i);
        }
        TestNGInputList.clear();
        TestNGInputList = null;
        ExcelProcessAndConfiguration.TestNGDataProviderInput = TestCaseTestNGINput;
        return TestCaseTestNGINput;
    }

    public static String formatCellDataToString(XSSFCell cellinstance) {
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cellinstance);
    }

    public static ObjectProperties getObjectProperties(String ObjectSheetname, String ObjKey) {

        XSSFSheet sheet = GlobalWorkBoookObject.getSheet(ObjectSheetname);
        ObjectProperties objvalue = new ObjectProperties();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            if (formatCellDataToString(sheet.getRow(i).getCell(0)).equals(ObjKey)) {
                for (int j = 1; j < sheet.getRow(i).getLastCellNum(); j++) {

                    if (formatCellDataToString(sheet.getRow(i).getCell(j)).isEmpty() == false) {
                        objvalue.setScreen(ObjectSheetname);
                        objvalue.setObjectKey(ObjKey);
                        objvalue.setAppiumIdentifier(formatCellDataToString(sheet.getRow(i).getCell(1)));
                        objvalue.setAppium_Identifier_Value(formatCellDataToString(sheet.getRow(i).getCell(2)));
                        objvalue.setObject_Type(formatCellDataToString(sheet.getRow(i).getCell(3)));
                        objvalue.setObject_Operation(formatCellDataToString(sheet.getRow(i).getCell(4)));

                    }

                }

                break;
            }

        }
        ObjectProperties.ObjectRepositoryMap.put(ObjKey, objvalue);
        // for (Entry<String, ObjectProperties> entry : ObjectProperties.ObjectRepositoryMap.entrySet()) {

        //   ASIConsole.out.println(entry.getKey() + "--->" + entry.getValue().toString());
        //}
        return objvalue;
    }

    public static ArrayList<TestStep> getTDRepo(String ObjectSheetName, String TestDataSheet, String TDidentifier, ArrayList<TestStep> LoopedTestStepList) {

        XSSFSheet sheet = GlobalWorkBoookObject.getSheet(TestDataSheet);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            if (formatCellDataToString(sheet.getRow(i).getCell(0)).equals(TDidentifier)) {
                for (int j = 1; j < sheet.getRow(i).getLastCellNum(); j++) {
                    if (formatCellDataToString(sheet.getRow(i).getCell(j)).isEmpty() == false) {
                        String objkey = formatCellDataToString(sheet.getRow(0).getCell(j));
                        TestStep ts = new TestStep();
                        ObjectProperties objvalue = getObjectProperties(ObjectSheetName, objkey);
                        ts.setActionKeyword(objvalue.getObject_Operation());
                        ts.setMappedTCnumber(TDidentifier);
                        ts.setObjKey(objkey);
                        ts.setReqParam(formatCellDataToString(sheet.getRow(i).getCell(j)));
                        LoopedTestStepList.add(ts);
                    }

                }

            }

        }

        return LoopedTestStepList;

    }

}
