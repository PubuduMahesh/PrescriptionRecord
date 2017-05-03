package cambio.precriptionrecord.view.patient;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cambio.precriptionrecord.model.drug.Drug;
import cambio.precriptionrecord.model.patient.TreatmentTableModel;
import cambio.precriptionrecord.model.prescription.PrescriptionTableModel;

public class TreatmentHistoryTable extends JPanel{
	JTable treatmentTable;
	GridBagLayout gridbag;
	public TreatmentHistoryTable(){
		gridbag = new GridBagLayout();
		GridBagConstraints tablConstraints = new GridBagConstraints();

		final String[] header = {"Date","DiagnosisDescription","DrugList","Doctor"};
		ArrayList<Drug> data = new ArrayList<Drug>();
		treatmentTable = new JTable(new TreatmentTableModel(data,header));

		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		treatmentTable.setPreferredScrollableViewportSize(new Dimension(425,100));
		treatmentTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(treatmentTable);

		tablConstraints.anchor = GridBagConstraints.NORTHWEST;//align content of main panel in to left top corner.
		tablConstraints.gridx = 0;
		tablConstraints.gridy = 0;
		tablConstraints.insets = new Insets(160, 275, 0, 0);
		gridbag.setConstraints(scrollPane, tablConstraints);
		add(scrollPane);
	}
}
