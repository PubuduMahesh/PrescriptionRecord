package cambio.precriptionrecord.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import cambio.precriptionrecord.controller.PatientController;

public class MenuBar extends JPanel{
	private PatientController patientController;
	private JMenuBar menuBar = new JMenuBar();
	
	public MenuBar(PatientController patientcontroller) {
		this.patientController = patientcontroller;
		
		this.setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(770,35));
//		setBorder(BorderFactory.createLineBorder(Color.black));
		
		GridBagConstraints menuBarConstraints = new GridBagConstraints();
		menuBarConstraints.anchor = GridBagConstraints.WEST;
		menuBarConstraints.gridx = 0;
		menuBarConstraints.gridy = 0;
		
		menuBar.setPreferredSize(new Dimension(650,30));
		addMenuBarItem();
		this.add(menuBar,menuBarConstraints);
		
		
	}	
	
	private void addMenuBarItem(){
		JMenu home = new JMenu("Home  ");
		JMenu patientManagement = new JMenu("Patient Management  ");
		JMenu doctorManagement = new JMenu("Doctor Management  ");
		JMenu prescriptionManagement = new JMenu("Prescription Management  ");
		JMenu drugManagement = new JMenu("Drug Management  ");
		JMenu help = new JMenu("Help  ");			

		menuBar.add(home);
		menuBar.add(patientManagement);
		menuBar.add(doctorManagement);
		menuBar.add(prescriptionManagement);
		menuBar.add(drugManagement);
		menuBar.add(help);
		//add sub menu item.
		addPatientSubMenu(patientManagement);
		addDoctorSubMenu(doctorManagement);
		addPrescriptionSubMenu(prescriptionManagement);
		addDrugSubMenu(drugManagement);
		
		
	}
	
	private void addPatientSubMenu(JMenu patientManage){
		JMenuItem newPatient = new JMenuItem("New Patient");		
		JMenuItem editPatient = new JMenuItem("Edit Patient");
		JMenuItem removePatient = new JMenuItem("Remove Patient");
		patientManage.add(newPatient);
		patientManage.add(editPatient);
		patientManage.add(removePatient);	
		
		newPatient.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				patientController.fireAddNewPatientPerformed(e);
			}
		});
	}
	
	private void addDoctorSubMenu(JMenu doctorManage){
		JMenuItem newDoctor = new JMenuItem("New Doctor");		
		JMenuItem editDoctor= new JMenuItem("Edit Doctor");
		JMenuItem removeDoctor = new JMenuItem("Remove Doctor");
		doctorManage.add(newDoctor);
		doctorManage.add(editDoctor);
		doctorManage.add(removeDoctor);
		
	}
	
	private void addPrescriptionSubMenu(JMenu prescriptionManage){
		JMenuItem newPrescription = new JMenuItem("New Prescription");	
		prescriptionManage.add(newPrescription);
		
	}
	
	private void addDrugSubMenu(JMenu drugManage){
		JMenuItem newDrug = new JMenuItem("New Drug");		
		JMenuItem editDrug = new JMenuItem("Edit Drugh");
		JMenuItem removeDrug = new JMenuItem("Remove Drug");
		drugManage.add(newDrug);
		drugManage.add(editDrug);
		drugManage.add(removeDrug);
		
	}
	
	private void openInternalFrame(JMenuItem menuItem){
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("pubudu");
			}
		});
	}
}
