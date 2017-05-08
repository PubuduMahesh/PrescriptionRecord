package cambio.precriptionrecord.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import cambio.precriptionrecord.controller.DoctorController;
import cambio.precriptionrecord.controller.PatientController;
import cambio.precriptionrecord.controller.PrescriptionController;
import cambio.precriptionrecord.controller.DrugController;

public class MenuBar extends JPanel {

    private final PatientController patientController;
    private final DoctorController doctorController;
    private final DrugController drugController;
    private final PrescriptionController prescriptionController;
    private final JMenuBar menuBar = new JMenuBar();

    public MenuBar(PatientController patientcontroller,
            DoctorController doctorController,
            DrugController drugController,
            PrescriptionController prescriptionController) {
        this.patientController = patientcontroller;
        this.doctorController = doctorController;
        this.prescriptionController = prescriptionController;
        this.drugController = drugController;
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(760, 35));

        GridBagConstraints menuBarConstraints = new GridBagConstraints();
        menuBarConstraints.anchor = GridBagConstraints.WEST;
        menuBarConstraints.gridx = 0;
        menuBarConstraints.gridy = 0;

        menuBar.setPreferredSize(new Dimension(670, 30));
        addMenuBarItem();
        this.add(menuBar, menuBarConstraints);

    }

    private void addMenuBarItem() {
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

    private void addPatientSubMenu(JMenu patientManage) {
        JMenuItem editPatient = new JMenuItem("Edit Patient");
        JMenuItem treatmentHistory = new JMenuItem("Treatment History");
        patientManage.add(editPatient);
        patientManage.add(treatmentHistory);

        editPatient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patientController.fireEditPatientPerformed(e);
            }
        });

        treatmentHistory.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                patientController.fireTreatmentHistoryPerformed(e);
            }
        });
    }

    private void addDoctorSubMenu(JMenu doctorManage) {
        JMenuItem editDoctor = new JMenuItem("Edit Doctor");
        doctorManage.add(editDoctor);

        editDoctor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doctorController.fireEditDoctorPerformed(e);
            }
        });

    }

    private void addPrescriptionSubMenu(JMenu prescriptionManage) {
        JMenuItem newPrescription = new JMenuItem("New Prescription");
        prescriptionManage.add(newPrescription);

        newPrescription.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                prescriptionController.fireAddNewPrescriptionPerformed(e);
            }
        });

    }

    private void addDrugSubMenu(JMenu drugManage) {
        JMenuItem newDrug = new JMenuItem("New Drug");
        JMenuItem editDrug = new JMenuItem("Edit Drugh");
        JMenuItem removeDrug = new JMenuItem("Remove Drug");
        drugManage.add(newDrug);
        drugManage.add(editDrug);
        drugManage.add(removeDrug);

        newDrug.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                drugController.fireAddNewDrugActionPerformed(e);
            }
        });

        editDrug.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                drugController.fireEditDrugActionPerformed(e);
            }
        });

        removeDrug.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                drugController.fireRemoveDrugActionPerformed(e);
            }
        });

    }
}
