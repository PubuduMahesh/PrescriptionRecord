package cambio.precriptionrecord.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionController {
	private final List<ActionListener> listenerNewPrescriptionOpen = new ArrayList<>();
	private final List<ActionListener> listenerEditPrescriptionDosage = new ArrayList<>();
	private final List<ActionListener> listenerSavePrescription = new ArrayList<>();
	private final List<ActionListener> listenerSavePrescriptionReverse = new ArrayList<>();
	private final List<ActionListener> listenerPatientDetailField = new ArrayList<>();
	private final List<ActionListener> listenerPatientDetail = new ArrayList<>();
	
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
	
	public void registerSavePrescriptionReverseListeners(ActionListener listener)
	{
		listenerSavePrescriptionReverse.add(listener);
	}
	
	public void fireSavePrescriptionReversePerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerSavePrescriptionReverse) {
			actionListener.actionPerformed(e);
		}
	}
        
	public void registerPatientDetailFieldListener(ActionListener listener)
	{
		listenerPatientDetailField.add(listener);
	}
	
	public void firePatientDetailFieldActionPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerPatientDetailField) {
			actionListener.actionPerformed(e);
		}
	}
	
	public void registerPatientDetailListener(ActionListener listener)
	{
		listenerPatientDetail.add(listener);
	}
	
	public void firePatientDetailActionPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerPatientDetail) {
			actionListener.actionPerformed(e);
		}
	}
}
