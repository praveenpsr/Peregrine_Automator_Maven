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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        configsheetTF = new javax.swing.JTextField();
        screenshotpathTF = new javax.swing.JTextField();
        screenshotandreportprefixTF = new javax.swing.JTextField();
        TestNGthreadpoolSpinner = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        excelinputTF = new javax.swing.JTextField();
        outputreportpathTF = new javax.swing.JTextField();
        inputexceltypeCombobox = new javax.swing.JComboBox<String>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        startbutton = new javax.swing.JButton();
        forcestopbutton = new javax.swing.JButton();
        saveconfigurationbutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Peregrine Automator v1.0");

        jPanel1.setToolTipText("");
        jPanel1.setMaximumSize(new java.awt.Dimension(4000, 4000));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Peregrine Automator");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("DESIGNED AND DEVELOPED BY PRAVEEN PSR  ");

        jLabel5.setFont(new java.awt.Font("MS Reference Sans Serif", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 51));
        jLabel5.setText("powered by ASI framework");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tool Configuration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel6.setText("Central Configuration Sheet Name");

        jLabel7.setText("Screen Shot Folder Path");

        jLabel8.setText("Screenshot and Report Filename Prefix");

        jLabel9.setText("Parallel Execution Limit");

        configsheetTF.setText("jTextField1");
        configsheetTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                configsheetTFKeyTyped(evt);
            }
        });

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

        screenshotandreportprefixTF.setText("jTextField1");
        screenshotandreportprefixTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                screenshotandreportprefixTFKeyTyped(evt);
            }
        });

        TestNGthreadpoolSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TestNGthreadpoolSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(configsheetTF, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(screenshotandreportprefixTF, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(screenshotpathTF, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TestNGthreadpoolSpinner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 134, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(configsheetTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(13, 13, 13)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(screenshotpathTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(13, 13, 13)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(screenshotandreportprefixTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addGap(13, 13, 13)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(TestNGthreadpoolSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "I/O Configuration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel15.setText("Excel File Path");

        jLabel16.setText("Output Report Path");

        jLabel18.setText("Input Excel Type");

        excelinputTF.setText("jTextField1");
        excelinputTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                excelinputTFKeyTyped(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(outputreportpathTF, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(excelinputTF, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inputexceltypeCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {excelinputTF, outputreportpathTF});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(excelinputTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputreportpathTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(inputexceltypeCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Execution Progress and Console", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea1.setColumns(15);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 1, 11)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setToolTipText("");
        jTextArea1.setDragEnabled(true);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("Execution Progress");

        jLabel3.setText("/ 10000");

        jLabel2.setText("jLabel2");

        startbutton.setBackground(new java.awt.Color(51, 255, 51));
        startbutton.setText("Start");
        startbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startbuttonActionPerformed(evt);
            }
        });

        forcestopbutton.setBackground(new java.awt.Color(255, 0, 51));
        forcestopbutton.setText("Force Stop");
        forcestopbutton.setAlignmentY(0.0F);
        forcestopbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forcestopbuttonActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(startbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(forcestopbutton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveconfigurationbutton))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addComponent(jLabel17))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(jScrollPane1)
                    .addGap(8, 8, 8)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(118, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(forcestopbutton)
                    .addComponent(saveconfigurationbutton)
                    .addComponent(startbutton))
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(125, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 361, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(10, 10, 10))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputexceltypeComboboxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputexceltypeComboboxKeyReleased
        saveconfigurationbutton.setVisible(true);
    }//GEN-LAST:event_inputexceltypeComboboxKeyReleased

    private void inputexceltypeComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputexceltypeComboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputexceltypeComboboxActionPerformed

    private void outputreportpathTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_outputreportpathTFKeyTyped
        saveconfigurationbutton.setVisible(true);
    }//GEN-LAST:event_outputreportpathTFKeyTyped

    private void outputreportpathTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputreportpathTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_outputreportpathTFActionPerformed

    private void excelinputTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_excelinputTFKeyTyped
        saveconfigurationbutton.setVisible(true);
    }//GEN-LAST:event_excelinputTFKeyTyped

    private void TestNGthreadpoolSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TestNGthreadpoolSpinnerStateChanged
        saveconfigurationbutton.setVisible(true);
    }//GEN-LAST:event_TestNGthreadpoolSpinnerStateChanged

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
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
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
