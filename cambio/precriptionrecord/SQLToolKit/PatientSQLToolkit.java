package cambio.precriptionrecord.SQLToolKit;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import cambio.precriptionrecord.model.patient.Patient;
import cambio.precriptionrecord.model.prescription.Prescription;
import cambio.precriptionrecord.util.DBConnection;
import cambio.precriptionrecord.view.patient.ProfilePicture;

public class PatientSQLToolkit {
	public String updatePatient(Patient patient,ProfilePicture profilePicture){
		DBConnection dbCon = new DBConnection();
        Connection connection = dbCon.getConnection();
        PreparedStatement statement = null;
        FileInputStream inputStream = null;
        ResultSet rs = null;
        String patientId = null;

        try {
            if (patient.getID().length()>0) {
                statement = connection.prepareStatement("UPDATE `patient` SET "
                        + " `name` = ?,"
                        + " `nic` = ?,"
                        + " `address` = ?,"
                        + " `gender` = ?,"
                        + " `status` = ?,"
                        + " `birthday` = ?, "
                        + "`telephone` = ?,"
                        + "`profilePicture` = ?,"
                        + " `healthDescription` = ? "
                        + "WHERE `patient`.`id` = ?", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, patient.getName());
                statement.setString(2, patient.getNIC());
                statement.setString(3, patient.getAddress());
                statement.setString(4, patient.getGender());
                statement.setString(5, patient.getStatus());
                statement.setString(6, patient.getBirthday());
                statement.setString(7, patient.getTp());
                if (profilePicture.getProfilePicturePath().length() == 0) {
                    statement.setNull(8, java.sql.Types.BLOB);
                } else {
                    File image = new File(patient.getProfilePicPath());
                    inputStream = new FileInputStream(image);
                    statement.setBlob(8, inputStream);
                }
                statement.setString(9, patient.getMedicalHistory());
                statement.setString(10, patient.getID());
            } else {
                statement = connection.prepareStatement("INSERT INTO `patient` (`id`,"
                        + " `name`,"
                        + " `nic`,"
                        + " `address`,"
                        + " `gender`, `status`,"
                        + " `birthday`, `telephone`,`profilePicture`,"
                        + " `healthDescription`) VALUES (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, null);
                statement.setString(2, patient.getName());
                statement.setString(3, patient.getNIC());
                statement.setString(4, patient.getAddress());
                statement.setString(5, patient.getGender());
                statement.setString(6, patient.getStatus());
                statement.setString(7, patient.getBirthday());
                statement.setString(8, patient.getTp());
                if (profilePicture.getProfilePicturePath().length() == 0) {
                    statement.setNull(9, java.sql.Types.BLOB);
                } else {
                    File image = new File(patient.getProfilePicPath());
                    
                    inputStream = new FileInputStream(image);
                    statement.setBlob(9, inputStream);
                }
                statement.setString(10, patient.getMedicalHistory());

            }
            if (statement.executeUpdate() > 0) {
                rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    patientId = rs.getInt(1) + "";
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally{
        	try{
        		statement.close();
        		connection.close();
        	}catch(Exception ex){
        		
        	}
        	return patientId;
        	
        }
	}
	public void deletePatient(String id){
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
            sql = "DELETE FROM `patient` WHERE `id` = " + id + "";
            stmt.executeUpdate(sql);            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{

            try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
	}
	
	public List<Patient> searchPatient(String name, String nic){
		List<Patient> patientList = null;
		DBConnection dbCon = new DBConnection();
        Connection con = dbCon.getConnection();
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
        	stmt = con.createStatement();
            if (name.length()>0 && nic.length()>0) {
                sql = "SELECT * from `patient` WHERE `name` LIKE '%" + name + "%' AND `nic` = '" + nic + "' ";
            } else if (name.length()>0 && nic.length()==0) {
                sql = "SELECT * from `patient` WHERE `name` LIKE '%" + name + "%'";
            } else if (name.length()==0 && nic.length()>0) {
                sql = "SELECT * from `patient` WHERE `nic` = '" + nic + "' ";
            } else {
                sql = "SELECT * from `patient`";
            }

            rs = stmt.executeQuery(sql);
            patientList = setPatientObject(rs);
            con.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        finally {
        	return patientList;
        }
	}
	
	private List<Patient> setPatientObject(ResultSet rs) {
		List<Patient> patientList = new ArrayList<Patient>();
		
        try {
            rs.last();
            int row = rs.getRow();
            rs.beforeFirst();
            if (row > 0) {

                while (rs.next()) {
                	Patient patient = new Patient();
                    patient.setID(rs.getObject("id").toString());
                    patient.setName(rs.getObject("name").toString());
                    patient.setNIC(rs.getObject("nic").toString());
                    patient.setAddress(rs.getObject("address").toString());
                    patient.setGender(rs.getObject("gender").toString());
                    patient.setStatus(rs.getObject("status").toString());
                    patient.setBirthday(rs.getObject("birthday").toString());
                    patient.setTp(rs.getObject("telephone").toString());
                    patient.setMedicalHistory(rs.getObject("healthDescription").toString());
                    patient.setProfilePicBLOB(rs.getBlob("profilePicture"));
                    patientList.add(patient);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No data found from the source", "Failed", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
        	return patientList;
        }
    }
	
	public List<Prescription> searchPrescription(String from,String to, String patientId){
		List<Prescription> prescriptionList = null;
		DBConnection dbCon = new DBConnection();
		Connection con = dbCon.getConnection();
		Statement stmt = null;
		String sql = null;
		ResultSet rs= null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if(from.length()>0 && to.length()>0)
				sql = "SELECT `prescription`.`date`,`prescription`.`diagDescription`,`prescription`.`drugList`,`doctor`.`name` from `prescription` INNER JOIN `doctor` ON `prescription`.`doctorID` = `doctor`.`id`" 
						+ "WHERE "
						+ "`prescription`.`date`< '"+to+"' "
						+ "&& "
						+ "`prescription`.`date` > '"+from+"'"
						+ " && `prescription`.`patientID` = '"+patientId+"'";
			else if(from.length()>0 && to.length()==0)
				sql = "SELECT `prescription`.`date`,`prescription`.`diagDescription`,`prescription`.`drugList`,`doctor`.`name` FROM  `prescription` INNER JOIN `doctor` ON `prescription`.`doctorID` = `doctor`.`id` WHERE `prescription`.`date` > '"+from+"' && `patientID` = '"+patientId+"'";
			else if(from.length()==0 && to.length()>0)
				sql = "SELECT  `prescription`.`date`,`prescription`.`diagDescription`,`prescription`.`drugList`,`doctor`.`name` FROM prescription INNER JOIN `doctor` ON `prescription`.`doctorID` = `doctor`.`id` WHERE `prescription`.`date`< '"+to+"' && `prescription`.`patientID` = '"+patientId+"'";
			else 
				sql = "SELECT `prescription`.`date`,`prescription`.`diagDescription`,`prescription`.`drugList`,`doctor`.`name` FROM  prescription INNER JOIN `doctor` ON `prescription`.`doctorID`=`doctor`.`id` WHERE `prescription`.`patientID` = '"+patientId+"'";	
			rs = stmt.executeQuery(sql);
			prescriptionList = setTreatmentHistoryObject(rs);
			con.close();
		}catch(Exception ex){
			
		}
		finally{
			return prescriptionList;
		}
	}
		
		private List<Prescription> setTreatmentHistoryObject(ResultSet rs){
			List<Prescription> prescriptionList = null; 
			try {   
				rs.last();
				int row = rs.getRow();
				rs.beforeFirst();
				if(row>0){
					prescriptionList = new ArrayList<Prescription>();
					while(rs.next()){
						Prescription prescription = new Prescription();
						prescription.setDate(rs.getObject("date").toString());
						prescription.setDiagnosisDescription(rs.getObject("diagDescription").toString());
						prescription.setDrugList(rs.getObject("drugList").toString());
						prescription.setDoctorName(rs.getObject("name").toString());
						prescriptionList.add(prescription);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "No data found from the source.", "Success", JOptionPane.INFORMATION_MESSAGE);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				return prescriptionList;
			}

		}
}
