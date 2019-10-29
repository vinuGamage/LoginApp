/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author vinu_g
 */
public class CustomerDAO {

    private static String query1 = "INSERT INTO user (fullname,username,password) VALUES (?,?,?)";
    private static String query2 = "SELECT * FROM user";
    private static String query3 = "SELECT * FROM user WHERE username = ?";
    private static Connection con;

    public static User retrieveUser(String username){
        try {
            con = DBConnection.ConnectDB();
          
            PreparedStatement ps = con.prepareStatement(query3);
             ps.setString(1,username);
            ResultSet rst = ps.executeQuery();
            User u1 = null;
           // ArrayList<User> users = new ArrayList<User>();
          // int count=0;
            while(rst.next()){
                u1 = new User(rst.getString(3),rst.getString(4),rst.getString(2));
               // users.add(u1);
               // count++;
            }
		System.out.println("paginated");
		return u1 ;
          
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
          return null;
    }
    
    
    
    
    //inserting user to the database
    public static boolean insertUsers(User u) {

        try {
            con = DBConnection.ConnectDB();
            PreparedStatement ps = con.prepareStatement(query1);
            ps.setString(1, u.getFullname());
            //System.out.println("fullname : " +  u.getFullname());
            ps.setString(2, u.getUsername());
            // System.out.println("username : " +  u.getUsername());
            ps.setString(3, u.getPassword());
            //System.out.println("password : " +  u.getPassword());
            System.out.println("inserted");
            ps.execute();

            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

//    public static void myFunction(String n){
//       if(ValidateDAO.checkExistence(n))
//            JOptionPane.showMessageDialog(null, "It already exists");
//       
//    }
    
    public static ArrayList<User> retrieveUsers(int start,int total){
        try {
            con = DBConnection.ConnectDB();
            PreparedStatement ps=con.prepareStatement(  
"SELECT * FROM user limit "+(start)+","+total);  
            //PreparedStatement ps = con.prepareStatement(query2);
            ResultSet rst = ps.executeQuery();
            
            ArrayList<User> users = new ArrayList<User>();
           int count=0;
            while(rst.next()){
                User u1 = new User(rst.getString(3),rst.getString(4),rst.getString(2));
                users.add(u1);
                count++;
            }
		System.out.println("paginated");
		return users ;
          
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
          return null;
    }
    
    public static int getCount(){
        try{
        con = DBConnection.ConnectDB();
          
          PreparedStatement ps = con.prepareStatement(query2);
            ResultSet rst = ps.executeQuery();
            
            //ArrayList<User> users = new ArrayList<User>();
           int count=0;
            while(rst.next()){
               
                count++;
            }
			
		return count ;
          
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return 0;
    }
 
     public static ArrayList<User> retrieveAllUsers(){
        try {
            con = DBConnection.ConnectDB();
          
            PreparedStatement ps = con.prepareStatement(query2);
            ResultSet rst = ps.executeQuery();
            
            ArrayList<User> users = new ArrayList<User>();
          // int count=0;
            while(rst.next()){
                User u1 = new User(rst.getString(3),rst.getString(4),rst.getString(2));
                users.add(u1);
               // count++;
            }
		System.out.println("paginated");
		return users ;
          
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
          return null;
    }
    
    
    
}
