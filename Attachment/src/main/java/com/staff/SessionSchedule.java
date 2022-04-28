package com.staff;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SessionSchedule
 */
@WebServlet("schedule")
public class SessionSchedule extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String session = req.getParameter("date");  
		String dip = req.getParameter("dip"); 
		String deg = req.getParameter("deg");
		
		 String LAW = "";
		 String SANRM = "";
		 String SASS = "";
		 String SEHURED = "";
		 String SHS = "";
		 String SIST = "";
		 String SOBE = "";
		 String SPAS = "";
		
		
		String schools[]= req.getParameterValues("schools");
		
		
		List schoolList = Arrays.asList(schools);
		
		if(schoolList.contains("SIST")) {
			SIST = "1";
		}else {
			SIST = "0";
		}
		if(schoolList.contains("LAW")) {
			LAW = "1";
		}else {
			LAW = "0";
		}
		if(schoolList.contains("SANRM")) {
			SANRM = "1";
		}else {
			SANRM = "0";
		}
		if(schoolList.contains("SASS")) {
			SASS = "1";
		}else {
			SASS = "0";
		}
		if(schoolList.contains("SEHURED")) {
			SEHURED = "1";
		}else {
			SEHURED = "0";
		}
		if(schoolList.contains("SHS")) {
			SHS = "1";
		}else {
			SHS = "0";
		}
		if(schoolList.contains("SOBE")) {
			SOBE = "1";
		}else {
			SOBE = "0";
		}
		if(schoolList.contains("SPAS")) {
			SPAS = "1";
		}else {
			SPAS = "0";
		}
		
		
		
		
		String url = "jdbc:mysql://localhost:3306/attachment";
		String uname = "root";
		String pass = "bmpg1998";
	    String sql ="insert into cohort (session,LAW,SANRM,SASS,SEHURED,SHS,SIST,SOBE,SPAS,diploma,degree) values(?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,uname,pass);
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1,session);
			st.setString(2,LAW);
			st.setString(3,SANRM);
			st.setString(4,SASS);
			st.setString(5,SEHURED);
			st.setString(6,SHS);
			st.setString(7,SIST);
			st.setString(8,SOBE);
			st.setString(9,SPAS);
			st.setString(10,dip);
			st.setString(11,deg);
			
			int count = st.executeUpdate();
			
			
			if(count > 0) {
				res.sendRedirect("session.jsp");
			}else {
				res.sendRedirect("session.jsp");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
