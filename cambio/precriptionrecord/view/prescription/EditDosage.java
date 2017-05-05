package cambio.precriptionrecord.view.prescription;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cambio.precriptionrecord.controller.PrescriptionController;
import cambio.precriptionrecord.model.drug.Drug;
import cambio.precriptionrecord.model.prescription.PrescriptionTableModel;

public class EditDosage extends JDialog{
	private GridBagLayout gridbag;
	private JTextField tName;
	private JTextArea tDescription;
	private JRadioButton rbTablet;
	private JRadioButton rbCapsules;
	private JRadioButton rbSyrups;
	private JTextField tDosage;
	private ButtonGroup bgType;
	private JTextField tID;
	private JButton bAdd;
	private JButton bCancel;
	private Drug drug;
	private PrescriptionController prescriptionController;
	
	public EditDosage(Drug drug,PrescriptionController prescriptionController){
		this.drug = drug;
		this.prescriptionController = prescriptionController;
		gridbag = new GridBagLayout();
		setVisible(true);
		setModal(true);		
		addComponent();
		setField();
		setLayout(gridbag);
		pack();
	}
	
	private void addComponent(){
		GridBagConstraints constraints  = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		addLabel();
		addField();
		setField();

		/*add buton action*/
		addButtonAction();
		cancelButtonAction();
	}
	
	private void addField() {
		GridBagConstraints constraintsField = new GridBagConstraints();
		constraintsField.anchor = GridBagConstraints.NORTHWEST;

		tName = new JTextField(20);
		tDescription = new JTextArea(3,20);
		tDosage = new JTextField(5);
		rbTablet = new JRadioButton("Tablet");
		rbCapsules = new JRadioButton("Capsules");
		rbSyrups = new JRadioButton("Syrups");
		tID = new JTextField(8);
		bAdd = new JButton("Add");
		bCancel = new JButton("Cancel");

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
		
		/*button - add*/
		constraintsField.insets = new Insets(200, 250, 0, 0);
		gridbag.setConstraints(bAdd, constraintsField);
		add(bAdd);
		
		/*button - Cancel*/
		constraintsField.insets = new Insets(200, 310, 0, 0);
		gridbag.setConstraints(bCancel, constraintsField);
		add(bCancel);
		
		

		bgType = new ButtonGroup();
		bgType.add(rbTablet);
		bgType.add(rbCapsules);
		bgType.add(rbSyrups); 
		
	}

	private void addLabel() {
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
		add(lName);

		/*Label - Description*/
		constraintsLabel.insets = new Insets(100, 0, 0, 0);
		gridbag.setConstraints(lDescription, constraintsLabel);
		add(lDescription);

		/*Label - Form*/
		constraintsLabel.insets = new Insets(140, 0, 0, 0);
		gridbag.setConstraints(lForm, constraintsLabel);
		add(lForm);

		/*Label - Dosage*/
		constraintsLabel.insets = new Insets(180, 0, 0, 0);
		gridbag.setConstraints(lDosage, constraintsLabel);
		add(lDosage);

		/*Label- Dosage Description*/
		constraintsLabel.gridx = 0;
		constraintsLabel.insets = new Insets(180, 200, 0, 0);
		gridbag.setConstraints(lDosageDescription, constraintsLabel);
		add(lDosageDescription);
		
	}
	
	private void setField(){	
		try{
			tID.setText(drug.getDrugId());
			tName.setText(drug.getDrugName());
			tDescription.setText(drug.getDescription());
			if(drug.getType().equals("Tablet"))
				rbTablet.setSelected(true);
			else if(drug.getType().equals("Syrups"))
				rbSyrups.setSelected(true);
			else
				rbCapsules.setSelected(true);
			tDosage.setText(drug.getDosage());
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	private void addButtonAction(){
		bAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Drug drug = new Drug();
				drug.setDrugId(tID.getText());
				drug.setDrugName(tName.getText());
				drug.setDescription(tDescription.getText());
				if(rbTablet.isSelected())
					drug.setType("Tablet");
				else if(rbCapsules.isSelected())
					drug.setType("Capsules");
				else
					drug.setType("Syrups");
				drug.setDosage(tDosage.getText());
				ActionEvent e1 = new ActionEvent(drug, -1, "");
				prescriptionController.fireEditPrescriptionDosagePerformed(e1);
				dispose();
				
			}
		});
	}
	private void cancelButtonAction(){
		bCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog (((Component) e.getSource()).getParent(), "Do You Really Want to Close This Window?","Warning",0);
				if(dialogResult == JOptionPane.YES_OPTION)
					dispose();
				
			}
		});
	}
	
	
}
