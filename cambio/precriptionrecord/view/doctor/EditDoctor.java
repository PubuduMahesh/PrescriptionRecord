package cambio.precriptionrecord.view.doctor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cambio.precriptionrecord.controller.DoctorController;
import cambio.precriptionrecord.model.doctor.Doctor;
import cambio.precriptionrecord.util.DBConnection;
import cambio.precriptionrecord.util.DatePicker;
import java.sql.Connection;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
public class EditDoctor extends JInternalFrame{
	private GridBagLayout gridbag;

	private JTextField tID;
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
	private JButton bClear;
	private DoctorController doctorController;

	public EditDoctor(DoctorController doctorController){
		this.doctorController = doctorController;

		JDesktopPane desktopPane = new JDesktopPane();
		setTitle("Edit Doctor");
		setPreferredSize(new Dimension(740,665));
		setClosable(true);
		setVisible(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		gridbag = new GridBagLayout();
		setLayout(gridbag);	

		createLayout();

		/*table row click action performed*/
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
		DoctorSearchPanel searchPanel = new DoctorSearchPanel(doctorController);		
		constraintsSearch.gridx = 0;
		constraintsSearch.gridy = 0;
		constraintsSearch.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(searchPanel,constraintsSearch);
		add(searchPanel);
	}

	private void addLabel(){
		GridBagConstraints constraintsLabel = new GridBagConstraints();
		constraintsLabel.anchor = GridBagConstraints.NORTHWEST;

		JLabel lID = new JLabel("ID");
		JLabel lName = new JLabel("Name");
		JLabel lNIC = new JLabel("NIC");
		JLabel lRegNumber = new JLabel("Reg:Number");
		JLabel lSpeciality = new JLabel("Speciality");
		JLabel lGender = new JLabel("Gender");
		JLabel lBirthday = new JLabel("Birthday");
		JLabel ltp = new JLabel("Telephone");
		JLabel lJobHistory = new JLabel("Job History");
		JLabel lProfilePicture = new JLabel();

		lName.setText("<html>Name <font color='red'> *</font></html>");
		lNIC.setText("<html>NIC <font color='red'> *</font></html>");
		lRegNumber.setText("<html>Reg:Number <font color='red'> *</font></html>");
		lSpeciality.setText("<html>Speciality <font color='red'> *</font></html>");
		lBirthday.setText("<html>Birthday <font color='red'> *</font></html>");
		lGender.setText("<html>Gender <font color='red'> *</font></html>");


		/*Label-ID*/
		constraintsLabel.insets = new Insets(0, 0, 0, 0);
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 1;
		gridbag.setConstraints(lID, constraintsLabel);
		add(lID);
		
		/*Label-Name*/
		constraintsLabel.insets = new Insets(35, 0, 0, 0);
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 1;
		gridbag.setConstraints(lName, constraintsLabel);
		add(lName);

		/*Label-NIC*/
		constraintsLabel.insets = new Insets(70, 0, 10, 40);
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 1;
		gridbag.setConstraints(lNIC, constraintsLabel);
		add(lNIC);

		/*Label-RegNumbert*/
		constraintsLabel.insets = new Insets(105, 0, 10, 40);
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 1;
		gridbag.setConstraints(lRegNumber, constraintsLabel);
		add(lRegNumber);


		/*Label-Speciality*/
		constraintsLabel.insets = new Insets(140, 0, 10, 40);
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 1;
		gridbag.setConstraints(lSpeciality, constraintsLabel);
		add(lSpeciality);

		/*Label-Gender*/
		constraintsLabel.insets = new Insets(0, 0, 10, 0);
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 2;
		gridbag.setConstraints(lGender, constraintsLabel);
		add(lGender);

		/*Label-Married Status*/
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 3;
		gridbag.setConstraints(lBirthday, constraintsLabel);
		add(lBirthday);

		/*Label-Birthday*/
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 4;
		gridbag.setConstraints(ltp, constraintsLabel);
		add(ltp);	

		/*Label Job History*/
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 5;
		gridbag.setConstraints(lJobHistory, constraintsLabel);
		add(lJobHistory);	

		/*Label-profile picture*/
		constraintsLabel.insets = new Insets(0, 380, 10, 40);
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
		
		tID = new JTextField(5);
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
		bClear = new JButton("Clear");
		
		/*text field - id*/
		tID.setEnabled(false);
		constraintsField.gridx = 0;
		constraintsField.gridy = 1;
		constraintsField.insets = new Insets(0, 100, 0, 0);
		gridbag.setConstraints(tID, constraintsField);
		add(tID);
		
		/*text field - name*/
		constraintsField.gridx = 0;
		constraintsField.gridy = 1;
		constraintsField.insets = new Insets(35, 100, 0, 0);
		gridbag.setConstraints(tName, constraintsField);
		add(tName);

		/*text field - nic*/
		constraintsField.gridx = 0;
		constraintsField.gridy = 1;
		constraintsField.insets = new Insets(70,100, 10, 40);
		gridbag.setConstraints(tNIC, constraintsField);
		add(tNIC);

		/*text field - regNumber*/
		constraintsField.insets = new Insets(105, 100, 10, 40);
		constraintsField.gridx = 0;
		constraintsField.gridy = 1;		
		gridbag.setConstraints(tRegNumber, constraintsField);
		add(tRegNumber);

		/*text field - Speciality*/
		constraintsField.insets = new Insets(140, 100, 10, 40);
		constraintsField.gridx = 0;
		constraintsField.gridy = 1;		
		gridbag.setConstraints(tSpeciality, constraintsField);
		add(tSpeciality);

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

		/*text field-birthday*/
		constraintsField.insets = new Insets(0, 100, 0, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 3;
		gridbag.setConstraints(tBirthday, constraintsField);
		add(tBirthday);

		/*button-birthday*/
		constraintsField.insets = new Insets(0, 330, 10, 40);
		bBirthday.setPreferredSize(new Dimension(25,20));
		constraintsField.gridx = 0;
		constraintsField.gridy = 3;
		gridbag.setConstraints(bBirthday, constraintsField);
		add(bBirthday);

		/*text field- telephone*/
		constraintsField.insets = new Insets(0, 100, 10, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 4;
		gridbag.setConstraints(tTp, constraintsField);
		add(tTp);		

		/*text job history*/
		constraintsField.insets = new Insets(0, 100, 10, 0);
		constraintsField.gridx = 0;
		constraintsField.gridy = 5;
		JScrollPane jspJobHistory = new JScrollPane(tJobHistory,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gridbag.setConstraints(jspJobHistory, constraintsField);
		add(jspJobHistory);

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
		gridbag.setConstraints(bClear, constraintsField);
		add(bClear);		

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


		/*Action Listeners*/
		birthdayButtonAction();
		discardButtonAction();
		saveButtonDisabled();
		saveButtonAction();
	}

	private void mouseClickRow(){
		doctorController.registerRowClickListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof Doctor) {
					Doctor doctor = (Doctor)e.getSource();
					setDoctorField(doctor);
				}

			}
		});
	}

