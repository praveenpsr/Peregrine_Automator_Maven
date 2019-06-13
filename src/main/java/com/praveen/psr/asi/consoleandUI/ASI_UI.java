/*
 File Desc : Swing Form GUI file that functions to initiate, stop and configure tests.
 Author / Created By : Praveen P.S.R.
 Created On : 12/10/2016
 Reviewed By : Sridhar K
 Last Modified By : Praveen P.S.R.
 Last Modified On : 10/11/2016
 */
package com.praveen.psr.asi.consoleandUI;

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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Lokchand Suren
 */
public class ASI_UI extends javax.swing.JFrame {

    ArrayList<String> ConfigValues = new ArrayList<String>();
    public static int invokestart, commentcount = 1;
    static String CommentText = "";
    public static ASI_UI objectused;
    static DefaultCaret caret;

    /**
     * Creates new form OrderCapture
     */
    public ASI_UI() {
        initComponents();
    }

    public static void EndAllTests() throws IOException {
        ASIConsole.out.println("All the Tests have been Completed");
        invokestart = 0;
        objectused.startbutton.setVisible(true);
        objectused.forcestopbutton.setVisible(false);
    }

    public synchronized static void Activities_Ongoing(String Comments) {
        //CommentText += "< Log : " + commentcount + "> " + Comments + "\n";
        CommentText += Comments;
        objectused.jTextArea1.setText(CommentText);
        caret = (DefaultCaret) objectused.jTextArea1.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        // ++commentcount;
    }

    private void getConfigurationValues() throws Exception {

        configsheetTF.setText(ExcelProcessAndConfiguration.ConfigurationSheet);
        screenshotpathTF.setText(ExcelProcessAndConfiguration.ScreenshotPath);
        screenshotandreportprefixTF.setText(ExcelProcessAndConfiguration.ScreensnapPrefix);
        excelinputTF.setText(ExcelProcessAndConfiguration.InputExcelPath);
        outputreportpathTF.setText(ExcelProcessAndConfiguration.ReportPath);
        TestNGthreadpoolSpinner.setValue(CoreTestNGTestClass.dataproviderthreadcount);
        inputexceltypeCombobox.setSelectedItem((Object) ExcelProcessAndConfiguration.ExcelType);

    }

    public static void endbatch() {
        CoreTestNGTestClass.Userforcequitstatus = true;
        //objectused.jButton4.setVisible(false);
        objectused.forcestopbutton.setVisible(false);
    }

    private void saveFrameworkConfig() throws Exception {
        ExcelProcessAndConfiguration.ConfigurationSheet = configsheetTF.getText();
        ExcelProcessAndConfiguration.ScreenshotPath = screenshotpathTF.getText();
        ExcelProcessAndConfiguration.ScreensnapPrefix = screenshotandreportprefixTF.getText();
        ExcelProcessAndConfiguration.InputExcelPath = excelinputTF.getText();
        ExcelProcessAndConfiguration.ReportPath = outputreportpathTF.getText();
        ExcelProcessAndConfiguration.ExcelType = inputexceltypeCombobox.getSelectedItem().toString();
        CoreTestNGTestClass.dataproviderthreadcount = Integer.parseInt(TestNGthreadpoolSpinner.getValue().toString());
        ConfigurationLoader.saveFrameworkConfig();
        Activities_Ongoing("Configuration Parameters Saved Sucessfully to '" + ConfigurationLoader.default_directory + "\\asiconfig.txt' !");
    }

