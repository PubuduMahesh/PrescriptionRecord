package cambio.precriptionrecord.view.drug;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cambio.precriptionrecord.SQLToolKit.DrugSQLToolkit;
import cambio.precriptionrecord.controller.CommonController;
import cambio.precriptionrecord.controller.DrugController;
import cambio.precriptionrecord.model.drug.Drug;
import cambio.precriptionrecord.util.DBConnection;

public class EditDrug extends JInternalFrame {

    private DrugController drugController = new DrugController();
    private final CommonController commonController;
    private final GridBagLayout gridbag;
    private JTextField tName;
    private JTextArea tDescription;
    private JRadioButton rbTablet;
    private JRadioButton rbCapsules;
    private JRadioButton rbSyrups;
    private ButtonGroup bgType;
    private JButton bSave;
    private JButton bClear;
    private JTextField tID;
    private JButton bDelete;

    public EditDrug() {
        this.drugController = new DrugController();
        this.commonController = new CommonController();
        setTitle("Update Drug");
        JDesktopPane desktopPane = new JDesktopPane();
        setPreferredSize(new Dimension(510, 510));
        setMinimumSize(new Dimension(510, 510));
        setClosable(true);
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        setFrameIcon(new javax.swing.ImageIcon("src/cambio/Image/drug.png"));
        gridbag = new GridBagLayout();
        setLayout(gridbag);

        createLayout();
        desktopPane.add(this);
        /*Button action performed*/
        clearButtonAction();
        saveButtonAction();
        mouseClickRow();
    }

    private void createLayout() {
        addSearchPanel();
        addLabel();
        addField();
    }

    private void addSearchPanel() {
        GridBagConstraints constraintsSearch = new GridBagConstraints();
        constraintsSearch.anchor = GridBagConstraints.NORTHWEST;
        int tbWidth = 410;
        int tbHeight = 130;
        DrugSearchPanel searchPanel = new DrugSearchPanel(drugController, tbWidth, tbHeight, commonController);
        constraintsSearch.gridx = 0;
        constraintsSearch.gridy = 0;
        constraintsSearch.insets = new Insets(0, 0, 0, 0);
        gridbag.setConstraints(searchPanel, constraintsSearch);
        add(searchPanel);
    }

    private void addLabel() {
        GridBagConstraints constraintsLabel = new GridBagConstraints();
        constraintsLabel.anchor = GridBagConstraints.NORTHWEST;

        JLabel lName = new JLabel();
        JLabel lDescription = new JLabel("Description");
        JLabel lForm = new JLabel();
        JLabel lID = new JLabel("drugID");

        lName.setText("<html>Name <font color='red'> *</font></html>");
        lForm.setText("<html>Drug Form <font color='red'> *</font></html>");

        constraintsLabel.gridx = 0;
        constraintsLabel.gridy = 1;

        /*Label - id*/
        constraintsLabel.insets = new Insets(10, 0, 0, 0);
        gridbag.setConstraints(lID, constraintsLabel);
        add(lID);

        /*Label - name*/
        constraintsLabel.insets = new Insets(60, 0, 0, 0);
        gridbag.setConstraints(lName, constraintsLabel);
        add(lName);

        /*Label - Description*/
        constraintsLabel.insets = new Insets(100, 0, 0, 0);
        gridbag.setConstraints(lDescription, constraintsLabel);
        add(lDescription);

        /*Label - Form*/
        constraintsLabel.insets = new Insets(140, 0, 0, 0);
        gridbag.setConstraints(lForm, constraintsLabel);
        add(lForm);
    }

