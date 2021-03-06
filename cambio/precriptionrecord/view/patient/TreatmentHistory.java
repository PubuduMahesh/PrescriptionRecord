package cambio.precriptionrecord.view.patient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cambio.precriptionrecord.SQLToolKit.PatientSQLToolkit;
import cambio.precriptionrecord.controller.CommonController;
import cambio.precriptionrecord.controller.PatientController;
import cambio.precriptionrecord.model.patient.Patient;
import cambio.precriptionrecord.model.patient.TreatementSearchedPatient;
import cambio.precriptionrecord.model.patient.TreatmentTableModel;
import cambio.precriptionrecord.model.prescription.Prescription;
import cambio.precriptionrecord.util.DBConnection;
import cambio.precriptionrecord.util.DatePicker;

public class TreatmentHistory extends JInternalFrame{
	private final PatientController patientController;
	private final CommonController commonController;
	private GridBagLayout gridbag;
	private JButton bTo;
	private JButton bFrom;
	private JTextField tFrom;
	private JTextField tTo;
	private JTextField tName;
	private JTextField tID;
	private JTextField tNIC;
	private JButton bSearch;

	public TreatmentHistory(){
		this.patientController = new PatientController();
		this.commonController = new CommonController();
		gridbag = new GridBagLayout();
		setTitle("Treatment History");
		setPreferredSize(new Dimension(740,665));
		setMinimumSize(new Dimension(740,665));
		setClosable(true);
		setResizable(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		gridbag = new GridBagLayout();
		setLayout(gridbag);			
		createLayout();
		setVisible(true);
	}	
	private void createLayout() {
		addPatientSearchPanel();
		addDateFieldPanel();
		addTreatmentHistoryTablePanel();		
		/*Action Listeners*/
		rowClickActionFired();

	}

	private void addPatientSearchPanel(){
		GridBagConstraints constraints = new GridBagConstraints();
		PatientSearchPanel patientSearchPanel = new PatientSearchPanel(patientController, 700, 140,commonController);
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		gridbag.setConstraints(patientSearchPanel, constraints);
		add(patientSearchPanel);

	}

	private void addDateFieldPanel(){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTHWEST;
		JLabel lName = new JLabel("Name");
		JLabel lID = new JLabel("ID");
		JLabel lNIC = new JLabel("NIC");
		JLabel lTimePeriod = new JLabel("Time Period");
		JLabel lFrom = new JLabel("From");
		JLabel lTo = new JLabel("To");
		tName = new JTextField(15);
		tID = new JTextField(5);
		tNIC = new JTextField(15);
		tFrom = new JTextField(15);
		bFrom = new JButton();
		tTo = new JTextField(15);
		bTo = new JButton();
		bSearch = new JButton("Search");

		constraints.gridx = 0;
		constraints.gridy = 0;

		constraints.insets = new Insets(210,10, 0, 0);
		gridbag.setConstraints(lID, constraints);
		add(lID);

		tID.setEnabled(false);
		constraints.insets = new Insets(210, 30, 0, 0);
		gridbag.setConstraints(tID, constraints);
		add(tID);

		constraints.insets = new Insets(210, 120, 0, 0);
		gridbag.setConstraints(lNIC, constraints);
		add(lNIC);

		tNIC.setEnabled(false);
		constraints.insets = new Insets(210, 160, 0, 0);
		gridbag.setConstraints(tNIC, constraints);
		add(tNIC);

		constraints.insets = new Insets(210, 365, 0, 0);
		gridbag.setConstraints(lName, constraints);
		add(lName);

		tName.setEnabled(false);
		constraints.insets = new Insets(210, 410, 0, 0);
		gridbag.setConstraints(tName, constraints);
		add(tName);	

		constraints.insets  = new Insets(250, 10, 0, 0);
		gridbag.setConstraints(lTimePeriod, constraints);
		add(lTimePeriod);

		constraints.insets = new Insets(250, 120, 0, 0);
		gridbag.setConstraints(lFrom, constraints);
		add(lFrom);

		constraints.insets = new Insets(250, 160, 0, 0);
		gridbag.setConstraints(tFrom, constraints);
		add(tFrom);

		bFrom.setIcon(new javax.swing.ImageIcon("src/cambio/Image/calendarIcon.png"));
		bFrom.setBorderPainted(false);
		bFrom.setContentAreaFilled(false);
		bFrom.setPreferredSize(new Dimension(20, 18));
		constraints.insets = new Insets(250, 330, 0, 0);
		gridbag.setConstraints(bFrom, constraints);
		add(bFrom);

		constraints.insets = new Insets(250, 365, 0, 0);
		gridbag.setConstraints(lTo, constraints);
		add(lTo);

		constraints.insets = new Insets(250, 410, 0, 0);
		gridbag.setConstraints(tTo, constraints);
		add(tTo);

		bTo.setIcon(new javax.swing.ImageIcon("src/cambio/Image/calendarIcon.png"));
		bTo.setBorderPainted(false);
		bTo.setContentAreaFilled(false);
		bTo.setPreferredSize(new Dimension(20, 18));
		constraints.insets = new Insets(250, 580, 0, 0);
		gridbag.setConstraints(bTo, constraints);
		add(bTo);

		constraints.insets = new Insets(250, 620, 0, 0);
		gridbag.setConstraints(bSearch, constraints);
		add(bSearch);

		/*to button action*/
		periodButtonAction(bFrom, tFrom);
		periodButtonAction(bTo,tTo);
		searchButtonAction();

	}

	private void addTreatmentHistoryTablePanel(){
		GridBagConstraints constraints = new GridBagConstraints();
		TreatmentHistoryTable historyTable = new TreatmentHistoryTable(commonController);
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.gridx = 0;
		constraints.gridy = 1;
		gridbag.setConstraints(historyTable, constraints);
		add(historyTable);

	}

	private void periodButtonAction(final JButton button, final JTextField textfield){
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String selDate = new DatePicker((TreatmentHistory)(button.getParent().getParent().getParent().getParent())).setPickedDate();
					Date birthday = new SimpleDateFormat("yyyy/MM/dd").parse(selDate);//convert the selected Date in to the "Date" type
					if (birthday.before(new Date())) {//check whether the selected date is grater than with respect to the current date. 
						textfield.setText(selDate);//set the date to the birthday text field.
					} else {//if validation is failed, warning message. 
						JOptionPane.showMessageDialog(null, "Date should be previous date", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (ParseException ex) {
					//add logger.
				}

			}
		});

	}

	private void searchButtonAction(){
		bSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Prescription> prescriptionList;
				PatientSQLToolkit patientSqlToolkit = new PatientSQLToolkit();
				prescriptionList = patientSqlToolkit.searchPrescription(tFrom.getText(),tTo.getText(), tID.getText());
				if(prescriptionList.size()>0){
					ActionEvent e1 = new ActionEvent(prescriptionList,-1,null);
					commonController.fireTreatmentHistoryActionPrformed(e1);
				}
				
				TreatementSearchedPatient searchedPatient = new TreatementSearchedPatient();
				searchedPatient.setPatientName(tName.getText());
				searchedPatient.setFromDate(tFrom.getText());
				searchedPatient.setToDate(tTo.getText());
				ActionEvent e1 = new ActionEvent(searchedPatient,-1,null);
				commonController.fireTreatmentHistoryPatientDetailActionPrformed(e1);

			}
		});

	}

	private void rowClickActionFired(){
		patientController.registerRowClickListeners(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setSelectedPatientField((Patient)e.getSource());

			}
		});
	}

	private void setSelectedPatientField(Patient patient){
		tName.setText(patient.getName());
		tID.setText(patient.getID());
		tNIC.setText(patient.getNIC());
	}


}
