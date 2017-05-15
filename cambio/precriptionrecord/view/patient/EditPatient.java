package cambio.precriptionrecord.view.patient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Blob;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cambio.precriptionrecord.controller.CommonController;
import cambio.precriptionrecord.controller.PatientController;
import cambio.precriptionrecord.model.patient.Patient;
import cambio.precriptionrecord.util.DBConnection;
import cambio.precriptionrecord.util.DatePicker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditPatient extends JInternalFrame {

    private final GridBagLayout gridbag;
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
    private ButtonGroup bgGender;
    private ButtonGroup bgStatus;
    private JTextField tID;
    private JButton bDelete;
    private JButton bUpdatge;
    private JButton bClear;
    ProfilePicture profilePicture;

    private final PatientController patientController;
    private final CommonController commonController;

    public EditPatient() {
        this.patientController = new PatientController();
        this.commonController = new CommonController();
        setTitle("Update Patient");
        setPreferredSize(new Dimension(740, 665));
//        setMinimumSize(new Dimension(740, 665));
        setClosable(true);
        setVisible(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        setFrameIcon(new javax.swing.ImageIcon("src/cambio/Image/addNewPatient.jpg"));
        gridbag = new GridBagLayout();
        setLayout(gridbag);
        createLayout();
        /*Mouse click action perform*/
        mouseClickRow();
    }

    private void createLayout() {
        addSearchPanel();
        addLabel();
        addField();
    }

    private void addSearchPanel() {
        GridBagConstraints constraintsSearch = new GridBagConstraints();
        constraintsSearch.anchor = GridBagConstraints.NORTHWEST;
        int tbWidth = 690;
        int tbHeight = 130;
        PatientSearchPanel searchPanel = new PatientSearchPanel(patientController, tbWidth, tbHeight, commonController);
        constraintsSearch.gridx = 0;
        constraintsSearch.gridy = 0;
        constraintsSearch.insets = new Insets(0, 0, 0, 0);
        gridbag.setConstraints(searchPanel, constraintsSearch);
        add(searchPanel);

    }

    private void addLabel() {
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
        JLabel lMedicalHistory = new JLabel("Medical History");

        lName.setText("<html>Name <font color='red'> *</font></html>");
        lBirthday.setText("<html>Birthday <font color='red'> *</font></html>");
        lGender.setText("<html>Gender <font color='red'> *</font></html>");
        lStatus.setText("<html>Marital Status <font color='red'> *</font></font>");

        /*Label-ID*/
        constraintsLabel.gridx = 0;
        constraintsLabel.gridy = 1;
        constraintsLabel.insets = new Insets(10, 0, 0, 0);
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
        constraintsLabel.insets = new Insets(115, 0, 10, 40);
        constraintsLabel.gridx = 0;
        constraintsLabel.gridy = 1;
        gridbag.setConstraints(lAddress, constraintsLabel);
        add(lAddress);

        /*Label-Gender*/
        constraintsLabel.insets = new Insets(5, 0, 10, 0);
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
    }

    private void addField() {
        GridBagConstraints constraintsField = new GridBagConstraints();
        constraintsField.anchor = GridBagConstraints.NORTHWEST;

        tID = new JTextField(8);
        tName = new JTextField(20);
        tNIC = new JTextField(20);
        tAddress = new JTextArea(3, 20);
        tBirthday = new JTextField(20);
        tTp = new JTextField(20);
        tMedicalHitory = new JTextArea(3, 20);
        bBirthday = new JButton();
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        rbSingle = new JRadioButton("Single");
        rbMarried = new JRadioButton("Married");
        rbDivorced = new JRadioButton("Divorced");

        bUpdatge = new JButton("Update");
        bClear = new JButton("Clear");
        bDelete = new JButton("Delete");

        /*text field - id*/
        tID.setEditable(false);
        constraintsField.gridx = 0;
        constraintsField.gridy = 1;
        constraintsField.insets = new Insets(10, 110, 0, 0);
        gridbag.setConstraints(tID, constraintsField);
        add(tID);

        /*text field - name*/
        constraintsField.gridx = 0;
        constraintsField.gridy = 1;
        constraintsField.insets = new Insets(40, 110, 0, 0);
        gridbag.setConstraints(tName, constraintsField);
        add(tName);

        /*text field - nic*/
        constraintsField.gridx = 0;
        constraintsField.gridy = 1;
        constraintsField.insets = new Insets(70, 110, 10, 40);
        gridbag.setConstraints(tNIC, constraintsField);
        add(tNIC);

        /*text field - address*/
        constraintsField.insets = new Insets(100, 110, 10, 40);
        constraintsField.gridx = 0;
        constraintsField.gridy = 1;
        JScrollPane jspAddress = new JScrollPane(tAddress, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        gridbag.setConstraints(jspAddress, constraintsField);
        add(jspAddress);

        /*radio button- male*/
        constraintsField.insets = new Insets(0, 110, 0, 0);
        constraintsField.gridx = 0;
        constraintsField.gridy = 2;
        gridbag.setConstraints(rbMale, constraintsField);
        add(rbMale);

        /*radio button-female*/
        constraintsField.insets = new Insets(0, 180, 10, 40);
        constraintsField.gridx = 0;
        constraintsField.gridy = 2;
        gridbag.setConstraints(rbFemale, constraintsField);
        add(rbFemale);

        /*radio button - single*/
        constraintsField.insets = new Insets(0, 110, 0, 0);
        constraintsField.gridx = 0;
        constraintsField.gridy = 3;
        gridbag.setConstraints(rbSingle, constraintsField);
        add(rbSingle);

        /*radio button-married*/
        constraintsField.insets = new Insets(0, 180, 10, 40);
        constraintsField.gridx = 0;
        constraintsField.gridy = 3;
        gridbag.setConstraints(rbMarried, constraintsField);
        add(rbMarried);

        /*radio button divorced.*/
        constraintsField.insets = new Insets(0, 260, 10, 40);
        constraintsField.gridx = 0;
        constraintsField.gridy = 3;
        gridbag.setConstraints(rbDivorced, constraintsField);
        add(rbDivorced);

        /*text field-birthday*/
        constraintsField.insets = new Insets(5, 110, 0, 0);
        constraintsField.gridx = 0;
        constraintsField.gridy = 4;
        gridbag.setConstraints(tBirthday, constraintsField);
        add(tBirthday);

        /*button-birthday*/
        bBirthday.setIcon(new javax.swing.ImageIcon("src/cambio/Image/calendarIcon.png"));
        bBirthday.setBorderPainted(false);
        bBirthday.setContentAreaFilled(false);
        constraintsField.insets = new Insets(0, 340, 0, 0);
        bBirthday.setPreferredSize(new Dimension(23, 25));
        bBirthday.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        constraintsField.gridx = 0;
        constraintsField.gridy = 4;
        gridbag.setConstraints(bBirthday, constraintsField);
        add(bBirthday);

        /*text field- telephone*/
        constraintsField.insets = new Insets(0, 110, 10, 0);
        constraintsField.gridx = 0;
        constraintsField.gridy = 5;
        gridbag.setConstraints(tTp, constraintsField);
        add(tTp);

        /*text field-health history*/
        constraintsField.insets = new Insets(0, 110, 10, 0);
        constraintsField.gridx = 0;
        constraintsField.gridy = 6;
        JScrollPane jspHealthHistory = new JScrollPane(tMedicalHitory, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        gridbag.setConstraints(jspHealthHistory, constraintsField);
        add(jspHealthHistory);

        /*button-update*/
        bUpdatge.setIcon(new javax.swing.ImageIcon("src/cambio/Image/saveicon.png"));
        constraintsField.insets = new Insets(0, 350, 10, 0);
        constraintsField.gridx = 0;
        constraintsField.gridy = 7;
        gridbag.setConstraints(bUpdatge, constraintsField);
        add(bUpdatge);

        /*button-Delete*/
        bDelete.setIcon(new javax.swing.ImageIcon("src/cambio/Image/delete.png"));
        constraintsField.insets = new Insets(0, 450, 10, 0);
        constraintsField.gridx = 0;
        constraintsField.gridy = 7;
        gridbag.setConstraints(bDelete, constraintsField);
        add(bDelete);

        /*button - clear*/
        bClear.setIcon(new javax.swing.ImageIcon("src/cambio/Image/clearicon.png"));
        constraintsField.insets = new Insets(0, 550, 0, 0);
        constraintsField.gridx = 0;
        constraintsField.gridy = 7;
        gridbag.setConstraints(bClear, constraintsField);
        add(bClear);

        /*profile picture*/
        profilePicture = new ProfilePicture();
        constraintsField.insets = new Insets(0, 210, 10, 40);
        constraintsField.anchor = GridBagConstraints.NORTH;
        constraintsField.gridx = 0;
        constraintsField.gridy = 1;
        gridbag.setConstraints(profilePicture, constraintsField);
        add(profilePicture);

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
        clearButtonAction();
        saveButtonDisabled();
        updateButtonAction();
        deleteButtonAction();
    }

    private void birthdayButtonAction() {
        bBirthday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selDate = new DatePicker((EditPatient) (bBirthday.getParent().getParent().getParent().getParent())).setPickedDate();
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

    private void clearButtonAction() {
        bClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearField();
            }

        });
    }

    private void saveButtonDisabled() {
        bUpdatge.setEnabled(false);
        bDelete.setEnabled(false);
        new UpdateDeleteButtonsTextFieldCondtion(tName);
        new UpdateDeleteButtonsTextFieldCondtion(tBirthday);
        new RadioButtonRadioButtonCondtion(rbMale);
        new RadioButtonRadioButtonCondtion(rbFemale);
        new RadioButtonRadioButtonCondtion(rbSingle);
        new RadioButtonRadioButtonCondtion(rbMarried);
        new RadioButtonRadioButtonCondtion(rbDivorced);

    }

    private class UpdateDeleteButtonsTextFieldCondtion {

        public UpdateDeleteButtonsTextFieldCondtion(JTextField textField) {
            textField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {
                    enableSaveButton();
                    enableRemoveButton();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    enableSaveButton();
                    enableRemoveButton();
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    enableSaveButton();
                    enableRemoveButton();
                }

            });
        }
    }

    private class RadioButtonRadioButtonCondtion {

        public RadioButtonRadioButtonCondtion(JRadioButton radioButton) {
            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableSaveButton();
                }
            });
        }
    }

    private void enableSaveButton() {
        if (!tName.getText().equals("") && 
            !tBirthday.getText().equals("") && 
            (rbMale.isSelected() || rbFemale.isSelected()) && 
            (rbSingle.isSelected()|| rbMarried.isSelected() || rbDivorced.isSelected())) {
            bUpdatge.setEnabled(true);
        } else {
            bUpdatge.setEnabled(false);
        }
    }

    private void enableRemoveButton() {
        if (!tID.getText().equals("")) {
            bDelete.setEnabled(true);
        } else {
            bDelete.setEnabled(false);
        }

    }

    private void updateButtonAction() {
        bUpdatge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Patient patient = new Patient();
                patient.setID(tID.getText());
                patient.setName(tName.getText());
                patient.setNIC(tNIC.getText());
                patient.setAddress(tAddress.getText());
                patient.setBirthday(tBirthday.getText());
                patient.setTp(tTp.getText());
                patient.setMedicalHistory(tMedicalHitory.getText());
                patient.setProfilePicPath(profilePicture.getProfilePicturePath());

                if (rbMale.isSelected()) {
                    patient.setGender("Male");
                } else {
                    patient.setGender("Female");
                }
                if (rbSingle.isSelected()) {
                    patient.setStatus("Single");
                } else if (rbMarried.isSelected()) {
                    patient.setStatus("Married");
                } else {
                    patient.setStatus("Divorce");
                }
                //patient.setProfilePicBLOB(profilePicture.getProfilePicBlob());
                savePatient(patient);

            }
        });
    }

    private void savePatient(Patient patient) {
        DBConnection dbCon = new DBConnection();
        Connection connection = dbCon.getConnection();
        PreparedStatement statement = null;
        FileInputStream inputStream = null;

        try {
            if (!patient.getID().equals("")) {
                statement = connection.prepareStatement("UPDATE `patient` SET "
                        + " `name` = ?,"
                        + " `nic` = ?,"
                        + " `address` = ?,"
                        + " `gender` = ?,"
                        + " `status` = ?,"
                        + " `birthday` = ?, "
                        + "`telephone` = ?,"
                        + "`profilePicture` = ?,"
                        + " `healthDescription` = ? "
                        + "WHERE `patient`.`id` = ?", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, patient.getName());
                statement.setString(2, patient.getNIC());
                statement.setString(3, patient.getAddress());
                statement.setString(4, patient.getGender());
                statement.setString(5, patient.getStatus());
                statement.setString(6, patient.getBirthday());
                statement.setString(7, patient.getTp());
                if (profilePicture.getProfilePicturePath().length() == 0) {
                    statement.setNull(8, java.sql.Types.BLOB);
                } else {
                    File image = new File(patient.getProfilePicPath());
                    inputStream = new FileInputStream(image);
                    statement.setBlob(8, inputStream);
                }
                statement.setString(9, patient.getMedicalHistory());
                statement.setString(10, patient.getID());
            } else {
                statement = connection.prepareStatement("INSERT INTO `patient` (`id`,"
                        + " `name`,"
                        + " `nic`,"
                        + " `address`,"
                        + " `gender`, `status`,"
                        + " `birthday`, `telephone`,`profilePicture`,"
                        + " `healthDescription`) VALUES (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, null);
                statement.setString(2, patient.getName());
                statement.setString(3, patient.getNIC());
                statement.setString(4, patient.getAddress());
                statement.setString(5, patient.getGender());
                statement.setString(6, patient.getStatus());
                statement.setString(7, patient.getBirthday());
                statement.setString(8, patient.getTp());
                if (profilePicture.getProfilePicturePath().length() == 0) {
                    statement.setNull(9, java.sql.Types.BLOB);
                } else {
                    File image = new File(patient.getProfilePicPath());
                    inputStream = new FileInputStream(image);
                    statement.setBlob(9, inputStream);
                }
                statement.setString(10, patient.getMedicalHistory());

            }
            if (statement.executeUpdate() > 0) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    patient.setID(rs.getInt(1) + "");
                }
            }
            clearField();
            patientController.fireUpdateRowPatientSearchTablePerformed(new ActionEvent(patient, -1, null));
            JOptionPane.showMessageDialog(null, "Patient detail updating succeeded.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void mouseClickRow() {
        patientController.registerRowClickListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof Patient) {
                    Patient patient = (Patient) e.getSource();
                    setPatientField(patient);
                }

            }
        });
    }

    private void setPatientField(Patient patient) {
        tID.setText(patient.getID());
        tName.setText(patient.getName());
        tNIC.setText(patient.getNIC());
        tAddress.setText(patient.getAddress());

        if (patient.getGender().equals("Male")) {
            rbMale.setSelected(true);
        } else {
            rbFemale.setSelected(true);
        }

        if (patient.getStatus().equals("Single")) {
            rbSingle.setSelected(true);
        } else if (patient.getStatus().equals("Married")) {
            rbMarried.setSelected(true);
        } else {
            rbDivorced.setSelected(true);
        }

        tBirthday.setText(patient.getBirthday());
        tTp.setText(patient.getTp());
        tMedicalHitory.setText(patient.getMedicalHistory());
        Blob profilePic = patient.getProfilePicBLOB();
        if (profilePic != null) {
            try {
                File tmpFile = new File("tmpImage");
                FileOutputStream fos = new FileOutputStream(tmpFile);
                fos.write(profilePic.getBytes(1L, (int) profilePic.length()));
                fos.close();
                profilePicture.setProfilePic(tmpFile.getAbsolutePath());

            } catch (Exception ex) {

            }
        }

    }

    private void clearField() {
        profilePicture.clearProfilePicture();
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

    private void deleteButtonAction() {
        bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like to Remove Patient?", "Warning", 0);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    deltePatient(tID.getText());
                }
            }
        });

    }

    private void deltePatient(String id) {
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
            sql = "DELETE FROM `patient` WHERE `id` = " + id + "";
            stmt.executeUpdate(sql);
            con.close();
            clearField();
            ActionEvent e1 = new ActionEvent(id, -1, "");
            patientController.fireRemoveRowPatientSearchTablePerformed(e1);
            JOptionPane.showMessageDialog(null, "Patient detail removing succeeded.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
