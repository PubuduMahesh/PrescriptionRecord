package cambio.precriptionrecord.view.prescription;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cambio.precriptionrecord.controller.PrescriptionController;

public class NewPrescription extends JInternalFrame{
	PrescriptionController prescriptionController;
	GridBagLayout gridbag;
	public NewPrescription(PrescriptionController prescriptionController){
		this.prescriptionController = prescriptionController;
		setTitle("Add New Drug");
		JDesktopPane desktopPane = new JDesktopPane();
		setPreferredSize(new Dimension(740,665));
		setClosable(true);
		setVisible(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		gridbag = new GridBagLayout();
		setLayout(gridbag);	
		createLayout();
		desktopPane.add(this); 
		
	}
	
	private void createLayout(){		
		addPatientPanel();
		addPrescriptionPanel();
	}
	
	private void addPatientPanel(){
		PatientPanel patienPanel = new PatientPanel();
		GridBagConstraints constraint = new GridBagConstraints();		
		constraint.gridx = 0;
		constraint.gridy = 0;
		gridbag.setConstraints(patienPanel, constraint);		
		add(patienPanel);
	}
	
	private void addPrescriptionPanel(){
		PrescriptionPanel prescriptionPanel = new PrescriptionPanel(prescriptionController);
		GridBagConstraints constraint = new GridBagConstraints();	
		constraint.gridx = 0;
		constraint.gridy = 1;
		gridbag.setConstraints(prescriptionPanel, constraint);
		add(prescriptionPanel);
	}
}
