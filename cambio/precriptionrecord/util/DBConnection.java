package cambio.precriptionrecord.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	public DBConnection() throws SQLException{		
		try {
			//Accessing driver from the JAR file
			Class.forName("com.mysql.jdbc.Driver");
			
			//creating a variable for the connection called "con".
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8012/prescription_record","root","");
			
			/*PreparedStatement statement = con.prepareStatement("select * From doctor");
			ResultSet result = statement.executeQuery();*/
			/*while(result.next()){
				System.out.println(result.getString(1));
			}*/
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
