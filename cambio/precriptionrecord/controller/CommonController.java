package cambio.precriptionrecord.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CommonController {
	private List<ActionListener> listenerPrescriptionReportClearAllFieldAction = new ArrayList<>();
	/*this register and fire methods with respect to the Prescription Report clear button. */
    public void registerClearPrescriptionReportAllElementActionListeners(ActionListener listener)
    {
    	listenerPrescriptionReportClearAllFieldAction.add(listener);
    }
    
    public void fireClearPrescriptionReportALlElementActionPerformed(ActionEvent e)
    {
    	for (ActionListener actionListener : listenerPrescriptionReportClearAllFieldAction) {
    		actionListener.actionPerformed(e);
    	}
    }
}
