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
import java.util.Date;
/**
 *
 * @author SHAW
 */
public class admin {
    String str=Paths.get("").toAbsolutePath().toString();

    void admindetails(String email,String pas){
        error er=new error();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
            String qury="select reset from admin where empid=? and emppas=?";
            PreparedStatement pstmt=con.prepareStatement(qury);
            pstmt.setString(1,email);
            pstmt.setString(2,pas);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                if(rs.getInt("reset")==0){
                    //change pass
                    changepass(email,pas);
                }else{
                    //loginmenu
                    adminloginmenu(email,pas);
                }
            }else{
                er.error("Invalid Emp Id/ Password");
            }
        }catch(Exception e){
            er.error(String.valueOf(e));
        }
    }
    void changepass(String email,String pas){
        error er=new error();
        Date ld=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY[HH:mm] :");
        String da=sdf.format(ld);
        try{
            Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
            int width=(int)dim.getWidth();
            int height=(int)dim.getHeight()-35;
            JFrame f=new JFrame();
            JDialog d=new JDialog(f,"change password");
            Image i=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
            d.setIconImage(i);
            d.setVisible(true);
            d.setLayout(null);
            d.setSize(width/2,height/2);
            d.setLocation(width/4,height/4);
            d.getContentPane().setBackground(Color.WHITE);

            JLabel title=new JLabel("FIRST TIME LOGIN",JLabel.CENTER);
            title.setBounds(0,10,width/2-20,30);
            title.setFont(new Font("SANS_SERIF",Font.BOLD,20));
            d.add(title);

            JLabel np=new JLabel("New Password:",JLabel.RIGHT);
            np.setFont(new Font("SANS_SERIF",Font.BOLD,15));
            np.setBounds(0,60,width/5,20);
            JPasswordField nps=new JPasswordField();
            nps.setBounds(width/4-40,60,170,20);

            JLabel cnp=new JLabel("Confirm Password:",JLabel.RIGHT);
            cnp.setFont(new Font("SANS_SERIF",Font.BOLD,15));
            cnp.setBounds(0,100,width/5,20);
            JPasswordField cnps=new JPasswordField();
            cnps.setBounds(width/4-40,100,170,20);

            JLabel stitle=new JLabel("<html>SECURITY QUESTION<i> (In case you forget your password)</html>",JLabel.CENTER);
            stitle.setBounds(0,140,width/2-20,30);
            stitle.setFont(new Font("SANS_SERIF",Font.BOLD,15));

            Choice c=new Choice();
            c.setFont(new Font("SANS_SERIF",Font.PLAIN,15));
            c.setBounds((width/12),190,width/3,20);
            c.add("What was your childhood nickname?");
            c.add("What's the name  of the first school you attended?");
            c.add("What was your first pet's name?");
            c.add("What's the name of the city where your parents meet?");
            c.add("What is the name of your favourite childhood friend?");
            JTextField sa=new JTextField();
            sa.setBounds(width/12,230,width/3,25);

            JButton login = new JButton("CHANGE PASSWORD");    
            login.setBackground(new java.awt.Color(76,82,112));
            login.setForeground(Color.WHITE);
            login.setFocusPainted(false);
            login.setBounds(width/5-40,270,200,30);
            login.setFont(new Font("SANS_SERIF", Font.BOLD,14));
            login.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent f){
                    boolean found=false;
                    try{
                        
                        checkemailandpass ceap=new checkemailandpass();
                        if(ceap.checkpass(nps.getText())){
                            if(nps.getText().equals(cnps.getText())){
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                                String change="update admin set emppas=? where empid=? and emppas=?";
                                String schange="update admin set security=? where empid=?";
                                String resetc="update admin set reset=1 where empid=?";
                                PreparedStatement stmt=con.prepareStatement(change);
                                stmt.setString(1,nps.getText());
                                stmt.setString(2,email);
                                stmt.setString(3,pas);
                                int i=stmt.executeUpdate();
                                stmt=con.prepareStatement(schange);
                                stmt.setString(1,c.getItem(c.getSelectedIndex())+sa.getText());
                                stmt.setString(2,email);
                                stmt.executeUpdate();
                                if(i==1){
                                    stmt=con.prepareStatement(resetc);
                                    stmt.setString(1,email);
                                    stmt.executeUpdate();
                                    found=true;
                                    String link=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\admin\\"+email+".txt";
                                    File ff=new File(link);
                                    ff.createNewFile();
                                    BufferedWriter bf=new BufferedWriter(new FileWriter(link,true));
                                    String passad=da+"Password Changed!\n";
                                    bf.write(passad);
                                    bf.close();
                                    er.success("Password Changed successfully");
                                }else
                                    er.error("Something went wrong");
                            }else{
                                er.error("Password not same");
                            }
                        }else{
                            er.passerror("Password Criteria Not Met!");
                        }
                    }catch(Exception e){
                        er.error(String.valueOf(e));
                    }
                    if(found)
                        d.dispose();
                }
            });
            d.add(np);
            d.add(nps);
            d.add(cnp);
            d.add(cnps);
            d.add(stitle);
            d.add(c);
            d.add(sa);
            d.add(login);
        }catch(Exception e){
            er.error(String.valueOf(e));
        }
    }
    void adminloginmenu(String email,String pas){
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        int width=(int)dim.getWidth();
        int height=(int)dim.getHeight()-35;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
            String qury="select empname from admin where empid=? and emppas=?";
            PreparedStatement pstmt=con.prepareStatement(qury);
            pstmt.setString(1,email);
            pstmt.setString(2,pas);
            pstmt.executeUpdate();
            ResultSet rs=pstmt.executeQuery();
            rs.next();
            error er=new error();
            String frm="Welcome "+rs.getString(1);
            String n="<html>Welcome,&nbsp;"+rs.getString(1)+"</html>";
            java.util.Date ld=new java.util.Date();
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY[HH:mm] :");
            String da=sdf.format(ld);
            String link=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\admin\\"+email+".txt";
            BufferedWriter bf=new BufferedWriter(new FileWriter(link,true));
            String passad=da+"User Logged In!\n";
            bf.write(passad);
            bf.close();
            JFrame f=new JFrame(frm);
            Image icon=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
            f.setIconImage(icon);
            n=n.toUpperCase();
            JLabel l=new JLabel(n,JLabel.CENTER);
            l.setFont(new Font("SANS_SERIF", Font.BOLD,35));
            l.setBounds(0,30,width-30,50);
            f.setVisible(true);
            f.setLayout(null);
            f.getContentPane().setBackground(Color.WHITE);
            f.setSize(width,height);
            
            
            JPanel opti=new JPanel(new GridLayout(4, 3, 50, 35));
            opti.setBackground(Color.WHITE);
            opti.setBounds(50,height/2-200,width-110,350);
            
            JButton searchbyactno = new JButton("Search Users By Account Number");    
            searchbyactno.setBackground(new java.awt.Color(76,82,112));
            searchbyactno.setForeground(Color.WHITE);
            searchbyactno.setFocusPainted(false);
            searchbyactno.setFont(new Font("SANS_SERIF", Font.BOLD,18));
            searchbyactno.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){
                    JFrame f=new JFrame("Search User by Account Number");
                    f.setResizable(false);
                    //JDialog d=new JDialog(f,"change password");
                    Image i=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
                    f.setIconImage(i);
                    f.setVisible(true);
                    f.setLayout(null);
                    f.setSize(width/2,height/4);
                    f.setLocation(width/4,height/3);
                    f.getContentPane().setBackground(Color.WHITE);

                    JLabel title=new JLabel("SEARCH BY ACCOUNT NUMBER",JLabel.CENTER);
                    title.setBounds(0,10,width/2-20,30);
                    title.setFont(new Font("SANS_SERIF",Font.BOLD,20));
                    f.add(title);

                    JLabel np=new JLabel("Enter Account Number:",JLabel.RIGHT);
                    np.setFont(new Font("SANS_SERIF",Font.BOLD,15));
                    np.setBounds(0,60,width/5,20);
                    JTextField nps=new JTextField();
                    nps.setBounds(width/4-40,60,230,20);

                    JButton login = new JButton("SEARCH");    
                    login.setBackground(new java.awt.Color(76,82,112));
                    login.setForeground(Color.WHITE);
                    login.setFocusPainted(false);
                    login.setBounds(width/5-40,100,200,30);
                    login.setFont(new Font("SANS_SERIF", Font.BOLD,14));
                    login.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent ae){
                           try{
                                String saccntno="select a.name,a.fathername,a.birthdate,a.gender,a.accountno,a.status,b.balance from accountdetails a inner join balancefacilities b on a.accountno=b.accountno where a.accountno=?";
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                                int acntno=Integer.valueOf(nps.getText());
                                PreparedStatement pstmt=con.prepareStatement(saccntno);
                                pstmt.setInt(1,acntno);
                                pstmt.executeUpdate();
                                ResultSet rs=pstmt.executeQuery();

                                if(rs.next()){
                                    java.util.Date ld=new java.util.Date();
                                    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY[HH:mm] :");
                                    String da=sdf.format(ld);
                                    String link=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\admin\\"+email+".txt";
                                    BufferedWriter bf=new BufferedWriter(new FileWriter(link,true));
                                    String data=da+"Searched Account Details Of: "+rs.getString("name")+" ,Having Account No. "+rs.getInt("accountno")+"\n";
                                    bf.write(data);
                                    bf.close();
                                    String color="red";
                                    if(rs.getString("status").equals("ACTIVE"))
                                        color="green";
                                    String temp="<html><br>Account Details: <br><br><hr>"+
                                    "|&emsp;Name:&emsp;"+rs.getString("name")+
                                    "&emsp;<br><hr>|&emsp;Fathers Name:&emsp;"+rs.getString("fathername")+
                                    "&emsp;<br><hr>|&emsp;DOB:&emsp;"+rs.getString("birthdate")+
                                    "&emsp;<br><hr>|&emsp;Gender:&emsp;"+rs.getString("gender")+
                                    "&emsp;<br><hr>|&emsp;Account No.:&emsp;"+rs.getInt("accountno")+
                                    "&emsp;<hr>|&emsp;Status:&emsp;<span style=\"color:"+color+";\">"+rs.getString("status")+
                                    "&emsp;</span><hr>|&emsp;Balance:&emsp;"+rs.getFloat("balance")+"&emsp;<br><hr>";
                                    //temp="<html>Account Details:<br><br><hr>"+datee+"|"+transaction+"<br><hr><br>"+temp+"</html>";;
                                    JLabel jll=new JLabel(temp,JLabel.LEFT);
                                    JPanel panel=new JPanel();
                                    panel.add(jll);
                                    JScrollPane scrollBar=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                                    JFrame frame=new JFrame("Account Details");
                                    frame.setLocation(width/2-160,height/2-160);
                                    Image im=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\tick.png");
                                    panel.setBackground(Color.WHITE);
                                    frame.setIconImage(im);
                                    frame.add(scrollBar);
                                    frame.setSize(320,320);
                                    frame.setVisible(true);
                                    
                                }else
                                    er.error("Invalid Account Number!");
                                con.close();

                            }catch(Exception h){
                                er.error("Account Number must be an integer.");
                            }
                           f.dispose();
                       } 
                    });
                    f.add(np);
                    f.add(nps);
                    f.add(login);
                }
            });
                    
            JButton searchbyname = new JButton("Search Users By Name");    
            searchbyname.setBackground(new java.awt.Color(76,82,112));
            searchbyname.setForeground(Color.WHITE);
            searchbyname.setFocusPainted(false);
            searchbyname.setFont(new Font("SANS_SERIF", Font.BOLD,18));
            searchbyname.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    java.util.Date ld=new java.util.Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY[HH:mm] :");
                    String da=sdf.format(ld);
                    JFrame f=new JFrame("Search User By Name");
                    f.setResizable(false);
                    //JDialog d=new JDialog(f,"change password");
                    Image i=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
                    f.setIconImage(i);
                    f.setVisible(true);
                    f.setLayout(null);
                    f.setSize(width/2,height/4);
                    f.setLocation(width/4,height/3);
                    f.getContentPane().setBackground(Color.WHITE);

                    JLabel title=new JLabel("SEARCH BY NAME",JLabel.CENTER);
                    title.setBounds(0,10,width/2-20,30);
                    title.setFont(new Font("SANS_SERIF",Font.BOLD,20));
                    f.add(title);

                    JLabel np=new JLabel("Enter Full Name:",JLabel.RIGHT);
                    np.setFont(new Font("SANS_SERIF",Font.BOLD,15));
                    np.setBounds(0,60,width/5,20);
                    JTextField nps=new JTextField();
                    nps.setBounds(width/4-40,60,230,20);

                    JButton login = new JButton("SEARCH");    
                    login.setBackground(new java.awt.Color(76,82,112));
                    login.setForeground(Color.WHITE);
                    login.setFocusPainted(false);
                    login.setBounds(width/5-40,100,200,30);
                    login.setFont(new Font("SANS_SERIF", Font.BOLD,14));
                    login.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent ae){
                           try{
                                String saccntno="select * from accountdetails where name=?";
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                                PreparedStatement pstmt=con.prepareStatement(saccntno);
                                pstmt.setString(1,nps.getText());
                                int cnt=pstmt.executeUpdate();
                                String link=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\admin\\"+email+".txt";
                                BufferedWriter bf=new BufferedWriter(new FileWriter(link,true));
                                String data=da+"Searched Using Name: "+nps.getText()+" and Found "+cnt+" results\n";
                                bf.write(data);
                                bf.close();
                                ResultSet rs=pstmt.executeQuery();
                                String temp="";
                                if(cnt>0){
                                    //er.success("Total "+cnt+" Account Found!");
                                    int resl=0;
                                    while(rs.next()){
                                        resl+=1;
                                        int bl=rs.getInt("accountno");
                                        String checkbal="select balance from balancefacilities where accountno="+bl;
                                        Statement stmt=con.createStatement();
                                        ResultSet rsb=stmt.executeQuery(checkbal);
                                        rsb.next();
                                        String color="red";
                                        if(rs.getString("status").equals("ACTIVE"))
                                            color="green";
                                        temp+=resl+".&emsp;Name:&nbsp;"+rs.getString("name")+"&emsp;Fathers Name:&nbsp;"+rs.getString("fathername")+"&emsp;DOB:&nbsp;"+rs.getString("birthdate")+"&emsp;Gender:&nbsp;"+rs.getString("gender")+"&emsp;Account No.:&nbsp;"+rs.getInt("accountno")+"&emsp;Status:&nbsp;<span style=\"color:"+color+";\">"+rs.getString("status")+"</span>&emsp;Balance:&nbsp;"+rsb.getFloat(1)+"<br>";
                                    }   
                                    temp="<html><br>Total&nbsp;"+cnt+"&nbsp;Result Found.<br><hr><br>"+temp+"</html>";
                                    JLabel jll=new JLabel(temp,JLabel.LEFT);
                                    JPanel panel=new JPanel();
                                    panel.add(jll);
                                    JScrollPane scrollBar=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                                    JFrame frame=new JFrame("Account Details");
                                    frame.setLocation(width/2-500,height/2-200);
                                    Image im=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\tick.png");
                                    panel.setBackground(Color.WHITE);
                                    frame.setIconImage(im);
                                    frame.add(scrollBar);
                                    frame.setSize(1000,400);
                                    frame.setVisible(true);
                                }else
                                    er.success("No Account Found!");
                                
                                con.close();

                            }catch(Exception h){
                                er.error("Something went wrong. Please try again");
                            }
                           f.dispose();
                       } 
                    });
                    f.add(np);
                    f.add(nps);
                    f.add(login);
                }
            });
            
            JButton trans = new JButton("Check Transaction");    
            trans.setBackground(new java.awt.Color(76,82,112));
            trans.setForeground(Color.WHITE);
            trans.setFocusPainted(false);
            trans.setFont(new Font("SANS_SERIF", Font.BOLD,18));
            trans.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    
                    JFrame f=new JFrame("Check Transaction Details");
                    f.setResizable(false);
                    //JDialog d=new JDialog(f,"change password");
                    Image i=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
                    f.setIconImage(i);
                    f.setVisible(true);
                    f.setLayout(null);
                    f.setSize(width/2,height/4);
                    f.setLocation(width/4,height/3);
                    f.getContentPane().setBackground(Color.WHITE);

                    JLabel title=new JLabel("CHECK TRANSACTION DETAILS",JLabel.CENTER);
                    title.setBounds(0,10,width/2-20,30);
                    title.setFont(new Font("SANS_SERIF",Font.BOLD,20));
                    f.add(title);

                    JLabel np=new JLabel("Enter Account Number:",JLabel.RIGHT);
                    np.setFont(new Font("SANS_SERIF",Font.BOLD,15));
                    np.setBounds(0,60,width/5,20);
                    JTextField nps=new JTextField();
                    nps.setBounds(width/4-40,60,230,20);

                    JButton login = new JButton("SEARCH");    
                    login.setBackground(new java.awt.Color(76,82,112));
                    login.setForeground(Color.WHITE);
                    login.setFocusPainted(false);
                    login.setBounds(width/5-40,100,200,30);
                    login.setFont(new Font("SANS_SERIF", Font.BOLD,14));
                    login.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent ae){
                           try{
                                String saccntno="select a.name,a.fathername,a.birthdate,a.gender,a.accountno,a.status,b.balance from accountdetails a inner join balancefacilities b on a.accountno=b.accountno where a.accountno=?";
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                                //System.out.print("Enter Account No.:");
                                int acntno=Integer.valueOf(nps.getText());
                                PreparedStatement pstmt=con.prepareStatement(saccntno);
                                pstmt.setInt(1,acntno);
                                pstmt.executeUpdate();
                                ResultSet rs=pstmt.executeQuery();

                                if(rs.next()){
                                    java.util.Date ld=new java.util.Date();
                                    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY[HH:mm] :");
                                    String da=sdf.format(ld);

                                    String name=rs.getString("name");
                                    String enquiry=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\users\\"+acntno+".txt";
                                    FileReader fr=new FileReader(enquiry);
                                    BufferedReader br=new BufferedReader(fr);
                                    int i=0;
                                    String temp="";
                                    while((i=br.read())!=-1){
                                        if(i==10)
                                            temp+="<br>";
                                        else
                                            temp+=(char)i;
                                    }
                                    String link=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\admin\\"+email+".txt";
                                    BufferedWriter bf=new BufferedWriter(new FileWriter(link,true));
                                    String data=da+"Transaction History Of User Having Account No.: "+acntno+"\n";
                                    bf.write(data);
                                    bf.close();
                                    String datee="&emsp;&emsp;&emsp;Date&emsp;&emsp;&emsp;";
                                    String transaction="&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Details";
                                    temp="<html>Transaction Details of&nbsp;"+name+"&nbsp;:<br><br><hr>"+datee+"|"+transaction+"<br><hr><br>"+temp+"</html>";
                                    JLabel jll=new JLabel(temp,JLabel.LEFT);
                                    JPanel panel=new JPanel();
                                    panel.add(jll);
                                    JScrollPane scrollBar=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                                    JFrame frame=new JFrame("Transaction Details");
                                    frame.setLocation(width/2-250,height/2-300);
                                    Image im=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\tick.png");
                                    panel.setBackground(Color.WHITE);
                                    frame.setIconImage(im);
                                    frame.add(scrollBar);
                                    frame.setSize(500,600);
                                    frame.setVisible(true);
                                    br.close();
                                    fr.close();
                                }else
                                    er.error("Invalid Account Number!");
                                con.close();

                            }catch(Exception h){
                                er.error("Account Number must be an integer.");
                            }
                           f.dispose();
                       } 
                    });
                    f.add(np);
                    f.add(nps);
                    f.add(login);
                }
            });
                
            JButton cp = new JButton("Change Password");    
            cp.setBackground(new java.awt.Color(76,82,112));
            cp.setForeground(Color.WHITE);
            cp.setFocusPainted(false);
            cp.setFont(new Font("SANS_SERIF", Font.BOLD,18));
            cp.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                        JFrame f=new JFrame("Change Password");
                        Image icon=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
                        f.setIconImage(icon);
                        JLabel l=new JLabel("CHANGE PASSWORD",JLabel.CENTER);
                        l.setFont(new Font("SANS_SERIF", Font.BOLD,35));
                        l.setBounds(0,50,width-30,30);
                        f.setVisible(true);
                        f.setLayout(null);
                        f.getContentPane().setBackground(Color.WHITE);
                        f.setSize(width,height);
                        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        JLabel opasl=new JLabel("Enter Old Password:",JLabel.RIGHT);
                        opasl.setBounds(0,150,(width/2)-70,25);
                        opasl.setFont(new Font("SANS_SERIF", Font.BOLD,15));
                        JPasswordField opas=new JPasswordField();
                        opas.setBounds((width/2)-50,150,150,25);
        
                        JLabel npasl=new JLabel("Enter New Password:",JLabel.RIGHT);
                        npasl.setBounds(0,200,(width/2)-70,25);
                        npasl.setFont(new Font("SANS_SERIF", Font.BOLD,15));
                        JPasswordField npas=new JPasswordField();
                        npas.setBounds((width/2)-50,200,150,25);
                        
                        JLabel cnpasl=new JLabel("Confirm Password:",JLabel.RIGHT);
                        cnpasl.setBounds(0,250,(width/2)-70,25);
                        cnpasl.setFont(new Font("SANS_SERIF", Font.BOLD,15));
                        JPasswordField cnpas=new JPasswordField();
                        cnpas.setBounds((width/2)-50,250,150,25);
        
                        JButton change = new JButton("CHANGE PASSWORD");    
                        change.setBackground(new java.awt.Color(76,82,112));
                        change.setForeground(Color.WHITE);
                        change.setFocusPainted(false);
                        change.setBounds((width/2)-100,340,200,40);
                        change.setFont(new Font("SANS_SERIF", Font.BOLD,14));
                        change.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                try{
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                                String changepass="update admin set emppas=? where emppas=? and empid=?";
                                checkemailandpass ceap=new checkemailandpass();
                                String npass=npas.getText();
                                String opass=opas.getText();
                                String cnpass=cnpas.getText();
                                String getoldpas="select emppas from admin where emppas=? and empid=?";
                                PreparedStatement ps=con.prepareStatement(getoldpas);
                                ps.setString(1,opass);
                                ps.setString(2,email);
                                ps.executeUpdate();
                                ResultSet rs=ps.executeQuery();
                                String oldpass;
                                if(rs.next()){
                                    oldpass=rs.getString("emppas");
                                }else{
                                    er.error("Old Password is Incorrect");
                                    return;
                                }
                                if(ceap.checkpass(npass)){
                                    if(!oldpass.equals(opass))
                                        er.error("Old Password is Incorrect!");
                                    else if(!cnpass.equals(npass)){
                                        er.error("Password Not Same!");
                                    }
                                    else if(opass.equals(oldpass) && opass.equals(npass)){
                                        er.error("Old Password Cannot Be Same as New Password!");
                                    }
                                    else{
                                        java.util.Date ld=new java.util.Date();
                                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY[HH:mm] :");
                                        String da=sdf.format(ld);
                                        PreparedStatement pstmt=con.prepareStatement(changepass);
                                        pstmt.setString(1,npass);
                                        pstmt.setString(2,opass);
                                        pstmt.setString(3,email);
                                        pstmt.executeUpdate();
                                        String link=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\admin\\"+email+".txt";
                                        BufferedWriter bf=new BufferedWriter(new FileWriter(link,true));
                                        String data=da+"Changed Password\n";
                                        bf.write(data);
                                        bf.close();
                                        er.success("Password Successfully Changed!");
                                        f.dispose();
                                    }
                                }else{
                                    er.passerror("Password Criteria Not met!");
                                }
                                con.close();
                                }catch(Exception h){
                                    er.error(String.valueOf(h));
                                }
                            }
                        });
                        JButton close=new JButton("CANCEL");
                        close.setBounds((width/2)-100,390,200,40);
                        close.setFont(new Font("SANS_SERIF", Font.BOLD,14));
                        close.setBackground(Color.RED);
                        close.addActionListener(new ActionListener(){  
                            public void actionPerformed(ActionEvent e){ 
                                f.dispose();
                            }
                        });
                        f.add(l);
                        f.add(opasl);
                        f.add(opas);
                        f.add(npasl);
                        f.add(npas);
                        f.add(cnpasl);
                        f.add(cnpas);
                        f.add(change);
                        f.add(close);
                    }catch(Exception h){
                        er.error(String.valueOf(h));
                    }
                }
            });
            
            
            JButton lockacnt = new JButton("Lock Account");    
            lockacnt.setBackground(new java.awt.Color(76,82,112));
            lockacnt.setForeground(Color.WHITE);
            lockacnt.setFocusPainted(false);
            lockacnt.setFont(new Font("SANS_SERIF", Font.BOLD,18));
            lockacnt.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JFrame f=new JFrame("Lock Account");
                    f.setResizable(false);
                    Image i=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
                    f.setIconImage(i);
                    f.setVisible(true);
                    f.setLayout(null);
                    f.setSize(width/2,height/4);
                    f.setLocation(width/4,height/3);
                    f.getContentPane().setBackground(Color.WHITE);

                    JLabel title=new JLabel("LOCK ACCOUNT",JLabel.CENTER);
                    title.setBounds(0,10,width/2-20,30);
                    title.setFont(new Font("SANS_SERIF",Font.BOLD,20));
                    f.add(title);

                    JLabel np=new JLabel("Enter Account Number:",JLabel.RIGHT);
                    np.setFont(new Font("SANS_SERIF",Font.BOLD,15));
                    np.setBounds(0,60,width/5,20);
                    JTextField nps=new JTextField();
                    nps.setBounds(width/4-40,60,230,20);

                    JButton login = new JButton("LOCK");    
                    login.setBackground(new java.awt.Color(76,82,112));
                    login.setForeground(Color.WHITE);
                    login.setFocusPainted(false);
                    login.setBounds(width/5-40,100,200,30);
                    login.setFont(new Font("SANS_SERIF", Font.BOLD,14));
                    login.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent ae){
                           try{
                                String saccntno="select a.name,a.fathername,a.birthdate,a.gender,a.accountno,a.status,b.balance from accountdetails a inner join balancefacilities b on a.accountno=b.accountno where a.accountno=?";
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                                int acntno=Integer.valueOf(nps.getText());
                                PreparedStatement pstmt=con.prepareStatement(saccntno);
                                pstmt.setInt(1,acntno);
                                pstmt.executeUpdate();
                                ResultSet rs=pstmt.executeQuery();
                                
                                if(rs.next()){
                                    if(rs.getString("status").equals("LOCK")){
                                        er.error("Account Already Locked");
                                        f.dispose();
                                    }else if(rs.getString("status").equals("DEACTIVE")){
                                        er.error("Can't Lock. Account is deactivated");
                                        f.dispose();
                                    }else{
                                        java.util.Date ld=new java.util.Date();
                                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY[HH:mm] :");
                                        String da=sdf.format(ld);
                                        JFrame f=new JFrame();
                                        JDialog d=new JDialog(f,"Confirm");
                                        Image icon=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\warning.png");
                                        d.setIconImage(icon);
                                        d.setLocation(width/2-200,height/2-75);
                                        d.setVisible(true);
                                        d.setSize(400,150);
                                        String name=rs.getString("name");
                                        JPanel p=new JPanel();
                                        p.setLayout(null);
                                        JLabel sure=new JLabel("<html>Are you sure you want to lock account of <u><i>"+name+"</i></u>.<br>Do you want to proceed?</html>",JLabel.CENTER);
                                        sure.setBounds(0,0,380,70);
                                        p.add(sure);
                                        JButton yclose=new JButton("Yes");
                                        yclose.setBounds(200-80,75,60,25);
                                        yclose.setBackground(Color.RED);
                                        yclose.setForeground(Color.WHITE);
                                        //yclose.setBorder(null);
                                        yclose.setFocusPainted(false);
                                        error er=new error();
                                        yclose.addActionListener(new ActionListener(){  
                                            boolean success=false;
                                            public void actionPerformed(ActionEvent e){ 

                                                try{
                                                    String inactive="update accountdetails set status='LOCK' where accountno=?";
                                                    Class.forName("oracle.jdbc.driver.OracleDriver");
                                                    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                                                    PreparedStatement pstmt=con.prepareStatement(inactive);
                                                    int acntno=Integer.valueOf(nps.getText());
                                                    pstmt.setInt(1,acntno);
                                                    pstmt.executeUpdate();
                                                    success=true;
                                                    f.dispose();

                                                }catch(Exception f){
                                                    er.error(String.valueOf(f));
                                                }
                                                if(success){
                                                    try{
                                                        String link=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\admin\\"+email+".txt";
                                                        BufferedWriter bf=new BufferedWriter(new FileWriter(link,true));
                                                        String data=da+"Account Lock Of "+name+" ,Having Account No. "+acntno+"\n";
                                                        bf.write(data);
                                                        bf.close();
                                                    }catch(Exception h){
                                                        er.error(String.valueOf(h));
                                                    }
                                                    er.success("Account Successfully Locked!");
                                                    f.dispose();
                                                }

                                            }
                                        });
                                        JButton close=new JButton("No");
                                        close.setBounds(200+10,75,60,25);
                                        close.setBackground(new java.awt.Color(76,82,112));
                                        close.setForeground(Color.WHITE);
                                        //close.setBorder(null);
                                        close.setFocusPainted(false);
                                        close.addActionListener(new ActionListener(){  
                                            public void actionPerformed(ActionEvent e){ 
                                                f.dispose();
                                            }
                                        });
                                        p.add(yclose);
                                        p.add(close);
                                        d.add(p);
                                    }
                                }else
                                    er.error("Invalid Account Number!");
                                con.close();

                            }catch(Exception h){
                                er.error("Account Number must be an integer.");
                            }
                           f.dispose();
                       } 
                    });
                    f.add(np);
                    f.add(nps);
                    f.add(login);
                }
            });
            JButton unlockacnt=new JButton("UnLock Account");
            unlockacnt.setFont(new Font("SANS_SERIF", Font.BOLD,18));
            unlockacnt.setBackground(new java.awt.Color(76,82,112));
            unlockacnt.setForeground(Color.WHITE);
            unlockacnt.setFocusPainted(false);
            unlockacnt.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){ 
                    JFrame f=new JFrame("Unlock Account");
                    f.setResizable(false);
                    Image i=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
                    f.setIconImage(i);
                    f.setVisible(true);
                    f.setLayout(null);
                    f.setSize(width/2,height/4);
                    f.setLocation(width/4,height/3);
                    f.getContentPane().setBackground(Color.WHITE);

                    JLabel title=new JLabel("UNLOCK ACCOUNT",JLabel.CENTER);
                    title.setBounds(0,10,width/2-20,30);
                    title.setFont(new Font("SANS_SERIF",Font.BOLD,20));
                    f.add(title);

                    JLabel np=new JLabel("Enter Account Number:",JLabel.RIGHT);
                    np.setFont(new Font("SANS_SERIF",Font.BOLD,15));
                    np.setBounds(0,60,width/5,20);
                    JTextField nps=new JTextField();
                    nps.setBounds(width/4-40,60,230,20);

                    JButton login = new JButton("UNLOCK");    
                    login.setBackground(new java.awt.Color(76,82,112));
                    login.setForeground(Color.WHITE);
                    login.setFocusPainted(false);
                    login.setBounds(width/5-40,100,200,30);
                    login.setFont(new Font("SANS_SERIF", Font.BOLD,14));
                    login.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent ae){
                           try{
                                String saccntno="select a.name,a.fathername,a.birthdate,a.gender,a.accountno,a.status,b.balance from accountdetails a inner join balancefacilities b on a.accountno=b.accountno where a.accountno=?";
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                                int acntno=Integer.valueOf(nps.getText());
                                PreparedStatement pstmt=con.prepareStatement(saccntno);
                                pstmt.setInt(1,acntno);
                                pstmt.executeUpdate();
                                ResultSet rs=pstmt.executeQuery();
                                
                                if(rs.next()){
                                    if(rs.getString("status").equals("ACTIVE")){
                                        er.error("Account is not locked");
                                        f.dispose();
                                    }else if(rs.getString("status").equals("DEACTIVE")){
                                        er.error("Can't Unlock. Account is deactivated");
                                        f.dispose();
                                    }else{
                                        java.util.Date ld=new java.util.Date();
                                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY[HH:mm] :");
                                        String da=sdf.format(ld);
                                        JFrame f=new JFrame();
                                        JDialog d=new JDialog(f,"Confirm");
                                        Image icon=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\warning.png");
                                        d.setIconImage(icon);
                                        d.setLocation(width/2-200,height/2-75);
                                        d.setVisible(true);
                                        d.setSize(400,150);
                                        String name=rs.getString("name");
                                        JPanel p=new JPanel();
                                        p.setLayout(null);
                                        JLabel sure=new JLabel("<html>Are you sure you want to unlock account of <u><i>"+name+"</i></u>.<br>Do you want to proceed?</html>",JLabel.CENTER);
                                        sure.setBounds(0,0,380,70);
                                        p.add(sure);
                                        JButton yclose=new JButton("Yes");
                                        yclose.setBounds(200-80,75,60,25);
                                        yclose.setBackground(Color.RED);
                                        yclose.setForeground(Color.WHITE);
                                        //yclose.setBorder(null);
                                        yclose.setFocusPainted(false);
                                        error er=new error();
                                        yclose.addActionListener(new ActionListener(){  
                                            boolean success=false;
                                            public void actionPerformed(ActionEvent e){ 

                                                try{
                                                    String inactive="update accountdetails set status='ACTIVE' where accountno=?";
                                                    Class.forName("oracle.jdbc.driver.OracleDriver");
                                                    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                                                    PreparedStatement pstmt=con.prepareStatement(inactive);
                                                    int acntno=Integer.valueOf(nps.getText());
                                                    pstmt.setInt(1,acntno);
                                                    pstmt.executeUpdate();
                                                    success=true;
                                                    f.dispose();

                                                }catch(Exception f){
                                                    er.error(String.valueOf(f));
                                                }
                                                if(success){
                                                    try{
                                                        String link=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\admin\\"+email+".txt";
                                                        BufferedWriter bf=new BufferedWriter(new FileWriter(link,true));
                                                        String data=da+"Account Unlock Of "+name+" ,Having Account No. "+acntno+"\n";
                                                        bf.write(data);
                                                        bf.close();
                                                    }catch(Exception h){
                                                        er.error(String.valueOf(h));
                                                    }
                                                    er.success("Account Successfully Unlocked!");
                                                    f.dispose();
                                                }

                                            }
                                        });
                                        JButton close=new JButton("No");
                                        close.setBounds(200+10,75,60,25);
                                        close.setBackground(new java.awt.Color(76,82,112));
                                        close.setForeground(Color.WHITE);
                                        //close.setBorder(null);
                                        close.setFocusPainted(false);
                                        close.addActionListener(new ActionListener(){  
                                            public void actionPerformed(ActionEvent e){ 
                                                f.dispose();
                                            }
                                        });
                                        p.add(yclose);
                                        p.add(close);
                                        d.add(p);
                                    }
                                }else
                                    er.error("Invalid Account Number!");
                                con.close();

                            }catch(Exception h){
                                er.error("Account Number must be an integer.");
                            }
                           f.dispose();
                       } 
                    });
                    f.add(np);
                    f.add(nps);
                    f.add(login);
                }
            });
            
            JButton activeacnt=new JButton("Activate Account");
            activeacnt.setFont(new Font("SANS_SERIF", Font.BOLD,18));
            activeacnt.setBackground(new java.awt.Color(76,82,112));
            activeacnt.setForeground(Color.WHITE);
            activeacnt.setFocusPainted(false);
            activeacnt.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){ 
                    JFrame f=new JFrame("Activate Account");
                    f.setResizable(false);
                    Image i=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
                    f.setIconImage(i);
                    f.setVisible(true);
                    f.setLayout(null);
                    f.setSize(width/2,height/4);
                    f.setLocation(width/4,height/3);
                    f.getContentPane().setBackground(Color.WHITE);

                    JLabel title=new JLabel("ACTIVATE ACCOUNT",JLabel.CENTER);
                    title.setBounds(0,10,width/2-20,30);
                    title.setFont(new Font("SANS_SERIF",Font.BOLD,20));
                    f.add(title);

                    JLabel np=new JLabel("Enter Account Number:",JLabel.RIGHT);
                    np.setFont(new Font("SANS_SERIF",Font.BOLD,15));
                    np.setBounds(0,60,width/5,20);
                    JTextField nps=new JTextField();
                    nps.setBounds(width/4-40,60,230,20);

                    JButton login = new JButton("ACTIVATE");    
                    login.setBackground(new java.awt.Color(76,82,112));
                    login.setForeground(Color.WHITE);
                    login.setFocusPainted(false);
                    login.setBounds(width/5-40,100,200,30);
                    login.setFont(new Font("SANS_SERIF", Font.BOLD,14));
                    login.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent ae){
                           try{
                                String saccntno="select a.name,a.fathername,a.birthdate,a.gender,a.accountno,a.status,b.balance from accountdetails a inner join balancefacilities b on a.accountno=b.accountno where a.accountno=?";
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                                int acntno=Integer.valueOf(nps.getText());
                                PreparedStatement pstmt=con.prepareStatement(saccntno);
                                pstmt.setInt(1,acntno);
                                pstmt.executeUpdate();
                                ResultSet rs=pstmt.executeQuery();
                                
                                if(rs.next()){
                                    if(rs.getString("status").equals("ACTIVE")){
                                        er.error("Account is not deactivated");
                                        f.dispose();
                                    }else if(rs.getString("status").equals("LOCK")){
                                        er.error("Can't Activate. Account is locked, Please Unlock First.");
                                        f.dispose();
                                    }else{
                                        java.util.Date ld=new java.util.Date();
                                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY[HH:mm] :");
                                        String da=sdf.format(ld);
                                        JFrame f=new JFrame();
                                        JDialog d=new JDialog(f,"Confirm");
                                        Image icon=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\warning.png");
                                        d.setIconImage(icon);
                                        d.setLocation(width/2-200,height/2-75);
                                        d.setVisible(true);
                                        d.setSize(400,150);
                                        String name=rs.getString("name");
                                        JPanel p=new JPanel();
                                        p.setLayout(null);
                                        JLabel sure=new JLabel("<html>Are you sure you want to activate account of <u><i>"+name+"</i></u>.<br>Do you want to proceed?</html>",JLabel.CENTER);
                                        sure.setBounds(0,0,380,70);
                                        p.add(sure);
                                        JButton yclose=new JButton("Yes");
                                        yclose.setBounds(200-80,75,60,25);
                                        yclose.setBackground(Color.RED);
                                        yclose.setForeground(Color.WHITE);
                                        //yclose.setBorder(null);
                                        yclose.setFocusPainted(false);
                                        error er=new error();
                                        yclose.addActionListener(new ActionListener(){  
                                            boolean success=false;
                                            public void actionPerformed(ActionEvent e){ 

                                                try{
                                                    String inactive="update accountdetails set status='ACTIVE' where accountno=?";
                                                    Class.forName("oracle.jdbc.driver.OracleDriver");
                                                    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                                                    PreparedStatement pstmt=con.prepareStatement(inactive);
                                                    int acntno=Integer.valueOf(nps.getText());
                                                    pstmt.setInt(1,acntno);
                                                    pstmt.executeUpdate();
                                                    success=true;
                                                    f.dispose();

                                                }catch(Exception f){
                                                    er.error(String.valueOf(f));
                                                }
                                                if(success){
                                                    try{
                                                        String link=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\admin\\"+email+".txt";
                                                        BufferedWriter bf=new BufferedWriter(new FileWriter(link,true));
                                                        String data=da+"Account Activated Of "+name+" ,Having Account No. "+acntno+"\n";
                                                        bf.write(data);
                                                        bf.close();
                                                        link=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\users\\"+acntno+".txt";
                                                        bf=new BufferedWriter(new FileWriter(link,true));
                                                        data=da+" Account Activated!\n";
                                                        bf.write(data);
                                                        bf.close();
                                                    }catch(Exception h){
                                                        er.error(String.valueOf(h));
                                                    }
                                                    er.success("Account Successfully Activated!");
                                                    f.dispose();
                                                }

                                            }
                                        });
                                        JButton close=new JButton("No");
                                        close.setBounds(200+10,75,60,25);
                                        close.setBackground(new java.awt.Color(76,82,112));
                                        close.setForeground(Color.WHITE);
                                        //close.setBorder(null);
                                        close.setFocusPainted(false);
                                        close.addActionListener(new ActionListener(){  
                                            public void actionPerformed(ActionEvent e){ 
                                                f.dispose();
                                            }
                                        });
                                        p.add(yclose);
                                        p.add(close);
                                        d.add(p);
                                    }
                                }else
                                    er.error("Invalid Account Number!");
                                con.close();

                            }catch(Exception h){
                                er.error("Account Number must be an integer.");
                            }
                           f.dispose();
                       } 
                    });
                    f.add(np);
                    f.add(nps);
                    f.add(login);
                }
            });
            JButton logout=new JButton("LOGOUT");
            logout.setFont(new Font("SANS_SERIF", Font.BOLD,18));
            logout.setBackground(Color.RED);
            logout.setForeground(Color.WHITE);
            logout.setFocusPainted(false);
            //logout.setBounds(width/2-105,height/2+90,200,60);
            logout.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    try{
                        java.util.Date ld=new java.util.Date();
                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY[HH:mm] :");
                        String da=sdf.format(ld);
                        String link=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\admin\\"+email+".txt";
                        BufferedWriter bf=new BufferedWriter(new FileWriter(link,true));
                        String passad=da+"User Logged Out!\n\n";
                        bf.write(passad);
                        bf.close();
                    }catch(Exception h){
                        er.error(String.valueOf(h));
                    }
                    f.dispose();
                }
            });
            f.add(l);
            opti.add(searchbyactno);
            opti.add(searchbyname);
            opti.add(trans);
            opti.add(cp);
            opti.add(lockacnt);
            opti.add(unlockacnt);
            opti.add(activeacnt);
            opti.add(logout);
            f.add(opti);
        }catch(Exception e){
            error er=new error();
            er.error(String.valueOf(e));
        }
    }
}
