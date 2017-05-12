package cambio.precriptionrecord.view.prescription;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import cambio.precriptionrecord.controller.CommonController;
import cambio.precriptionrecord.controller.PrescriptionController;

public class NewPrescription extends JInternalFrame{
	private PrescriptionController prescriptionController;
	private CommonController commonController;
	private GridBagLayout gridbag;
	public NewPrescription(PrescriptionController prescriptionController){
		this.prescriptionController = prescriptionController;
		this.commonController = new CommonController();
		setTitle("Add New Prescription");
		JDesktopPane desktopPane = new JDesktopPane();
		setPreferredSize(new Dimension(740,665));
		setClosable(true);
		setVisible(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
                setFrameIcon(new javax.swing.ImageIcon("src/cambio/Image/prescription.png"));
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
		PatientPanel patienPanel = new PatientPanel(prescriptionController,commonController);
		GridBagConstraints constraint = new GridBagConstraints();		
		constraint.gridx = 0;
		constraint.gridy = 0;
		gridbag.setConstraints(patienPanel, constraint);		
		add(patienPanel);
	}
	
	private void addPrescriptionPanel(){
		PrescriptionPanel prescriptionPanel = new PrescriptionPanel(prescriptionController,commonController);
		GridBagConstraints constraint = new GridBagConstraints();	
		constraint.gridx = 0;
		constraint.gridy = 1;
		gridbag.setConstraints(prescriptionPanel, constraint);
		add(prescriptionPanel);
	}
}
