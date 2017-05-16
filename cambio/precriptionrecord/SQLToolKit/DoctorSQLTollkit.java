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

import cambio.precriptionrecord.model.doctor.Doctor;
import cambio.precriptionrecord.util.DBConnection;
import cambio.precriptionrecord.view.doctor.ProfilePicture;

public class DoctorSQLTollkit {
	public String updateDoctor(Doctor doctor,ProfilePicture profilePicture){
		DBConnection dbCon = new DBConnection();
		Connection connection = dbCon.getConnection();
		PreparedStatement statement = null;
		FileInputStream inputStream = null;
		ResultSet rs =null;
		String doctorID = null;
		try {
			if (doctor.getId().length()>0) {
				statement = connection.prepareStatement("UPDATE `doctor` SET "
						+ " `name` = ?,"
						+ " `nic` = ?,"
						+ " `regNumber` = ?,"
						+ " `speciality` = ?,"
						+ " `gender` = ?,"
						+ " `birthday` = ?, "
						+ "`telephone` = ?,"
						+ "`jobHistory` = ?,"
						+ " `profilePicture` = ? "
						+ "WHERE `doctor`.`id` = ?", Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, doctor.getName());
				statement.setString(2, doctor.getNic());
				statement.setString(3, doctor.getRegNumber());
				statement.setString(4, doctor.getSpeiality());
				statement.setString(5, doctor.getGender());
				statement.setString(6, doctor.getBirthday());
				statement.setString(7, doctor.getTp());
				statement.setString(8, doctor.getJobHistory());
				if (profilePicture.getProfilePicturePath().length() > 0) {
					File image = new File(doctor.getDoctorProfilePicPath());
					inputStream = new FileInputStream(image);
					statement.setBlob(9, inputStream);
				} else {
					statement.setNull(9, java.sql.Types.BLOB);
				}
				statement.setString(10, doctor.getId());
			} else {
				statement = connection.prepareStatement("INSERT INTO `doctor` (`id`,"
						+ " `name`,"
						+ " `nic`,"
						+ " `regNumber`,"
						+ " `speciality`,"
						+ " `gender`,"
						+ " `birthday`, "
						+ " `telephone`,"
						+ " `jobHistory`,"
						+ "`profilePicture`) VALUES (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, null);
				statement.setString(2, doctor.getName());
				statement.setString(3, doctor.getNic());
				statement.setString(4, doctor.getRegNumber());
				statement.setString(5, doctor.getSpeiality());
				statement.setString(6, doctor.getGender());
				statement.setString(7, doctor.getBirthday());
				statement.setString(8, doctor.getTp());
				statement.setString(9, doctor.getJobHistory());
				if (profilePicture.getProfilePicturePath().length() > 0) {
					File image = new File(doctor.getDoctorProfilePicPath());
					inputStream = new FileInputStream(image);
					statement.setBlob(10, inputStream);
				} else {
					statement.setNull(10, java.sql.Types.BLOB);
				}
			}

			if (statement.executeUpdate() > 0) {
				rs = statement.getGeneratedKeys();
				doctorID = rs.getInt(1) + "";
				
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return doctorID;
		}
	}
	
	public void deletDoctor(String regNumber){
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		Statement stmt = null;
		String sql = null;

		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			sql = "DELETE FROM `doctor` WHERE `doctor`.`regNumber` = '" + regNumber + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public List<Doctor> searchDoctor(String name,String regNumber){
		DBConnection dbCon = new DBConnection();
		Connection con = dbCon.getConnection();
		Statement stmt = null;
		String sql = null;
		ResultSet rs= null;
		List<Doctor> doctorList = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if(name.length()>0 && regNumber.length()>0)
				sql = "SELECT * FROM `doctor` WHERE `name` LIKE '%"+name+"%' && `regNumber` = '"+regNumber+"'";			
			else if(name.length()>0 && regNumber.length()==0)
				sql = "SELECT * FROM `doctor` WHERE `name` LIKE '%"+name+"%'";			
			else if(name.length()==0 && regNumber.length()>0)
				sql = "SELECT * FROM `doctor` WHERE `regNumber` = '"+regNumber+"'";	
			else
				sql = "SELECT * FROM `doctor`";
			rs = stmt.executeQuery(sql);
			doctorList = setDoctorObject(rs);
			con.close();					

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally{
			return doctorList;
		}
	}
	private List<Doctor> setDoctorObject(ResultSet rs){
		List<Doctor> doctorList = new ArrayList<Doctor>();
		try {
			rs.last();
			int row = rs.getRow();
			rs.beforeFirst();
			if(row > 0){
				while(rs.next()){
					Doctor doctor = new Doctor();
					doctor = new Doctor();
					doctor.setId(rs.getObject("id").toString());
					doctor.setName(rs.getObject("name").toString());
					doctor.setNic(rs.getObject("nic").toString());
					doctor.setRegNumber(rs.getObject("regNumber").toString());
					doctor.setSpeiality(rs.getObject("speciality").toString());
					doctor.setGender(rs.getObject("gender").toString());
					doctor.setBirthday(rs.getObject("birthday").toString());
					doctor.setTp(rs.getObject("telephone").toString());
					doctor.setJobHistory(rs.getObject("jobHistory").toString());
					doctor.setDoctorProfilePic(rs.getBlob("profilePicture"));
					doctorList.add(doctor);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "No data found from the source.", "Failed", JOptionPane.INFORMATION_MESSAGE);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			return doctorList;
		}
	}
}
