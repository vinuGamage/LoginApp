                                                                                                                                                                                                                                    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import jxl.write.WriteException;
import service.GenerateExcel;

/**
 *
 * @author vinu_g
 */
public class GenerateReportExcel extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GenerateReportExcel</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GenerateReportExcel at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
       
        String fileName = req.getParameter("fileName");
        try {
            if (GenerateExcel.generateXcel(fileName)) {
                JOptionPane pane = new JOptionPane("XCEL Successfully created in E:\\GeneratedReports\\" + fileName + ".xsl", JOptionPane.OK_OPTION);
                JDialog dialog = pane.createDialog("Status");
                dialog.setAlwaysOnTop(true);
                dialog.show();
                req.getRequestDispatcher("AllUsers.jsp").forward(req, response);
            } else {
                JOptionPane pane = new JOptionPane("Error generating XCEL", JOptionPane.OK_OPTION);
                JDialog dialog = pane.createDialog("Status");
                dialog.setAlwaysOnTop(true);
                dialog.show();
                req.getRequestDispatcher("AllUsers.jsp").forward(req, response);
            }
        } catch (WriteException ex) {
            Logger.getLogger(GenerateReportExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
