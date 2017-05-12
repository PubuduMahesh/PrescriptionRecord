package cambio.precriptionrecord.view.prescription;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import cambio.precriptionrecord.controller.CommonController;
import cambio.precriptionrecord.controller.DrugController;
import cambio.precriptionrecord.controller.PrescriptionController;
import cambio.precriptionrecord.model.drug.Drug;
import cambio.precriptionrecord.model.prescription.Prescription;
import cambio.precriptionrecord.model.prescription.PrescriptionForm;
import cambio.precriptionrecord.model.prescription.PrescriptionTableModel;
import cambio.precriptionrecord.util.DBConnection;
import cambio.precriptionrecord.util.DatePicker;
import cambio.precriptionrecord.view.Home;
import cambio.precriptionrecord.view.drug.DrugSearchPanel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.swing.JRViewer;
//import net.sf.jasperreports.swing.JRViewer;

import org.json.simple.JSONObject;

public class PrescriptionPanel extends JInternalFrame {

    private GridBagLayout gridbag;
    private JButton bDate;
    private JTextField tDate;
    private JTextArea tAnalysis;
    private JTable prescriptionTable;
    private JButton bAdd;
    private JButton bEdit;
    private JButton bSave;
    private JButton bPrint;
    private JButton bSend;
    private JButton bClear;
    private JButton bRemove;
    private PrescriptionTableModel tbModel;
    private DrugSearchPanel drugSearchPanel;
    private final DrugController drugController = new DrugController();
    private final PrescriptionController prescriptionController;
    private final CommonController commonController;

    public PrescriptionPanel(PrescriptionController prescriptionController, CommonController commonController) {
        this.prescriptionController = prescriptionController;
        this.commonController = commonController;
        gridbag = new GridBagLayout();
        setLayout(gridbag);
        setPreferredSize(new Dimension(730, 350));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        /*converting JInternalFrame into BasicInternalFrame and hide the default title bar of the JInternal frame. */
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
        createLayout();
        setVisible(true);
    }

    private void createLayout() {
        addField();
        addDrugSearchPanel();
        addPrescriptionTable();
        saveButtonDisabled();
    }

