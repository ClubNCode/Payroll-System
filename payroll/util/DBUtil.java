package com.wipro.payroll.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	public static Connection getDBConnection(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
			} 
			Connection con = null;
			try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","B42514336532","B42514336532");
			}catch(NullPointerException e){
				return null;
			}
			
			catch (SQLException e) {
				e.printStackTrace();
			
			return null;
			}
			
			
			return con;
	}

}
