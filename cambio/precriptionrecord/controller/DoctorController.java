package cambio.precriptionrecord.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DoctorController {
	private List<ActionListener> listenerNewDoctorOpen = new ArrayList<>();
	private List<ActionListener> listenerEditDoctorOpen = new ArrayList<>();
	private List<ActionListener> listenerRowClick = new ArrayList<>();
	
	public void registerAddNewDoctortListeners(ActionListener listener)
	{
		listenerNewDoctorOpen.add(listener);
	}

	public void fireAddNewDoctorPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerNewDoctorOpen) {
			actionListener.actionPerformed(e);
		}
	}
	
	public void registerEditDoctortListeners(ActionListener listener)
	{
		listenerEditDoctorOpen.add(listener);
	}
	
	public void fireEditDoctorPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerEditDoctorOpen) {
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
}
