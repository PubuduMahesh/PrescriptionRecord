package cambio.precriptionrecord.view.patient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cambio.precriptionrecord.controller.CommonController;
import cambio.precriptionrecord.model.drug.Drug;
import cambio.precriptionrecord.model.patient.TreatementSearchedPatient;
import cambio.precriptionrecord.model.patient.TreatmentTableModel;
import cambio.precriptionrecord.model.prescription.Prescription;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

public class TreatmentHistoryTable extends JPanel{
	private static JTable treatmentTable;
	private GridBagLayout gridbag;
	private JButton bClear;
	private JButton bPrint;
	private TreatmentTableModel tbModel;
	private CommonController commonController;
	public TreatmentHistoryTable(CommonController commonController){	
		this.commonController = commonController;
		gridbag = new GridBagLayout();
		GridBagConstraints tablConstraints = new GridBagConstraints();
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setPreferredSize(new Dimension(720, 300));
		final String[] header = {"Date","DiagnosisDescription","DrugList","Doctor"};
		ArrayList<Prescription> data = new ArrayList<Prescription>();
		treatmentTable = new JTable(new TreatmentTableModel(data,header));
		tbModel = (TreatmentTableModel)treatmentTable.getModel();
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		treatmentTable.setPreferredScrollableViewportSize(new Dimension(710,200));
		treatmentTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(treatmentTable);

		tablConstraints.anchor = GridBagConstraints.NORTHWEST;//align content of main panel in to left top corner.
		tablConstraints.gridx = 0;
		tablConstraints.gridy = 0;
		tablConstraints.insets = new Insets(160, 275, 0, 0);
		gridbag.setConstraints(scrollPane, tablConstraints);
		add(scrollPane);
		
		bClear = new JButton("Clear");
		tablConstraints.gridx = 0;
		tablConstraints.gridy = 0;
		tablConstraints.insets = new Insets(250, 700, 0, 0);
		gridbag.setConstraints(bClear, tablConstraints);
		add(bClear);
		
		bPrint = new JButton("Print");
		tablConstraints.gridx = 0;
		tablConstraints.gridy = 0;
		tablConstraints.insets = new Insets(250, 750, 0, 0);
		gridbag.setConstraints(bPrint, tablConstraints);
		add(bPrint);
		
		setTableData();
		clearButtonAction();
		printButtonAction(); 
	}
	private void clearButtonAction(){
		bClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTable();
				
			}
		});
	}
	private void clearTable(){
		int rowCount = tbModel.getRowCount();
		for(int i = 0; i<rowCount; i++){
			tbModel.removeRow(0);
			
		}
	}
        
	private void printButtonAction() {
        bPrint.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                List<Prescription> listItems = new ArrayList<Prescription>();
                listItems = tbModel.getTableData();
                printReport(listItems);

            }
        });
    }
	
	private void printReport(List<Prescription> listItems) {
        try {
        	JRBeanCollectionDataSource itmesJRBean = new JRBeanCollectionDataSource(listItems);
        	
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("ItemDataSource", itmesJRBean);
            parameters.put("patientName", "Patient Name");
            parameters.put("fromDate", "2");
            parameters.put("toDate", "3");
            String sourceName = "src/cambio/report/temp1.jrxml";
            JasperReport report = JasperCompileManager.compileReport(sourceName);

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

            JDialog dialog = new JDialog();
            dialog.setVisible(true);
			dialog.getContentPane().add(new JRViewer(jasperPrint));
            dialog.pack();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }
	private void setTableData(){
		commonController.registerTreatmentHistoryActionListeners(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTable();
				List<Prescription> prescriptionList = (List<Prescription>)e.getSource();
				for(Prescription prescription:prescriptionList)
					tbModel.updateTable(prescription);
				
				
			}
		});
	}
	private void setSearchedPatientObject(){
		commonController.registerTreatmentHistoryPatientDetailActionListeners(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() instanceof TreatementSearchedPatient){
					TreatementSearchedPatient tempPatient = (TreatementSearchedPatient)e.getSource();
//					searchedPatientName = 
				}
				
			}
		});
	}
	
}
