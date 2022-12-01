package com.mtumer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mtumer.model.Employee;

public class EmpFetch {
	public static List<Employee> getByName(String name) {
        List<Employee> list=new ArrayList<Employee>();

        try{
            Connection con=EmpFetch.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from employee");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Employee e=new Employee();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setRole(rs.getString(3));
                e.setCountry(rs.getString(4));

                list.add(e);
            }
            con.close();
        }catch(Exception e){e.printStackTrace();}

        return list;
	}

	public static List<Employee> getAllEmployees() {
        List<Employee> list=new ArrayList<Employee>();

        try{
            Connection con=EmpFetch.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from employee");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Employee e=new Employee();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setRole(rs.getString(3));
                e.setCountry(rs.getString(4));

                list.add(e);
            }
            con.close();
        }catch(Exception e){e.printStackTrace();}

        return list;
	}

    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","root");

        }catch(Exception e){System.out.println(e);}
        return con;
    }

	public static Employee getEmployeeById(int id) {
        Employee e=new Employee();

        try{
            Connection con=EmpFetch.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from employee where id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setRole(rs.getString(3));
                e.setCountry(rs.getString(4));
            }
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return e;
	}

	public static int saveEdit(Employee e) {
        int editResult = 0;
        try{
            Connection con=EmpFetch.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "update employee set name=?,role=?,country=? where id=?");
            ps.setString(1,e.getName());
            ps.setString(2,e.getRole());
            ps.setString(3,e.getCountry());
            ps.setInt(4,e.getId());

            editResult = ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return editResult;
        
	}

	public static int delete(int id) {
        int deleted = 0;
        try{
            Connection con=EmpFetch.getConnection();
            PreparedStatement ps=con.prepareStatement("delete from employee where id=?");
            ps.setInt(1,id);
            deleted = ps.executeUpdate();

            con.close();
        }catch(Exception e){e.printStackTrace();}

        return deleted;
		
	}




}
