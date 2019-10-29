/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import model.User;

/**
 *
 * @author vinu_g
 */
public class GeneratePDF {

    public static boolean generatePDF(String fileName) throws FileNotFoundException, DocumentException {

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\GeneratedReports\\" + fileName + ".pdf"));
        document.open();

        try {
            BaseFont base = BaseFont.createFont("C:\\Windows\\Fonts\\GOTHIC.TTF", BaseFont.WINANSI, BaseFont.EMBEDDED);

            Font pfont = new Font(base, 12, Font.BOLD, BaseColor.DARK_GRAY);
            Font hfont = new Font(base, 10, Font.BOLD);
            Font dfont = new Font(base, 10, Font.NORMAL);

            document.add(new Paragraph("All users", pfont));

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(110);

            table.setSpacingAfter(11f);
            table.setSpacingBefore(11f);

            float[] colWidth = {8f, 8f};
            table.setWidths(colWidth);
            PdfPCell ch1 = new PdfPCell(new Paragraph("Employee Full Name", hfont));
            PdfPCell ch2 = new PdfPCell(new Paragraph("Employee Username", hfont));
            
//            ch1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            ch2.setHorizontalAlignment(Element.ALIGN_CENTER);
            

            ch1.setPadding(5f);
            ch2.setPadding(5f);
           
            table.addCell(ch1);
            table.addCell(ch2);
            


            ArrayList<User> userList = CustomerDAO.retrieveAllUsers();
		int x=0;
		while(x<userList.size()) {
			User u1 = userList.get(x);
			PdfPCell ch10=new PdfPCell (new Paragraph(u1.getFullname(),dfont));
			PdfPCell ch11=new PdfPCell (new Paragraph(u1.getUsername(),dfont));
			
			
//			ch10.setHorizontalAlignment(Element.ALIGN_CENTER);
//			ch11.setHorizontalAlignment(Element.ALIGN_CENTER);
//			
//			
//			ch10.setVerticalAlignment(Element.ALIGN_MIDDLE);
//			ch11.setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			
			ch10.setPadding(5f);
			ch11.setPadding(5f);
			
			
			table.addCell(ch10);
			table.addCell(ch11);
			
			
			x++;
		}
			
            document.add(table);
            document.close();
            writer.close();
            return true;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;

    }
}
