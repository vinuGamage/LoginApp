/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import model.User;

/**
 *
 * @author vinu_g
 */
public class ValidateDAO {

    private static String query1 = "SELECT * FROM user WHERE username = ?";
    private static Connection con;

    //Retrieving the available users password for validation
    public static String validateUser(User u) {
        try {
            con = DBConnection.ConnectDB();

            PreparedStatement pst = con.prepareStatement(query1);
            pst.setString(1, u.getUsername());
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                return rst.getString("password");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    //Further validating the hashed the password
    public static boolean hashingPaswordandValidating(String pass, User u) throws NoSuchAlgorithmException {

        String hash = validateUser(u);

        if (pass.equals(hash)) {
            return true;
        } else {
            return false;
        }

    }

    //Hashing the password
    public static String encryptThisString(String input) {
        try {
            // getInstance() method is called with algorithm SHA-1 
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // digest() method is called 
            // to calculate message digest of the input string 
            // returned as array of byte 
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value 
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText 
            return hashtext;
        } // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //Checking whether the user is already existing or not
    public static boolean checkExistence(String user) {

        System.out.println("User........" + user);
        try {
            con = DBConnection.ConnectDB();

            PreparedStatement pst = con.prepareStatement(query1);
            pst.setString(1, user);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                return true;
            }
            System.out.println("in db");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
