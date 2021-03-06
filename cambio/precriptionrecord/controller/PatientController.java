package cambio.precriptionrecord.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PatientController {
	private List<ActionListener> listenerEditPatientOpen = new ArrayList<>();
	private List<ActionListener> listenerRowClick = new ArrayList<>();
	private List<ActionListener> listenerRemoveRowPatientSearchTable = new ArrayList<>();
	private List<ActionListener> listenerUpdateRowPatientSearchTable = new ArrayList<>();
	private List<ActionListener> listenerTreatmentHistoryOpen = new ArrayList<>();
	
	
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
	
}
