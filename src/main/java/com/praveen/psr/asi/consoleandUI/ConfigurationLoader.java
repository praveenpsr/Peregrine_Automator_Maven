/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praveen.psr.asi.consoleandUI;

import static com.praveen.psr.asi.consoleandUI.ASIConsole.loadConsoleObjects;
import com.praveen.psr.asi.coretestmodule.CoreRunnerClass;
import com.praveen.psr.asi.coretestmodule.CoreTestNGTestClass;
import com.praveen.psr.asi.excelreader.ExcelProcessAndConfiguration;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author temp
 */
public class ConfigurationLoader {

    private static Scanner input;

    static ArrayList<String> ConfigValues = new ArrayList<String>();
    public static String default_directory = System.getProperty("user.dir") + "/resources";
    public static int UserInteractionparameter;

    public static void saveFrameworkConfig() throws Exception {
        ArrayList<String> ConfigValues = new ArrayList<String>();
        File configfiledirectory = new File(default_directory);
        if (!configfiledirectory.exists()) {
            try {
                configfiledirectory.mkdir();
            } catch (SecurityException se) {
            }
        }
        File configfile = new File(default_directory + "\\asiconfig.txt");
        if (!configfile.exists()) {
            configfile.createNewFile();
        }
        FileWriter FW = new FileWriter(configfile.getAbsoluteFile());
        BufferedWriter BW1 = new BufferedWriter(FW);
        BW1.write(ExcelProcessAndConfiguration.ConfigurationSheet);
        BW1.newLine();
        BW1.write(ExcelProcessAndConfiguration.ScreenshotPath);
        BW1.newLine();
        BW1.write(ExcelProcessAndConfiguration.ScreensnapPrefix);
        BW1.newLine();
        BW1.write(ExcelProcessAndConfiguration.InputExcelPath);
        BW1.newLine();
        BW1.write(ExcelProcessAndConfiguration.ReportPath);
        BW1.newLine();
        BW1.write("" + CoreTestNGTestClass.dataproviderthreadcount);
        BW1.newLine();
        BW1.write(ExcelProcessAndConfiguration.ExcelType);
        BW1.newLine();
        BW1.close();
        ASIConsole.out.println("Configuration Parameters Saved Sucessfully to '" + default_directory + "\\asiconfig.txt' !");
    }

    public static void loadconfigurationValues() throws Exception {
        String Temp;
        try {
            FileReader FR = new FileReader(ConfigurationLoader.default_directory + "\\asiconfig.txt");
            BufferedReader BR = new BufferedReader(FR);
            System.out.println("Config File '" + ConfigurationLoader.default_directory + "\\asiconfig.txt' is found. Loading Values from Config file..");
            while ((Temp = BR.readLine()) != null) {
                ConfigValues.add(Temp);
            }
            ExcelProcessAndConfiguration.ConfigurationSheet = ConfigValues.get(0);
            ExcelProcessAndConfiguration.ScreenshotPath = ConfigValues.get(1);
            ExcelProcessAndConfiguration.ScreensnapPrefix = ConfigValues.get(2);
            ExcelProcessAndConfiguration.InputExcelPath = ConfigValues.get(3);
            ExcelProcessAndConfiguration.ReportPath = ConfigValues.get(4);
            CoreTestNGTestClass.dataproviderthreadcount = Integer.parseInt(ConfigValues.get(5));
            ExcelProcessAndConfiguration.ExcelType = ConfigValues.get(6);
            BR.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Config File Not Found at '" + ConfigurationLoader.default_directory + "\\asiconfig.txt' !");
            if (UserInteractionparameter == 1) {
                System.out.println("Setting Default Values for the UI..");
                ExcelProcessAndConfiguration.ConfigurationSheet = "<CHANGE THIS PARAMETER>";
                ExcelProcessAndConfiguration.ScreenshotPath = "<CHANGE THIS PARAMETER>";
                ExcelProcessAndConfiguration.ScreensnapPrefix = "<CHANGE THIS PARAMETER>";
                ExcelProcessAndConfiguration.InputExcelPath = "<CHANGE THIS PARAMETER>";
                ExcelProcessAndConfiguration.ReportPath = "<CHANGE THIS PARAMETER>";
                CoreTestNGTestClass.dataproviderthreadcount = 1;
                ExcelProcessAndConfiguration.ExcelType = "Excel - PageObjectModel Format";
            } else if (UserInteractionparameter == 2) {
                setConfigValuesConsole();
            }

        }
    }