	private void setDoctorField(Doctor doctor){
		tID.setText(doctor.getId());
		tName.setText(doctor.getName());
		tNIC.setText(doctor.getNic());
		tRegNumber.setText(doctor.getRegNumber());
		tSpeciality.setText(doctor.getSpeiality());
		if(doctor.getGender().equals("Male"))
			rbMale.setSelected(true);
		else
			rbFemale.setSelected(true);
		tBirthday.setText(doctor.getBirthday());
		tTp.setText(doctor.getTp());
		tJobHistory.setText(doctor.getJobHistory());
	}

	private void birthdayButtonAction(){
		bBirthday.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String selDate = new DatePicker((EditDoctor)(bBirthday.getParent().getParent().getParent().getParent())).setPickedDate();
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
		bClear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){
				clearField();
			}
		});
	}

	private void clearField(){
		tID.setText("");
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
					Doctor doctor = new Doctor();
					doctor.setId(tID.getText());
					doctor.setName(tName.getText());
					doctor.setNic(tNIC.getText());
					doctor.setRegNumber(tRegNumber.getText());
					doctor.setSpeiality(tSpeciality.getText());				
					doctor.setBirthday(tBirthday.getText());
					doctor.setTp(tTp.getText());
					doctor.setJobHistory( tJobHistory.getText());
					if(rbMale.isSelected())
						doctor.setGender("Male");
					else 
						doctor.setGender("Female");	

					saveDoctor(doctor);	
					
				}
				
			}

		});
		
	}

	private void saveDoctor(Doctor doctor){
		DBConnection dbCon = new DBConnection();
		Connection connection = dbCon.getConnection();
		Statement stmt = null;
		String sql;

		try{
			stmt = connection.createStatement();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		try{
			sql = "UPDATE `doctor` SET"
					+"`name` = '"+doctor.getName()+"',"
					+ "`nic` = '"+doctor.getNic()+"',"
					+ "`regNumber` = '"+doctor.getRegNumber()+"',"
					+ "`speciality` = '"+doctor.getSpeiality()+"',"
					+ "`gender` = '"+doctor.getGender()+"',"
					+ "`birthday` = '"+doctor.getBirthday()+"',"
					+ "`telephone` = '"+doctor.getTp()+"',"
					+ "`jobHistory` = '"+doctor.getJobHistory()+"',"
					+ "`profilePicture` = NULL "
					+ " WHERE "
					+ "`doctor`.`id` = '"+doctor.getId()+"'";
			stmt.executeUpdate(sql);
			connection.close();
			clearField();
			ActionEvent e = new ActionEvent(doctor,-1,"");
			doctorController.fireUpdateRowDoctorSearchTablePerformed(e);
			JOptionPane.showMessageDialog(null, "Doctor detail saving succeeded.", "Success", JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
