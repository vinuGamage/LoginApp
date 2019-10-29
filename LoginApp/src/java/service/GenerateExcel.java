/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import model.User;

/**
 *
 * @author vinu_g
 */
public class GenerateExcel {

    public static boolean generateXcel(String fileName) throws IOException, WriteException {

        File file = new File("E:\\GeneratedReports\\" + fileName + ".xls");

        WritableWorkbook myexcel = Workbook.createWorkbook(file);
        WritableSheet mysheet = myexcel.createSheet(fileName, 0);
        Label l1 = new Label(0, 0, "Employee Full Name");
        Label l2 = new Label(1, 0,  "EmployeeUsername");
        mysheet.addCell(l1);
        mysheet.addCell(l2);
        ArrayList<User> userList = CustomerDAO.retrieveAllUsers();
        int x = 0;
        while (x < userList.size()) {
            User u1 = userList.get(x);
            Label l3 = new Label(0, x+2, u1.getFullname());
            Label l4 = new Label(1, x+2, u1.getUsername());

            mysheet.addCell(l3);
            mysheet.addCell(l4);

            x++;
        }
        
        myexcel.write();
        myexcel.close();
        return true;
    }

}
