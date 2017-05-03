package temp;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cambio.precriptionrecord.controller.DrugController;
import cambio.precriptionrecord.controller.PrescriptionController;
import cambio.precriptionrecord.model.drug.Drug;

public class EditDrugComponent extends JPanel{
	private GridBagLayout gridbag;
	private JTextField tName;
	private JTextArea tDescription;
	private JRadioButton rbTablet;
	private JRadioButton rbCapsules;
	private JRadioButton rbSyrups;
	private JTextField tDosage;
	private ButtonGroup bgType;
	private JTextField tID;
	private PrescriptionController prescriptionController;
	
	public EditDrugComponent(PrescriptionController prescriptionController){
		this.prescriptionController = prescriptionController;
		gridbag = new GridBagLayout();
		setLayout(gridbag);
		addLabel();
//		addField();		
	}

	private void addLabel(){
		GridBagConstraints constraintsLabel = new GridBagConstraints();
		constraintsLabel.anchor = GridBagConstraints.NORTHWEST;

		JLabel lName = new JLabel();
		JLabel lDescription = new JLabel("Description");
		JLabel lForm = new JLabel();
		JLabel lDosage = new JLabel();
		JLabel lDosageDescription = new JLabel("Per Day.");
		JLabel lID = new JLabel("drugID");

		lName.setText("<html>Name <font color='red'> *</font></html>");
		lForm.setText("<html>Drug Form <font color='red'> *</font></html>");
		lDosage.setText("<html>Dosage <font color='red'> *</font></html>");

		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 0;

		/*Label - id*/
		constraintsLabel.insets = new Insets(10, 0, 0, 0);
		gridbag.setConstraints(lID, constraintsLabel);
		add(lID);

		/*Label - name*/
		constraintsLabel.insets = new Insets(60, 0, 0, 0);
		gridbag.setConstraints(lName, constraintsLabel);
//		add(lName);

		/*Label - Description*/
		constraintsLabel.insets = new Insets(100, 0, 0, 0);
		gridbag.setConstraints(lDescription, constraintsLabel);
//		add(lDescription);

		/*Label - Form*/
		constraintsLabel.insets = new Insets(140, 0, 0, 0);
		gridbag.setConstraints(lForm, constraintsLabel);
//		add(lForm);

		/*Label - Dosage*/
		constraintsLabel.insets = new Insets(180, 0, 0, 0);
		gridbag.setConstraints(lDosage, constraintsLabel);
//		add(lDosage);

		/*Label- Dosage Description*/
		constraintsLabel.gridx = 0;
		constraintsLabel.insets = new Insets(180, 200, 0, 0);
		gridbag.setConstraints(lDosageDescription, constraintsLabel);
//		add(lDosageDescription);
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
		tID = new JTextField(8);

		/*Temp*/
		tName.setText("name");
		tDescription.setText("description");
		tDosage.setText("dosage");

		/*Text Field - id*/
		tID.setEnabled(false);
		constraintsField.insets = new Insets(10, 100, 0, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 0;
		gridbag.setConstraints(tID, constraintsField);
		add(tID);

		/*Text Feild - name*/
		constraintsField.insets = new Insets(50, 100, 0, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 0;
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

		/*Text Feild - dosage*/
		constraintsField.insets = new Insets(180, 100, 0, 0);
		gridbag.setConstraints(tDosage, constraintsField);
		add(tDosage);

		bgType = new ButtonGroup();
		bgType.add(rbTablet);
		bgType.add(rbCapsules);
		bgType.add(rbSyrups); 

		

	}
}


