/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.bankmanagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.nio.file.Paths;
/**
 *
 * @author SHAW
 */
class Bankmanagement {

    public static void main(String[] args) {
        
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        int width=(int)dim.getWidth();
        int height=(int)dim.getHeight()-35;
        String str=Paths.get("").toAbsolutePath().toString();
        JFrame f = new JFrame("Bank Management System");
        Image icon=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
        f.setIconImage(icon);
        JLabel l=new JLabel("BANK MANAGEMENT SYSTEM",JLabel.CENTER);
        l.setFont(new Font("SANS_SERIF", Font.BOLD,35));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(Color.WHITE);
        JButton b1 = new JButton("Create Account");  
        b1.setBackground(new java.awt.Color(76,82,112));
        b1.setForeground(Color.WHITE);
        //b1.setBorder(null);
        b1.setFocusPainted(false);
        b1.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){  
                create con=new create();
                con.create(width,height);
            }
        }); 
        JButton b2 = new JButton("User Login");    
        b2.setBackground(new java.awt.Color(76,82,112));
        b2.setForeground(Color.WHITE);
        //b2.setBorder(null);
        b2.setFocusPainted(false);
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                userlogin ul=new userlogin();
                ul.login(width,height);
            }
        });
        JButton b3 = new JButton("Admin Login");
        b3.setBackground(new java.awt.Color(76,82,112));
        b3.setForeground(Color.WHITE);
        //b3.setBorder(null);
        b3.setFocusPainted(false);
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                userlogin ul=new userlogin();
                ul.adminlogin(width,height);
            }
        });
        
        JButton about = new JButton("About Us");
        about.setBackground(new java.awt.Color(76,82,112));
        about.setForeground(Color.WHITE);
        //b3.setBorder(null);
        about.setFocusPainted(false);
        about.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                aboutus abt=new aboutus();
                abt.aboutus(width,height);
            }
        });
        
        
        JButton close=new JButton("Exit");
        l.setBounds(0,50,width-30,30);
        b1.setBounds((width/2)-100,150,150,50);  
        b2.setBounds((width/2)-100,200,150,50);
        b3.setBounds((width/2)-100,250,150,50);
        about.setBounds((width/2)-100,300,150,50);
        close.setBounds((width/2)-95,370,140,50);
        b1.setFont(new Font("SANS_SERIF", Font.BOLD,14));
        b2.setFont(new Font("SANS_SERIF", Font.BOLD,14));
        b3.setFont(new Font("SANS_SERIF", Font.BOLD,14));
        about.setFont(new Font("SANS_SERIF", Font.BOLD,14));
        close.setFont(new Font("SANS_SERIF", Font.BOLD,14));
        close.setBackground(Color.RED);
        close.setBorder(null);
        close.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                System.exit(0);
            }
        });
        f.add(l);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(about);
        f.add(close);
        f.setSize(width,height);    
        f.setLayout(null);    
        f.setVisible(true);
    }
}
