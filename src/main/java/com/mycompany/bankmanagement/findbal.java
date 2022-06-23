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
public class findbal {
    float balance(String email){
        final String ins="select balance from balancefacilities where emailid=? ";
        float temp=0;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
            PreparedStatement pstmt=con.prepareStatement(ins);
            pstmt.setString(1,email);
            pstmt.executeUpdate();
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                temp=rs.getFloat("balance");
            }
            con.close();
        }
        catch(Exception e){
            error er=new error();
            er.error(String.valueOf(e));
        }
        return temp;
    }
}
