package com.staff;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("registration")
public class registration extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String staffno = request.getParameter("staffno");
		String contact = request.getParameter("contact");
		String pass = request.getParameter("pass");
		String re_pass = request.getParameter("re_pass");
		
		String url = "jdbc:mysql://localhost:3306/attachment";
		String uname = "root";
		String password = "bmpg1998";
		
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,uname,password);
			PreparedStatement ps = con.prepareStatement("insert into staff_users (staff_no,pass,contact) values(?,?,?)");
			
			
			ps.setString(1, staffno);
			ps.setString(2, pass);
			ps.setString(3, contact);
			
			
			int count = ps.executeUpdate();
			
			
			if(count > 0) {
				response.sendRedirect("staff_login.jsp");
			}else {
				response.sendRedirect("staff_Registration.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
