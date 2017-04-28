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
import cambio.precriptionrecord.model.patient.Patient;
public class EditDoctor extends JInternalFrame{
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
//		addSearchPanel();		
		addLabel();
		addField();
	}
	
	private void addSearchPanel(){
		GridBagConstraints constraintsSearch = new GridBagConstraints();
		constraintsSearch.anchor = GridBagConstraints.NORTHWEST;
		JLabel tempLable = new JLabel("Temp");
		tempLable.setPreferredSize(new Dimension(650, 10));
		tempLable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(tempLable);
//		DoctorSearchPanel searchPanel = new DoctorSearchPanel(doctorController);		
		constraintsSearch.gridx = 0;
		constraintsSearch.gridy = 0;
		constraintsSearch.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(tempLable,constraintsSearch);
		add(tempLable);
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
		
		tName = new JTextField(20);
		tNIC = new JTextField(20);
		tRegNumber = new JTextField();
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
		JScrollPane jspAddress = new JScrollPane(tRegNumber,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
		JScrollPane jspHealthHistory = new JScrollPane(tJobHistory,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
		
		
		/*Action Listeners*/
		/*birthdayButtonAction();
		discardButtonAction();
		saveButtonDisabled();
		saveButtonAction();*/
	}
	
	private void mouseClickRow(){
		doctorController.registerRowClickListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof Patient) {
					Doctor doctor = (Doctor)e.getSource();
					setDoctorField(doctor);
				}

			}
		});
	}
	
	private void setDoctorField(Doctor doctor){
		
	}
}
