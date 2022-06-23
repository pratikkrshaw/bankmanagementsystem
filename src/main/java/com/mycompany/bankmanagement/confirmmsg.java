/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankmanagement;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
/**
 *
 * @author SHAW
 */
public class confirmmsg {
    static boolean success;
    boolean confirm(int acnt){
        
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        int width=(int)dim.getWidth();
        int height=(int)dim.getHeight()-35;
        JFrame f=new JFrame();
        JDialog d=new JDialog(f,"Confirm");
        d.setLocation(width/2-200,height/2-75);
        d.setVisible(true);
        d.setSize(400,150);
        
        JPanel p=new JPanel();
        p.setLayout(null);
        JLabel sure=new JLabel("<html>You won't be able to access your account anymore.<br>Do you want to proceed?</html>",JLabel.CENTER);
        sure.setBounds(0,0,380,70);
        p.add(sure);
        JButton yclose=new JButton("Yes");
        yclose.setBounds(200-80,75,50,20);
        yclose.setBackground(new java.awt.Color(255,92,92));
        yclose.setBorder(null);
        error er=new error();
        yclose.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                //boolean found=false;
                try{
                    String inactive="update accountdetails set status='DEACTIVE' where accountno=?";
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                    PreparedStatement pstmt=con.prepareStatement(inactive);
                    pstmt.setInt(1,acnt);
                    pstmt.executeUpdate();
                    success=true;
                    String newtemp="C:\\Users\\SHAW\\Desktop\\"+acnt+".txt";
                    BufferedWriter bw=new BufferedWriter(new FileWriter(newtemp,true));
                    String data="Account Deactivated!\n";
                    bw.write(data);
                    bw.close();
                    //f.dispose();
                    
                }catch(Exception f){
                    er.error(String.valueOf(f));
                }
               
            }
            
        });
        
        JButton close=new JButton("No");
        close.setBounds(200+10,75,50,20);
        close.setBackground(new java.awt.Color(255,92,92));
        close.setBorder(null);
        close.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                success=false;
                //f.dispose();
            }
        });
        p.add(yclose);
        p.add(close);
        d.add(p);
        
        return success;
    }
}
