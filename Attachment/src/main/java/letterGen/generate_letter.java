package letterGen;


import java.io.FileNotFoundException;

 
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class generate_letter {

	public static void main(String[] args) throws DocumentException, MalformedURLException, IOException {
		String file_name = "C:\\Users\\magup\\Desktop\\letters\\letter.pdf";
		Document letter = new Document();
		
		PdfWriter.getInstance(letter, new FileOutputStream(file_name));
		letter.open();
		
		Paragraph heading = new Paragraph("KISII UNIVERSITY");
		letter.add(heading);
		Paragraph space = new Paragraph("\n");
		letter.add(space);
		Paragraph txt1 = new Paragraph("RE : TO WHOM IT MAY CONCERN");
		letter.add(txt1);
		Paragraph txt2 = new Paragraph("The above name person is a student of Kisii University, registered in the school of _ , pursuing _.");
		letter.add(txt2);
		Paragraph txt3 = new Paragraph("In partial fulfillment of the course, the curriculum requires that the student should should go for field attachment for twelve"
				+ "(12) weeks for practical experience. Your institution has been identified as one of the relevant places our students can gain experience and quidance.");
		letter.add(txt3);
		Paragraph txt4 = new Paragraph("The purpose of this letter is therefore to introduce the student to you and to request you to accept them in your organization beginning _____ "
				+ "and accord them any necessary support during their stay with you.");
		letter.add(txt4);
		Paragraph txt5 = new Paragraph("Please note that insurance cover for the student will be processed on commencement of the attachment period.");
		letter.add(txt5);
		Paragraph txt6 = new Paragraph("Yours faithfully,");
		letter.add(txt6);
		
		letter.close();
		System.out.println("finished");
	}

}
