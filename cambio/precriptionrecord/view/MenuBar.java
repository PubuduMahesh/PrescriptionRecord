package cambio.precriptionrecord.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MenuBar extends JPanel{
	public MenuBar() {
		GridBagConstraints menuBarConstraints = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		JMenuBar menuBar = new JMenuBar();	
		menuBar.setPreferredSize(new Dimension(650,30));
		addMenuBarItem(menuBar);
		setPreferredSize(new Dimension(720,35));
//		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(menuBar,menuBarConstraints);
	}	
	
	private void addMenuBarItem(JMenuBar menuBar){
		JMenu home = new JMenu("Home  ");
		JMenu patientManage = new JMenu("Patient Management  ");
		JMenu doctorManagement = new JMenu("Doctor Management  ");
		JMenu prescriptionManagement = new JMenu("Prescription Management  ");
		JMenu drugManagement = new JMenu("Drug Management  ");
		JMenu help = new JMenu("Help  ");
		
		
		
		menuBar.add(home);
		menuBar.add(patientManage);
		menuBar.add(doctorManagement);
		menuBar.add(prescriptionManagement);
		menuBar.add(drugManagement);
		menuBar.add(help);		
		//add sub menu item.
//		addPatientSubMenu(patientManage);
	}
	
	private void addPatientSubMenu(JMenu patientManage){
		JMenuItem newPatient = new JMenuItem("New Patient   ");
		JMenuItem editPatient = new JMenuItem("Edit Patient   ");
		JMenuItem removePatient = new JMenuItem("Remove Patient");
		patientManage.add(newPatient);
//		patientManage.add(editPatient);
//		patientManage.add(removePatient);
		
	}
}
