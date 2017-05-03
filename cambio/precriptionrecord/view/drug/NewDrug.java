package cambio.precriptionrecord.view.drug;

import cambio.precriptionrecord.controller.DrugController;
import cambio.precriptionrecord.model.drug.Drug;
import cambio.precriptionrecord.util.DBConnection;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;

public class NewDrug extends JInternalFrame{

	DrugController drugController = new DrugController();
	GridBagLayout gridbag;
	JTextField tName;
	JTextArea tDescription;
	JRadioButton rbTablet;
	JRadioButton rbCapsules;
	JRadioButton rbSyrups;
	JTextField tDosage;
	ButtonGroup bgType;
	JButton bSave;
	JButton bClear;

	public NewDrug(DrugController drugController){
		this.drugController = drugController;
		setTitle("Add New Drug");
		JDesktopPane desktopPane = new JDesktopPane();
		setPreferredSize(new Dimension(400,400));
		setClosable(true);
		setVisible(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		gridbag = new GridBagLayout();
		setLayout(gridbag);	

		createLayout();
		desktopPane.add(this);  
		/*Button action performed*/
		clearButtonAction();
		saveButtonAction();
	}

	private void createLayout(){
		addLabel();
		addField();
	}

	private void addLabel(){
		GridBagConstraints constraintsLabel = new GridBagConstraints();
		constraintsLabel.anchor = GridBagConstraints.NORTHWEST;

		JLabel lName = new JLabel();
		JLabel lDescription = new JLabel("Description");
		JLabel lForm = new JLabel();
		JLabel lDosage = new JLabel();
		JLabel lDosageDescription = new JLabel("Per Day.");

		lName.setText("<html>Name <font color='red'> *</font></html>");
		lForm.setText("<html>Drug Form <font color='red'> *</font></html>");
		lDosage.setText("<html>Dosage <font color='red'> *</font></html>");

		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 0;

		/*Label - name*/
		constraintsLabel.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(lName, constraintsLabel);
		add(lName);

		/*Label - Description*/
		constraintsLabel.insets = new Insets(60, 0, 0, 0);
		gridbag.setConstraints(lDescription, constraintsLabel);
		add(lDescription);

		/*Label - Form*/
		constraintsLabel.insets = new Insets(100, 0, 0, 0);
		gridbag.setConstraints(lForm, constraintsLabel);
		add(lForm);

		/*Label - Dosage*/
		constraintsLabel.insets = new Insets(140, 0, 0, 0);
		gridbag.setConstraints(lDosage, constraintsLabel);
		add(lDosage);

		/*Label- Dosage Description*/
		constraintsLabel.gridx = 1;
		constraintsLabel.insets = new Insets(140, 100, 0, 0);
		gridbag.setConstraints(lDosageDescription, constraintsLabel);
		add(lDosageDescription);
	}

	private void addField(){
		GridBagConstraints constraintsField = new GridBagConstraints();
		constraintsField.anchor = GridBagConstraints.NORTHWEST;

		tName = new JTextField(20);
		tDescription = new JTextArea(3,20);
		tDosage = new JTextField(5);
		rbTablet = new JRadioButton("Tablet");
		rbCapsules = new JRadioButton("Capsules");
		rbSyrups = new JRadioButton("Syrups");  
		bSave = new JButton("Save");
		bClear = new JButton("Clear");

		/*Temp*/
		tName.setText("name");
		tDescription.setText("description");
		tDosage.setText("dosage");

		/*Text Feild - name*/
		constraintsField.gridx = 1;
		constraintsField.gridy = 0;
		gridbag.setConstraints(tName, constraintsField);
		add(tName);

		/*Text Area - description*/
		constraintsField.insets = new Insets(40, 0, 0, 0);
		gridbag.setConstraints(tDescription, constraintsField);
		add(tDescription);

		/*Radio Button - tablet*/
		constraintsField.insets = new Insets(100, 0, 0, 0);
		gridbag.setConstraints(rbTablet, constraintsField);
		add(rbTablet);

		/*Radio Button - capsules*/
		constraintsField.insets = new Insets(100, 80, 0, 0);
		gridbag.setConstraints(rbCapsules, constraintsField);
		add(rbCapsules);

		/*Radio Button - syrups*/
		constraintsField.insets = new Insets(100, 190, 0, 0);
		gridbag.setConstraints(rbSyrups, constraintsField);
		add(rbSyrups);

		/*Text Feild - dosage*/
		constraintsField.insets = new Insets(140, 0, 0, 0);
		gridbag.setConstraints(tDosage, constraintsField);
		add(tDosage);

		/*Button - Save*/
		constraintsField.gridx = 1;
		constraintsField.gridy = 1;
		constraintsField.insets = new Insets(20, 100, 0, 0);
		gridbag.setConstraints(bSave, constraintsField);
		add(bSave);

		/*Button - clear*/
		constraintsField.insets = new Insets(20, 170, 0, 0);
		gridbag.setConstraints(bClear, constraintsField);
		add(bClear);

		bgType = new ButtonGroup();
		bgType.add(rbTablet);
		bgType.add(rbCapsules);
		bgType.add(rbSyrups); 

		saveButtonDisabled();
	}

	private void clearButtonAction(){
		bClear.addActionListener(new ActionListener (){

			@Override
			public void actionPerformed(ActionEvent e){
				clearField();
			}
		}); 
	}

	private void saveButtonAction(){
		bSave.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){
				int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like to Save Drug?", "Warning",0);
				if(dialogResult == JOptionPane.YES_OPTION){
					Drug drug = new Drug();
					drug.setDrugName(tName.getText());
					drug.setDescription(tDescription.getText());
					if(rbCapsules.isSelected())
						drug.setType("Capsules");
					else if(rbTablet.isSelected())
						drug.setType("Tablet");
					else
						drug.setType("Syrups");
					drug.setDosage(tDosage.getText());
					saveDrug(drug);
				}
			}
		});
	}

	private void saveDrug(Drug drug){
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		Statement stmt = null;
		String sql;
		try {
			stmt = connection.createStatement();
			sql = "INSERT INTO `drug` (`drugId`,"
					+ "`drugName`,"
					+ "`description`,"
					+ "`type`,"
					+ "`dosage`) VALUES (NULL,"
					+ "'"+drug.getDrugName()+"',"
					+ "'"+drug.getDescription()+"',"
					+ "'"+drug.getType()+"',"
					+ "'"+drug.getDosage()+"')";

			stmt.executeUpdate(sql);
			connection.close();
			clearField();
			JOptionPane.showMessageDialog(null, "Drug detail saving succeeded.", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void clearField(){
		tName.setText("");
		tDescription.setText("");
		bgType.clearSelection();
		tDosage.setText("");
	}
	
	private void saveButtonDisabled(){
		bSave.setEnabled(false);
		new SaveButtonTextFieldCondtion(tName);		
		new SaveButtonRadioButtonCondtion(rbTablet);
		new SaveButtonRadioButtonCondtion(rbCapsules);
		new SaveButtonRadioButtonCondtion(rbSyrups);
		new SaveButtonTextFieldCondtion(tDosage);
	}
	
	private class SaveButtonTextFieldCondtion{
		public SaveButtonTextFieldCondtion(JTextField textField){
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
	
	private class SaveButtonRadioButtonCondtion{
		public SaveButtonRadioButtonCondtion(JRadioButton radioButton){
			radioButton.addActionListener(new ActionListener (){
				@Override
				public void actionPerformed (ActionEvent e){
					enableSaveButton();
				}
			});
		}
	}
	
	private void enableSaveButton(){
		if(!tName.getText().equals("")
				&& !tDosage.getText().equals("")
				&& (rbTablet.isSelected() 
						||rbCapsules.isSelected()
						||rbSyrups.isSelected())){
			bSave.setEnabled(true);
		}
		else
			bSave.setEnabled(false);		
	}
}
