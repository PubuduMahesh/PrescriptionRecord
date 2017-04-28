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
