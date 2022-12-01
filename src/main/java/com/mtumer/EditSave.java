package com.mtumer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtumer.model.Employee;
import com.mtumer.EmpFetch;


@WebServlet("/EditSave")
public class EditSave extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        String sid=request.getParameter("id");
        int id=Integer.parseInt(sid);
        String name=request.getParameter("name");
        String role=request.getParameter("role");
        String country=request.getParameter("country");

        Employee e=new Employee();
        e.setId(id);
        e.setName(name);
        e.setRole(role);
        e.setCountry(country);

        int status= EmpFetch.saveEdit(e);
        if(status>0){
        	request.getRequestDispatcher("index.jsp").forward(request, response);
        }else{
            out.println("Failed to Save Changes");
        }

        out.close();
    }

}