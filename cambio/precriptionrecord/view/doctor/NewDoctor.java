package cambio.precriptionrecord.view.doctor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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

import cambio.precriptionrecord.controller.DoctorController;
import cambio.precriptionrecord.model.doctor.Doctor;
import cambio.precriptionrecord.util.DBConnection;
import cambio.precriptionrecord.util.DatePicker;

public class NewDoctor extends JInternalFrame{
	private GridBagLayout gridbag;
	
	private JTextField tName;
	private JTextField tNIC;
	private JTextField tBirthday;
	private JTextField tTp;
	private JTextField tRegNumber;
	private JTextField tSpeciality;
	private JTextArea tJobHistory;
	private JRadioButton rbMale;
	private JRadioButton rbFemale;
	private JButton bBirthday;
	private JButton bProfilePicAdd;
	private JButton bProfilePicDelete; 
	private ButtonGroup bgGender;
	
	private JButton bSave;
	private JButton bDiscard;
	private DoctorController doctorController;
	public NewDoctor(DoctorController doctorController){
		this.doctorController = doctorController;
		JDesktopPane desktopPane = new JDesktopPane();
		setTitle("Add New Doctor");
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
		JLabel lRegNumber = new JLabel("Reg: Number");
		JLabel lSpeciality = new JLabel("Speciality");
		JLabel ltp = new JLabel("Telephone");
		JLabel lProfilePicture = new JLabel();
		JLabel lJobHistory = new JLabel("Job History");
		JLabel lLeft = new JLabel();
		
		lName.setText("<html>Name <font color='red'> *</font></html>");
		lNIC.setText("<html>NIC <font color='red'> *</font></html>");
		lRegNumber.setText("<html>Reg:Number <font color='red'> *</font></html>");
		lSpeciality.setText("<html>Speciality <font color='red'> *</font></html>");
		lBirthday.setText("<html>Birthday <font color='red'> *</font></html>");
		lGender.setText("<html>Gender <font color='red'> *</font></html>");
		
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
		
		/*Label-regNumber*/
		constraintsLabel.insets = new Insets(110, 0, 22, 40);
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 0;
		gridbag.setConstraints(lRegNumber, constraintsLabel);
		add(lRegNumber);
		
		/*Label-Speciality*/
		constraintsLabel.insets = new Insets(165, 0, 22, 40);
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 0;
		gridbag.setConstraints(lSpeciality, constraintsLabel);
		add(lSpeciality);
		
		/*Label-Gender*/
		constraintsLabel.insets = new Insets(10, 0, 22, 0);
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 1;
		gridbag.setConstraints(lGender, constraintsLabel);
		add(lGender);
		
		/*Label-Married Status*/
		
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
		
		/*Label working History*/
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 5;
		gridbag.setConstraints(lJobHistory, constraintsLabel);
		add(lJobHistory);	
		
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
		tRegNumber = new JTextField(20);
		tSpeciality = new JTextField(20);
		tBirthday= new JTextField(20);
		tTp = new JTextField(20);
		tJobHistory = new JTextArea(3,20);
		bBirthday = new JButton(":)");
		rbMale = new JRadioButton("Male");
		rbFemale = new JRadioButton("Female");
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
		
		/*text field - register number*/
		constraintsField.insets = new Insets(100, 0, 22, 40);
		constraintsField.gridx = 1;
		constraintsField.gridy = 0;		
		gridbag.setConstraints(tRegNumber, constraintsField);
		add(tRegNumber);
		
		/*text field - Specialty*/
		constraintsField.insets = new Insets(150, 0, 22, 40);
		constraintsField.gridx = 1;
		constraintsField.gridy = 0;		
		gridbag.setConstraints(tSpeciality, constraintsField);
		add(tSpeciality);
		
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
		JScrollPane jspHealthHistory = new JScrollPane(tJobHistory,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
		
		/*Action Performed*/
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
					String selDate = new DatePicker((NewDoctor)(bBirthday.getParent().getParent().getParent().getParent())).setPickedDate();
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
	
	private void clearField(){
		tName.setText("");
		tNIC.setText("");
		tRegNumber.setText("");
		tSpeciality.setText("");
		bgGender.clearSelection();
		tBirthday.setText("");
		tTp.setText("");
		tJobHistory.setText("");
	}
	
	private void saveButtonDisabled(){
		bSave.setEnabled(false);
		new SaveButtonTextFieldCondtion(tName);		
		new SaveButtonTextFieldCondtion(tBirthday);	
		new SaveButtonRadioButtonCondtion(rbMale);
		new SaveButtonRadioButtonCondtion(rbFemale);
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
		if(!tName.getText().equals("") && 
				!tNIC.getText().equals("") && 
				!tRegNumber.getText().equals("") && 
				!tSpeciality.getText().equals("") && 
				(rbMale.isSelected()||rbFemale.isSelected()) &&
				!tBirthday.getText().equals("")){
			bSave.setEnabled(true);
		}
		else{
			bSave.setEnabled(false);
		}
	}
	
	private void saveButtonAction(){
		bSave.addActionListener(new ActionListener (){

			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like to Save Doctor?", "Warning",0);
				if(dialogResult == JOptionPane.YES_OPTION){
					String name = tName.getText();
					String nic = tNIC.getText();
					String regNumber = tRegNumber.getText();
					String speciality = tSpeciality.getText();
					String gender;
					
					if(rbMale.isSelected())
						gender = "Male";
					else 
						gender = "Female";
					
					String birthday = tBirthday.getText();
					String tp = tTp.getText();
					String jobHistory = tJobHistory.getText();
					
					Doctor doctor = new Doctor();
					doctor.setName(name);
					doctor.setNic(nic);
					doctor.setRegNumber(regNumber);
					doctor.setSpeiality(speciality);
					doctor.setGender(gender);
					doctor.setBirthday(birthday);
					doctor.setTp(tp);
					doctor.setJobHistory(jobHistory);
					
					saveDoctor(doctor);			
				}
			}
			
		});
	}
	
	private void saveDoctor(Doctor doctor){
		System.out.println("save doctor");
		DBConnection dbCon = new DBConnection();
		Connection connection = dbCon.getConnection();
		Statement stmt = null;
		String sql = null;
		
		try{
			stmt = connection.createStatement();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		try{
			sql = "INSERT INTO `doctor` (`id`,"
					+ " `name`,"
					+ " `nic`,"
					+ " `regNumber`,"
					+ " `speciality`, "
					+ "`gender`,"
					+ " `birthday`, "
					+ "`telephone`,"
					+ " `jobHistory`,"
					+ " `profilePicture`) VALUES (NULL,"
					+ " '"+doctor.getName()+"',"
					+ " '"+doctor.getNic()+"',"
					+ " '"+doctor.getRegNumber()+"',"
					+ " '"+doctor.getSpeiality()+"',"
					+ " '"+doctor.getGender()+"',"
					+ " '"+doctor.getBirthday()+"',"
					+ " '"+doctor.getTp()+"',"
					+ " '"+doctor.getJobHistory()+"',"
					+ " NULL)";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			connection.close();
//			clearField();
			JOptionPane.showMessageDialog(null, "Doctor detail saving succeeded.", "Success", JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
