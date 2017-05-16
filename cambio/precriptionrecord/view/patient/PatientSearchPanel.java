package cambio.precriptionrecord.view.patient;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cambio.precriptionrecord.SQLToolKit.PatientSQLToolkit;
import cambio.precriptionrecord.controller.CommonController;
import cambio.precriptionrecord.controller.PatientController;
import cambio.precriptionrecord.model.patient.PatientTableModel;
import cambio.precriptionrecord.model.patient.Patient;
import cambio.precriptionrecord.util.DBConnection;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class PatientSearchPanel extends JPanel {

    private final GridBagLayout gridbag;
    private JLabel lSearchName;
    private JLabel lSearchNIC;
    private JTextField tSearchName;
    private JTextField tSearchNIC;
    private JButton bSearchButton;
    private JButton bClearButton;
    private JTable searchTable;
    private final PatientTableModel tbModel;
    private final PatientController patientController;
    private final CommonController commonController;

    public PatientSearchPanel(PatientController patientController,
            int tbWidth,
            int tbHeight,
            CommonController commonController) {
        this.patientController = patientController;
        this.commonController = commonController;
        this.gridbag = new GridBagLayout();
        setLayout(gridbag);
        setPreferredSize(new Dimension(tbWidth + 30, tbHeight + 70));
        addSearchBar();
        addTable(tbWidth, tbHeight);
        this.tbModel = (PatientTableModel) searchTable.getModel();
        /*commonController Action Fired*/
        commonControllerAction();
    }

    private void addSearchBar() {
        GridBagConstraints searchConstraints = new GridBagConstraints();

        lSearchName = new JLabel("Name");
        lSearchNIC = new JLabel("NIC");
        tSearchName = new JTextField(20);
        tSearchNIC = new JTextField(12);
        bSearchButton = new JButton("Search");
        bClearButton = new JButton("Clear");

        searchConstraints.anchor = GridBagConstraints.NORTHWEST;

        searchConstraints.gridx = 0;
        searchConstraints.gridy = 0;
        searchConstraints.insets = new Insets(0, 0, 0, 0);
        gridbag.setConstraints(lSearchName, searchConstraints);
        add(lSearchName);

        searchConstraints.insets = new Insets(0, 50, 0, 0);
        gridbag.setConstraints(tSearchName, searchConstraints);
        add(tSearchName);

        searchConstraints.insets = new Insets(0, 300, 10, 0);
        gridbag.setConstraints(lSearchNIC, searchConstraints);
        add(lSearchNIC);

        searchConstraints.insets = new Insets(0, 330, 0, 0);
        gridbag.setConstraints(tSearchNIC, searchConstraints);
        add(tSearchNIC);

        searchConstraints.insets = new Insets(0, 500, 0, 0);
        gridbag.setConstraints(bSearchButton, searchConstraints);
        add(bSearchButton);

        searchConstraints.insets = new Insets(0, 590, 0, 0);
        gridbag.setConstraints(bClearButton, searchConstraints);
        add(bClearButton);

        /*button Actions*/
        searchButtonAction();
        clearButtonAction();
    }

    private void addTable(int tbWidth, int tbHeight) {

        GridBagConstraints tablConstraints = new GridBagConstraints();

        final String[] header = {"ID", "Name", "NIC", "Address", "Gender", "Status", "Birthday", "Telehphone", "Health Description"};
        List<Patient> data = new ArrayList<Patient>();
        searchTable = new JTable(new PatientTableModel(data, header));

        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        searchTable.setPreferredScrollableViewportSize(new Dimension(tbWidth, tbHeight));
        searchTable.setFillsViewportHeight(true);
        scrollPane.setViewportView(searchTable);

        tablConstraints.anchor = GridBagConstraints.NORTHWEST;//align content of main panel in to left top corner.
        tablConstraints.gridx = 0;
        tablConstraints.gridy = 1;
        gridbag.setConstraints(scrollPane, tablConstraints);
        add(scrollPane);

        /*Mouse Clicked Event*/
        mouseClickAction();

        /*Remove Button Clicked Actions*/
        removeRow();

        /*Edit Button Clicked Action*/
        updateRow();

    }

    private void searchButtonAction() {
        bSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable();
                PatientSQLToolkit patientToolKit = new PatientSQLToolkit();
                List<Patient> patientList = patientToolKit.searchPatient(tSearchName.getText(), tSearchNIC.getText());
                try{
                	if(patientList.size()>0){
                		for(Patient patient:patientList)
                			tbModel.updateTable(patient);   
                	}
                		             		
                }
                catch(Exception ex){
                	System.out.println(ex.getMessage());
                }
                	
            }

        });
    }

    private void clearButtonAction() {
        bClearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tSearchNIC.setText("");
                tSearchName.setText("");
                clearTable();

            }
        });
    }

    

    private void mouseClickAction() {
        searchTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    final JTable target = (JTable) e.getSource();
                    final int row = target.getSelectedRow();
                    if (row != -1) {
                        Patient patient = ((PatientTableModel) target.getModel()).getValue(row);
                        ActionEvent eClick = new ActionEvent(patient, -1, "");
                        patientController.fireRowClickActionPerformed(eClick);
                    }

                }
            }
        });
    }

    private void removeRow() {
        patientController.registerRemoveRowPatientSearchTableListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = ((PatientTableModel) searchTable.getModel()).getRowIndex(e.getSource().toString());
                tbModel.removeRow(rowIndex);
            }
        });
    }

    private void updateRow() {
        patientController.registerUpdateRowPatientSearchTableListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Patient patient = (Patient) e.getSource();
                int updatedRow = ((PatientTableModel) searchTable.getModel()).getRowIndex(patient.getID());
                if(updatedRow >= 0)
                	tbModel.setValueAtRow(patient, updatedRow);
                else{
                	tbModel.updateTable(patient);
                }
                	
            }
        });
    }

    private void commonControllerAction() {
        /*This action is fired when clear button of the prescription form is clicked*/
        commonController.registerClearPrescriptionReportAllElementActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tSearchName.setText("");
                tSearchNIC.setText("");
                clearTable();

            }
        });
    }

    private void clearTable() {
        int rowCount = tbModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            tbModel.removeRow(0);

        }
    }
}
