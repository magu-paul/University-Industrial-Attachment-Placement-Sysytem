package com.student;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("login")
public class Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regno = request.getParameter("reg");
		String pass = request.getParameter("pass");
		
		HttpSession session = request.getSession();
		String url="jdbc:mysql://localhost:3306/attachment?useSSL=false ";
		String uname="root";
		String password = "bmpg1998";
		
		String sql ="select * from stud_users where regno= ? and pass = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,password);
			PreparedStatement ps = con.prepareStatement(sql);
			PreparedStatement data = con.prepareStatement("select fname,lname from student where regno = ?");
			data.setString(1, regno);
			ps.setString(1, regno);
			ps.setString(2, pass);
			
			
			
			ResultSet rs = ps.executeQuery();
			ResultSet udata = data.executeQuery();
			udata.next();
			if(rs.next()) {
				String path = "letters/"+regno.replace("/","-")+".pdf";
				session.setAttribute("regno", regno);
				session.setAttribute("letter", path);
				session.setAttribute("uname", udata.getString(1)+" "+udata.getString(2));
				response.sendRedirect("student_home.jsp");
			}else {
				
				response.sendRedirect("student_login.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}

}
