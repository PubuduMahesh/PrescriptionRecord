package cambio.precriptionrecord.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PatientController {
	private List<ActionListener> listenerNewPatientOpen = new ArrayList<>();
	
	public void registerAddNewPatientListeners(ActionListener listener)
	{
		listenerNewPatientOpen.add(listener);
	}

	public void fireAddNewPatientPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerNewPatientOpen) {
			actionListener.actionPerformed(e);
		}
	}
	
}
