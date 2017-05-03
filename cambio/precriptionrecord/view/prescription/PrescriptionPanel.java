package cambio.precriptionrecord.view.prescription;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import cambio.precriptionrecord.controller.DrugController;
import cambio.precriptionrecord.controller.PrescriptionController;
import cambio.precriptionrecord.model.drug.Drug;
import cambio.precriptionrecord.model.drug.DrugTableModel;
import cambio.precriptionrecord.model.prescription.PrescriptionTableModel;
import cambio.precriptionrecord.util.DatePicker;
import cambio.precriptionrecord.view.drug.DrugSearchPanel;
import cambio.precriptionrecord.view.drug.EditDrug;

public class PrescriptionPanel extends JInternalFrame{
	private GridBagLayout gridbag;
	private JButton bDate;
	private JTextField tDate;
	private JTextArea tDiagnosis;
	private JTable prescriptionTable;
	private JButton bAdd;
	private JButton bEdit;
	private JButton bSave;
	private JButton bPrint;
	private JButton bSend;
	private JButton bClear;
	private DrugController drugController = new DrugController();
	private PrescriptionController prescriptionController;
	public PrescriptionPanel(PrescriptionController prescriptionController){
		this.prescriptionController = prescriptionController;
		gridbag = new GridBagLayout();
		setLayout(gridbag);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Prescription"));
		setPreferredSize(new Dimension(730,350));
		setVisible(true);
		/*converting JInternalFrame into BasicInternalFrame and hide the default title bar of the JInternal frame. */
		BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
		bi.setNorthPane(null);
		createLayout();
	}

	private void createLayout(){
		addField();
		addDrugSearchPanel();
		addPrescriptionTable();
	}

	private void addField(){
		GridBagConstraints constraints = new GridBagConstraints();
		JLabel lDate = new JLabel("Date");
		JLabel lDiagnosis = new JLabel ("Diagnosis");
		tDate = new JTextField(8);
		bDate = new JButton(":)");
		tDiagnosis = new JTextArea(15,18);
		bAdd = new JButton("Add");
		bEdit = new JButton ("Edit");
		bSave = new JButton("Save");
		bPrint = new JButton("Print");
		bSend = new JButton("Send");
		bClear = new JButton("Clear");

		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.gridx = 0;
		constraints.gridy = 0;

		/*label - date*/
		constraints.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(lDate, constraints);
		add(lDate);

		/*text field - date*/
		constraints.insets = new Insets(0, 60, 0, 0);
		gridbag.setConstraints(tDate, constraints);
		add(tDate);

		/*button - date*/
		bDate.setPreferredSize(new Dimension(20, 20));
		constraints.insets = new Insets(0, 160, 0, 0);
		gridbag.setConstraints(bDate, constraints);
		add(bDate);

		/*label - diagnosis*/
		constraints.insets = new Insets(200, 0, 0, 0);
		gridbag.setConstraints(lDiagnosis, constraints);
		add(lDiagnosis);

		/*text area - diagnosis*/
		constraints.insets = new Insets(30, 60, 0, 0);
		JScrollPane diagnosisScrollPane = new JScrollPane(tDiagnosis,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gridbag.setConstraints(diagnosisScrollPane, constraints);
		add(diagnosisScrollPane);

		/*button - add*/
		constraints.insets = new Insets(120, 650, 0, 0);
		gridbag.setConstraints(bAdd, constraints);
		add(bAdd);
		
		/*button - edit*/
		constraints.insets = new Insets(120, 575, 0, 0);
		gridbag.setConstraints(bEdit, constraints);
		add(bEdit);
		
		/*button - save*/
		constraints.insets = new Insets(285, 275, 0, 0);
		gridbag.setConstraints(bSave, constraints);
		add(bSave);
		
		/*button - print*/
		constraints.insets = new Insets(285, 350, 0, 0);
		gridbag.setConstraints(bPrint, constraints);
		add(bPrint);
		
		/*button - send*/
		constraints.insets = new Insets(285, 425, 0, 0);
		gridbag.setConstraints(bSend, constraints);
		add(bSend);
		
		/*button - clear*/
		constraints.insets = new Insets(285, 500, 0, 0);
		gridbag.setConstraints(bClear, constraints);
		add(bClear);
		
		/*button action */
		dateButtonAction();
		mouseClickRow();
	}

	private void addDrugSearchPanel(){
		GridBagConstraints constraints = new GridBagConstraints();
		int tbWidth = 400;
		int tbHeight = 50;
		DrugSearchPanel drugSearchPanel = new DrugSearchPanel(drugController, tbWidth, tbHeight);
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 275, 0, 0);
		gridbag.setConstraints(drugSearchPanel, constraints);		
		add(drugSearchPanel);
	}

	private void addPrescriptionTable(){
		GridBagConstraints tablConstraints = new GridBagConstraints();

		final String[] header = {"id","name","description","type","dosage"};
		ArrayList<Drug> data = new ArrayList<Drug>();
		prescriptionTable = new JTable(new PrescriptionTableModel(data,header));

		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		prescriptionTable.setPreferredScrollableViewportSize(new Dimension(425,100));
		prescriptionTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(prescriptionTable);

		tablConstraints.anchor = GridBagConstraints.NORTHWEST;//align content of main panel in to left top corner.
		tablConstraints.gridx = 0;
		tablConstraints.gridy = 0;
		tablConstraints.insets = new Insets(160, 275, 0, 0);
		gridbag.setConstraints(scrollPane, tablConstraints);
		add(scrollPane);
	}
	
	private void dateButtonAction(){
		bDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String selDate = new DatePicker((PrescriptionPanel)(bDate.getParent().getParent().getParent().getParent())).setPickedDate();
					Date date = new SimpleDateFormat("yyyy/MM/dd").parse(selDate);//convert the selected Date in to the "Date" type
					if (date.before(new Date())) {//check whether the selected date is grater than with respect to the current date. 
						tDate.setText(selDate);//set the date to the birthday text field.
					} else {//if validation is failed, warning message. 
						JOptionPane.showMessageDialog(null, "Date should be previous date", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (ParseException ex) {
					//add logger.
				}
			}
		});
	}
	
	private void addButtonAction(final Drug drug){
		bAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrescriptionTableModel tableModel = (PrescriptionTableModel)prescriptionTable.getModel();
				tableModel.updateTable(drug);
			}
		});
	}
	
	private void mouseClickRow(){
		drugController.registerRowClickListeners(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() instanceof Drug){
					Drug drug = (Drug)e.getSource();
					addButtonAction(drug);
					editButtonAction(drug);
				}
			}
		});
	}
	
	private void editButtonAction(final Drug drug){
		bEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createDialog(drug);
			}
		});
	}
	
	private void createDialog(Drug drug){
		new EditDosage(drug);
	}

}
