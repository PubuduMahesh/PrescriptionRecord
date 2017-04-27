package cambio.precriptionrecord.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	Connection con;
	public DBConnection(){		
			//Accessing driver from the JAR file
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
			
			//creating a variable for the connection called "con".
			try {
				String dbName = "prescription_record";
				String dbUserName = "root";
				String dbPassword = "";
				String connectionString = "jdbc:mysql://localhost/" + dbName + "?user=" + dbUserName + "&password=" + dbPassword + "&useUnicode=true&characterEncoding=UTF-8";
				con = DriverManager.getConnection(connectionString);
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
	}
	
	public Connection getConnection(){
		return con;
	}
}
