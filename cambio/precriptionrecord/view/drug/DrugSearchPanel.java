package cambio.precriptionrecord.view.drug;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import cambio.precriptionrecord.SQLToolKit.DrugSQLToolkit;
import cambio.precriptionrecord.controller.CommonController;
import cambio.precriptionrecord.controller.DrugController;
import cambio.precriptionrecord.model.drug.Drug;
import cambio.precriptionrecord.model.drug.DrugTableModel;
import cambio.precriptionrecord.util.DBConnection;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class DrugSearchPanel extends JPanel {

    private GridBagLayout gridbag;
    private JLabel lSearchName;
    private JLabel lSearchDrugId;
    private JTextField tSearchName;
    private JTextField tSearchDrugId;
    private JButton bSearchButton;
    private JButton bClearButton;
    private JTable searchTable;
    DrugTableModel tbModel;

    private final DrugController drugController;
    private final CommonController commonController;

    public DrugSearchPanel(DrugController drugController, int tbWidth, int tbHeight,
            CommonController commonController) {
        this.drugController = drugController;
        this.commonController = commonController;
        this.gridbag = new GridBagLayout();
        setLayout(gridbag);
        setPreferredSize(new Dimension(tbWidth + 25, tbHeight + 70));
        addSearchBar();
        addTable(tbWidth, tbHeight);
        tbModel = (DrugTableModel) searchTable.getModel();
    }

    private void addSearchBar() {
        GridBagConstraints searchConstraints = new GridBagConstraints();
        searchConstraints.anchor = GridBagConstraints.WEST;

        lSearchName = new JLabel("Name");
        lSearchDrugId = new JLabel("ID");
        tSearchName = new JTextField(10);
        tSearchDrugId = new JTextField(6);
        bSearchButton = new JButton("Search");
        bClearButton = new JButton("Clear");

        searchConstraints.gridx = 0;
        searchConstraints.gridy = 0;
        searchConstraints.insets = new Insets(0, 0, 0, 0);
        gridbag.setConstraints(lSearchName, searchConstraints);
        add(lSearchName);

        searchConstraints.insets = new Insets(0, 40, 0, 0);
        gridbag.setConstraints(tSearchName, searchConstraints);
        add(tSearchName);

        searchConstraints.insets = new Insets(0, 160, 10, 0);
        gridbag.setConstraints(lSearchDrugId, searchConstraints);
        add(lSearchDrugId);

        searchConstraints.insets = new Insets(0, 175, 0, 0);
        gridbag.setConstraints(tSearchDrugId, searchConstraints);
        add(tSearchDrugId);

        searchConstraints.insets = new Insets(0, 250, 0, 0);
        gridbag.setConstraints(bSearchButton, searchConstraints);
        add(bSearchButton);

        searchConstraints.insets = new Insets(0, 340, 0, 0);
        gridbag.setConstraints(bClearButton, searchConstraints);
        add(bClearButton);

        /*button Actions*/
        searchButtonAction();
        clearButtonAction();

        fireCommonControllerAction();
    }

    private void addTable(int tbWidth, int tbHeight) {

        GridBagConstraints tablConstraints = new GridBagConstraints();

        final String[] header = {"id", "name", "description", "type"};
        List<Drug> data = new ArrayList<Drug>();
        searchTable = new JTable(new DrugTableModel(data, header));

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
                DrugSQLToolkit drugToolkit = new DrugSQLToolkit();
                List<Drug> drugList = drugToolkit.searchDrug(tSearchName.getText(), tSearchDrugId.getText());
                if(drugList.size()>0){
                	for(Drug drug:drugList)
                		tbModel.updateTable(drug);
                }
                
            }

        });
    }

    private void clearButtonAction() {
        bClearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
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
                    if (row >= 0) {
                        Drug drug = tbModel.getValue(row);
                        ActionEvent eClick = new ActionEvent(drug, -1, "");
                        drugController.fireRowClickActionPerformed(eClick);
                    }

                }
                else if(e.getClickCount() == 2){
                	final JTable target = (JTable) e.getSource();
                    final int row = target.getSelectedRow();
                    if (row >= 0) {
                        Drug drug = tbModel.getValue(row);
                        ActionEvent eClick = new ActionEvent(drug, -1, "");
                        drugController.fireRowDoubleClickActionPerformed(eClick);
                    }
                }
            }
        });
    }

    private void removeRow() {
        drugController.registerRemoveRowDrugSearchTableListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = tbModel.getRowIndex(e.getSource().toString());
                tbModel.removeRow(rowIndex);
            }
        });
    }

    private void updateRow() {
        drugController.registerEditRowDrugSearchTableListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Drug drug = (Drug) e.getSource();
                int updatedRow = tbModel.getRowIndex(drug.getDrugId());
                if(updatedRow >= 0)
                    tbModel.setValueAtRow(drug, updatedRow);
                else
                    tbModel.updateTable(drug);
            }
        });
    }

    private void clearTable() {
        int rowCount = tbModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            tbModel.removeRow(0);

        }
    }

    private void fireCommonControllerAction() {
        /*this method is called when clear button of the prescription form.*/
        commonController.registerClearPrescriptionReportAllElementActionListeners(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tSearchDrugId.setText("");
                tSearchName.setText("");
                clearTable();

            }
        });
    }

}