    private void addField() {
        GridBagConstraints constraintsField = new GridBagConstraints();
        constraintsField.anchor = GridBagConstraints.NORTHWEST;

        tName = new JTextField(20);
        tDescription = new JTextArea(3, 20);
        rbTablet = new JRadioButton("Tablet");
        rbCapsules = new JRadioButton("Capsules");
        rbSyrups = new JRadioButton("Syrups");
        bSave = new JButton("Save");
        bClear = new JButton("Clear");
        tID = new JTextField(8);
        bDelete = new JButton("Delete");

        /*Text Field - id*/
        tID.setEnabled(false);
        constraintsField.insets = new Insets(10, 100, 0, 0);
        constraintsField.gridx = 0;
        constraintsField.gridy = 1;
        gridbag.setConstraints(tID, constraintsField);
        add(tID);

        /*Text Feild - name*/
        constraintsField.insets = new Insets(50, 100, 0, 0);
        constraintsField.gridx = 0;
        constraintsField.gridy = 1;
        gridbag.setConstraints(tName, constraintsField);
        add(tName);

        /*Text Area - description*/
        constraintsField.insets = new Insets(90, 100, 0, 0);
        gridbag.setConstraints(tDescription, constraintsField);
        add(tDescription);

        /*Radio Button - tablet*/
        constraintsField.insets = new Insets(140, 100, 0, 0);
        gridbag.setConstraints(rbTablet, constraintsField);
        add(rbTablet);

        /*Radio Button - capsules*/
        constraintsField.insets = new Insets(140, 180, 0, 0);
        gridbag.setConstraints(rbCapsules, constraintsField);
        add(rbCapsules);

        /*Radio Button - syrups*/
        constraintsField.insets = new Insets(140, 290, 0, 0);
        gridbag.setConstraints(rbSyrups, constraintsField);
        add(rbSyrups);

        /*Button - Save*/
        bSave.setIcon(new javax.swing.ImageIcon("src/cambio/Image/saveicon.png"));
        constraintsField.gridx = 0;
        constraintsField.gridy = 2;
        constraintsField.insets = new Insets(20, 200, 0, 0);
        gridbag.setConstraints(bSave, constraintsField);
        add(bSave);

        /*Button - Save*/
        bDelete.setIcon(new javax.swing.ImageIcon("src/cambio/Image/delete.png"));
        constraintsField.gridx = 0;
        constraintsField.gridy = 2;
        constraintsField.insets = new Insets(20, 300, 0, 0);
        gridbag.setConstraints(bDelete, constraintsField);
        add(bDelete);

        /*Button - clear*/
        bClear.setIcon(new javax.swing.ImageIcon("src/cambio/Image/clearicon.png"));
        constraintsField.insets = new Insets(20, 410, 0, 0);
        gridbag.setConstraints(bClear, constraintsField);
        add(bClear);

        bgType = new ButtonGroup();
        bgType.add(rbTablet);
        bgType.add(rbCapsules);
        bgType.add(rbSyrups);

        saveButtonDisabled();
        deleteButtonAction();
    }

    private void clearButtonAction() {
        bClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clearField();
            }
        });
    }

    private void saveButtonAction() {
        bSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Drug drug = new Drug();
                drug.setDrugId(tID.getText());
                drug.setDrugName(tName.getText());
                drug.setDescription(tDescription.getText());
                if (rbCapsules.isSelected()) {
                    drug.setType("Capsules");
                } else if (rbTablet.isSelected()) {
                    drug.setType("Tablet");
                } else {
                    drug.setType("Syrups");
                }
                saveDrug(drug);
            }
        });
    }

    private void saveDrug(Drug drug) {        
    	DrugSQLToolkit drugSQlToolkit = new DrugSQLToolkit();
    	ResultSet rs = drugSQlToolkit.updateDrug(drug);
    	try {
			if (rs != null && rs.next()) {
			    drug.setDrugId(rs.getInt(1) + "");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	drugController.fireEditRowDrugSearchTablePerformed(new ActionEvent(drug, -1, ""));
    	JOptionPane.showMessageDialog(null, "Drug detail saving succeeded.", "Success", JOptionPane.INFORMATION_MESSAGE);
    	clearField();
    }

    private void clearField() {
        tID.setText("");
        tName.setText("");
        tDescription.setText("");
        bgType.clearSelection();
    }

    private void saveButtonDisabled() {
        bSave.setEnabled(false);
        tName.getDocument().addDocumentListener(new SaveButtonTextFieldCondtion() );
        rbTablet.addActionListener(new SaveButtonRadioButtonCondtion() );
        rbCapsules.addActionListener(new SaveButtonRadioButtonCondtion() );
        rbSyrups.addActionListener(new SaveButtonRadioButtonCondtion() );
    }

    private class SaveButtonTextFieldCondtion implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			enableSaveButton();
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			enableSaveButton();
			
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			enableSaveButton();
			
		}
    }

    private class SaveButtonRadioButtonCondtion implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			enableSaveButton();
			
		}
    }

    private void enableSaveButton() {
        if (tName.getText().length()>0
                && (rbTablet.isSelected()
                || rbCapsules.isSelected()
                || rbSyrups.isSelected())) {
            bSave.setEnabled(true);
        } else {
            bSave.setEnabled(false);
        }
    }

    private void mouseClickRow() {
        drugController.registerRowClickListeners(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof Drug) {
                    Drug drug = (Drug) e.getSource();
                    setDrugField(drug);
                }
            }
        });
    }

    private void setDrugField(Drug drug) {
        tID.setText(drug.getDrugId());
        tName.setText(drug.getDrugName());
        tDescription.setText(drug.getDescription());
        if (drug.getType().equals("Tablet")) {
            rbTablet.setSelected(true);
        } else if (drug.getType().equals("Capsules")) {
            rbCapsules.setSelected(true);
        } else {
            rbSyrups.setSelected(true);
        }
    }

    private void deleteButtonAction() {
        bDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like to Remove Drug?", "Warning", 0);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    Drug drug = new Drug();
                    drug.setDrugId(tID.getText());
                    removeDrug(drug);
                }
            }
        });
    }

    private void removeDrug(Drug drug) {        
    	DrugSQLToolkit drugSqlToolKit = new DrugSQLToolkit();
    	drugSqlToolKit.DeleteDrug(drug);
    	drugController.fireRemoveRowDrugSearchTablePerformed(new ActionEvent(drug.getDrugId(), -1, ""));
    	clearField();
    	JOptionPane.showMessageDialog(null, "Drug detail saving succeeded.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
