/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;
import model.User;          
import service.CustomerDAO;
import service.ValidateDAO;

/**
 *
 * @author vinu_g
 */
public class loginServlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet loginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
       
        int page = 1;
            int recordsPerPage = 3;
        
                System.out.println("pages :" + req.getParameter("page"));
           if(req.getParameter("page") != null)
                page = Integer.parseInt(req.getParameter("page"));
                List<User> list = CustomerDAO.retrieveUsers((page-1)*recordsPerPage,
                                     recordsPerPage);
            
            int noOfRecords = CustomerDAO.getCount();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            req.setAttribute("employeeList", list);
                System.out.println("    List " + list.get(0).getFullname());
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
        //String  page=req.getParameter("page");
         req.getRequestDispatcher("AllUsers.jsp").forward(req, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            HttpSession session = req.getSession();
            User p = (User)session.getAttribute("username");
            //String pass = p.getPassword();
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            System.out.println("Username" + username);
            System.out.println("Password" + password);
            User user = new User(username, password);
            
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(password.getBytes());
//            byte[] digest = md.digest();
//            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            
            String myHash = ValidateDAO.encryptThisString(password);
            //Validating the password and username
            if(ValidateDAO.hashingPaswordandValidating(myHash,user)){
                
                JOptionPane.showMessageDialog(null, "You are successfully logged in");
        
            int page = 1;
            int recordsPerPage = 3;
        
                System.out.println("pages :" + req.getParameter("page"));
           if(req.getParameter("page") != null)
                page = Integer.parseInt(req.getParameter("page"));
                List<User> list = CustomerDAO.retrieveUsers((page-1)*recordsPerPage,
                                     recordsPerPage);
            
            int noOfRecords = CustomerDAO.getCount();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            req.setAttribute("employeeList", list);
                System.out.println("    List " + list.get(0).getFullname());
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
                req.getRequestDispatcher("AllUsers.jsp?page=1").forward(req, response);
            }else{
                     JOptionPane.showMessageDialog(null, "Invalid password or username");
                     req.getRequestDispatcher("login.jsp").forward(req, response);
                     
                     
            }
     
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
