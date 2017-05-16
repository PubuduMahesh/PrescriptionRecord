package cambio.precriptionrecord.view;

import cambio.precriptionrecord.controller.CommonController;
import cambio.precriptionrecord.util.DBConnection;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class Login extends JPanel {

    private GridBagLayout gridbag;
    private JTextField tNIC;
    private JPasswordField tPassword;
    private JButton bLogin;
    private CommonController commonController;

    public Login(CommonController commonController) {
        this.commonController = commonController;
        gridbag = new GridBagLayout();
        JDesktopPane desktopPane = new JDesktopPane();
        setPreferredSize(new Dimension(763, 750));
        gridbag = new GridBagLayout();
        setLayout(gridbag);
        createLayout();
        desktopPane.add(this);
        setVisible(true);

    }

    private void createLayout() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        JLabel lNIC = new JLabel();
        JLabel lPassword = new JLabel();
        JLabel lConfirmPassword = new JLabel("ConfirmPassword");
        lNIC.setText("<html>NIC<font color='red'>*</font></html>");
        lPassword.setText("<html>Password<font color='red'>*</font></html>");
        lConfirmPassword.setText("<html>Confirm Password<font color='red'>*</font></html>");

        tNIC = new JTextField(10);
        tPassword = new JPasswordField(10);
        bLogin = new JButton("Login");
        
        /*label- nic*/
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 160, 0, 0);
        gridbag.setConstraints(lNIC, constraints);
        add(lNIC);

        /*text field - nic*/
        constraints.insets = new Insets(0, 195, 0, 0);
        gridbag.setConstraints(tNIC, constraints);
        add(tNIC);

        /*label - password*/
        constraints.insets = new Insets(0, 335, 0, 0);
        gridbag.setConstraints(lPassword, constraints);
        add(lPassword);

        /*text field - password*/
        constraints.insets = new Insets(0, 420, 0, 0);
        gridbag.setConstraints(tPassword, constraints);
        add(tPassword);

        /*button - login*/
        bLogin.setPreferredSize(new Dimension(70,20));
        bLogin.setFont(new Font("Arial", Font.PLAIN, 8));
        constraints.insets = new Insets(0, 560, 0, 0);
        gridbag.setConstraints(bLogin, constraints);
        add(bLogin);

        /*register panel*/
        RegisterPanel registerPanel = new RegisterPanel();
        constraints.insets = new Insets(20, 0, 0, 0);
        gridbag.setConstraints(registerPanel, constraints);
        add(registerPanel);
        /*Button action*/
        loginButtonAction();
        loginButtonDisabled();
        enterButtonAction();
    }

    private void loginButtonAction() {
        bLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkLoginCredintials();
            }
        });

    }

    private void checkLoginCredintials() {
        String nic = tNIC.getText();
        String password = tPassword.getText();

        DBConnection dbCon = new DBConnection();
        Connection con = dbCon.getConnection();
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
            sql = "SELECT `nic` FROM `user` WHERE  `user`.`nic` = '" + nic + "' && `user`.`password` = '" + password + "'";
            rs = stmt.executeQuery(sql);
            rs.last();
            int row = rs.getRow();
            rs.beforeFirst();
            if (row == 1) {
                while (rs.next()) {
                    String userNic = rs.getObject("nic").toString();
                    ActionEvent e = new ActionEvent(userNic, -1, null);
                    setVisible(false);
                    commonController.fireLoginCredentialSuccessActionPerformed(e);
                }

            } else {
            	tNIC.setText("");
            	tPassword.setText("");
                JOptionPane.showMessageDialog(null, "Login credentials failed", "Failed", JOptionPane.INFORMATION_MESSAGE);
            }
            con.close();

        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
            e1.printStackTrace();
        }

    }
    private void loginButtonDisabled(){
    	bLogin.setEnabled(false);
    	new LoginButtonTextFieldCondtion(tNIC);
    	new LoginButtonTextFieldCondtion(tPassword);
    }
    private class LoginButtonTextFieldCondtion {

        public LoginButtonTextFieldCondtion(JTextField textField) {
            textField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {
                    enableLoginButton();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                	enableLoginButton();
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                	enableLoginButton();
                }

            });
        }
    }
    private void enableLoginButton(){
    	if(tPassword.getPassword().length > 0 && tNIC.getText().length()>0)
    		bLogin.setEnabled(true);
    	else
    		bLogin.setEnabled(false);
    }
    private void enterButtonAction(){
    	textFieldAction(tNIC);
    	textFieldAction(tPassword);
    	
    }
    private void textFieldAction(JTextField textfield){
    	textfield.addKeyListener(new KeyAdapter(){
    		public void keyPressed(KeyEvent e)
    	      {
    	        if (e.getKeyCode() == KeyEvent.VK_ENTER)
    	        {
    	        	checkLoginCredintials();
    	        }
    	      }
    	});
    	
    }
    
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {
    	  if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
    	   }
    	} 

}
