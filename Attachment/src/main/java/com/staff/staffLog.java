package com.staff;

import java.io.IOException;

import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("staffLog")
public class staffLog extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String staffno = request.getParameter("staffno");
		String pass = request.getParameter("pass");
		
		String url = "jdbc:mysql://localhost:3306/attachment";
		String uname = "root";
		String password = "bmpg1998";
		
		HttpSession session = request.getSession();
		String sql = "select * from staff_users where staff_no=? and pass = ?";
		Connection con = null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url,uname,password);
		PreparedStatement ps = con.prepareStatement(sql);
		PreparedStatement data = con.prepareStatement("select fname,lname from staff where staff_id = ?");
		data.setString(1, staffno);
		ps.setString(1,staffno);
		ps.setString(2, pass);
		ResultSet rs = ps.executeQuery();
		ResultSet udata = data.executeQuery();
		udata.next();
		if(rs.next()) {
			session.setAttribute("staffno", staffno);
			session.setAttribute("name", udata.getString(1)+" "+udata.getString(2));
			response.sendRedirect("staff_home.jsp");
		}else {
			response.sendRedirect("staff_login.jsp");
		}
		}catch(Exception e) {
			
		}finally {
			
		}
	}

}
