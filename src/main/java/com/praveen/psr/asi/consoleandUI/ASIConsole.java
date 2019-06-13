/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praveen.psr.asi.consoleandUI;

import static com.praveen.psr.asi.consoleandUI.ConfigurationLoader.default_directory;
import com.praveen.psr.asi.excelreader.ExcelProcessAndConfiguration;
import com.praveen.psr.asi.frameworkcore.TestCase;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author temp
 */
public class ASIConsole {

    private static boolean Printstreamprint = false;
    private static int counter = 1;
    public static ASIConsole out = new ASIConsole();
    static FileWriter FW = null;
    static BufferedWriter BW1 = null;

    public static void loadConsoleObjects() {
        try {
            File Directorycreate = new File(ExcelProcessAndConfiguration.ReportPath + "\\Logs\\");
            if (!Directorycreate.exists()) {
                try {//try catch block 2 - Opened
                    Directorycreate.mkdirs();
                } catch (SecurityException se) {
                }//try catch block 2 - Closed
            }
            File configfile = new File(ExcelProcessAndConfiguration.ReportPath + "\\Logs\\Logs_" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + ".txt");
            if (!configfile.exists()) {
                configfile.createNewFile();
            }
            FW = new FileWriter(configfile.getAbsoluteFile());
            BW1 = new BufferedWriter(FW);
        } catch (IOException ex) {
            Logger.getLogger(ASIConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static {
        loadConsoleObjects();
    }

    public static void writeLogData() throws IOException {
        BW1.close();
        FW.close();
        BW1 = null;
        FW = null;
    }

    private synchronized void ASIConsolesprintln(String input) throws IOException {
        if (BW1 == null) {
            loadConsoleObjects();
        }
        input = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:aa").format(new Date()) + " | line - " + counter + " : (Thread-" + Thread.currentThread().getId() + ") | " + input;
        System.out.println(input);
        if (ConfigurationLoader.UserInteractionparameter == 1) {
            ASI_UI.Activities_Ongoing(input + "\n");
        }
        BW1.write(input);
        BW1.newLine();
        ++counter;
    }

    private synchronized void ASIConsolesprint(String input) {
        System.out.print(input);
        if (ConfigurationLoader.UserInteractionparameter == 1) {
            ASI_UI.Activities_Ongoing(input);
        }
    }

    public void println() throws IOException {
        if (Printstreamprint == true) {
            System.out.println();
        }
        ASIConsolesprintln("\n");
    }

    public void println(boolean x) throws IOException {
        if (Printstreamprint == true) {
            System.out.println(x);
        }
        ASIConsolesprintln("" + x);
    }

    public void println(char x) throws IOException {
        if (Printstreamprint == true) {
            System.out.println(x);
        }
        ASIConsolesprintln("" + x);
    }

    public void println(int x) throws IOException {
        if (Printstreamprint == true) {
            System.out.println(x);
        }
        ASIConsolesprintln("" + x);
    }

    public void println(long x) throws IOException {
        if (Printstreamprint == true) {
            System.out.println(x);
        }
        ASIConsolesprintln("" + x);
    }

    public void println(float x) throws IOException {
        if (Printstreamprint == true) {
            System.out.println(x);
        }
        ASIConsolesprintln("" + x);
    }

    public void println(double x) throws IOException {
        if (Printstreamprint == true) {
            System.out.println(x);
        }
        ASIConsolesprintln("" + x);
    }

    public void println(char x[]) throws IOException {
        if (Printstreamprint == true) {
            System.out.println(x);
        }
        ASIConsolesprintln("" + x);
    }

    public void println(String x) throws IOException {
        if (Printstreamprint == true) {
            System.out.println(x);
        }
        ASIConsolesprintln("" + x);
    }

    public void println(Object x) throws IOException {
        if (Printstreamprint == true) {
            System.out.println(x);
        }
        ASIConsolesprintln("" + x);
    }

    public void println(TestCase TC, String Message) throws IOException {
        if (Printstreamprint == true) {
            System.out.println(TC.getTestCaseNumber() + " : " + Message);
        }
        ASIConsolesprintln(TC.getTestCaseNumber() + " : " + Message);
    }

    public void print(boolean x) {
        if (Printstreamprint == true) {
            System.out.print(x);
        }
        ASIConsolesprint("" + x);
    }

    public void print(char x) {
        if (Printstreamprint == true) {
            System.out.print(x);
        }
        ASIConsolesprint("" + x);
    }

    public void print(int x) {
        if (Printstreamprint == true) {
            System.out.print(x);
        }
        ASIConsolesprint("" + x);
    }

    public void print(long x) {
        if (Printstreamprint == true) {
            System.out.print(x);
        }
        ASIConsolesprint("" + x);
    }

    public void print(float x) {
        if (Printstreamprint == true) {
            System.out.print(x);
        }
        ASIConsolesprint("" + x);
    }

    public void print(double x) {
        if (Printstreamprint == true) {
            System.out.print(x);
        }
        ASIConsolesprint("" + x);
    }

    public void print(char x[]) {
        if (Printstreamprint == true) {
            System.out.print(x);
        }
        ASIConsolesprint("" + x);
    }

    public void print(String x) {
        if (Printstreamprint == true) {
            System.out.print(x);
        }
        ASIConsolesprint("" + x);
    }

    public void print(Object x) {
        if (Printstreamprint == true) {
            System.out.print(x);
        }
        ASIConsolesprint("" + x);
    }
}
