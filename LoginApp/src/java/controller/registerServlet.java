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
public class registerServlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet registerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet registerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            System.out.println("name : " + name);
            String username = req.getParameter("user");
             System.out.println("username : " + username);
            String pass = req.getParameter("pass");
             System.out.println("password : " + pass);
            
            
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(pass.getBytes());
//            byte[] digest = md.digest();
//            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

                
               //Checking whether the user is already exists with that same username
               if(ValidateDAO.checkExistence(username)){
                    JOptionPane.showMessageDialog(null, "Username already exits");
                    req.getRequestDispatcher("register.jsp").forward(req, response);
               }else{
                   //encrypting the entered password
                    String myHash = ValidateDAO.encryptThisString(pass);
            
                        User u = new User(username,myHash,name);
                        System.out.println("password : " + myHash);
                        
                        //If the insertion of the user was successfully done
                        if(CustomerDAO.insertUsers(u)){
                                HttpSession session = req.getSession();
                                session.setAttribute("username", u);
                                req.getRequestDispatcher("login.jsp").forward(req, response);
                        }
                        else{
                               req.getRequestDispatcher("register.jsp").forward(req, response);
                        }

               
              }

           
        } catch (Exception ex) {
            Logger.getLogger(registerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
