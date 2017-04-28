package cambio.precriptionrecord.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cambio.precriptionrecord.controller.DoctorController;
import cambio.precriptionrecord.controller.PatientController;

public class Home extends JFrame{
	private JPanel panelMain = new JPanel(new GridBagLayout());
	private PatientController patientController = new PatientController();
	private DoctorController doctorController = new DoctorController();
	public Home() {
		initUI();
		configureLayout();
		
	}
	private void initUI() {	
		setTitle("Dispensary Managment.");
		setSize(780,780);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width/2,screenSize.height/2);
		Point location = new Point(middle.x-this.getWidth()/2,middle.y-this.getHeight()/2);
		setLocation(location);//Frame will be centered on the screen.
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		getContentPane().add(panelMain);
		setVisible(true);
	}
	
	private void configureLayout() {		
		addTitle();
		addMenuBar();
		addEmptyPanel();

		
	}
	
	private void addTitle(){
		GridBagConstraints titleConstraints = new GridBagConstraints();
		titleConstraints.weightx = 0.5;
		titleConstraints.weighty = 0.5;
		titleConstraints.gridx = 0;
		titleConstraints.gridy = 0;		
		titleConstraints.anchor = GridBagConstraints.NORTHWEST;
		panelMain.add(new PageTitle(""),titleConstraints);
	}
	
	private void addMenuBar(){
		GridBagConstraints menuBarConstraints = new GridBagConstraints();
		menuBarConstraints.weightx = 0.5;
		menuBarConstraints.weighty = 0.5;
		menuBarConstraints.gridx = 0;
		menuBarConstraints.gridy = 1;		
		menuBarConstraints.anchor = GridBagConstraints.NORTHWEST;
		panelMain.add(new MenuBar(patientController,doctorController),menuBarConstraints);
	}
	
	private void addEmptyPanel(){
		GridBagConstraints emptyPanelConstraints = new GridBagConstraints();
		emptyPanelConstraints.weightx = 0.5;
		emptyPanelConstraints.weighty = 0.5;
		emptyPanelConstraints.gridx = 0;
		emptyPanelConstraints.gridy = 2;
		emptyPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
		panelMain.add(new EmptyPanel(patientController,doctorController),emptyPanelConstraints);
	}	
}
