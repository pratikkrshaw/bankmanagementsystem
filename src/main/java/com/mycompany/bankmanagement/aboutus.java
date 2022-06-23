/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankmanagement;

import java.awt.*;
import java.nio.file.Paths;
import javax.swing.*;

/**
 *
 * @author SHAW
 */
public class aboutus extends Canvas{
    String str=Paths.get("").toAbsolutePath().toString();

    public void paint(Graphics g) {  
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        int width=(int)dim.getWidth();
        int height=(int)dim.getHeight()-35;
        
        Toolkit t=Toolkit.getDefaultToolkit();  
        Image i1=t.getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\p1.png");  
        g.drawImage(i1,100,60,this);  
        Image i1n=t.getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\p1n.png");  
        g.drawImage(i1n,300,80,this); 
        
        
        Image i2=t.getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\p2.png");  
        g.drawImage(i2,width-300,60,this);
        Image i2n=t.getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\p2n.png");  
        g.drawImage(i2n,width-620,80,this); 
        
        Image i3=t.getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\p3.png");  
        g.drawImage(i3,100,260,this); 
        Image i3n=t.getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\p3n.png");  
        g.drawImage(i3n,300,280,this); 
        
        Image i4=t.getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\p4.png");  
        g.drawImage(i4,width-300,260,this);         
        Image i4n=t.getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\p4n.png");  
        g.drawImage(i4n,width-620,290,this);   
    } 
    void aboutus(int width,int height){
        aboutus a=new aboutus();
        JFrame frame=new JFrame("About Us");
        Image im=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
        frame.setIconImage(im);
        JLabel jll=new JLabel("OUR TEAM",JLabel.CENTER);
        jll.setFont(new Font("SANS_SERIF",Font.BOLD,35));
        jll.setBounds(0,10,width-50,40);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.add(jll);
        JLabel name1=new JLabel("ABOUT THE PROJECT",JLabel.CENTER);
        name1.setFont(new Font("SANS_SERIF",Font.BOLD,35));
        name1.setBounds(0,450,width-50,40);
        frame.add(name1);
        String moto="<html><b><i>Motto:</i></b> Our motto is to develop a software program for managing the entire bank process related to Administration accounts customer accounts and to keep each every track about their property and their various transaction processes efficiently.<br>" +
"<b><i>Customer Satisfaction:</b></i> Client can do his operations comfortably without any risk or losing of his privacy. Our software will perform and fulfill all the tasks that any customer would desire. <br>" +
"<b><i>Saving Customer Time:</b></i> Client doesn't need to go to the bank to do small operation.<br>" +
"<b><i>Protecting the Customer:</b></i> It helps the customer to be satisfied and comfortable in his choices, this protection contains customer’s account, money and his privacy. <br>" +
"<b><i>Transferring Money:</b></i> Help client transferring money to/or another bank or country.</html>";
        JLabel ja=new JLabel(moto,Label.LEFT);
        ja.setFont(new Font("SANS_SERIF",Font.PLAIN,15));
        ja.setBounds(30,450,width-80,height-500);
        frame.add(ja);
        frame.add(a);
        
        frame.setSize(width,height);
        frame.setVisible(true);
    }
}
