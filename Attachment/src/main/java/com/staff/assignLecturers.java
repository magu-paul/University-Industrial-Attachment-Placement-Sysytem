package com.staff;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class assignLecturers
 */
@WebServlet("assignLecturers")
public class assignLecturers extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String school = request.getParameter("school").toUpperCase();
		String depid = request.getParameter("dep").toUpperCase();
		String url ="jdbc:mysql://localhost:3306/attachment";
		String uname = "root";
		String pass = "bmpg1998";
		int no_of_staff = 0;
		int no_of_students = 0;
		int students_per_staff = 0;
		
		Connection con = null;
		try {
		getClass().forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url,uname,pass);
		
		PreparedStatement count_staff = con.prepareStatement("select count(su.staff_no) from staff_users su join staff s on su.staff_no = s.staff_id where s.school_id = ? and depid=?");
		count_staff.setString(1, school);
		count_staff.setString(2, depid);
		ResultSet staff_count = count_staff.executeQuery();
		if(staff_count.next()) {
			no_of_staff =Integer.parseInt(staff_count.getString(1));
		}
		PreparedStatement count_student = con.prepareStatement("select count(su.regno) from stud_users su join student s using(regno) where s.school_id = ? and depid=?");
		count_student.setString(1, school);
		count_student.setString(2, depid);
		ResultSet student_count = count_student.executeQuery();
		if(student_count.next()) {
			no_of_students =Integer.parseInt(student_count.getString(1));
		}
		
		PreparedStatement ps = con.prepareStatement("select su.staff_no from staff_users su join staff s on su.staff_no = s.staff_id where s.school_id = ? and s.depid=?");
		ps.setString(1, school);
		ps.setString(2, depid);
		ResultSet staff_ids = ps.executeQuery();
		
		PreparedStatement ps2 = con.prepareStatement("select su.regno from stud_users su join student s using(regno) where s.school_id = ? and s.depid=?");
		ps2.setString(1, school);
		ps2.setString(2, depid);
		ResultSet student_reg = ps2.executeQuery();
		
		students_per_staff = no_of_students/no_of_staff;
		PreparedStatement update = con.prepareStatement("update stud_users set staff= ? where regno = ?");
		int checkout = 0;
		for(int i=0; i<no_of_staff;i++) {
			if(staff_ids.next()) {
				int count = 0;
				for(int j=0; j<no_of_students && count <=students_per_staff;j++) {
					if(student_reg.next()) {
						
						update.setString(1, staff_ids.getString(1));
						update.setString(2, student_reg.getString(1));
						checkout = update.executeUpdate();
						
						count++;
					}
				}
				count=0;
			}else
				break;
		}
		if(checkout > 0) {
			response.sendRedirect("session.jsp");
		}else
			response.getWriter().println("Failed to assign lecturers");
		
		}catch(Exception e) {
			
		}
	}

}
