package cambio.precriptionrecord.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PatientController {
	private List<ActionListener> listenerNewPatientOpen = new ArrayList<>();
	private List<ActionListener> listenerEditPatientOpen = new ArrayList<>();
	private List<ActionListener> listenerRemovePatientOpen = new ArrayList<>();
	private List<ActionListener> listenerRowClick = new ArrayList<>();
	private List<ActionListener> listenerRemoveRowPatientSearchTable = new ArrayList<>();
	private List<ActionListener> listenerUpdateRowPatientSearchTable = new ArrayList<>();
	private List<ActionListener> listenerTreatmentHistoryOpen = new ArrayList<>();
	private List<ActionListener> listenerSaveNewPatient = new ArrayList<>();
	private List<ActionListener> listenerSaveNewPatientReverse = new ArrayList<>();
	private List<ActionListener> listenerClearPatientField = new ArrayList<>();
	private List<ActionListener> listenerSaveEditPatient1 = new ArrayList<>();
	
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
	
	public void registerEditPatientListeners(ActionListener listener)
	{
		listenerEditPatientOpen.add(listener);
	}
	
	public void fireEditPatientPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerEditPatientOpen) {
			actionListener.actionPerformed(e);
		}
	}
	
	public void registerRemovePatientListeners(ActionListener listener)
	{
		listenerRemovePatientOpen.add(listener);
	}
	
	public void fireRemovePatientPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerRemovePatientOpen) {
			actionListener.actionPerformed(e);
		}
	}
	
	public void registerTreatmentHistoryListeners(ActionListener listener)
	{
		listenerTreatmentHistoryOpen.add(listener);
	}
	
	public void fireTreatmentHistoryPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerTreatmentHistoryOpen) {
			actionListener.actionPerformed(e);
		}
	}
	
	public void registerRowClickListeners(ActionListener listener)
	{
		listenerRowClick.add(listener);
	}
	
	public void fireRowClickActionPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerRowClick) {
			actionListener.actionPerformed(e);
		}
	}
	
	public void registerRemoveRowPatientSearchTableListeners(ActionListener listener)
	{
		listenerRemoveRowPatientSearchTable.add(listener);
	}
	
	public void fireRemoveRowPatientSearchTablePerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerRemoveRowPatientSearchTable) {
			actionListener.actionPerformed(e);
		}
	}
	
	public void registerUpdateRowPatientSearchTableListeners(ActionListener listener)
	{
		listenerUpdateRowPatientSearchTable.add(listener);
	}
	
	public void fireUpdateRowPatientSearchTablePerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerUpdateRowPatientSearchTable) {
			actionListener.actionPerformed(e);
		}
	}
	
	public void registerSaveNewPatientListeners(ActionListener listener)//profile picture- new patient
	{
		listenerSaveNewPatient.add(listener);
	}
	
	public void fireSaveNewPatientPerformed(ActionEvent e)//new patient
	{
		for (ActionListener actionListener : listenerSaveNewPatient) {
			actionListener.actionPerformed(e);
		}
	}
	public void registerSaveNewPatientReverseListeners(ActionListener listener)//new patient
	{	
		listenerSaveNewPatientReverse.add(listener);
	}
	
	public void fireSaveNewPatientReversePerformed(ActionEvent e)//profile picture - new patient
	{
		for (ActionListener actionListener : listenerSaveNewPatientReverse) {
			actionListener.actionPerformed(e);
		}
	}
	public void registerClearPatientFieldListeners(ActionListener listener)
	{
		listenerClearPatientField.add(listener);
	}
	
	public void fireClearPatientFieldPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerClearPatientField) {
			actionListener.actionPerformed(e);
		}
	}
        
	public void registerPatientEditListeners(ActionListener listener)//profile picture - edit patient
	{
		listenerSaveEditPatient1.add(listener);
	}
	
	public void firePatientEditPerformed(ActionEvent e)//edit patient
	{
		for (ActionListener actionListener : listenerSaveEditPatient1) {
			actionListener.actionPerformed(e);
		}
	}
	public void registerPatientEditReverseListeners(ActionListener listener)//edit patient
	{
		listenerSaveNewPatientReverse.add(listener);
	}
	
	public void firePatientEditReversePerformed(ActionEvent e)//profile picture - edit patient
	{
		for (ActionListener actionListener : listenerSaveNewPatientReverse) {
			actionListener.actionPerformed(e);
		}
	}
	public void registerClearEditPatientFieldListeners(ActionListener listener)
	{
		listenerClearPatientField.add(listener);
	}
	
	public void fireClearEditPatientFieldPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerClearPatientField) {
			actionListener.actionPerformed(e);
		}
	}
	
}
