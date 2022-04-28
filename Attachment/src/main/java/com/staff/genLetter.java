package com.staff;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class genLetter
 */
@WebServlet("genLetter")
public class genLetter extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "jdbc:mysql://localhost:3306/attachment";
		String uname = "root";
		String password = "bmpg1998";
		Connection con = null;
		String session = "";
		String file_name = "";
		int count = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,uname,password);
			Statement st = con.createStatement();
			ResultSet countStud = st.executeQuery("select count(*) from stud_users");
			if(countStud.next()) {
				count =Integer.parseInt(countStud.getString(1));
			}
			ResultSet sesName = st.executeQuery("select session from cohort limit 1");
			if(sesName.next()) {
				session = sesName.getString(1);
			}
			ResultSet rs = st.executeQuery("select st.regno,st.fname,st.mname,st.lname,s.name,c.name from stud_users stu join student st using(regno) join school s \r\n"
					+ "on s.id=st.school_id join courses c using(course_id)");
			for(int i =0; i< count; i++) {
				if(rs.next()) {
					String reg = rs.getString(1);
					String school = rs.getString(5);
					String course = rs.getString(6);
					String fname = rs.getString(2);
					String mname = rs.getString(3);
					String lname = rs.getString(4);
					String full_name = fname+" "+mname+" "+lname;
					full_name = full_name.toUpperCase();
					String regPath = reg.replace("/", "-");
					file_name = "C:\\Users\\magup\\eclipse-workspace\\Attachment\\src\\main\\webapp\\letters\\".concat(regPath).concat(".pdf");
					file_name = file_name.toString();
					Document letter = new Document();
					try {
					PdfWriter.getInstance(letter, new FileOutputStream(file_name));
					letter.open();
					
					Paragraph heading = new Paragraph("KISII UNIVERSITY");
					letter.add(heading);
					Paragraph space = new Paragraph("\n");
					letter.add(space);
					Paragraph rec = new Paragraph("TO WHOM IT MAT CONCERN");
					letter.add(rec);
					Paragraph sal = new Paragraph("Dear Sir/Madam");
					letter.add(sal);
					letter.add(space);
					Paragraph txt1 = new Paragraph("RE : ATTACHMENT REQUEST FOR "+full_name+"-REG. NO. "+reg);
					letter.add(txt1);
					letter.add(space);
					Paragraph txt2 = new Paragraph("The above name person is a student of Kisii University, registered in the " +school +", pursuing "+course+".");
					letter.add(txt2);
					Paragraph txt3 = new Paragraph("In partial fulfillment of the course, the curriculum requires that the student should should go for field attachment for twelve"
							+ "(12) weeks for practical experience. Your institution has been identified as one of the relevant places our students can gain experience and quidance.");
					letter.add(txt3);
					Paragraph txt4 = new Paragraph("The purpose of this letter is therefore to introduce the student to you and to request you to accept them in your organization beginning "+
							session+ " and accord them any necessary support during their stay with you.");
					letter.add(txt4);
					Paragraph txt5 = new Paragraph("Please note that insurance cover for the student will be processed on commencement of the attachment period.");
					letter.add(txt5);
					Paragraph txt6 = new Paragraph("Yours faithfully,");
					letter.add(txt6);
					
					letter.close();
					}catch(Exception e) {
						
					}
				}else {
					break;
				}
				
			}
			response.sendRedirect("session.jsp");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
