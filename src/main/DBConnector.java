package main;

import java.sql.*;

public class DBConnector {
	protected static Connection conn = null;
	protected static Statement st = null;
	protected static ResultSet rs = null;
	
	public void startDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void connect() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://insurance2021.cjxccmnvymye.us-east-2.rds.amazonaws.com:3306/insurance_2021?serverTimezone=UTC&useSSL=false", "user", "12345678");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean execute(String str) {
		try {
			st = conn.createStatement();
			st.executeUpdate(str);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void close() {
		try {
    		if(st != null) st.close();
    		if(conn != null) conn.close();
    		if(rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean read(String str){
		try {
			st = conn.createStatement();
			rs = st.executeQuery(str);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    }

}
