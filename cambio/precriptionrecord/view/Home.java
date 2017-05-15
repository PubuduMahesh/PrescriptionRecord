package cambio.precriptionrecord.view;

import cambio.precriptionrecord.controller.CommonController;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import cambio.precriptionrecord.view.doctor.EditDoctor;
import cambio.precriptionrecord.view.drug.EditDrug;
import cambio.precriptionrecord.view.patient.EditPatient;
import cambio.precriptionrecord.view.patient.TreatmentHistory;
import cambio.precriptionrecord.view.prescription.NewPrescription;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {

    private JDesktopPane panelMain = new JDesktopPane();
    private CommonController commonController = new CommonController();
    public static String userNIC;

    public Home() {
        initUI();
        configureLayout();        
        setVisible(true);

    }

    private void initUI() {
        setTitle("Dispensary Managment.");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        setSize(770, 780);
        Point location = new Point(middle.x - this.getWidth() / 2, middle.y - this.getHeight()/2);
        setLocation(location);//Frame will be centered on the screen.
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panelMain);
        panelMain.setLayout(new BorderLayout());
        panelMain.setVisible(true);
    }

    private void configureLayout() {
    	addMenuBar();
//        addLoginPanel();
        

    }

    private void addTitle() {
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.weightx = 0.5;
        titleConstraints.weighty = 0.5;
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.anchor = GridBagConstraints.NORTHWEST;
        panelMain.add(new PageTitle(""), titleConstraints);
    }

    private void addMenuBar() {
    	JMenuBar menuBar = new JMenuBar();
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
        addPatientSubMenu(patientManagement);
        addDoctorSubMenu(doctorManagement);
        addPrescriptionSubMenu(prescriptionManagement);
        addDrugSubMenu(drugManagement);
    	setJMenuBar(menuBar);
    }
    private void addPatientSubMenu(JMenu patientManage) {
        JMenuItem editPatient = new JMenuItem("Update Patient");
        JMenuItem treatmentHistory = new JMenuItem("Treatment History");
        patientManage.add(editPatient);
        patientManage.add(treatmentHistory);

        editPatient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	EditPatient editPatient = new EditPatient();
            	panelMain.add(editPatient); 
            	try {
            		editPatient.setSelected(true);
            		editPatient.toFront();
                }catch (java.beans.PropertyVetoException e1) {}
            }
        });

        treatmentHistory.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	TreatmentHistory treatmentHistory = new TreatmentHistory();
            	panelMain.add(treatmentHistory);
            	try{
            		treatmentHistory.setSelected(true);
            	}
            	catch(Exception ex){
            		System.out.println(ex.getMessage());
            	}
            }
        });
    }

    private void addDoctorSubMenu(JMenu doctorManage) {
        JMenuItem editDoctor = new JMenuItem("Update Doctor");
        doctorManage.add(editDoctor);

        editDoctor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	EditDoctor doctor = new EditDoctor();
            	panelMain.add(doctor);
            	try{
            		doctor.setSelected(true);
            		doctor.toFront();
            	}catch(Exception ex){
            		
            	}
            }
        });

    }

    private void addPrescriptionSubMenu(JMenu prescriptionManage) {
        JMenuItem newPrescription = new JMenuItem("New Prescription");
        prescriptionManage.add(newPrescription);

        newPrescription.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	NewPrescription newPrescription = new NewPrescription();
            	panelMain.add(newPrescription);
            	try{
            		newPrescription.setSelected(true);
            	}catch(Exception ex){
            		
            	}
            }
        });

    }

    private void addDrugSubMenu(JMenu drugManage) {
        JMenuItem editDrug = new JMenuItem("Update Drug");
        drugManage.add(editDrug);
        editDrug.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EditDrug editDrug = new EditDrug();
                panelMain.add(editDrug);
                try{
                	editDrug.setSelected(true);
                }catch(Exception ex){
                	
                }
                
            }
        });

    }

    private void addLoginPanel() {
        GridBagConstraints loginPanelConstraints = new GridBagConstraints();
        loginPanelConstraints.weightx = 0.5;
        loginPanelConstraints.weighty = 0.5;
        loginPanelConstraints.gridx = 0;
        loginPanelConstraints.gridy = 2;
        loginPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        panelMain.add(new Login(commonController)/*, loginPanelConstraints*/);
        commonController.registerLoginCredentialsSuccessActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userNIC = e.getSource().toString();
                addMenuBar();
            }
        });
    }
   
}
