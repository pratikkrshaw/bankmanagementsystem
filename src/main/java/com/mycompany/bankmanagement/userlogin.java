/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankmanagement;

import java.awt.*;
import java.awt.event.*;
import java.nio.file.Paths;
import javax.swing.*;

/**
 *
 * @author SHAW
 */
public class userlogin {
    String str=Paths.get("").toAbsolutePath().toString();
    void login(int width,int height){
        JFrame f=new JFrame("User Login");
        Image icon=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
        f.setIconImage(icon);
        JLabel l=new JLabel("USER LOGIN",JLabel.CENTER);
        l.setFont(new Font("SANS_SERIF", Font.BOLD,35));
        l.setBounds(0,50,width-30,30);
        f.setVisible(true);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.WHITE);
        f.setSize(width,height);
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel email=new JLabel("Email Id:",JLabel.RIGHT);
        email.setBounds(0,150,(width/2)-70,25);
        email.setFont(new Font("SANS_SERIF", Font.BOLD,15));
        TextField emailid=new TextField();
        emailid.setBounds((width/2)-50,150,150,25);
        emailid.setEditable(true);

        JLabel pass=new JLabel("Password:",JLabel.RIGHT);
        pass.setBounds(0,200,(width/2)-70,25);
        pass.setFont(new Font("SANS_SERIF", Font.BOLD,15));
        JPasswordField pas=new JPasswordField();
        pas.setBounds((width/2)-50,200,150,25);

        JButton res = new JButton("Forgot your password?");    
        res.setBackground(new java.awt.Color(76,82,112));
        res.setForeground(Color.WHITE);
        res.setFocusPainted(false);
        res.setBounds((width/2)-140,260,250,40);
        res.setFont(new Font("SANS_SERIF", Font.ITALIC,14));
        res.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               resetpassword r=new resetpassword();
               r.reset();
           } 
        });
        
        
        JButton login = new JButton("LOGIN");    
        login.setBackground(new java.awt.Color(76,82,112));
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        login.setBounds((width/2)-140,310,250,40);
        login.setFont(new Font("SANS_SERIF", Font.BOLD,14));
        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Check c=new Check();
                c.check(emailid.getText(),pas.getText());
            } 
        });                
        JButton close=new JButton("CLOSE");
        close.setBounds((width/2)-140,360,250,40);
        close.setFont(new Font("SANS_SERIF", Font.BOLD,14));
        close.setBackground(Color.RED);
        close.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                f.dispose();
            }
        });
        f.add(l);
        f.add(email);
        f.add(emailid);
        f.add(pass);
        f.add(pas);
        f.add(res);
        f.add(login);
        f.add(close);
    }
    void adminlogin(int width,int height){
        JFrame f=new JFrame("Admin Login");
        Image icon=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
        f.setIconImage(icon);
        JLabel l=new JLabel("ADMIN LOGIN",JLabel.CENTER);
        l.setFont(new Font("SANS_SERIF", Font.BOLD,35));
        l.setBounds(0,50,width-30,30);
        f.setVisible(true);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.WHITE);
        f.setSize(width,height);
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel email=new JLabel("Employee Id:",JLabel.RIGHT);
        email.setBounds(0,150,(width/2)-70,25);
        email.setFont(new Font("SANS_SERIF", Font.BOLD,15));
        JTextField emailid=new JTextField();
        emailid.setBounds((width/2)-50,150,150,25);
                
        JLabel pass=new JLabel("Password:",JLabel.RIGHT);
        pass.setBounds(0,200,(width/2)-70,25);
        pass.setFont(new Font("SANS_SERIF", Font.BOLD,15));
        JPasswordField pas=new JPasswordField();
        pas.setBounds((width/2)-50,200,150,25);
        
        JButton res = new JButton("Forgot your password?");    
        res.setBackground(new java.awt.Color(76,82,112));
        res.setForeground(Color.WHITE);
        res.setFocusPainted(false);
        res.setBounds((width/2)-140,260,250,40);
        res.setFont(new Font("SANS_SERIF", Font.ITALIC,14));
        res.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               resetpassword r=new resetpassword();
               r.adminreset();
           } 
        });
                
        JButton login = new JButton("LOGIN");    
        login.setBackground(new java.awt.Color(76,82,112));
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        login.setBounds((width/2)-140,310,250,40);
        login.setFont(new Font("SANS_SERIF", Font.BOLD,14));
        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                admin ad=new admin();
                ad.admindetails(emailid.getText(),pas.getText());
            }
        });
                
        JButton close=new JButton("CLOSE");
        close.setBounds((width/2)-140,360,250,40);
        close.setFont(new Font("SANS_SERIF", Font.BOLD,14));
        close.setBackground(Color.RED);
        close.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                f.dispose();
            }
        });
        f.add(l);
        f.add(email);
        f.add(emailid);
        f.add(pass);
        f.add(pas);
        f.add(res);
        f.add(login);
        f.add(close);
    }
}
