package com.mtumer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mtumer.model.Employee;


public class dbProc {
	
	public static Connection getConnection() throws ClassNotFoundException {
		Connection con = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","root");
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
				
	}
	public static int save (Employee emp) throws ClassNotFoundException, SQLException {
		int status = 0;
		Connection con = dbProc.getConnection();
		PreparedStatement stmt = con.prepareStatement("insert into employee(name,role,country) values (?,?,?)");
		stmt.setString(1, emp.getName());
		stmt.setString(2, emp.getRole());
		stmt.setString(3, emp.getCountry());
		status = stmt.executeUpdate();
		con.close();
		return status;
	}

}
