package cambio.precriptionrecord.view.patient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import cambio.precriptionrecord.model.drug.Drug;
import cambio.precriptionrecord.model.patient.TreatmentTableModel;
import cambio.precriptionrecord.model.prescription.Prescription;
import cambio.precriptionrecord.model.prescription.PrescriptionTableModel;

public class TreatmentHistoryTable extends JPanel{
	private static JTable treatmentTable;
	private GridBagLayout gridbag;
	private JButton bClear;
	private TreatmentTableModel tbModel;
	public TreatmentHistoryTable(){		
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
		
		clearButtonAction();
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
	
	public static JTable getTable(){
		return treatmentTable;
	}
	
}
