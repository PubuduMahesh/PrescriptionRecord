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

public class RemovePatient extends JInternalFrame{
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
	
	private JButton bRemove;
	private JButton bDiscard;
	
	private PatientController patientController;
	
	public RemovePatient(PatientController patientController){
		this.patientController = patientController;
		
		JDesktopPane desktopPane = new JDesktopPane();
		setTitle("Remove Patient");
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
		int tbHieght = 130;
		PatientSearchPanel searchPanel = new PatientSearchPanel(patientController, tbWidth, tbHieght);		
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
		
		bRemove = new JButton("Remove");
		bDiscard = new JButton("Clear");
				
		/*text field - id*/
		tID.setEditable(false);
		constraintsField.gridx = 0;
		constraintsField.gridy = 1;
		constraintsField.insets = new Insets(0, 100, 0, 0);
		gridbag.setConstraints(tID, constraintsField);
		add(tID);
		
		/*text field - name*/
		tName.setEditable(false);
		constraintsField.gridx = 0;
		constraintsField.gridy = 1;
		constraintsField.insets = new Insets(40, 100, 0, 0);
		gridbag.setConstraints(tName, constraintsField);
		add(tName);
		
		/*text field - nic*/
		tNIC.setEditable(false);
		constraintsField.gridx = 0;
		constraintsField.gridy = 1;
		constraintsField.insets = new Insets(70, 100, 10, 40);
		gridbag.setConstraints(tNIC, constraintsField);
		add(tNIC);
		
		/*text field - address*/
		tAddress.setEditable(false);
		constraintsField.insets = new Insets(100, 100, 10, 40);
		constraintsField.gridx = 0;
		constraintsField.gridy = 1;
		JScrollPane jspAddress = new JScrollPane(tAddress,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gridbag.setConstraints(jspAddress, constraintsField);
		add(jspAddress);
		
		/*radio button- male*/
		rbMale.setEnabled(false);
		constraintsField.insets = new Insets(0, 100, 0, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 2;
		gridbag.setConstraints(rbMale, constraintsField);
		add(rbMale);
		
		/*radio button-female*/
		rbFemale.setEnabled(false);
		constraintsField.insets = new Insets(0, 170, 10, 40);
		constraintsField.gridx = 0;
		constraintsField.gridy = 2;
		gridbag.setConstraints(rbFemale, constraintsField);
		add(rbFemale);
		
		/*radio button - single*/
		rbSingle.setEnabled(false);
		constraintsField.insets = new Insets(0, 100, 0, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 3;
		gridbag.setConstraints(rbSingle, constraintsField);
		add(rbSingle);
		
		/*radio button-married*/
		rbMarried.setEnabled(false);
		constraintsField.insets = new Insets(0, 170, 10, 40);
		constraintsField.gridx = 0;
		constraintsField.gridy = 3;
		gridbag.setConstraints(rbMarried, constraintsField);
		add(rbMarried);
		
		/*radio button divorced.*/
		rbDivorced.setEnabled(false);
		constraintsField.insets = new Insets(0, 250, 10, 40);
		constraintsField.gridx = 0;
		constraintsField.gridy = 3;
		gridbag.setConstraints(rbDivorced, constraintsField);
		add(rbDivorced);
		
		/*text field-birthday*/
		tBirthday.setEditable(false);
		constraintsField.insets = new Insets(0, 100, 0, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 4;
		gridbag.setConstraints(tBirthday, constraintsField);
		add(tBirthday);
		
		/*button-birthday*/
		bBirthday.setEnabled(false);
		constraintsField.insets = new Insets(0, 330, 10, 40);
		bBirthday.setPreferredSize(new Dimension(25,20));
		constraintsField.gridx = 0;
		constraintsField.gridy = 4;
		gridbag.setConstraints(bBirthday, constraintsField);
		add(bBirthday);
		
		/*text field- telephone*/
		tTp.setEditable(false);
		constraintsField.insets = new Insets(0, 100, 10, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 5;
		gridbag.setConstraints(tTp, constraintsField);
		add(tTp);		
		
		/*text field-health history*/
		tMedicalHitory.setEditable(false);
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
		gridbag.setConstraints(bRemove, constraintsField);
		add(bRemove);
		
		/*button - discard*/
		constraintsField.insets = new Insets(0, 420, 0, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 7;
		gridbag.setConstraints(bDiscard, constraintsField);
		add(bDiscard);		

		/*button-profile picture add*/
		bProfilePicAdd.setEnabled(false);
		constraintsField.insets = new Insets(150, 375, 0, 0);
		bProfilePicAdd.setPreferredSize(new Dimension(70,15));
		bProfilePicAdd.setFont(new Font("seif",Font.ITALIC,10));
		constraintsField.gridx = 0;
		constraintsField.gridy = 1;
		gridbag.setConstraints(bProfilePicAdd, constraintsField);
		add(bProfilePicAdd);
		
		/*button-profile picture delete*/
		bProfilePicDelete.setEnabled(false);
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
		removeButtonEnabled();
		removeButtonAction();
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
				discardField();				
			}
			
		});
	}
	
	private void removeButtonEnabled(){
		bRemove.setEnabled(false);
		new RemoveButtonTextFieldCondtion(tName);
		
	}
	
	private class  RemoveButtonTextFieldCondtion{
		public RemoveButtonTextFieldCondtion (JTextField textField) {
			textField.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void changedUpdate(DocumentEvent e) {
					enableRemoveButton();
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					enableRemoveButton();
				}

				@Override
				public void insertUpdate(DocumentEvent e) {
					enableRemoveButton();
				}


			});
		}
	}
	
	private void enableRemoveButton(){
		if(!tName.getText().equals("")){
			bRemove.setEnabled(true);
		}
		else{
			bRemove.setEnabled(false);
		}
	}
	
	private void removeButtonAction(){
		bRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Remove Patient?","Warning",0);
				if(dialogResult == JOptionPane.YES_OPTION)
					removePatient(tID.getText());
			}
		});
	}
	
	private void removePatient(String id){
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
			sql = "DELETE FROM `patient` WHERE `id` = "+id+"";
			stmt.executeUpdate(sql);
			con.close();
			discardField();
			ActionEvent e1 = new ActionEvent(id,-1,"");
			patientController.fireRemoveRowPatientSearchTablePerformed(e1);
			JOptionPane.showMessageDialog(null, "Patient detail removing succeeded.", "Success", JOptionPane.INFORMATION_MESSAGE);
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
	
	private void discardField(){
		tID.setText("");
		tName.setText("");
		tNIC.setText("");
		tAddress.setText("");
		bgGender.clearSelection();
		bgStatus.clearSelection();
		tBirthday.setText("");
		tTp.setText("");
		tMedicalHitory.setText("");
	}
	
}
