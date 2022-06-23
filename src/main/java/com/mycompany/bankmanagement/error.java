package com.mycompany.bankmanagement;



import java.awt.*;
import java.awt.event.*;
import java.nio.file.Paths;
import javax.swing.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author SHAW
 */
public class error {
    Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int)dim.getWidth();
    int height=(int)dim.getHeight()-35;
    String str=Paths.get("").toAbsolutePath().toString();

    void error(String e){
        JFrame f= new JFrame();  
        Image im=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\error.png");
        f.setIconImage(im);
        JDialog d = new JDialog(f , "Error", true);
        d.setLayout( new FlowLayout() ); 
        String exc=String.valueOf(e);
        int len=exc.length();
        d.setLocation(width/2-(len*5),height/2-100);
        JLabel err=new JLabel(exc,JLabel.CENTER);
        d.add(err);  
        d.setSize(len*10,70);    
        d.setVisible(true);
    }
    void passerror(String e){
        JFrame f= new JFrame();  
        Image im=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\error.png");
        f.setIconImage(im);
        JDialog d = new JDialog(f , "Error", true);
        d.setLayout( new FlowLayout() ); 
        String exc="<html>"+String.valueOf(e)+"<br><br>• MUST contain at least 8 characters (12+ recommended)<br>"+
                "• MUST contain at least one uppercase letter<br>" +
                "• MUST contain at least one lowercase letter<br>" +
                "• MUST contain at least one number<br>" +
                "• MUST contain at least one special character (!”#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ )</html>";
        //int len=exc.length();
        d.setLocation(width/2-300,height/2-100);
        JLabel err=new JLabel(exc,JLabel.CENTER);
        d.add(err);  
        d.setSize(600,170);    
        d.setVisible(true);
    }
    void success(String e){
        JFrame f= new JFrame();  
        Image im=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\tick.png");
        f.setIconImage(im);
        JDialog d = new JDialog(f , "Success", true);
        d.setLocation(width/2-200,height/2-100);
        d.setLayout( new FlowLayout() );
        JLabel err=new JLabel(e,JLabel.CENTER);
        d.add(err);   
        d.setSize(400,80);    
        d.setVisible(true);
        
    }
}
