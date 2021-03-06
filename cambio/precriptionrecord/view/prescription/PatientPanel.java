package cambio.precriptionrecord.view.prescription;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import cambio.precriptionrecord.controller.CommonController;
import cambio.precriptionrecord.controller.PatientController;
import cambio.precriptionrecord.controller.PrescriptionController;
import cambio.precriptionrecord.model.patient.Patient;
import cambio.precriptionrecord.util.AgeCalculator;
import cambio.precriptionrecord.view.patient.PatientSearchPanel;

public class PatientPanel extends JPanel{
	JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	GridBagLayout gridbag;
	PatientController patientController = new PatientController();
	JTextField tName;
	JTextField tID;
	JTextField tAge;
	JTextField tTelephone;
	PrescriptionController prescriptionCotroler;
	CommonController commonController;
	public PatientPanel(PrescriptionController prescriptionController,CommonController commonController) {
		this.prescriptionCotroler = prescriptionController;
		this.commonController = commonController;
		gridbag = new GridBagLayout();
		setLayout(gridbag);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Patient"));
		setPreferredSize(new Dimension(730,290));
		createLayout();
		scrollPane.add(this);
	}

	private void createLayout(){
		addPatientSearchTable();
		addLabel();	
		addField();

		mouseClickRow();
		commonControllerActionFired();

	}

	private void addLabel(){
		JLabel lPatientName = new JLabel("Name");
		JLabel lPatientID = new JLabel("ID");
		JLabel lPatientAge = new JLabel("Age");
		JLabel lPatientTelephone = new JLabel("Telephone");
		JLabel lPatientProfilePicture = new JLabel("Picture");

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 1;

		/*JLabel - name*/		
		constraints.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(lPatientName, constraints);
		add(lPatientName);

		/*JLabel - id*/
		constraints.insets = new Insets(0, 300, 0, 0);
		gridbag.setConstraints(lPatientID, constraints);
		add(lPatientID);

		/*JLabel - age*/
		constraints.insets = new Insets(40, 0, 0, 0);
		gridbag.setConstraints(lPatientAge, constraints);
		add(lPatientAge);

		/*JLabel - telephone*/
		constraints.insets = new Insets(40, 300, 0, 0);
		gridbag.setConstraints(lPatientTelephone, constraints);
		add(lPatientTelephone);
	}

	private void addField(){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;

		tName = new JTextField(20);
		tID = new JTextField(10);
		tAge = new JTextField(5);
		tTelephone = new JTextField(10);

		constraints.gridx = 0;
		constraints.gridy = 1;

		/*text field - name*/
		tName.setEditable(false);
		constraints.insets = new Insets(0, 50, 0, 0);
		gridbag.setConstraints(tName, constraints);
		add(tName);

		/*text field - id*/
		tID.setEditable(false);
		constraints.insets = new Insets(0, 380, 0, 0);
		gridbag.setConstraints(tID, constraints);
		add(tID);

		/*text field - age*/
		tAge.setEditable(false);
		constraints.insets = new Insets(40, 50, 0, 0);
		gridbag.setConstraints(tAge, constraints);
		add(tAge);

		/*text field - telephone*/
		tTelephone.setEditable(false);
		constraints.insets = new Insets(40, 380, 0, 0);
		gridbag.setConstraints(tTelephone, constraints);
		add(tTelephone);
	}

	private void addPatientSearchTable(){
		GridBagConstraints constraints = new GridBagConstraints();
		int tbWidth = 690;
		int tbHeight = 135;		
		PatientSearchPanel patienSearchPanel = new PatientSearchPanel(patientController, tbWidth, tbHeight,commonController);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(patienSearchPanel, constraints);		
		add(patienSearchPanel);
	}

	private void mouseClickRow(){
		patientController.registerRowClickListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof Patient) {
					Patient patient = (Patient)e.getSource();
					setPatientCustomField(patient);
					prescriptionCotroler.firePatientDetailActionPerformed(e);
				}

			}
		});
	}

	private void setPatientCustomField(Patient patient){
		AgeCalculator age = new AgeCalculator();
		tName.setText(patient.getName());
		tID.setText(patient.getID());
		tAge.setText(age.ageCalculator(patient.getBirthday()));
		tTelephone.setText(patient.getTp());
		ActionEvent e = new ActionEvent(tID,-1,null);
		prescriptionCotroler.firePatientDetailFieldActionPerformed(e);

	}
	private void commonControllerActionFired(){
		commonController.registerClearPrescriptionReportAllElementActionListeners(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tName.setText("");
				tAge.setText("");
				tID.setText("");
				tTelephone.setText("");

			}
		});
	}
}
