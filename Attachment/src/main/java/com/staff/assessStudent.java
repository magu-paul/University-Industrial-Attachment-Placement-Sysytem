package com.staff;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class assessStudent
 */
@WebServlet("assessStudent")
public class assessStudent extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String regno = req.getParameter("regno");
		int marks = Integer.parseInt(req.getParameter("marks"));
		
		String url="jdbc:mysql://localhost:3306/attachment";
		String uname ="root";
		String password ="bmpg1998";
		String sql ="update stud_users set marks = ? where regno = ?";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,password);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, marks);
			st.setString(2, regno);
			
			int count = st.executeUpdate();
			if(count > 0) {
				res.sendRedirect("students.jsp");
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
