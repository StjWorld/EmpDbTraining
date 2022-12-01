package com.mtumer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtumer.model.Employee;

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String role = request.getParameter("role");
		String country = request.getParameter("country");
		
		Employee emp = new Employee();
				
		emp.setName(name);
		emp.setRole(role);
		emp.setCountry(country);
		
		int status;
		try {
			status = dbProc.save(emp);
			if(status>0) {
				out.print("<meta http-equiv='refresh' content='5;URL=index.jsp'>");
				out.print("<p style='color:green;'>Employee Saved!</p>"
						+ "<p style='color:green;'> Redirecting to login page</p>");
			}else {
				out.println("<p style='color:red;'>Some problem ocurred. Could not save employee.</p>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

		
	}
}