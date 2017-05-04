package cambio.precriptionrecord.view.prescription;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.swing.text.Document;

import cambio.precriptionrecord.controller.DrugController;
import cambio.precriptionrecord.controller.PrescriptionController;
import cambio.precriptionrecord.model.drug.Drug;
import cambio.precriptionrecord.model.drug.DrugTableModel;
import cambio.precriptionrecord.model.patient.Patient;
import cambio.precriptionrecord.model.prescription.Prescription;
import cambio.precriptionrecord.model.prescription.PrescriptionTableModel;
import cambio.precriptionrecord.util.DBConnection;
import cambio.precriptionrecord.util.DatePicker;
import cambio.precriptionrecord.view.drug.DrugSearchPanel;
import cambio.precriptionrecord.view.drug.EditDrug;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

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
	private PrescriptionTableModel tbModel;
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
		
		/*action */
		dateButtonAction();
		mouseClickRow();
		saveButtonAction();
		printButtonAction();
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
				tbModel = (PrescriptionTableModel)prescriptionTable.getModel();
				tbModel.updateTable(drug);
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
		new EditDosage(drug,prescriptionController);
		editDosageAddButtonAction();
	}

	private void editDosageAddButtonAction() {
		prescriptionController.registerEditPrescriptionDosageListeners(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tbModel = (PrescriptionTableModel)prescriptionTable.getModel();
				tbModel.updateTable((Drug)e.getSource());
				
			}
		});
		
	}
	
	private void saveButtonAction(){
		bSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				prescriptionController.fireSavePrescriptionPerformed(e);
				prescriptionController.registerSavePrescriptionReverseListeners(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						savePrescription(setPrescriptionObject((String)e.getSource()));
					}
				});
				
			}
		});
	}
	
	private Prescription setPrescriptionObject(String patientID){
		Prescription prescription = new Prescription();
		prescription.setDate(tDate.getText());
		prescription.setDiagnosisDescription(tDiagnosis.getText());
		prescription.setDoctorID("1");//need to change
		prescription.setDrugList(createDrugList());
		prescription.setPatientID(patientID);
		
		return prescription;
		
	}
	
	private String createDrugList(){
		int rowCount = tbModel.getRowCount();
		Drug drug = new Drug();
		String drugList = null;
		for(int rowIndex =0; rowIndex< rowCount; rowIndex++){
			drug = tbModel.getValue(rowIndex);
			drugList+= drug.getDrugId()+"-"+drug.getDrugName()
			+"-"+drug.getDescription()
			+"-"+drug.getType()
			+"-"+drug.getDosage()+",\n"; 
			
		}
		return drugList;
		
	}
	
	private void savePrescription(Prescription prescription){
		DBConnection dbCon = new DBConnection();
		Connection con = dbCon.getConnection();
		Statement stmt = null;
		String sql = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			sql = "INSERT INTO `prescription` (`id`,"
					+ " `patientID`,"
					+ " `doctorID`,"
					+ " `diagDescription`,"
					+ " `drugList`,"
					+ " `date`) VALUES (NULL,"
					+ " '"+prescription.getPatientID()+"',"
					+ " '"+prescription.getDoctorID()+"',"
					+ " '"+prescription.getDiagnosisDescription()+"',"
					+ " '"+prescription.getDrugList()+"',"
					+ " '"+prescription.getDate()+"')";			
			stmt.executeUpdate(sql);
			con.close();
			JOptionPane.showMessageDialog(null, "Prescription detail saving succeeded.", "Success", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Prescription detail saving failed", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void printButtonAction(){
		bPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				printReport();
				
			}
		});
	}
	
	private void printReport(){
		try{
			List<Map<String,? >> dataSource = new ArrayList<Map<String, ?>>();
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("name", "Sangakkara");
			dataSource.add(m); 
			JRDataSource jrdataSource = new JRBeanCollectionDataSource(dataSource);
			String sourceName = "src/cambio/precriptionrecord/report/Blank_A4.jrxml";
			JasperReport report = JasperCompileManager.compileReport(sourceName);
			JasperPrint filledReport = JasperFillManager.fillReport(report, null,jrdataSource);
			JDialog dialog = new JDialog();
			dialog.setVisible(true);			
			dialog.getContentPane().add(new JRViewer(filledReport));
			dialog.pack();
		}catch(Exception ex){
			System.out.println(ex.getMessage() );
		}
	}

}
