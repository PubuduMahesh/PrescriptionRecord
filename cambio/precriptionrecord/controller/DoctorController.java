package cambio.precriptionrecord.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DoctorController {
	private List<ActionListener> listenerNewDoctorOpen = new ArrayList<>();
	private List<ActionListener> listenerEditDoctorOpen = new ArrayList<>();
	private List<ActionListener> listenerRowClick = new ArrayList<>();
	private List<ActionListener> listenerRemoveDoctor = new ArrayList<>();
	private List<ActionListener> listenerUpdateRow = new ArrayList<>();
	private List<ActionListener> listenerRemoveRow = new ArrayList<>();
	private List<ActionListener> listenerSaveNewDoctor = new ArrayList<>();
	private List<ActionListener> listenerSaveNewDoctorReverse = new ArrayList<>();
	private List<ActionListener> listenerClearDoctorField = new ArrayList<>();
	private List<ActionListener> listenerEditDoctorField = new ArrayList<>();
	private List<ActionListener> listenerEditDoctorFieldReverse = new ArrayList<>();
	private List<ActionListener> listenerEditDoctorFieldClear = new ArrayList<>();
	
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
	
	public void registerEditDoctorListeners(ActionListener listener)
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
	
	public void registerRemoveDoctorListeners(ActionListener listener)
	{
		listenerRemoveDoctor.add(listener);
	}
	
	public void fireRemoveDoctorActionPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerRemoveDoctor) {
			actionListener.actionPerformed(e);
		}
	}
	
	public void registerUpdateRowDoctorSearchTableListeners(ActionListener listener)
	{
		listenerUpdateRow.add(listener);
	}
	
	public void fireUpdateRowDoctorSearchTablePerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerUpdateRow) {
			actionListener.actionPerformed(e);
		}
	}
	
	public void registerRemoveRowDoctorSearchTableListeners(ActionListener listener)
	{
		listenerRemoveRow.add(listener);
	}
	
	public void fireRemoveRowDoctorSearchTablePerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerRemoveRow) {
			actionListener.actionPerformed(e);
		}
	}
	
	public void registerSaveNewDoctorListeners(ActionListener listener)
	{
		listenerSaveNewDoctor.add(listener);
	}
	
	public void fireSaveNewDoctorPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerSaveNewDoctor) {
			actionListener.actionPerformed(e);
		}
	}
	
	public void registerSaveNewDoctorReverseListeners(ActionListener listener)
	{
		listenerSaveNewDoctorReverse.add(listener);
	}
	
	public void fireSaveNewDoctorReversePerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerSaveNewDoctorReverse) {
			actionListener.actionPerformed(e);
		}
	}
	public void registerClearDoctorFieldListeners(ActionListener listener)
	{
		listenerClearDoctorField.add(listener);
	}
	
	public void fireClearDoctorFieldPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerClearDoctorField) {
			actionListener.actionPerformed(e);
		}
	}
	public void registerEditDoctorFieldListeners(ActionListener listener)
	{
		listenerEditDoctorField.add(listener);
	}
	
	public void fireEditDoctorFieldPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerEditDoctorField) {
			actionListener.actionPerformed(e);
		}
	}
	public void registerEditDoctorFieldReverseListeners(ActionListener listener)
	{
		listenerEditDoctorFieldReverse.add(listener);
	}
	
	public void fireEditDoctorFieldReversePerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerEditDoctorFieldReverse) {
			actionListener.actionPerformed(e);
		}
	}
	public void registerClearEditDoctorFieldListeners(ActionListener listener)
	{
		listenerEditDoctorFieldClear.add(listener);
	}
	
	public void fireClearEditDoctorFieldPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerEditDoctorFieldClear) {
			actionListener.actionPerformed(e);
		}
	}
}
