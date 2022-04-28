package com.staff;

import java.io.IOException;
import java.sql.*;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("schedule")
public class Schedule_session extends HttpServlet
{
public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
	String date = req.getParameter("date");
	String school = req.getParameter("groups");
	
	String url = "jdbc:mysql://localhost:3306/attachment";
	String uname = "root";
	String pass = "bmpg1998";
	Connection con;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url,uname,pass);
		PreparedStatement ps = con.prepareStatement("insert into sessions values(?,?)");
		ps.setString(1,date);
		ps.setString(2, school);
		ps.executeUpdate();
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
