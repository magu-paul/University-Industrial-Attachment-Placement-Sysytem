package com.student;

import java.sql.*;
import java.util.ArrayList;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("register")
public class RegisterServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regno = request.getParameter("reg").toString();
		String email = request.getParameter("email");
		String id =request.getParameter("id");
		String station = request.getParameter("station");
		String company = request.getParameter("company");
		String phone = request.getParameter("contact");
		String pass = request.getParameter("pass");
		int year =Integer.parseInt( regno.substring(11));
		String school = "";
		String category = "";
		
		String LAW = "";
		String SANRM = "";
		String SASS = "";
		String SEHURED = "";
		String SHS = "";
		String SIST = "";
		String SOBE = "";
		String SPAS = "";
		String dip ="";
		String deg ="";
		int max_year = 0 ;
		ArrayList<String> schools = new ArrayList<>();
		
		
		String url="jdbc:mysql://localhost:3306/attachment";
		String uname ="root";
		String password = "bmpg1998";
		
		HttpSession session = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,uname,password);
			PreparedStatement ps = con.prepareStatement("insert into stud_users (regno,email,id,station,company,phone,pass) values(?,?,?,?,?,?,?)");
			PreparedStatement check = con.prepareStatement("select * from student where regno = ?");
			//cohort
			
			Statement checkLaw = con.createStatement();
			Statement checkSANRM = con.createStatement();
			Statement checkSASS = con.createStatement();
			Statement checkSEHURED = con.createStatement();
			Statement checkSHS = con.createStatement();
			Statement checkSIST = con.createStatement();
			Statement checkSOBE = con.createStatement();
			Statement checkSPAS = con.createStatement();
			Statement checkDip = con.createStatement();
			
			
			
			
			
			check.setString(1, regno);
			
			ps.setString(1, regno);
			ps.setString(2, email);
			ps.setString(3, id);
			ps.setString(4, station);
			ps.setString(5, company);
			ps.setString(6, phone);
			ps.setString(7, pass);
			
			if(checkLaw.executeQuery("select LAW from cohort where LAW = '1'").next()) {
			LAW = "LAW";
			schools.add(LAW);
			}
			if(checkSANRM.executeQuery("select SANRM from cohort where SANRM = '1'").next()) {
				SANRM="SANRM";
				schools.add(SANRM);
				}
			if(checkSASS.executeQuery("select SASS from cohort where SASS = '1'").next()) {
				SASS = "SASS";
				schools.add(SASS);
				}
			if(checkSEHURED.executeQuery("select SEHURED from cohort where SEHURED = '1'").next()) {
				SEHURED = "SEHURED";
				schools.add(SEHURED);
				}
			if(checkSHS.executeQuery("select SHS from cohort where SHS = '1'").next()) {
				SHS = "SHS";
				schools.add(SHS);
				}
			if(checkSIST.executeQuery("select SIST from cohort where SIST = '1'").next()) {
				SIST = "SIST";
				schools.add(SIST);
				}
			if(checkSOBE.executeQuery("select SOBE from cohort where SOBE = '1'").next()) {
				SOBE = "SOBE";
				schools.add(SOBE);
				}
			if(checkSPAS.executeQuery("select SPAS from cohort where SPAS = '1'").next()) {
				SPAS = "SPAS";
				schools.add(SPAS);
				}
			ResultSet cat = checkDip.executeQuery("select diploma,degree from cohort limit 1");
			if(cat.next()) {
				dip = cat.getString(1);
				deg = cat.getString(2);
			}
			
			
			ResultSet rs = check.executeQuery();
			if(rs.next()) {
				school = rs.getString(8);
				category = rs.getString(9);
				
				
			}
			if(category.equals("1")) {
				max_year = Integer.parseInt(deg);
			
			}
			if(category.equals("2")) {
				max_year = Integer.parseInt(dip);
				
			}
			
			if(schools.contains(school) && (year <= max_year)) { 
			int count = ps.executeUpdate();
			
			if(count > 0) {
				
				response.sendRedirect("student_login.jsp");	
				
			}else {
				
				response.sendRedirect("student_registration.jsp");	
			}
			}else {
				response.getWriter().println("Not allowed to register");
			}
			
		
			
		}catch(Exception e) {
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
