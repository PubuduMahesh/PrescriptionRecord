package cambio.precriptionrecord.view.patient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.omg.CORBA.INITIALIZE;

import javax.swing .JTextArea;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import cambio.precriptionrecord.util.DBConnection;
import cambio.precriptionrecord.util.DatePicker;
import cambio.precriptionrecord.model.Patient;


public class NewPatient extends JInternalFrame{
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
	
	private JButton bSave;
	private JButton bDiscard;
	
	public NewPatient(){		
		JDesktopPane desktopPane = new JDesktopPane();
		setTitle("Add New Patient");
		setPreferredSize(new Dimension(740,665));
		setClosable(true);
		setVisible(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		gridbag = new GridBagLayout();
		setLayout(gridbag);	
		
		createLayout();
			
		desktopPane.add(this);
	}
	
	private void createLayout(){
		addLabel();
		addField();
	}
	
	private void addLabel(){
		GridBagConstraints constraintsLabel = new GridBagConstraints();
		constraintsLabel.anchor = GridBagConstraints.NORTHWEST;
		
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
		lNIC.setText("<html>NIC <font color='red'> *</font></html>");
		lBirthday.setText("<html>Birthday <font color='red'> *</font></html>");
		lGender.setText("<html>Gender <font color='red'> *</font></html>");
		lStatus.setText("<html>Martial Status <font color='red'> *</font></font>");
		
		/*Label-Name*/
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 0;
		gridbag.setConstraints(lName, constraintsLabel);
		add(lName);
		
		/*Label-nic*/
		constraintsLabel.insets = new Insets(55, 0, 22, 40);
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 0;
		gridbag.setConstraints(lNIC, constraintsLabel);
		add(lNIC);
		
		/*Label-Address*/
		constraintsLabel.insets = new Insets(110, 0, 22, 40);
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 0;
		gridbag.setConstraints(lAddress, constraintsLabel);
		add(lAddress);
		
		/*Label-Gender*/
		constraintsLabel.insets = new Insets(10, 0, 22, 0);
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 1;
		gridbag.setConstraints(lGender, constraintsLabel);
		add(lGender);
		
		/*Label-Married Status*/
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 2;
		gridbag.setConstraints(lStatus, constraintsLabel);
		add(lStatus);
		
		/*Label-Birthday*/
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 3;
		gridbag.setConstraints(lBirthday, constraintsLabel);
		add(lBirthday);
		
		
		/*Label-Telephone*/
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 4;
		gridbag.setConstraints(ltp, constraintsLabel);
		add(ltp);	
		
		/*Label Medical History*/
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 5;
		gridbag.setConstraints(lMedicalHistory, constraintsLabel);
		add(lMedicalHistory);	
		
		/*Label-profile picture*/
		constraintsLabel.anchor = GridBagConstraints.NORTH;
		lProfilePicture.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lProfilePicture.setPreferredSize(new Dimension(135,135));
		constraintsLabel.gridx = 3;
		constraintsLabel.gridy = 0;
		constraintsLabel.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(lProfilePicture, constraintsLabel);
		add(lProfilePicture);
		lProfilePicture.setIcon(new javax.swing.ImageIcon("./Image/Empty_profile_picture.gif"));

		/*Label-Left arrangement*/
		lLeft.setPreferredSize(new Dimension(115,100));
		constraintsLabel.gridx = 4;
		constraintsLabel.gridy = 0;
		gridbag.setConstraints(lLeft,constraintsLabel);
		add(lLeft);	
	}
	
	private void addField(){
		GridBagConstraints constraintsField = new GridBagConstraints();
		constraintsField.anchor = GridBagConstraints.NORTHWEST;
		constraintsField.ipadx = 5;
		constraintsField.ipady = 10;
		
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
		
		/*text field - name*/
		constraintsField.gridx = 1;
		constraintsField.gridy = 0;
		gridbag.setConstraints(tName, constraintsField);
		add(tName);
		
		/*text field - NIC*/
		constraintsField.gridx = 1;
		constraintsField.gridy = 0;
		constraintsField.insets = new Insets(50, 0, 22, 40);
		gridbag.setConstraints(tNIC, constraintsField);
		add(tNIC);
		
		/*text field - address*/
		constraintsField.insets = new Insets(100, 0, 22, 40);
		constraintsField.gridx = 1;
		constraintsField.gridy = 0;
		JScrollPane jspAddress = new JScrollPane(tAddress,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gridbag.setConstraints(jspAddress, constraintsField);
		add(jspAddress);
		
		/*radio button- male*/
		constraintsField.insets = new Insets(0, 0, 0, 0);
		constraintsField.gridx = 1;
		constraintsField.gridy = 1;
		gridbag.setConstraints(rbMale, constraintsField);
		add(rbMale);
		
		/*radio button-female*/
		constraintsField.insets = new Insets(0, 70, 22, 40);
		constraintsField.gridx = 1;
		constraintsField.gridy = 1;
		gridbag.setConstraints(rbFemale, constraintsField);
		add(rbFemale);
		
		/*radio button - single*/
		constraintsField.insets = new Insets(0, 0, 0, 0);
		constraintsField.gridx = 1;
		constraintsField.gridy = 2;
		gridbag.setConstraints(rbSingle, constraintsField);
		add(rbSingle);
		
		/*radio button-married*/
		constraintsField.insets = new Insets(0, 70, 22, 40);
		constraintsField.gridx = 1;
		constraintsField.gridy = 2;
		gridbag.setConstraints(rbMarried, constraintsField);
		add(rbMarried);
		
		/*radio button divorced.*/
		constraintsField.insets = new Insets(0, 150, 22, 40);
		constraintsField.gridx = 1;
		constraintsField.gridy = 2;
		gridbag.setConstraints(rbDivorced, constraintsField);
		add(rbDivorced);
		
		/*text field-birthday*/
		constraintsField.insets = new Insets(0, 0, 0, 0);
		constraintsField.gridx = 1;
		constraintsField.gridy = 3;
		gridbag.setConstraints(tBirthday, constraintsField);
		add(tBirthday);
		
		/*button-birthday*/
		constraintsField.insets = new Insets(0, 230, 22, 40);
		bBirthday.setPreferredSize(new Dimension(25,20));
		constraintsField.gridx = 1;
		constraintsField.gridy = 3;
		gridbag.setConstraints(bBirthday, constraintsField);
		add(bBirthday);
		
		/*text field- telephone*/
		constraintsField.insets = new Insets(0, 0, 22, 0);
		constraintsField.gridx = 1;
		constraintsField.gridy = 4;
		gridbag.setConstraints(tTp, constraintsField);
		add(tTp);		
		
		/*text field-health history*/
		constraintsField.gridx = 1;
		constraintsField.gridy = 5;
		JScrollPane jspHealthHistory = new JScrollPane(tMedicalHitory,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gridbag.setConstraints(jspHealthHistory, constraintsField);
		add(jspHealthHistory);
		
		/*button-save*/
		constraintsField.gridx = 3;
		constraintsField.gridy = 6;
		gridbag.setConstraints(bSave, constraintsField);
		add(bSave);
		
		/*button - discard*/
		constraintsField.insets = new Insets(0, 70, 0, 0);
		constraintsField.gridx = 3;
		constraintsField.gridy = 6;
		gridbag.setConstraints(bDiscard, constraintsField);
		add(bDiscard);		

		/*button-profile picture add*/
		constraintsField.insets = new Insets(150, 10, 0, 0);
		bProfilePicAdd.setPreferredSize(new Dimension(60,15));
		bProfilePicAdd.setFont(new Font("seif",Font.ITALIC,10));
		constraintsField.gridx = 3;
		constraintsField.gridy = 0;
		gridbag.setConstraints(bProfilePicAdd, constraintsField);
		add(bProfilePicAdd);
		
		/*button-profile picture delete*/
		constraintsField.insets = new Insets(150, 80, 0, 0);
		bProfilePicDelete.setPreferredSize(new Dimension(60,15));
		bProfilePicDelete.setFont(new Font("seif",Font.ITALIC,10));
		constraintsField.gridx = 3;
		constraintsField.gridy = 0;
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
					Date birthday = new SimpleDateFormat("yyyy/MM/dd").parse(selDate);//convert the selected Date in to the "Date" type
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
				clearField();			
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
				
				int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Save Patient?","Warning",0);
				if(dialogResult == JOptionPane.YES_OPTION){
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
					
					savePatient(new Patient(null,name,nic,address,gender,status,birthday,telephone,medicalHistory));
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
			sql = "INSERT INTO `patient` (`id`,"
					+ " `name`,"
					+ " `nic`,"
					+ " `address`,"
					+ " `gender`, `status`,"
					+ " `birthday`, `telephone`,"
					+ " `profilePicture`,"
					+ " `healthDescription`) VALUES (NULL,"
					+ " '"+patient.getName()+"',"
					+ " '"+patient.getNIC()+"',"
					+ " '"+patient.getAddress()+"',"
					+ " '"+patient.getGender()+"',"
					+ " '"+patient.getStatus()+"',"
					+ " '"+patient.getBirthday()+"',"
					+ " '"+patient.getTp()+"',"
					+ " NULL,"
					+ " '"+patient.getMedicalHistory()+"')";			
			stmt.executeUpdate(sql);
			con.close();
			clearField();
			JOptionPane.showMessageDialog(null, "Patient detail saving succeeded.", "Success", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Patient detail saving failed", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void clearField(){
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
