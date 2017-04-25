package cambio.precriptionrecord.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class NewPatient extends JInternalFrame{
	public NewPatient(){		
		JDesktopPane desktopPane = new JDesktopPane();
		setTitle("Add New Patient");
		setPreferredSize(new Dimension(740,665));
		setClosable(true);
		setVisible(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		
		setLayout(new GridBagLayout());	
		
		createLayout();
			
		desktopPane.add(this);
	}
	
	private void createLayout(){
		addLabel();
	}
	
	private void addLabel(){
		GridBagConstraints constraintsLabel = new GridBagConstraints();
		constraintsLabel.anchor = GridBagConstraints.WEST;
		constraintsLabel.gridx = 0;
		constraintsLabel.gridy = 0;
		
		JLabel lName = new JLabel("Name");
		JLabel lBirthday = new JLabel("Birthday");
		JLabel lAddress = new JLabel("Address");
		JLabel ltp = new JLabel("Telephone");
		
		add(lName,constraintsLabel);
		constraintsLabel.gridy = 1;
		add(lAddress);
		constraintsLabel.gridy = 2;
		add(lBirthday);
		constraintsLabel.gridy = 3;
		add(ltp);
		
		
	}
}
