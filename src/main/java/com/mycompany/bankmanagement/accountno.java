/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author SHAW
 */
public class accountno {
    int accountno(String email){
        int accountno=0;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
            String acnt="select accountno from accountdetails where emailid=?";
            PreparedStatement stmt=con.prepareStatement(acnt);
            stmt.setString(1,email);
            //stmt.setString(2,pas);
            ResultSet rs=stmt.executeQuery();
            rs.next();
            accountno=rs.getInt(1);
            con.close();
            
        }catch(Exception e){
            System.out.print(e);
        }
        return accountno;
    }
}
