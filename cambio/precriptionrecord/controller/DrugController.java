package cambio.precriptionrecord.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DrugController {
    private List<ActionListener> listenerNewDrugOpen = new ArrayList<>();
    private List<ActionListener> listenerEditDrugOpen = new ArrayList<>();
    private List<ActionListener> listenerClearDrugField = new ArrayList<>();
    private List<ActionListener> listenerRowClick = new ArrayList<>();
    private List<ActionListener> listenerEditRow = new ArrayList<>();
    private List<ActionListener> listenerRemoveDrug = new ArrayList<>();
    private List<ActionListener> listenerRemoveRowDrug = new ArrayList<>();
    private List<ActionListener> listenerSaveButtonBehaviorAltered = new ArrayList<>();
    private List<ActionListener> listenerEditSaveButtonAction = new ArrayList<>();
    private List<ActionListener> listenerEditSaveButtonActionReverse = new ArrayList<>();
        
    public void registerAddNewDrugListeners(ActionListener listener)
	{
		listenerNewDrugOpen.add(listener);
	}

    public void fireAddNewDrugActionPerformed(ActionEvent e)
    {
            for (ActionListener actionListener : listenerNewDrugOpen) {
                    actionListener.actionPerformed(e);
            }
    }
    
    public void registerClearFieldDrugListeners(ActionListener listener)
    {
    	listenerClearDrugField.add(listener);
    }
    
    public void fireClearFieldActionPerformed(ActionEvent e)
    {
    	for (ActionListener actionListener : listenerClearDrugField) {
    		actionListener.actionPerformed(e);
    	}
    }
    
    public void registerEditDrugListeners(ActionListener listener)
    {
    	listenerEditDrugOpen.add(listener);
    }
    
    public void fireEditDrugActionPerformed(ActionEvent e)
    {
    	for (ActionListener actionListener : listenerEditDrugOpen) {
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
    
    public void registerEditRowDrugSearchTableListeners(ActionListener listener)
    {
    	listenerEditRow.add(listener);
    }
    
    public void fireEditRowDrugSearchTablePerformed(ActionEvent e)
    {
    	for (ActionListener actionListener : listenerEditRow) {
    		actionListener.actionPerformed(e);
    	}
    }
    
    public void registerRemoveDrugListeners(ActionListener listener)
    {
    	listenerRemoveDrug.add(listener);
    }
    
    public void fireRemoveDrugActionPerformed(ActionEvent e)
    {
    	for (ActionListener actionListener : listenerRemoveDrug) {
    		actionListener.actionPerformed(e);
    	}
    }
    
    public void registerRemoveRowDrugSearchTableListeners(ActionListener listener)
    {
    	listenerRemoveRowDrug.add(listener);
    }
    
    public void fireRemoveRowDrugSearchTablePerformed(ActionEvent e)
    {
    	for (ActionListener actionListener : listenerRemoveRowDrug) {
    		actionListener.actionPerformed(e);
    	}
    }
    
    public void registerSaveButtonBehaviorAlteredListeners(ActionListener listener)
    {
    	listenerSaveButtonBehaviorAltered.add(listener);
    }
    
    public void fireSaveButtonBehaviorAlteredPerformed(ActionEvent e)
    {
    	for (ActionListener actionListener : listenerSaveButtonBehaviorAltered) {
    		actionListener.actionPerformed(e);
    	}
    }
    
    public void registerEditSaveButtonActionListeners(ActionListener listener)
    {
    	listenerEditSaveButtonAction.add(listener);
    }
    
    public void fireEditSaveButtonActionPerformed(ActionEvent e)
    {
    	for (ActionListener actionListener : listenerEditSaveButtonAction) {
    		actionListener.actionPerformed(e);
    	}
    }
    
    public void registerEditSaveButtonActionListenersReverse(ActionListener listener)
    {
    	listenerEditSaveButtonActionReverse.add(listener);
    }
    
    public void fireEditSaveButtonActionPerformedReverse(ActionEvent e)
    {
    	for (ActionListener actionListener : listenerEditSaveButtonActionReverse) {
    		actionListener.actionPerformed(e);
    	}
    }
}
