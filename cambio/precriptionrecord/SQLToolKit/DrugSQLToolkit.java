package cambio.precriptionrecord.SQLToolKit;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import cambio.precriptionrecord.util.DBConnection;
import cambio.precriptionrecord.model.drug.*;

public class DrugSQLToolkit {
	public ResultSet updateDrug(Drug drug) {
		DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        String sql;
        try {
            stmt = connection.createStatement();
            if (drug.getDrugId().length()>0) {
                sql = "UPDATE `drug` SET"
                        + "`drugName` = '" + drug.getDrugName() + "',"
                        + "`description` = '" + drug.getDescription() + "',"
                        + "`type` = '" + drug.getType() + "',"
                        + "`dosage` = '" + drug.getDosage() + "'"
                        + "WHERE"
                        + "`drug`.`drugID` = '" + drug.getDrugId() + "'";
            } else {
                sql = "INSERT INTO `drug` (`drugId`,"
                        + "`drugName`,"
                        + "`description`,"
                        + "`type`,"
                        + "`dosage`) VALUES (NULL,"
                        + "'" + drug.getDrugName() + "',"
                        + "'" + drug.getDescription() + "',"
                        + "'" + drug.getType() + "',"
                        + "'" + drug.getDosage() + "')";
            }
            stmt.executeUpdate(sql, stmt.RETURN_GENERATED_KEYS);            
            rs = stmt.getGeneratedKeys();            
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        finally{
        	try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return rs;
        }
	}
	
	public void DeleteDrug(Drug drug){
		DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();
        Statement stmt = null;
        String sql;
        try {
            stmt = connection.createStatement();
            sql = "DELETE FROM `drug` WHERE `drug`.`drugId` = '" + drug.getDrugId() + "'";
            stmt.executeUpdate(sql);            
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        finally{
        	try{
        		stmt.close();
            	connection.close();	
        	}catch(Exception ex){
        		
        	}
        	
        }
	}
	
	public List<Drug> searchDrug(String name, String id){
		DBConnection dbCon = new DBConnection();
        Connection con = dbCon.getConnection();
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        List<Drug> drugList = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
            if(id.length()>0 && name.length()>0){
                sql = "SELECT * FROM `drug` WHERE `drugID` = '"+id+"' && `drugName` LIKE '%"+name+"%'";
            }
            else if(id.length()>0 && name.length()==0){
                sql = "SELECT * FROM `drug` WHERE `drugID` = '"+id+"'";
            }
            else if(id.length()==0 && name.length()>0){
                sql = "SELECT * FROM `drug` WHERE `drugName` LIKE '%"+name+"%'";
            }
            else{
                sql = "SELECT * FROM `drug`";
            }
            
            rs = stmt.executeQuery(sql);
            drugList = setDrugObject(rs);
            con.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        finally{
        	return drugList;
        }
	}
	
	private List<Drug> setDrugObject(ResultSet rs) {
		List<Drug> drugList = new ArrayList<Drug>();
        try {
            rs.last();
            if (rs.getRow() > 0) {
                rs.beforeFirst();
                String id;
                String name;
                String description;
                String type;
                String dosage;

                while (rs.next()) {
                	Drug drug = new Drug();
                    id = rs.getObject("drugID").toString();
                    name = rs.getObject("drugName").toString();
                    description = rs.getObject("description").toString();
                    type = rs.getObject("type").toString();
                    dosage = rs.getObject("dosage").toString();
                    drug = new Drug();
                    drug.setDrugId(id);
                    drug.setDrugName(name);
                    drug.setDescription(description);
                    drug.setType(type);
                    drugList.add(drug);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No data found from the source", "Failed", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
        	return drugList;
        }
    }
}