    private void addField() {
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel lDate = new JLabel("Date");
        JLabel lAnalysis = new JLabel("Diagnosis");
        JLabel lPrescription = new JLabel("Prescription");
        tDate = new JTextField(8);
        bDate = new JButton();
        tAnalysis = new JTextArea(17, 18);
        bAdd = new JButton("Add");
        bEdit = new JButton("Edit");
        bSave = new JButton("Save");
        bPrint = new JButton("Print");
        bSend = new JButton("Send");
        bClear = new JButton("Clear");
        bRemove = new JButton("Remove");

        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.gridx = 0;
        constraints.gridy = 0;

        /*label - date*/
        constraints.insets = new Insets(0, 0, 0, 0);
        gridbag.setConstraints(lDate, constraints);
        add(lDate);

        /*text field - date*/
        constraints.insets = new Insets(0, 60, 0, 0);
        gridbag.setConstraints(tDate, constraints);
        add(tDate);

        /*button - date*/
        bDate.setIcon(new javax.swing.ImageIcon("src/cambio/Image/calendarIcon.png"));
        bDate.setPreferredSize(new Dimension(20, 20));
        constraints.insets = new Insets(0, 160, 0, 0);
        gridbag.setConstraints(bDate, constraints);
        add(bDate);

        /*label - diagnosis*/
        constraints.insets = new Insets(200, 0, 0, 0);
        gridbag.setConstraints(lAnalysis, constraints);
        add(lAnalysis);

        /*text area - Analysis*/
        constraints.insets = new Insets(30, 60, 0, 0);
        JScrollPane diagnosisScrollPane = new JScrollPane(tAnalysis, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        gridbag.setConstraints(diagnosisScrollPane, constraints);
        add(diagnosisScrollPane);

        /*button - add*/
        constraints.insets = new Insets(110, 635, 0, 0);
        gridbag.setConstraints(bAdd, constraints);
        add(bAdd);

        /*button - edit*/
        constraints.insets = new Insets(110, 565, 0, 0);
        gridbag.setConstraints(bEdit, constraints);
        add(bEdit);

        /*lable - prescription*/
        constraints.insets = new Insets(135, 280, 0, 0);
        gridbag.setConstraints(lPrescription, constraints);
        add(lPrescription);

        /*button - save*/
        constraints.insets = new Insets(285, 275, 0, 0);
        gridbag.setConstraints(bSave, constraints);
        add(bSave);

        /*button - print*/
        constraints.insets = new Insets(285, 350, 0, 0);
        gridbag.setConstraints(bPrint, constraints);
        add(bPrint);

        /*button - send*/
        constraints.insets = new Insets(285, 425, 0, 0);
        gridbag.setConstraints(bSend, constraints);
        add(bSend);

        /*button - clear*/
        constraints.insets = new Insets(285, 500, 0, 0);
        gridbag.setConstraints(bClear, constraints);
        add(bClear);

        /*button - remove*/
        constraints.insets = new Insets(285, 575, 0, 0);
        gridbag.setConstraints(bRemove, constraints);
        add(bRemove);

        lDate.setText("<html>Date <font color='red'> *</font></html>");
        lAnalysis.setText("<html>Analys <font color='red'> *</font></html>");
        lPrescription.setText("<html>Prescription <font color='red'>*</font></html>");

        /*action */
        dateButtonAction();
        mouseClickRow();
        saveButtonAction();
        printButtonAction();
        sendButtonAction();
        clearButtonAction();
        removeButtonAction();
        setPrescriptionObject();
    }

    private void addDrugSearchPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        int tbWidth = 410;
        int tbHeight = 50;
        drugSearchPanel = new DrugSearchPanel(drugController, tbWidth, tbHeight, commonController);
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 275, 0, 0);
        gridbag.setConstraints(drugSearchPanel, constraints);
        add(drugSearchPanel);
    }

    private void addPrescriptionTable() {
        GridBagConstraints tablConstraints = new GridBagConstraints();

        final String[] header = {"ID", "Name", "Description", "Type", "Dosage"};
        ArrayList<Drug> data = new ArrayList<Drug>();
        prescriptionTable = new JTable(new PrescriptionTableModel(data, header));

        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        prescriptionTable.setPreferredScrollableViewportSize(new Dimension(425, 100));
        prescriptionTable.setFillsViewportHeight(true);
        scrollPane.setViewportView(prescriptionTable);

        tablConstraints.anchor = GridBagConstraints.NORTHWEST;//align content of main panel in to left top corner.
        tablConstraints.gridx = 0;
        tablConstraints.gridy = 0;
        tablConstraints.insets = new Insets(160, 275, 0, 0);
        gridbag.setConstraints(scrollPane, tablConstraints);
        add(scrollPane);

        this.tbModel = (PrescriptionTableModel) prescriptionTable.getModel();
    }

    private void dateButtonAction() {
        bDate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selDate = new DatePicker((PrescriptionPanel) (bDate.getParent().getParent().getParent().getParent())).setPickedDate();
                    Date date = new SimpleDateFormat("yyyy/MM/dd").parse(selDate);//convert the selected Date in to the "Date" type
                    if (date.before(new Date())) {//check whether the selected date is grater than with respect to the current date. 
                        tDate.setText(selDate);//set the date to the birthday text field.
                    } else {//if validation is failed, warning message. 
                        JOptionPane.showMessageDialog(null, "Date should be previous date", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ParseException ex) {
                    //add logger.
                }
            }
        });
    }

    private void addButtonAction(final Drug drug) {
        bAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tbModel = (PrescriptionTableModel) prescriptionTable.getModel();
                tbModel.updateTable(drug);
            }
        });
    }

    private void mouseClickRow() {
        drugController.registerRowClickListeners(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof Drug) {
                    Drug drug = (Drug) e.getSource();
                    addButtonAction(drug);
                    editButtonAction(drug);
                }
            }
        });
    }

    private void editButtonAction(final Drug drug) {
        bEdit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createDialog(drug);
            }
        });
    }

    private void createDialog(Drug drug) {
        new EditDosage(drug, prescriptionController);
        editDosageAddButtonAction();
    }

    private void editDosageAddButtonAction() {
        prescriptionController.registerEditPrescriptionDosageListeners(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tbModel.updateTable((Drug) e.getSource());

            }
        });

    }

    private void saveButtonAction() {
        bSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                prescriptionController.fireSavePrescriptionPerformed(e);
                

            }
        });
    }

    private void setPrescriptionObject() {
    	final Prescription prescription = new Prescription();
    	prescriptionController.registerSavePrescriptionReverseListeners(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                prescription.setDate(tDate.getText());
                prescription.setDiagnosisDescription(tAnalysis.getText());
                prescription.setDoctorID(Home.userNIC);//need to change
                prescription.setDrugList(createDrugList());
                prescription.setPatientID((String) e.getSource());
                savePrescription(prescription);
            }
        });

    }

    private String createDrugList() {
        int rowCount = tbModel.getRowCount();
        Drug drug = new Drug();
        String drugList = null;
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            drug = tbModel.getValue(rowIndex);
            drugList += drug.getDrugId() + "-" + drug.getDrugName()
                    + "-" + drug.getDescription()
                    + "-" + drug.getType()
                    + "-" + drug.getDosage() + ",\n";

        }
        return drugList;

    }

    private void savePrescription(Prescription prescription) {
        DBConnection dbCon = new DBConnection();
        Connection con = dbCon.getConnection();
        Statement stmt = null;
        String sql = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            sql = "INSERT INTO `prescription` (`id`,`patientID`,`doctorID`,`diagDescription`,`drugList`,`date`) "
                    + "VALUES(NULL,'"+prescription.getPatientID()+"',(SELECT `id` FROM  `doctor` WHERE `doctor`.`nic` = '"+prescription.getDoctorID()+"'),'"+prescription.getDiagnosisDescription()+"','"+prescription.getDrugList()+"','"+prescription.getDate()+"')";
            stmt.executeUpdate(sql);
            con.close();
            JOptionPane.showMessageDialog(null, "Prescription detail saving succeeded.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Prescription detail saving failed", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void printButtonAction() {
        bPrint.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Prescription prescription = new Prescription();
                prescription.setDate(tDate.getText());
                prescription.setDiagnosisDescription(tAnalysis.getText());
                printReport(prescription);

            }
        });
    }

    private void sendButtonAction() {
        bSend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PrescriptionForm prescriptionForm = new PrescriptionForm();
                prescriptionForm.setPatientName("Mahela");
                prescriptionForm.setPatientAge("20");
                prescriptionForm.setPatientTelePhone("0775252215");
                prescriptionForm.setDate(tDate.getText());
                prescriptionForm.setDoctorName("Sanath");
                prescriptionForm.setDoctorTelephone("0775855623");
                createJsonObject(prescriptionForm);

            }
        });

    }

    private void clearButtonAction() {
        bClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like to Clear All Field?", "Warning", 0);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    clearField();
                }

            }
        });
    }

    private void printReport(Prescription prescription) {
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("date", prescription.getDate());
            parameters.put("diagnosis", prescription.getDiagnosisDescription());
            String sourceName = "src/cambio/report/temp.jrxml";
            JasperReport report = JasperCompileManager.compileReport(sourceName);

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

            JDialog dialog = new JDialog();
            dialog.setVisible(true);
//			dialog.getContentPane().add(new JRViewer(jasperPrint));
            dialog.pack();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

    private void createJsonObject(PrescriptionForm prescriptionForm) {
        JSONObject object = new JSONObject();
        object.put("patientName", prescriptionForm.getPatientName());
        object.put("patientAge", prescriptionForm.getPatientAge());
        object.put("patientTelephone", prescriptionForm.getPatientTelePhone());
        object.put("date", prescriptionForm.getDate());
        object.put("doctorName", prescriptionForm.getDoctorName());
        object.put("doctorTelephone", prescriptionForm.getDoctorTelephone());

        StringWriter out = new StringWriter();
        try {
            object.writeJSONString(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void clearField() {
        tDate.setText("");
        tAnalysis.setText("");
        ActionEvent e = new ActionEvent(drugSearchPanel, -2, null);
        commonController.fireClearPrescriptionReportALlElementActionPerformed(e);//clearing drug table
        int rowCount = tbModel.getRowCount();//clearing prescriptionTable		
        for (int i = 0; i < rowCount; i++) {
            tbModel.removeRow(0);

        }
    }

    private void saveButtonDisabled() {
        bSave.setEnabled(false);
        new SaveButtonTextFieldCondtion(tDate);
        new SaveButtonTextFieldAreaCondtion(tAnalysis);
    }

    private class SaveButtonTextFieldCondtion {

        public SaveButtonTextFieldCondtion(JTextField textField) {
            textField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {
                    enableSaveButton();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    enableSaveButton();
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    enableSaveButton();
                }

            });
        }
    }

    private class SaveButtonTextFieldAreaCondtion {

        public SaveButtonTextFieldAreaCondtion(JTextArea textArea) {
            textArea.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {
                    enableSaveButton();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    enableSaveButton();
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    enableSaveButton();
                }

            });
        }
    }

    private void enableSaveButton() {
        if (!tDate.getText().equals("") && !tAnalysis.getText().equals("")) {
            bSave.setEnabled(true);
        } else {
            bSave.setEnabled(false);
        }
    }

    private void removeButtonAction() {
        bRemove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int row = prescriptionTable.getSelectedRow();
                if (row >= 0) {
                    tbModel.removeRow(row);
                }

            }
        });
    }
    

}
