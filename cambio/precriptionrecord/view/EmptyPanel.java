package cambio.precriptionrecord.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import cambio.precriptionrecord.controller.DoctorController;
import cambio.precriptionrecord.controller.PatientController;
import cambio.precriptionrecord.model.patient.Patient;
import cambio.precriptionrecord.view.doctor.EditDoctor;
import cambio.precriptionrecord.view.doctor.NewDoctor;
import cambio.precriptionrecord.view.patient.EditPatient;
import cambio.precriptionrecord.view.patient.NewPatient;
import cambio.precriptionrecord.view.patient.RemovePatient;

public class EmptyPanel extends JPanel{
	private PatientController patientController;
	private DoctorController doctorController;
	public EmptyPanel(PatientController patientController, DoctorController doctorController){
		this.patientController = patientController;
		this.doctorController = doctorController;
		this.setPreferredSize(new Dimension(770, 670));
//		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		loadNewPatientGUI();
		loadEditPatientGUI();	
		loadRemovePatientGUI();	
		
		loadNewDoctorGUI();
		loadEditDoctorGUI();
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
	
	private void loadNewDoctorGUI(){
		doctorController.registerAddNewDoctortListeners(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				NewDoctor newDoctor = new NewDoctor(doctorController);
				add(newDoctor);
			}
		});
	}
	
	private void loadEditDoctorGUI(){
		doctorController.registerEditDoctortListeners(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EditDoctor editDoctor = new EditDoctor(doctorController);
				add(editDoctor);
			}
		});
	}
}
