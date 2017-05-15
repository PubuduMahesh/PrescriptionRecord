package cambio.precriptionrecord.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CommonController {
	private final List<ActionListener> listenerPrescriptionReportClearAllFieldAction = new ArrayList<>();
	private final List<ActionListener> listenerTreatmentHistoryPatientDetailAction = new ArrayList<>();
	private final List<ActionListener> listenerTreatmentHistoryAction = new ArrayList<>();
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
    public void registerLoginCredentialsSuccessActionListeners(ActionListener listener)
    {
    	listenerPrescriptionReportClearAllFieldAction.add(listener);
    }
    
    public void fireLoginCredentialSuccessActionPerformed(ActionEvent e)
    {
    	for (ActionListener actionListener : listenerPrescriptionReportClearAllFieldAction) {
    		actionListener.actionPerformed(e);
    	}
    }
    /*this methods fires when click the search button in treatment history*/
    public void registerTreatmentHistoryPatientDetailActionListeners(ActionListener listener)
    {
    	listenerTreatmentHistoryPatientDetailAction.add(listener);
    }
    
    public void fireTreatmentHistoryPatientDetailActionPrformed(ActionEvent e)
    {
    	for (ActionListener actionListener : listenerTreatmentHistoryPatientDetailAction) {
    		actionListener.actionPerformed(e);
    	}
    }
    /*these methods would be fired when search button is clicked in Treatment histroy page. */
    public void registerTreatmentHistoryActionListeners(ActionListener listener)
    {
    	listenerTreatmentHistoryAction.add(listener);
    }
    
    public void fireTreatmentHistoryActionPrformed(ActionEvent e)
    {
    	for (ActionListener actionListener : listenerTreatmentHistoryAction) {
    		actionListener.actionPerformed(e);
    	}
    }
}
