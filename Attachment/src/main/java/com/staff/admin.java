package com.staff;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class admin
 */
@WebServlet("admin")
public class admin extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String staffno = (String) session.getAttribute("staffno");
		
		
		String url = "jdbc:mysql://localhost:3306/attachment";
		String uname = "root";
		String password = "bmpg1998";
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,uname,password);
			PreparedStatement st = con.prepareStatement("select staff_no from staff_users where staff_no = ? and role = 'admin'");
			st.setString(1, staffno);
			ResultSet adminCheck = st.executeQuery();
			if(adminCheck.next()) {
				response.sendRedirect("Admin_home.jsp");
			}else {
				response.sendRedirect("staff_home.jsp");	
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
