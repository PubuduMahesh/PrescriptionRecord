package cambio.precriptionrecord.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionController {
	private List<ActionListener> listenerNewPrescriptionOpen = new ArrayList<>();
	private List<ActionListener> listenerEditPrescriptionDosage = new ArrayList<>();
	
	public void registerAddNewPrescriptionListeners(ActionListener listener)
	{
		listenerNewPrescriptionOpen.add(listener);
	}

	public void fireAddNewPrescriptionPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerNewPrescriptionOpen) {
			actionListener.actionPerformed(e);
		}
	}
	
	public void registerEditPrescriptionDosageListeners(ActionListener listener)
	{
		listenerEditPrescriptionDosage.add(listener);
	}
	
	public void fireEditPrescriptionDosagePerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerEditPrescriptionDosage) {
			actionListener.actionPerformed(e);
		}
	}
}
