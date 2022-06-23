/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankmanagement;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.*;

/**
 *
 * @author SHAW
 */
public class resetpassword {
    String str=Paths.get("").toAbsolutePath().toString();
    void reset(){
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        int width=(int)dim.getWidth();
        int height=(int)dim.getHeight()-35;
        JFrame f= new JFrame("Forgot your password");
        f.setResizable(false);
        Image im=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
        f.getContentPane().setBackground(Color.WHITE);
        f.setIconImage(im);
        f.setSize(width/2,height/2);
        f.setLocation(width/4,height/4);
        f.setVisible(true);
        f.setLayout(null);
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel title=new JLabel("VERIFY DETAILS",JLabel.CENTER);
        title.setFont(new Font("SANS_SERIF",Font.BOLD,20));
        title.setBounds(0,10,width/2,22);
        
        JLabel email=new JLabel("Email Id:",JLabel.RIGHT);
        email.setFont(new Font("SANS_SERIF",Font.BOLD,15));
        email.setBounds(0,60,width/5,20);
        JTextField e=new JTextField();
        e.setBounds(width/4-40,60,170,20);
        
        Choice c=new Choice();
        c.setFont(new Font("SANS_SERIF",Font.PLAIN,15));
        c.setBounds((width/12),100,width/3,20);
        c.add("What was your childhood nickname?");
        c.add("What's the name  of the first school you attended?");
        c.add("What was your first pet's name?");
        c.add("What's the name of the city where your parents meet?");
        c.add("What is the name of your favourite childhood friend?");
        JTextField sa=new JTextField();
        sa.setBounds(width/12,140,width/3,25);
        
        JLabel np=new JLabel("New Password:",JLabel.RIGHT);
        np.setFont(new Font("SANS_SERIF",Font.BOLD,15));
        np.setBounds(0,180,width/5,20);
        JPasswordField nps=new JPasswordField();
        nps.setBounds(width/4-40,180,170,20);
        
        JLabel cnp=new JLabel("Confirm Password:",JLabel.RIGHT);
        cnp.setFont(new Font("SANS_SERIF",Font.BOLD,15));
        cnp.setBounds(0,220,width/5,20);
        JPasswordField cnps=new JPasswordField();
        cnps.setBounds(width/4-40,220,170,20);
        
        JButton login = new JButton("CHANGE PASSWORD");    
        login.setBackground(new java.awt.Color(76,82,112));
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        login.setBounds(width/5-30,260,200,30);
        login.setFont(new Font("SANS_SERIF", Font.BOLD,14));
        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent be){
                error er=new error();
                boolean found;
                try{
                    found=false;
                    checkemailandpass ceap=new checkemailandpass();
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                    String getdata="select security from accountdetails where emailid=?";
                    String updateadata="update accountdetails set password=? where emailid=? and security=?";
                    String updateldata="update logindetails set pass=? where emailid=?";
                    PreparedStatement stmt=con.prepareStatement(getdata);
                    stmt.setString(1,e.getText());
                    ResultSet rs=stmt.executeQuery();
                    if(rs.next()){
                        if((c.getItem(c.getSelectedIndex())+sa.getText()).equals(rs.getString(1))){
                            if(nps.getText().equals(cnps.getText())){
                                if(ceap.checkpass(nps.getText())){
                                    PreparedStatement pstmt=con.prepareStatement(updateadata);
                                    pstmt.setString(1,nps.getText());
                                    pstmt.setString(2,e.getText());
                                    pstmt.setString(3,c.getItem(c.getSelectedIndex())+sa.getText());
                                    pstmt.executeUpdate();
                                    pstmt=con.prepareStatement(updateldata);
                                    pstmt.setString(1,nps.getText());
                                    pstmt.setString(2,e.getText());
                                    pstmt.executeUpdate();
                                    er.success("Password Changed Successfully");
                                    found=true;
                                }else{
                                    er.passerror("Password Criteria Not Met.");
                                }
                            }else{
                                er.error("Password Not Same.");
                            }
                        }else{
                            er.error("Incorrect Security Answer");
                        }

                    }else{
                        er.error("Incorrect Email Id");
                    }
                    if(found)
                        f.dispose();
                    con.close();
                    
                }catch(Exception i){
                    er.error(String.valueOf(i));
                }
            } 
        }); 
        
        f.add(title);
        f.add(e);
        f.add(email);
        f.add(c);
        f.add(sa);
        f.add(np);
        f.add(nps);
        f.add(cnp);
        f.add(cnps);
        f.add(login);
    }
    void adminreset(){
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        int width=(int)dim.getWidth();
        int height=(int)dim.getHeight()-35;
        JFrame f= new JFrame("Forgot your password");  
        f.setResizable(false);
        Image im=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
        f.getContentPane().setBackground(Color.WHITE);
        f.setIconImage(im);
        f.setSize(width/2,height/2);
        f.setLocation(width/4,height/4);
        f.setVisible(true);
        f.setLayout(null);
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel title=new JLabel("VERIFY DETAILS",JLabel.CENTER);
        title.setFont(new Font("SANS_SERIF",Font.BOLD,20));
        title.setBounds(0,10,width/2,22);
        
        JLabel email=new JLabel("Employee Id:",JLabel.RIGHT);
        email.setFont(new Font("SANS_SERIF",Font.BOLD,15));
        email.setBounds(0,60,width/5,20);
        JTextField e=new JTextField();
        e.setBounds(width/4-40,60,170,20);
        
        Choice c=new Choice();
        c.setFont(new Font("SANS_SERIF",Font.PLAIN,15));
        c.setBounds((width/12),100,width/3,20);
        c.add("What was your childhood nickname?");
        c.add("What's the name  of the first school you attended?");
        c.add("What was your first pet's name?");
        c.add("What's the name of the city where your parents meet?");
        c.add("What is the name of your favourite childhood friend?");
        JTextField sa=new JTextField();
        sa.setBounds(width/12,140,width/3,25);
        
        JLabel np=new JLabel("New Password:",JLabel.RIGHT);
        np.setFont(new Font("SANS_SERIF",Font.BOLD,15));
        np.setBounds(0,180,width/5,20);
        JPasswordField nps=new JPasswordField();
        nps.setBounds(width/4-40,180,170,20);
        
        JLabel cnp=new JLabel("Confirm Password:",JLabel.RIGHT);
        cnp.setFont(new Font("SANS_SERIF",Font.BOLD,15));
        cnp.setBounds(0,220,width/5,20);
        JPasswordField cnps=new JPasswordField();
        cnps.setBounds(width/4-40,220,170,20);
        
        JButton login = new JButton("CHANGE PASSWORD");    
        login.setBackground(new java.awt.Color(76,82,112));
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        login.setBounds(width/5-30,260,200,30);
        login.setFont(new Font("SANS_SERIF", Font.BOLD,14));
        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent be){
                error er=new error();
                boolean found;
                try{
                    found=false;
                    checkemailandpass ceap=new checkemailandpass();
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                    String getdata="select security from admin where empid=?";
                    String updateadata="update admin set emppas=? where empid=? and security=?";
                    PreparedStatement stmt=con.prepareStatement(getdata);
                    stmt.setString(1,e.getText());
                    ResultSet rs=stmt.executeQuery();
                    if(rs.next()){
                        if((c.getItem(c.getSelectedIndex())+sa.getText()).equals(rs.getString(1))){
                            if(nps.getText().equals(cnps.getText())){
                                if(ceap.checkpass(nps.getText())){
                                    PreparedStatement pstmt=con.prepareStatement(updateadata);
                                    pstmt.setString(1,nps.getText());
                                    pstmt.setString(2,e.getText());
                                    pstmt.setString(3,c.getItem(c.getSelectedIndex())+sa.getText());
                                    pstmt.executeUpdate();
                                    java.util.Date ld=new java.util.Date();
                                    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY[HH:mm] :");
                                    String da=sdf.format(ld);
                                    String link=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\admin\\"+e.getText()+".txt";
                                    BufferedWriter bf=new BufferedWriter(new FileWriter(link,true));
                                    String passad=da+"Password Changed using \"Forgot your password\"!\n";
                                    bf.write(passad);
                                    bf.close();
                                    er.success("Password Changed Successfully");
                                    found=true;
                                }else{
                                    er.passerror("Password Criteria Not Met.");
                                }
                            }else{
                                er.error("Password Not Same.");
                            }
                        }else{
                            er.error("Incorrect Security Answer");
                        }

                    }else{
                        er.error("Incorrect Employee Id");
                    }
                    if(found)
                        f.dispose();
                    con.close();
                    
                }catch(Exception i){
                    er.error(String.valueOf(i));
                }
            } 
        }); 
        
        f.add(title);
        f.add(e);
        f.add(email);
        f.add(c);
        f.add(sa);
        f.add(np);
        f.add(nps);
        f.add(cnp);
        f.add(cnps);
        f.add(login);
    }
}
