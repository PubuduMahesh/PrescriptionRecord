package cambio.precriptionrecord.SQLToolKit;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import cambio.precriptionrecord.model.prescription.Prescription;
import cambio.precriptionrecord.util.DBConnection;

public class PrescriptionSQLToolkit {
	public void updatePrescription(Prescription prescription){
		DBConnection dbCon = new DBConnection();
        Connection con = dbCon.getConnection();
        Statement stmt = null;
        String sql = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            sql = "INSERT INTO `prescription` (`id`,`patientID`,`doctorID`,`diagDescription`,`drugList`,`date`) "
                    + "VALUES(NULL,'" + prescription.getPatientID() + "',(SELECT `id` FROM  `doctor` WHERE `doctor`.`nic` = '" + prescription.getDoctorID() + "'),'" + prescription.getDiagnosisDescription() + "','" + prescription.getDrugList() + "','" + prescription.getDate() + "')";
            stmt.executeUpdate(sql);
            con.close();
            JOptionPane.showMessageDialog(null, "Prescription detail saving succeeded.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Prescription detail saving failed", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
	}
}
