package cambio.precriptionrecord.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import cambio.precriptionrecord.controller.PatientController;
import cambio.precriptionrecord.model.Patient;
import cambio.precriptionrecord.view.patient.EditPatient;
import cambio.precriptionrecord.view.patient.NewPatient;
import cambio.precriptionrecord.view.patient.RemovePatient;

public class EmptyPanel extends JPanel{
	private PatientController patientController;
	public EmptyPanel(PatientController patientController){
		this.patientController = patientController;
		this.setPreferredSize(new Dimension(770, 670));
//		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		loadNewPatientGUI();
		loadEditPatientGUI();	
		loadRemovePatientGUI();	
	}
	
	private void loadNewPatientGUI(){
		patientController.registerAddNewPatientListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewPatient newPatient = new NewPatient();
				add(newPatient);
			}
		});
	}
	
	private void loadEditPatientGUI(){
		patientController.registerEditPatientListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditPatient editPatient = new EditPatient(patientController);
				add(editPatient);
			}
		});
	}
	
	private void loadRemovePatientGUI(){
		patientController.registerRemovePatientListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RemovePatient removePatient = new RemovePatient(patientController);
				add(removePatient);
			}
		});
	}
}
