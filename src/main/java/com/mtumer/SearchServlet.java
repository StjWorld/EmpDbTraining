package com.mtumer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtumer.model.Employee;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		List<Employee> list = EmpFetch.getByName(name);
        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Country</th> <th>Edit</th><th>Delete</th></tr>");
        for(Employee e:list){
        	if(name.equals(e.getName())) {
	            out.print("<tr><td>"+e.getId()+"</td><td>"+e.getName()+"</td><td>"+e.getRole()+"</td> <td>"+e.getCountry()+"</td>"
	                    +"<td><a href='EditServlet?id="
	                    +e.getId()+"'>edit</a></td> <td><a href='DeleteServlet?id="
	                    +e.getId()+"'>delete</a></td></tr>");
        	}
        }
        out.print("</table>");

        out.close();
		
	}
}