package cambio.precriptionrecord.view;

import cambio.precriptionrecord.controller.CommonController;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import cambio.precriptionrecord.controller.DoctorController;
import cambio.precriptionrecord.controller.DrugController;
import cambio.precriptionrecord.controller.PatientController;
import cambio.precriptionrecord.controller.PrescriptionController;
import cambio.precriptionrecord.view.doctor.EditDoctor;
import cambio.precriptionrecord.view.drug.EditDrug;
import cambio.precriptionrecord.view.drug.NewDrug;
import cambio.precriptionrecord.view.drug.RemoveDrug;
import cambio.precriptionrecord.view.patient.EditPatient;
import cambio.precriptionrecord.view.patient.TreatmentHistory;
import cambio.precriptionrecord.view.prescription.NewPrescription;

public class EmptyPanel extends JPanel {
    private final PatientController patientController;
    private final DoctorController doctorController;
    private final DrugController drugController;
    private final PrescriptionController prescriptionController;

    public EmptyPanel(PatientController patientController, DoctorController doctorController, DrugController drugController, PrescriptionController prescriptionController) {
        this.patientController = patientController;
        this.doctorController = doctorController;
        this.drugController = drugController;
        this.prescriptionController = prescriptionController;
        this.setPreferredSize(new Dimension(760, 670));
                
        loadEditPatientGUI();
        loadTreatmentHistoryGUI();
        
        loadEditDoctorGUI();

        loadNewPrescriptionGUI();

        loadNewDrugGUI();
        loadEditDrugGUI();
        loadRemoveDrugGUI();
    }

    private void loadEditPatientGUI() {
        patientController.registerEditPatientListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditPatient editPatient = new EditPatient(patientController);
                add(editPatient);
            }
        });
    }

    private void loadEditDoctorGUI() {
        doctorController.registerEditDoctorListeners(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EditDoctor editDoctor = new EditDoctor(doctorController);
                add(editDoctor);
            }
        });
    }

    private void loadNewDrugGUI() {
        drugController.registerAddNewDrugListeners(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                NewDrug newDrug = new NewDrug(drugController);
                add(newDrug);
            }
        });
    }

    private void loadEditDrugGUI() {
        drugController.registerEditDrugListeners(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EditDrug editDrug = new EditDrug(drugController);
                add(editDrug);
            }
        });
    }

    private void loadRemoveDrugGUI() {
        drugController.registerRemoveDrugListeners(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveDrug removeDrug = new RemoveDrug(drugController);
                add(removeDrug);
            }
        });
    }

    private void loadNewPrescriptionGUI() {
        prescriptionController.registerAddNewPrescriptionListeners(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                NewPrescription newPrescription = new NewPrescription(prescriptionController);
                add(newPrescription);
            }
        });
    }

    public void loadTreatmentHistoryGUI() {
        patientController.registerTreatmentHistoryListeners(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TreatmentHistory treatmentHistory = new TreatmentHistory(patientController);
                add(treatmentHistory);

            }
        });
    }

}