    public static void showforcestopbutton() {
        objectused.forcestopbutton.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        configsheetTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        screenshotpathTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        screenshotandreportprefixTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        saveconfigurationbutton = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        excelinputTF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        outputreportpathTF = new javax.swing.JTextField();
        startbutton = new javax.swing.JButton();
        forcestopbutton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        inputexceltypeCombobox = new javax.swing.JComboBox<String>();
        TestNGthreadpoolSpinner = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ASI Automator v1.0");

        jPanel1.setToolTipText("");
        jPanel1.setMaximumSize(new java.awt.Dimension(4000, 4000));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("ASI Framework Configuration");

        jLabel6.setText("Central Configuration Sheet Name");

        configsheetTF.setText("jTextField1");
        configsheetTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                configsheetTFKeyTyped(evt);
            }
        });

        jLabel7.setText("Screen Shot Path");

        screenshotpathTF.setText("jTextField1");
        screenshotpathTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screenshotpathTFActionPerformed(evt);
            }
        });
        screenshotpathTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                screenshotpathTFKeyTyped(evt);
            }
        });

        jLabel8.setText("Screenshot and Report Prefix");

        screenshotandreportprefixTF.setText("jTextField1");
        screenshotandreportprefixTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                screenshotandreportprefixTFKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("Framework Configuration");

        saveconfigurationbutton.setBackground(new java.awt.Color(51, 204, 255));
        saveconfigurationbutton.setText("Save Configuration");
        saveconfigurationbutton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                saveconfigurationbuttonStateChanged(evt);
            }
        });
        saveconfigurationbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveconfigurationbuttonActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("I/O Configuration");

        jLabel15.setText("Excel File Path");

        excelinputTF.setText("jTextField1");
        excelinputTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                excelinputTFKeyTyped(evt);
            }
        });

        jLabel16.setText("Output Report Path");

        outputreportpathTF.setText("jTextField1");
        outputreportpathTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputreportpathTFActionPerformed(evt);
            }
        });
        outputreportpathTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                outputreportpathTFKeyTyped(evt);
            }
        });

        startbutton.setBackground(new java.awt.Color(51, 255, 51));
        startbutton.setText("Start");
        startbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startbuttonActionPerformed(evt);
            }
        });

        forcestopbutton.setBackground(new java.awt.Color(255, 0, 51));
        forcestopbutton.setText("Force Stop");
        forcestopbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forcestopbuttonActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea1.setColumns(15);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setToolTipText("");
        jTextArea1.setDragEnabled(true);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel9.setText("TestNG Thread Pool Threshold");

        jLabel2.setText("jLabel2");

        jLabel3.setText("/ 10000");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("Execution Progress");

        jLabel18.setText("Input Excel Type");

        inputexceltypeCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Excel - Linear ROW Format", "Excel - PageObjectModel Format" }));
        inputexceltypeCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputexceltypeComboboxActionPerformed(evt);
            }
        });
        inputexceltypeCombobox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputexceltypeComboboxKeyReleased(evt);
            }
        });

        TestNGthreadpoolSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TestNGthreadpoolSpinnerStateChanged(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Designed and Developed by Praveen PSR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(outputreportpathTF, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(excelinputTF, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(183, 183, 183)
                                .addComponent(inputexceltypeCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(51, 51, 51)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(configsheetTF, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                        .addComponent(screenshotandreportprefixTF)
                                        .addComponent(screenshotpathTF))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TestNGthreadpoolSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(66, 66, 66))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(startbutton)
                        .addGap(97, 97, 97)
                        .addComponent(forcestopbutton)
                        .addGap(91, 91, 91)
                        .addComponent(saveconfigurationbutton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {excelinputTF, outputreportpathTF});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel12, jLabel14, jLabel17});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel15, jLabel16});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(1, 1, 1)
                .addComponent(jLabel12)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(configsheetTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(screenshotpathTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(screenshotandreportprefixTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(TestNGthreadpoolSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(excelinputTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputreportpathTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(inputexceltypeCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(forcestopbutton)
                    .addComponent(saveconfigurationbutton)
                    .addComponent(startbutton))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel14, jLabel17});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void forcestopbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forcestopbuttonActionPerformed
        Activities_Ongoing("Quitting Tests..Force failing all running Tests..Executions will be Stalled.. This may take time..");
        //ExcelReader.TerminateallSauceVMs();// TODO add your handling code here:
        endbatch();
        Activities_Ongoing("Test Cases Stalled! Results may not be as expected.!Wait until the Test cases are quit and report is generated..");
    }//GEN-LAST:event_forcestopbuttonActionPerformed

    private void startbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startbuttonActionPerformed
        if (invokestart == 0) {
            ASIConsole.loadConsoleObjects();
            Activities_Ongoing("Starting Execution..\n");
            CoreTestNGTestClass.Userforcequitstatus = false;
            invokestart = 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        //new ExcelReader().configurationsplitter();

                        ExcelProcessAndConfiguration.ConfigurationSheet = configsheetTF.getText();
                        ExcelProcessAndConfiguration.ScreenshotPath = screenshotpathTF.getText();
                        ExcelProcessAndConfiguration.ScreensnapPrefix = screenshotandreportprefixTF.getText();
                        ExcelProcessAndConfiguration.InputExcelPath = excelinputTF.getText();
                        ExcelProcessAndConfiguration.ReportPath = outputreportpathTF.getText();
                        ExcelProcessAndConfiguration.ExcelType = inputexceltypeCombobox.getSelectedItem().toString();
                        if (Integer.parseInt(TestNGthreadpoolSpinner.getValue().toString()) == 0 || TestNGthreadpoolSpinner.getValue().equals("") || TestNGthreadpoolSpinner.getValue() == null) {
                            CoreTestNGTestClass.dataproviderthreadcount = 1;
                        } else {
                            try {
                                CoreTestNGTestClass.dataproviderthreadcount = Integer.parseInt(TestNGthreadpoolSpinner.getValue().toString());
                            } catch (NumberFormatException Ne) {
                                CoreTestNGTestClass.dataproviderthreadcount = 1;
                            }
                        }
                        new CoreRunnerClass().runCases();

                        // TODO add your handling code here:
                    } catch (IllegalArgumentException | IOException ex) {
                        Logger.getLogger(ASI_UI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ASI_UI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(ASI_UI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
            Activities_Ongoing("Execution Started!\n");
            startbutton.setVisible(false);
            forcestopbutton.setVisible(true);
        } else {
            if (invokestart == 1) {
                Activities_Ongoing("Exection has started Already, Please wait until completetion..!");
            }
        }
    }//GEN-LAST:event_startbuttonActionPerformed

    private void outputreportpathTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_outputreportpathTFKeyTyped
        saveconfigurationbutton.setVisible(true);
    }//GEN-LAST:event_outputreportpathTFKeyTyped

    private void outputreportpathTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputreportpathTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_outputreportpathTFActionPerformed

    private void excelinputTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_excelinputTFKeyTyped
        saveconfigurationbutton.setVisible(true);
    }//GEN-LAST:event_excelinputTFKeyTyped

    private void saveconfigurationbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveconfigurationbuttonActionPerformed
        try {
            saveFrameworkConfig();       // TODO add your handling code here:
            saveconfigurationbutton.setVisible(false);
        } catch (Exception ex) {
            Logger.getLogger(ASI_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveconfigurationbuttonActionPerformed

    private void saveconfigurationbuttonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_saveconfigurationbuttonStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_saveconfigurationbuttonStateChanged

    private void screenshotandreportprefixTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_screenshotandreportprefixTFKeyTyped
        saveconfigurationbutton.setVisible(true);
    }//GEN-LAST:event_screenshotandreportprefixTFKeyTyped

    private void screenshotpathTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_screenshotpathTFKeyTyped
        saveconfigurationbutton.setVisible(true);
    }//GEN-LAST:event_screenshotpathTFKeyTyped

    private void screenshotpathTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screenshotpathTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_screenshotpathTFActionPerformed

    private void configsheetTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configsheetTFKeyTyped
        saveconfigurationbutton.setVisible(true);
    }//GEN-LAST:event_configsheetTFKeyTyped

    private void inputexceltypeComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputexceltypeComboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputexceltypeComboboxActionPerformed

    private void TestNGthreadpoolSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TestNGthreadpoolSpinnerStateChanged
        saveconfigurationbutton.setVisible(true);
    }//GEN-LAST:event_TestNGthreadpoolSpinnerStateChanged

    private void inputexceltypeComboboxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputexceltypeComboboxKeyReleased
        saveconfigurationbutton.setVisible(true);
    }//GEN-LAST:event_inputexceltypeComboboxKeyReleased

    /**
     * @param args the command line arguments
     */
    public synchronized static void addcompletionprogress(int completedthreads) {
        objectused.jLabel2.setText("" + completedthreads);
        objectused.jProgressBar1.setValue(completedthreads);
    }

    public synchronized static void setMaxlimit2progress(int Numberofcases) {
        objectused.jProgressBar1.setMinimum(0);
        objectused.jProgressBar1.setMaximum(Numberofcases);
        objectused.jProgressBar1.setValue(0);
        objectused.jLabel2.setText("0");
        objectused.jLabel3.setText("/ " + Numberofcases + " test case execution(s) completed");
        objectused.jLabel17.setVisible(true);
        objectused.jLabel2.setVisible(true);
        objectused.jLabel3.setVisible(true);
        objectused.jProgressBar1.setVisible(true);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ASI_UI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ASI_UI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ASI_UI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ASI_UI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            /* Create and display the form */
            objectused = new ASI_UI();
            objectused.getConfigurationValues();
            objectused.setVisible(true);
            objectused.saveconfigurationbutton.setVisible(false);
            objectused.startbutton.setVisible(true);
            objectused.forcestopbutton.setVisible(false);
            objectused.jLabel17.setVisible(false);
            objectused.jLabel2.setVisible(false);
            objectused.jLabel3.setVisible(false);
            objectused.jProgressBar1.setVisible(false);
            //objectused.jScrollPane1.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        } catch (Exception ex) {
            Logger.getLogger(ASI_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        //objectused = new ASI_UI();
        //objectused.RunUI();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner TestNGthreadpoolSpinner;
    private javax.swing.JTextField configsheetTF;
    private javax.swing.JTextField excelinputTF;
    private javax.swing.JButton forcestopbutton;
    private javax.swing.JComboBox<String> inputexceltypeCombobox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField outputreportpathTF;
    private javax.swing.JButton saveconfigurationbutton;
    private javax.swing.JTextField screenshotandreportprefixTF;
    private javax.swing.JTextField screenshotpathTF;
    private javax.swing.JButton startbutton;
    // End of variables declaration//GEN-END:variables
}