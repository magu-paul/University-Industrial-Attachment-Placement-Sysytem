package com.staff;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updatePassStaff
 */
@WebServlet("updatePassStaff")
public class updatePassStaff extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String staffno = req.getParameter("staffno");
		String phone = req.getParameter("phone");
		String pass = req.getParameter("pass");
		
		String url="jdbc:mysql://localhost:3306/attachment";
		String uname ="root";
		String password ="bmpg1998";
		String sql ="update staff_users set pass = ? where staff_no = ? and contact = ?";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,password);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, pass);
			st.setString(2, staffno);
			st.setString(3, phone);
			int count = st.executeUpdate();
			if(count > 0) {
				res.sendRedirect("staff_login.jsp");
			}else {
				res.getWriter().println("update error");
			}
		} catch (ClassNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
