package cambio.precriptionrecord.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import cambio.precriptionrecord.controller.PatientController;
import cambio.precriptionrecord.model.Patient;

public class EmptyPanel extends JPanel{
	private PatientController patientController;
	public EmptyPanel(PatientController patientController){
		this.setPreferredSize(new Dimension(770, 670));
//		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		patientController.registerAddNewPatientListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewPatient newPatient = new NewPatient();
				add(newPatient);
			}
		});
	}
}
