/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import service.CustomerDAO;
import service.ValidateDAO;

/**
 *
 * @author vinu_g
 */
public class LoginTest {

    String myHash = ValidateDAO.encryptThisString("pami");
    String myHash1 = ValidateDAO.encryptThisString("chathuma");
    String myHash2 = ValidateDAO.encryptThisString("rivinu");
   
    User u = new User("pami", myHash, "Pamitha Gamage");
    User u1 = new User("chathuma", myHash, "Chathuma Gamage");
    User u2 = new User("rivinu", myHash, "Rivinu Gamage");

    public LoginTest() {
        
    }
//    
//    @Test
//    public void testInsert() {
//        boolean insert = CustomerDAO.insertUsers(u);
//        assertTrue("User pamitha was successfully inserted", insert);
//        assertTrue("User chathuma was successfully inserted", CustomerDAO.insertUsers(u1));
//        assertTrue("User rivinu was successfully inserted", CustomerDAO.insertUsers(u2));
//    }
//    
    
    @Test
    public void testRetreive() {
        User u111 = CustomerDAO.retrieveUser("pami");
         User u11 = CustomerDAO.retrieveUser("chathuma");
          User u21 = CustomerDAO.retrieveUser("rivinu");
        assertEquals(u, u111);
         assertEquals(u1, u11);
        assertEquals(u2, u21);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
