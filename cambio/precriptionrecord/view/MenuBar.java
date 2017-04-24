package cambio.precriptionrecord.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MenuBar extends JPanel{
	public MenuBar() {
		JMenuBar menuBar = new JMenuBar();		
		addMenuBarItem(menuBar);
		this.add(menuBar);
	}	
	
	private void addMenuBarItem(JMenuBar menuBar){
		JMenu home = new JMenu("Home");
		JMenu patientManage = new JMenu("PatientManagement");
		JMenu doctorManagement = new JMenu("DoctorManagement");
		JMenu prescriptionManagement = new JMenu("PrescriptionManagement");
		JMenu drugManagement = new JMenu("drugManagement");
		
		menuBar.add(home);
		menuBar.add(patientManage);
		menuBar.add(doctorManagement);
		menuBar.add(prescriptionManagement);
		menuBar.add(drugManagement);
	}
}
