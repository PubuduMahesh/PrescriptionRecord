package cambio.precriptionrecord.view.patient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cambio.precriptionrecord.controller.PatientController;
import cambio.precriptionrecord.model.patient.Patient;
import cambio.precriptionrecord.util.DBConnection;
import cambio.precriptionrecord.util.DatePicker;

public class EditPatient extends JInternalFrame{
	private GridBagLayout gridbag;
	private JTextField tName;
	private JTextField tNIC;
	private JTextField tBirthday;
	private JTextField tTp;
	private JTextArea tAddress;
	private JTextArea tMedicalHitory;
	private JRadioButton rbMale;
	private JRadioButton rbFemale;
	private JRadioButton rbSingle;
	private JRadioButton rbMarried;
	private JRadioButton rbDivorced;
	private JButton bBirthday;
	private JButton bProfilePicAdd;
	private JButton bProfilePicDelete; 
	private ButtonGroup bgGender;
	private ButtonGroup bgStatus;
	private JTextField tID;
	
	private JButton bSave;
	private JButton bDiscard;
	
	private PatientController patientController;
	
	public EditPatient(PatientController patientController){
		this.patientController = patientController;
		
		JDesktopPane desktopPane = new JDesktopPane();
		setTitle("Edit Patient");
		setPreferredSize(new Dimension(740,665));
		setClosable(true);
		setVisible(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		gridbag = new GridBagLayout();
		setLayout(gridbag);	
		
		createLayout();
		
		/*Mouse click action perform*/
		mouseClickRow();
			
		desktopPane.add(this);
	}
	
	private void createLayout(){
		addSearchPanel();
		addLabel();
		addField();
	}
	
	private void addSearchPanel(){
		GridBagConstraints constraintsSearch = new GridBagConstraints();
		constraintsSearch.anchor = GridBagConstraints.NORTHWEST;
		int tbWidth = 690;
		int tbHeight = 130;
		PatientSearchPanel searchPanel = new PatientSearchPanel(patientController,tbWidth, tbHeight);		
		constraintsSearch.gridx = 0;
		constraintsSearch.gridy = 0;
		constraintsSearch.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(searchPanel,constraintsSearch);
		add(searchPanel);
	
	}
	
	private void addLabel(){
		GridBagConstraints constraintsLabel = new GridBagConstraints();
		constraintsLabel.anchor = GridBagConstraints.NORTHWEST;
		
		JLabel lID = new JLabel("Patient ID");
		JLabel lName = new JLabel();
		JLabel lNIC = new JLabel("NIC");
		JLabel lBirthday = new JLabel();
		JLabel lGender = new JLabel();
		JLabel lStatus = new JLabel();
		JLabel lAddress = new JLabel("Address");
		JLabel ltp = new JLabel("Telephone");
		JLabel lProfilePicture = new JLabel();
		JLabel lMedicalHistory = new JLabel("Medical History");
		JLabel lLeft = new JLabel();
		
		lName.setText("<html>Name <font color='red'> *</font></html>");
		lBirthday.setText("<html>Birthday <font color='red'> *</font></html>");
		lGender.setText("<html>Gender <font color='red'> *</font></html>");
		lStatus.setText("<html>Martial Status <font color='red'> *</font></font>");
		
		/*Label-ID*/
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 1;
		gridbag.setConstraints(lID, constraintsLabel);
		add(lID);
		
		/*Label-Name*/
		constraintsLabel.insets = new Insets(40, 0, 0, 0);
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 1;
		gridbag.setConstraints(lName, constraintsLabel);
		add(lName);
		
		/*Label-NIC*/
		constraintsLabel.insets = new Insets(75, 0, 10, 40);
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 1;
		gridbag.setConstraints(lNIC, constraintsLabel);
		add(lNIC);
		
		/*Label-Address*/
		constraintsLabel.insets = new Insets(110, 0, 10, 40);
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 1;
		gridbag.setConstraints(lAddress, constraintsLabel);
		add(lAddress);
		
		/*Label-Gender*/
		constraintsLabel.insets = new Insets(10, 0, 10, 0);
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 2;
		gridbag.setConstraints(lGender, constraintsLabel);
		add(lGender);
		
		/*Label-Married Status*/
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 3;
		gridbag.setConstraints(lStatus, constraintsLabel);
		add(lStatus);
		
		/*Label-Birthday*/
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 4;
		gridbag.setConstraints(lBirthday, constraintsLabel);
		add(lBirthday);
		
		
		/*Label-Telephone*/
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 5;
		gridbag.setConstraints(ltp, constraintsLabel);
		add(ltp);	
		
		/*Label Medical History*/
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 6;
		gridbag.setConstraints(lMedicalHistory, constraintsLabel);
		add(lMedicalHistory);	
		
		/*Label-profile picture*/
		constraintsLabel.insets = new Insets(0, 210, 10, 40);
		constraintsLabel.anchor = GridBagConstraints.NORTH;
		lProfilePicture.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lProfilePicture.setPreferredSize(new Dimension(135,135));
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 1;
		gridbag.setConstraints(lProfilePicture, constraintsLabel);
		add(lProfilePicture);
		
		/*Label-Left arrangement*/
		/*lLeft.setPreferredSize(new Dimension(115,100));
		constraintsLabel.gridx = 4;
		constraintsLabel.gridy = 1;
		gridbag.setConstraints(lLeft,constraintsLabel);
		add(lLeft);	*/
	}
	
	private void addField(){
		GridBagConstraints constraintsField = new GridBagConstraints();
		constraintsField.anchor = GridBagConstraints.NORTHWEST;
		
		tID = new JTextField(8);
		tName = new JTextField(20);
		tNIC = new JTextField(20);
		tAddress = new JTextArea(3,20);
		tBirthday= new JTextField(20);
		tTp = new JTextField(20);
		tMedicalHitory = new JTextArea(3,20);
		bBirthday = new JButton(":)");
		rbMale = new JRadioButton("Male");
		rbFemale = new JRadioButton("Female");
		rbSingle = new JRadioButton("Single");
		rbMarried = new JRadioButton("Married");
		rbDivorced = new JRadioButton("Divorced");
		bProfilePicAdd = new JButton("Edit");
		bProfilePicDelete = new JButton("Delete");
		
		bSave = new JButton("Save");
		bDiscard = new JButton("Discard");
				
		/*text field - id*/
		tID.setEditable(false);
		constraintsField.gridx = 0;
		constraintsField.gridy = 1;
		constraintsField.insets = new Insets(0, 100, 0, 0);
		gridbag.setConstraints(tID, constraintsField);
		add(tID);
		
		/*text field - name*/
		constraintsField.gridx = 0;
		constraintsField.gridy = 1;
		constraintsField.insets = new Insets(40, 100, 0, 0);
		gridbag.setConstraints(tName, constraintsField);
		add(tName);
		
		/*text field - nic*/
		constraintsField.gridx = 0;
		constraintsField.gridy = 1;
		constraintsField.insets = new Insets(70, 100, 10, 40);
		gridbag.setConstraints(tNIC, constraintsField);
		add(tNIC);
		
		/*text field - address*/
		constraintsField.insets = new Insets(100, 100, 10, 40);
		constraintsField.gridx = 0;
		constraintsField.gridy = 1;
		JScrollPane jspAddress = new JScrollPane(tAddress,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gridbag.setConstraints(jspAddress, constraintsField);
		add(jspAddress);
		
		/*radio button- male*/
		constraintsField.insets = new Insets(0, 100, 0, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 2;
		gridbag.setConstraints(rbMale, constraintsField);
		add(rbMale);
		
		/*radio button-female*/
		constraintsField.insets = new Insets(0, 170, 10, 40);
		constraintsField.gridx = 0;
		constraintsField.gridy = 2;
		gridbag.setConstraints(rbFemale, constraintsField);
		add(rbFemale);
		
		/*radio button - single*/
		constraintsField.insets = new Insets(0, 100, 0, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 3;
		gridbag.setConstraints(rbSingle, constraintsField);
		add(rbSingle);
		
		/*radio button-married*/
		constraintsField.insets = new Insets(0, 170, 10, 40);
		constraintsField.gridx = 0;
		constraintsField.gridy = 3;
		gridbag.setConstraints(rbMarried, constraintsField);
		add(rbMarried);
		
		/*radio button divorced.*/
		constraintsField.insets = new Insets(0, 250, 10, 40);
		constraintsField.gridx = 0;
		constraintsField.gridy = 3;
		gridbag.setConstraints(rbDivorced, constraintsField);
		add(rbDivorced);
		
		/*text field-birthday*/
		constraintsField.insets = new Insets(0, 100, 0, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 4;
		gridbag.setConstraints(tBirthday, constraintsField);
		add(tBirthday);
		
		/*button-birthday*/
		constraintsField.insets = new Insets(0, 330, 10, 40);
		bBirthday.setPreferredSize(new Dimension(25,20));
		constraintsField.gridx = 0;
		constraintsField.gridy = 4;
		gridbag.setConstraints(bBirthday, constraintsField);
		add(bBirthday);
		
		/*text field- telephone*/
		constraintsField.insets = new Insets(0, 100, 10, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 5;
		gridbag.setConstraints(tTp, constraintsField);
		add(tTp);		
		
		/*text field-health history*/
		constraintsField.insets = new Insets(0, 100, 10, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 6;
		JScrollPane jspHealthHistory = new JScrollPane(tMedicalHitory,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gridbag.setConstraints(jspHealthHistory, constraintsField);
		add(jspHealthHistory);
		
		/*button-save*/
		constraintsField.insets = new Insets(0, 350, 10, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 7;
		gridbag.setConstraints(bSave, constraintsField);
		add(bSave);
		
		/*button - discard*/
		constraintsField.insets = new Insets(0, 420, 0, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 7;
		gridbag.setConstraints(bDiscard, constraintsField);
		add(bDiscard);		

		/*button-profile picture add*/
		constraintsField.insets = new Insets(150, 375, 0, 0);
		bProfilePicAdd.setPreferredSize(new Dimension(70,15));
		bProfilePicAdd.setFont(new Font("seif",Font.ITALIC,10));
		constraintsField.gridx = 0;
		constraintsField.gridy = 1;
		gridbag.setConstraints(bProfilePicAdd, constraintsField);
		add(bProfilePicAdd);
		
		/*button-profile picture delete*/
		constraintsField.insets = new Insets(150, 455, 0, 0);
		bProfilePicDelete.setPreferredSize(new Dimension(70,15));
		bProfilePicDelete.setFont(new Font("seif",Font.ITALIC,10));
		constraintsField.gridx = 0;
		constraintsField.gridy = 1;
		gridbag.setConstraints(bProfilePicDelete, constraintsField);
		add(bProfilePicDelete);
		
		/*grouping radio buttons.*/
		bgGender = new ButtonGroup();
		bgGender.add(rbMale);
		bgGender.add(rbFemale);
		
		bgStatus = new ButtonGroup();
		bgStatus.add(rbSingle);
		bgStatus.add(rbMarried);
		bgStatus.add(rbDivorced);	
		
		/*Action Listeners*/
		birthdayButtonAction();
		discardButtonAction();
		saveButtonDisabled();
		saveButtonAction();
	}
	
	private void birthdayButtonAction(){
		bBirthday.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String selDate = new DatePicker((NewPatient)(bBirthday.getParent().getParent().getParent().getParent())).setPickedDate();
					Date birthday = new SimpleDateFormat("dd/MM/yyyy").parse(selDate);//convert the selected Date in to the "Date" type
					if (birthday.before(new Date())) {//check whether the selected date is grater than with respect to the current date. 
						tBirthday.setText(selDate);//set the date to the birthday text field.
					} else {//if validation is failed, warning message. 
						JOptionPane.showMessageDialog(null, "Birthday should be previous date", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (ParseException ex) {
					//add logger.
				}
			}
		});
	}
	
	private void discardButtonAction(){
		bDiscard.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){				
				tName.setText("");
				tNIC.setText("");
				tAddress.setText("");
				bgGender.clearSelection();
				bgStatus.clearSelection();
				tBirthday.setText("");
				tTp.setText("");
				tMedicalHitory.setText("");				
			}
			
		});
	}
	
	private void saveButtonDisabled(){
		bSave.setEnabled(false);
		new SaveButtonTextFieldCondtion(tName);		
		new SaveButtonTextFieldCondtion(tBirthday);	
		new SaveButtonRadioButtonCondtion(rbMale);
		new SaveButtonRadioButtonCondtion(rbFemale);
		new SaveButtonRadioButtonCondtion(rbSingle);
		new SaveButtonRadioButtonCondtion(rbMarried);
		new SaveButtonRadioButtonCondtion(rbDivorced);
		
	}
	
	private class  SaveButtonTextFieldCondtion{
		public SaveButtonTextFieldCondtion (JTextField textField) {
			textField.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void changedUpdate(DocumentEvent e) {
					enableSaveButton();
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					enableSaveButton();
				}

				@Override
				public void insertUpdate(DocumentEvent e) {
					enableSaveButton();
				}


			});
		}
	}
	private class SaveButtonRadioButtonCondtion{
		public SaveButtonRadioButtonCondtion(JRadioButton radioButton){
			radioButton.addActionListener(new ActionListener (){
				@Override
				public void actionPerformed (ActionEvent e){
					enableSaveButton();
				}
			});
		}
	}
	
	private void enableSaveButton(){
		if(!tName.getText().equals("") && !tBirthday.getText().equals("") && (rbMale.isSelected() 
			||rbFemale.isSelected()) && (rbSingle.isSelected() 
			|| rbMarried.isSelected() || rbDivorced.isSelected())){
			bSave.setEnabled(true);
		}
		else{
			bSave.setEnabled(false);
		}
	}
	
	private void saveButtonAction(){
		bSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Edit Patient?","Warning",0);
				if(dialogResult == JOptionPane.YES_OPTION){
					String id = tID.getText();
					String name = tName.getText();
					String nic = tNIC.getText();
					String address = tAddress.getText();
					String gender;
					String status;
					String birthday;
					String telephone;
					String medicalHistory;

					if(rbMale.isSelected())
						gender = "Male";				
					else
						gender = "Female";
					if(rbSingle.isSelected())
						status = "Single";
					else if(rbMarried.isSelected())
						status = "Married";
					else
						status = "Divorce";

					birthday = tBirthday.getText();
					telephone = tTp.getText();
					medicalHistory = tMedicalHitory.getText();		

					savePatient(new Patient(id,name,nic,address,gender,status,birthday,telephone,medicalHistory));
				}
			}
		});
	}
	
	private void savePatient(Patient patient){
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
			sql = "UPDATE `patient` SET "
					+ "`id` = '"+patient.getID()+"', "
					+ "`name` = '"+patient.getName()+"', "
					+ "`nic` = '"+patient.getNIC()+"', "
					+ "`address` = '"+patient.getAddress()+"', "
					+ "`gender` = '"+patient.getGender()+"', "
					+ "`status` = '"+patient.getStatus()+"', "
					+ "`birthday` = '"+patient.getBirthday()+"', "
					+ "`telephone` = '"+patient.getTp()+"', "
					+ "`healthDescription` = '"+patient.getMedicalHistory()+"' "
					+ "WHERE "
					+ "`patient`.`nic` = '"+patient.getNIC()+"' ";			
			stmt.executeUpdate(sql);
			con.close();
			ActionEvent e = new ActionEvent(patient,-1,"");
			patientController.fireUpdateRowPatientSearchTablePerformed(e);
			JOptionPane.showMessageDialog(null, "Patient detail saving succeeded.", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void mouseClickRow(){
		patientController.registerRowClickListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof Patient) {
					Patient patient = (Patient)e.getSource();
					setPatientField(patient);
				}

			}
		});
	}
	
	private void setPatientField(Patient patient){
		tID.setText(patient.getID());
		tName.setText(patient.getName());
		tNIC.setText(patient.getNIC());
		tAddress.setText(patient.getAddress());
		
		if(patient.getGender().equals("Male"))
			rbMale.setSelected(true);
		else
			rbFemale.setSelected(true);
		
		if(patient.getStatus().equals("Single"))
			rbSingle.setSelected(true);
		else if(patient.getStatus().equals("Married"))
			rbMarried.setSelected(true);
		else
			rbDivorced.setSelected(true);
		
		tBirthday.setText(patient.getBirthday());
		tTp.setText(patient.getTp());
		tMedicalHitory.setText(patient.getMedicalHistory());
	}
	
}
