package cambio.precriptionrecord.view;

import cambio.precriptionrecord.util.DBConnection;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RegisterPanel extends JPanel {

    private final GridBagLayout gridbag;
    private JTextField tNIC;
    private JPasswordField tPassword;
    private JPasswordField tConfirmPassword;
    private JComboBox cUSerType;
    private JButton bRegistr;
    private JButton bClear;
    private JLabel lpasswordConfirmError;
    private boolean passwordMatched = false;

    public RegisterPanel() {
        gridbag = new GridBagLayout();
        setLayout(gridbag);
        setPreferredSize(new Dimension(650, 300));
//        setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));

        addLabel();
        addField();

    }

    private void addLabel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        JLabel lTopic = new JLabel();
        JLabel lNIC = new JLabel();
        JLabel lPPW = new JLabel();
        JLabel lConfirmPPW = new JLabel();
        JLabel lUserType = new JLabel();
        JLabel lTitle = new JLabel();

        lNIC.setText("<html>NIC <font color='red'>*</font></html>");
        lPPW.setText("<html>Password <font color='red'>*</font></html>");
        lConfirmPPW.setText("<html>Confirm Password <font color='red'>*</font></html>");
        lUserType.setText("<html>User Type <font color='red'>*</font></html>");
        lTitle.setText("<html><font Size='8'>Register Here</font></html>");

        /*label - title*/
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 0, 0);
        gridbag.setConstraints(lTitle, constraints);
        add(lTitle);
        
        /*label - nic*/
        constraints.insets = new Insets(60, 0, 0, 0);
        gridbag.setConstraints(lNIC, constraints);
        add(lNIC);

        /*label - ppw*/
        constraints.insets = new Insets(100, 0, 0, 0);
        gridbag.setConstraints(lPPW, constraints);
        add(lPPW);

        /*label - confirm ppw*/
        constraints.insets = new Insets(140, 0, 0, 0);
        gridbag.setConstraints(lConfirmPPW, constraints);
        add(lConfirmPPW);

        /*laebl - user type*/
        constraints.insets = new Insets(180, 0, 0, 0);
        gridbag.setConstraints(lUserType, constraints);
        add(lUserType);
        
		constraints.insets = new Insets(140, 350, 0, 0);
		lpasswordConfirmError = new JLabel();
		lpasswordConfirmError.setText("<html><font color='red'>Password is missed matched</font></html>");
		gridbag.setConstraints(lpasswordConfirmError, constraints);
		add(lpasswordConfirmError);
		lpasswordConfirmError.setVisible(false);

    }

    private void addField() {
        tNIC = new JTextField(10);
        tPassword = new JPasswordField(10);
        tConfirmPassword = new JPasswordField(10);
        cUSerType = new JComboBox();
        bRegistr = new JButton("Rgister");
        bClear = new JButton("Clear");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;

        constraints.gridx = 0;
        constraints.gridy = 0;

        /*text field - nic*/
        constraints.insets = new Insets(60, 160, 0, 0);
        gridbag.setConstraints(tNIC, constraints);
        add(tNIC);

        /*text field - password*/
        constraints.insets = new Insets(100, 160, 0, 0);
        gridbag.setConstraints(tPassword, constraints);
        add(tPassword);

        /*text field - confirm password*/
        constraints.insets = new Insets(140, 160, 0, 0);
        gridbag.setConstraints(tConfirmPassword, constraints);
        add(tConfirmPassword);

        /*combo box - user type */
        cUSerType.addItem("Doctor");
        cUSerType.addItem("Patient");
        cUSerType.addItem("Admin");
        constraints.insets = new Insets(180, 160, 0, 0);
        gridbag.setConstraints(cUSerType, constraints);
        add(cUSerType);

        /*button - register*/
        constraints.insets = new Insets(220, 160, 0, 0);
        gridbag.setConstraints(bRegistr, constraints);
        add(bRegistr);

        /*button - clear*/
        constraints.insets = new Insets(220, 260, 0, 0);
        gridbag.setConstraints(bClear, constraints);
        add(bClear);

        /*Register Button Disabled*/
        registerButtonAction();
        registerButtonDisabled();
        passwordChecking();

    }

    private void registerButtonAction() {
        bRegistr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }

        });
    }

    private void registerUser() {
    	System.out.println(tPassword.getPassword().toString());
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();
        Statement stmt = null;
        String sql;
        try {
            stmt = connection.createStatement();
            sql = "INSERT INTO `user` (`id`,"
                    + "`nic`,"
                    + "`password`,"
                    + "`userType`) VALUES (NULL,"
                    + "'" + tNIC.getText() + "',"
                    + "'" + tPassword.getText() + "',"
                    + "'" + cUSerType.getSelectedItem() + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            connection.close();
            clearField();
            JOptionPane.showMessageDialog(null, "Registration Success :).", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }
    private void registerButtonDisabled(){
    	bRegistr.setEnabled(false);
    	new RegisterButtonTextFieldCondtion(tNIC);
    	new RegisterButtonTextFieldCondtion(tPassword);
    	new RegisterButtonTextFieldCondtion(tConfirmPassword);
    }
    private class RegisterButtonTextFieldCondtion {

        public RegisterButtonTextFieldCondtion(JTextField textField) {
            textField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {
                    enableRegisterButton();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    enableRegisterButton();
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    enableRegisterButton();
                }

            });
        }
    }
    private void enableRegisterButton(){
    	if(!tNIC.getText().equals("") && tPassword.getPassword().length > 0 && tConfirmPassword.getPassword().length > 0 && passwordMatched){
    		bRegistr.setEnabled(true);
    		
    	}
    	else{
    		bRegistr.setEnabled(false);
    		
    	}
    	
    }    
    private void clearField(){
    	tNIC.setText("");
    	tPassword.setText("");
    	tConfirmPassword.setText("");
    	
    }
    private void passwordChecking(){
    	new PasswordMatchingCondtion();
    }
    private void matchPassword(){
    	    if(Arrays.toString(tPassword.getPassword()).equals(Arrays.toString(tConfirmPassword.getPassword()))){
    	    	lpasswordConfirmError.setVisible(false); 
    	    	passwordMatched = true;
    	    }
    	    else{
    	    	lpasswordConfirmError.setVisible(true);
    	    	passwordMatched = false;
    	    }
    	    	
    }
    private class PasswordMatchingCondtion {

        public PasswordMatchingCondtion() {
            tConfirmPassword.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {
                	matchPassword();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                	matchPassword();
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                	matchPassword();
                }

            });
        }
    }
}