    public static void printConfigurationToConsole() throws IOException {
        ASIConsole.out.println("----------------------Configuration-----------------------");
        ASIConsole.out.println("Configuration Sheet                : " + ExcelProcessAndConfiguration.ConfigurationSheet);
        ASIConsole.out.println("Screenshot Path                    : " + ExcelProcessAndConfiguration.ScreenshotPath);
        ASIConsole.out.println("Screenshot and Report Prefix       : " + ExcelProcessAndConfiguration.ScreensnapPrefix);
        ASIConsole.out.println("Input Excel Path                   : " + ExcelProcessAndConfiguration.InputExcelPath);
        ASIConsole.out.println("Output Report Path                 : " + ExcelProcessAndConfiguration.ReportPath);
        ASIConsole.out.println("TestNG Thread Pool Threshold count : " + CoreTestNGTestClass.dataproviderthreadcount);
        ASIConsole.out.println("Input Excel Type                   : " + ExcelProcessAndConfiguration.ExcelType);
        ASIConsole.out.println("----------------------------------------------------------");
    }

    public static void setConfigValuesConsole() throws Exception {
        ASIConsole.out.println("Please enter the value for the below parameters..");
        ASIConsole.out.println("----------------------------------------------------------");
        ASIConsole.out.println("Configuration Sheet : ");
        ExcelProcessAndConfiguration.ConfigurationSheet = input.next();
        ASIConsole.out.println("Screenshot Path : ");
        ExcelProcessAndConfiguration.ScreenshotPath = input.next();
        ASIConsole.out.println("Screenshot and Report Prefix : ");
        ExcelProcessAndConfiguration.ScreensnapPrefix = input.next();
        ASIConsole.out.println("Input Excel Path : ");
        ExcelProcessAndConfiguration.InputExcelPath = input.next();
        ASIConsole.out.println("Output Report Path : ");
        ExcelProcessAndConfiguration.ReportPath = input.next();
        ASIConsole.out.println("TestNG Thread Pool Threshold count : ");
        CoreTestNGTestClass.dataproviderthreadcount = input.nextInt();
        ASIConsole.out.println("Input Excel Type : ");
        ExcelProcessAndConfiguration.ExcelType = input.next();
        ASIConsole.out.println("----------------------------------------------------------");
        ASIConsole.out.println("Do you want to save the above configuration for future test? Yes(1)/No(0) ");
        if (input.nextInt() == 1) {
            saveFrameworkConfig();
            proceedExcelRun();
        } else {
            proceedExcelRun();
        }
    }

    public static void proceedExcelRun() throws IOException, Exception {
        printConfigurationToConsole();
        ASIConsole.out.println("Continue to Run Test Cases with the Above Configuration Parameters?(Yes(1)/No(0))");
        if (input.nextInt() == 1) {
            ASIConsole.out.println("Starting Execution via Console Run..");
            new CoreRunnerClass().runCases();
            ASIConsole.out.println("Completed Console Run");
            ASIConsole.out.println("Do you want to Re-run? (Yes(1)/No(0))");
            if (input.nextInt() == 1) {
                proceedExcelRun();
            } else {
                ASIConsole.out.println("Completed Console Run");
            }
        } else {
            ASIConsole.out.println("Do you want to reconfigure the Paramters ? (Yes(1)/No(0))");
            if (input.nextInt() == 1) {
                setConfigValuesConsole();
            } else {
                ASIConsole.out.println("No Test Cases Run - Completing Console Run");
            }
        }
    }

    public static void main(String[] args) throws IOException, Exception {

        input = new Scanner(System.in);
        System.out.println("----------------Welcome to Peregrine Automator!--------------------");
        System.out.println("----------Designed and Developed by Praveen PSR--------------");
        System.out.println("Please select the Run Mode ");
        System.out.println("1.Windows UI ");
        System.out.println("2.Native Console ");
        System.out.println("Type - 1 or 2 to choose the above option..");
        if (input.nextInt() == 1) {
            UserInteractionparameter = 1;
        } else {
            UserInteractionparameter = 2;
        }
        loadconfigurationValues();
        System.out.println("-------------------------------------------------------------");
        if (UserInteractionparameter == 1) {
            ASI_UI.main(new String[2]);
        } else if (UserInteractionparameter == 2) {
            proceedExcelRun();
        }
    }

}
