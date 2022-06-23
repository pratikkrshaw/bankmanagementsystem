/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
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
class Check {
    void check(String email,String pas){
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        int width=(int)dim.getWidth();
        int height=(int)dim.getHeight()-35;
        String str=Paths.get("").toAbsolutePath().toString();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
            String qury="select a.name,a.fathername,a.accountno,a.gender,a.status from accountdetails a inner join logindetails l on a.emailid=? where a.password=?";
            PreparedStatement pstmt=con.prepareStatement(qury);
            pstmt.setString(1,email);
            pstmt.setString(2,pas);
            pstmt.executeUpdate();
            ResultSet rs=pstmt.executeQuery();
            String temp="null";
            error er=new error();
            if(rs.next()){
                temp="Account Details!\n\nAccount Holder Name: "+rs.getString(1)+"\nFather's Name: "+rs.getString(2)+"\nAccount Number: "+rs.getInt(3)+"\nGender: "+rs.getString(4)+"\nStatus: "+rs.getString(5);
            }else{
                er.error("Incorrect Email Id/Password!");
                return;
            }
            String n="Welcome "+rs.getString(1);
            String ftitle="<html>Welcome,&nbsp;"+rs.getString(1)+"</html>";
            if(rs.getString(5).equals("DEACTIVE")){
                
                er.error("Account is Deactivated. Please Contact Bank!");
                return;
            }
            if(rs.getString(5).equals("LOCK")){
                
                er.error("Account is Locked. Please Contact Bank!");
                return;
            }
            JFrame f=new JFrame(n);
            Image icon=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
            f.setIconImage(icon);
            ftitle=ftitle.toUpperCase();
            JLabel l=new JLabel(ftitle,JLabel.CENTER);
            l.setFont(new Font("SANS_SERIF", Font.BOLD,35));
            l.setBounds(0,30,width-30,50);
            f.setVisible(true);
            f.setLayout(null);
            f.getContentPane().setBackground(Color.WHITE);
            f.setSize(width,height);
            JTextArea details=new JTextArea(temp);
            details.setEditable(false);
            details.setBounds(55,120,width-35,height/2-200);
            details.setFont(new Font("MONOSPACE",Font.PLAIN,18));
            
            JPanel opti=new JPanel(new GridLayout(3, 3, 50, 35));
            opti.setBackground(Color.WHITE);
            opti.setBounds(50,height/2-30,width-110,250);
            
            JButton viewbal = new JButton("View Balance");    
            viewbal.setBackground(new java.awt.Color(76,82,112));
            viewbal.setForeground(Color.WHITE);
            viewbal.setFocusPainted(false);
            viewbal.setFont(new Font("SANS_SERIF", Font.BOLD,18));
            viewbal.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){ 
                    final String ins="select balance from balancefacilities where emailid=?";                    
                    try{
                        JFrame f= new JFrame();  
                        Image im=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\tick.png");
                        f.setIconImage(im);
                        JDialog d = new JDialog(f , "Balance", true);
                        d.setLocation(width/2-200,height/2-100);
                        d.setLayout( new FlowLayout() );
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                        PreparedStatement pstmt=con.prepareStatement(ins);
                        pstmt.setString(1,email);
                        pstmt.executeUpdate();
                        ResultSet rs=pstmt.executeQuery();
                        rs.next();
                        float temp=rs.getFloat("balance");
                        String balance="Available Balance: "+String.valueOf(temp);
                        JLabel err=new JLabel(balance,JLabel.CENTER);
                        con.close();
                        d.add(err);   
                        d.setSize(400,80);    
                        d.setVisible(true);
                    }
                    catch(Exception f){
                        er.error("Cannot Fetch Balance!");
                    }
                    
                }
            });
                    
            JButton transfer = new JButton("Transfer Funds");    
            transfer.setBackground(new java.awt.Color(76,82,112));
            transfer.setForeground(Color.WHITE);
            transfer.setFocusPainted(false);
            transfer.setFont(new Font("SANS_SERIF", Font.BOLD,18));
            transfer.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                        JFrame f=new JFrame("Transfer Funds");
                        Image icon=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
                        f.setIconImage(icon);
                        JLabel l=new JLabel("TRANSFER FUNDS",JLabel.CENTER);
                        l.setFont(new Font("SANS_SERIF", Font.BOLD,35));
                        l.setBounds(0,50,width-30,30);
                        f.setVisible(true);
                        f.setLayout(null);
                        f.getContentPane().setBackground(Color.WHITE);
                        f.setSize(width,height);
                        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        JLabel racnt=new JLabel("Account No. Of Reciever:",JLabel.RIGHT);
                        racnt.setBounds(0,150,(width/2)-70,25);
                        racnt.setFont(new Font("SANS_SERIF", Font.BOLD,15));
                        TextField ra=new TextField();
                        ra.setBounds((width/2)-50,150,150,25);
                        ra.setEditable(true);
        
                        JLabel amt=new JLabel("Amount:",JLabel.RIGHT);
                        amt.setBounds(0,200,(width/2)-70,25);
                        amt.setFont(new Font("SANS_SERIF", Font.BOLD,15));
                        JTextField samt=new JTextField();
                        samt.setBounds((width/2)-50,200,150,25);
        
                        JButton tf = new JButton("TRANSFER FUNDS");    
                        tf.setBackground(new java.awt.Color(76,82,112));
                        tf.setForeground(Color.WHITE);
                        tf.setFocusPainted(false);
                        tf.setBounds((width/2)-100,290,200,40);
                        tf.setFont(new Font("SANS_SERIF", Font.BOLD,14));
                        tf.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                float rbal=0,sbal=0;
                                final String update="update balancefacilities set balance=? where accountno=?";
                                try{ 
                                    String findraccbal="select balance from balancefacilities where accountno=?";
                                    String findsaccbal="select balance from balancefacilities where accountno=?";
                                    Class.forName("oracle.jdbc.driver.OracleDriver");
                                    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                                    PreparedStatement stmt=con.prepareStatement(findraccbal);
                                    int raccnt=Integer.valueOf(ra.getText());
               
                                    stmt.setInt(1,raccnt);
                                    stmt.executeUpdate();
                                    ResultSet rs=stmt.executeQuery();
                                    if(rs.next()){
                                        rbal=rs.getFloat(1);
                                    }else{
                                        er.error("Invalid Account Number.");
                                    }
                                    stmt=con.prepareStatement(findsaccbal);
                                    accountno an=new accountno();
                                    int saccnt=an.accountno(email);
                                    stmt.setInt(1,saccnt);
                                    stmt.executeUpdate();
                                    rs=stmt.executeQuery();
                                    if(rs.next()){
                                        sbal=rs.getInt(1);
                                    }
                                    Float bal=Float.valueOf(samt.getText());
                                    String findstatus="select status from accountdetails where accountno=?";
                                    PreparedStatement stment=con.prepareStatement(findstatus);
                                    stment.setInt(1,raccnt);
                                    ResultSet fs=stment.executeQuery();
                                    fs.next();
                                    if(saccnt==raccnt){
                                        er.error("Can't transfer funds to same account");
                                    }else if(sbal-bal<0){
                                        er.error("Not Enought Balance!");
                                    }else if(fs.getString("status").equals("LOCK") || fs.getString("status").equals("DEACTIVE")){
                                        er.error("Can't transfer funds to this account number!");
                                    }else{
                                        java.util.Date ld=new java.util.Date();
                                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY[HH:mm] :");
                                        String da=sdf.format(ld);
                                        rbal+=bal;
                                        sbal-=bal;
                                        stmt=con.prepareStatement(update);
                                        stmt.setFloat(1,rbal);
                                        stmt.setInt(2,raccnt);
                                        stmt.executeUpdate();
                                        stmt=con.prepareStatement(update);
                                        stmt.setFloat(1,sbal);
                                        stmt.setInt(2,saccnt);
                                        stmt.executeUpdate();
                                        String dname="select ad.name from accountdetails ad inner join balancefacilities bf on ad.emailid=bf.emailid where ad.accountno="+raccnt;
                                        Statement st=con.createStatement();
                                        ResultSet r=st.executeQuery(dname);
                                        if(r.next()){
                                            String newtemp=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\users\\"+saccnt+".txt";
                                            FileWriter fw=new FileWriter(newtemp,true);
                                            BufferedWriter bw=new BufferedWriter(fw);
                                            String balen=da+" Money Transfered Rs."+bal+" to "+r.getString(1)+"\n";
                                            String findname="select name from accountdetails where accountno="+saccnt;
                                            r=st.executeQuery(findname);
                                            String n="";
                                            if(r.next())
                                                n=r.getString(1);
                                            String rbalen=da+" Money Received Rs."+bal+" from "+n+"\n";
                                            bw.write(balen);
                                            bw.close();
                                            newtemp=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\users\\"+raccnt+".txt";
                                            fw=new FileWriter(newtemp,true);
                                            bw=new BufferedWriter(fw);
                                            bw.write(rbalen);
                                            bw.close();
                                            er.success("Money Successfully Transfered!");
                                        }
                                    }       
                                    con.close();
                                }catch(Exception l){
                                    er.error("Can't Tranfer Funds. Try Again Later.");
                                }
                                f.dispose();
                            } 
                        });                
                        JButton close=new JButton("CANCEL");
                        close.setBounds((width/2)-100,340,200,40);
                        close.setFont(new Font("SANS_SERIF", Font.BOLD,14));
                        close.setBackground(Color.RED);
                        close.addActionListener(new ActionListener(){  
                            public void actionPerformed(ActionEvent e){ 
                                f.dispose();
                            }
                        });
                        f.add(l);
                        f.add(racnt);
                        f.add(ra);
                        f.add(amt);
                        f.add(samt);
                        f.add(tf);
                        f.add(close);
                        }catch(Exception h){
                            //String her=)
                            er.error(String.valueOf(h));
                        }
                }
            });
            
            JButton trans = new JButton("Transaction Details");    
            trans.setBackground(new java.awt.Color(76,82,112));
            trans.setForeground(Color.WHITE);
            trans.setFocusPainted(false);
            trans.setFont(new Font("SANS_SERIF", Font.BOLD,18));
            trans.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                        String acnt="select accountno from accountdetails where emailid=?";
                        PreparedStatement stmt=con.prepareStatement(acnt);
                        stmt.setString(1,email);
                        //stmt.setString(2,pas);
                        ResultSet rs=stmt.executeQuery();
                        rs.next();
                        int accountno=rs.getInt(1);
                        String enquiry=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\users\\"+accountno+".txt";
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
                        String datee="&emsp;&emsp;&emsp;Date&emsp;&emsp;&emsp;";
                        String transaction="&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Details";
                        temp="<html>Transaction Details:<br><br><hr>"+datee+"|"+transaction+"<br><hr><br>"+temp+"</html>";
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
                    }catch(Exception h){
                        er.error(String.valueOf(h));
                    }
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
                        //String temp=pas;
                        change.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                try{
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                                String changepass="update logindetails set pass=? where pass=? and emailid=?";
                                checkemailandpass ceap=new checkemailandpass();
                                String npass=npas.getText();
                                String opass=opas.getText();
                                String cnpass=cnpas.getText();
                                //oldpass=temp;
                                
                                //need to implement try catch;
                                String getoldpas="select pass from logindetails where pass=? and emailid=?";
                                PreparedStatement ps=con.prepareStatement(getoldpas);
                                ps.setString(1,opass);
                                ps.setString(2,email);
                                ps.executeUpdate();
                                ResultSet rs=ps.executeQuery();
                                String oldpass;
                                if(rs.next()){
                                    oldpass=rs.getString("pass");
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
                                        PreparedStatement pstmt=con.prepareStatement(changepass);
                                        pstmt.setString(1,npass);
                                        pstmt.setString(2,opass);
                                        pstmt.setString(3,email);
                                        pstmt.executeUpdate();
                                        changepass="update accountdetails set password=? where password=? and emailid=?";
                                        pstmt=con.prepareStatement(changepass);
                                        pstmt.setString(1,npass);
                                        pstmt.setString(2,opass);
                                        pstmt.setString(3,email);
                                        pstmt.executeUpdate();
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
            
            
            JButton dac = new JButton("Deactivate Account");    
            dac.setBackground(new java.awt.Color(76,82,112));
            dac.setForeground(Color.WHITE);
            dac.setFocusPainted(false);
            dac.setFont(new Font("SANS_SERIF", Font.BOLD,18));
            //confirmmsg cf=new confirmmsg();
            
            dac.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    boolean found=false;
                    try{
                        accountno ac=new accountno();
                        int acnt=ac.accountno(email);
                        String check="select name from accountdetails where accountno=?";
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                        PreparedStatement pstmt=con.prepareStatement(check);
                        pstmt.setInt(1,acnt);
                        pstmt.executeUpdate();
                        ResultSet rs=pstmt.executeQuery();
                        if(rs.next()){
                            JFrame f=new JFrame();
                            UIManager.put("Button.background",new java.awt.Color(76,82,112));
                            UIManager.put("Button.foreground",Color.white);
                            int a=JOptionPane.showConfirmDialog(f,"You won't be able to access your account anymore.\nDo you want to proceed?","Are you sure?",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);  
                            
                            if(a==JOptionPane.YES_OPTION){  
                                try{
                                    java.util.Date ld=new java.util.Date();
                                    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY[HH:mm] :");
                                    String da=sdf.format(ld);
                                    String inactive="update accountdetails set status='DEACTIVE' where accountno=?";
                                    Class.forName("oracle.jdbc.driver.OracleDriver");
                                    con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                                    pstmt=con.prepareStatement(inactive);
                                    pstmt.setInt(1,acnt);
                                    pstmt.executeUpdate();
                                    //success=true;
                                    found=true;
                                    String newtemp=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\users\\"+acnt+".txt";
                                    BufferedWriter bw=new BufferedWriter(new FileWriter(newtemp,true));
                                    String data=da+" Account Deactivated!\n";
                                    bw.write(data);
                                    bw.close();
                                    f.dispose();//found=true;
                                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

                                }catch(Exception j){
                                        er.error(String.valueOf(j));
                                }
                                 
                            }
                            /*JFrame f=new JFrame();
                            JDialog d=new JDialog(f,"Confirm");
                            Image icon=Toolkit.getDefaultToolkit().getImage("C:\\Users\\SHAW\\Downloads\\warning.png");
                            d.setIconImage(icon);
                            d.setLocation(width/2-200,height/2-75);
                            d.setVisible(true);
                            d.setSize(400,150);

                            JPanel p=new JPanel();
                            p.setLayout(null);
                            JLabel sure=new JLabel("<html>You won't be able to access your account anymore.<br>Do you want to proceed?</html>",JLabel.CENTER);
                            sure.setBounds(0,0,380,70);
                            p.add(sure);
                            JButton yclose=new JButton("Yes");
                            yclose.setBounds(200-80,75,60,25);
                            yclose.setBackground(Color.ORANGE);
                            yclose.setForeground(Color.WHITE);
                            //yclose.setBorder(null);
                            yclose.setFocusPainted(false);
                            error er=new error();
                            yclose.addActionListener(new ActionListener(){  
                                boolean success=false;
                                public void actionPerformed(ActionEvent e){ 
                                    
                                    try{
                                        java.util.Date ld=new java.util.Date();
                                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY[HH:mm] :");
                                        String da=sdf.format(ld);
                                        String inactive="update accountdetails set status='DEACTIVE' where accountno=?";
                                        Class.forName("oracle.jdbc.driver.OracleDriver");
                                        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root");
                                        PreparedStatement pstmt=con.prepareStatement(inactive);
                                        pstmt.setInt(1,acnt);
                                        pstmt.executeUpdate();
                                        success=true;
                                        //found=true;
                                        String newtemp="C:\\Users\\SHAW\\Desktop\\"+acnt+".txt";
                                        BufferedWriter bw=new BufferedWriter(new FileWriter(newtemp,true));
                                        String data=da+" Account Deactivated!\n";
                                        bw.write(data);
                                        bw.close();
                                        f.dispose();

                                    }catch(Exception f){
                                        er.error(String.valueOf(f));
                                    }
                                    if(success){
                                        er.success("Account Successfully Deactivated!");
                                        System.exit(0);
                                    }
                                }
                            });

                            JButton close=new JButton("No");
                            close.setBounds(200+10,75,60,25);
                            close.setBackground(Color.ORANGE);
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
                           // System.out.println(found);*/
                            
                        }  
                        else{
                            er.error("Something is wrong. Please try again later.");
                        }
                        if(found){
                            er.success("Account Successfully Deactivated!");
                            f.dispose();
                        }
                        con.close();
                    }catch(Exception j){
                        er.error(String.valueOf(j));
                    }
                }
            });
            JButton close=new JButton("LOGOUT");
            close.setFont(new Font("SANS_SERIF", Font.BOLD,18));
            close.setBackground(Color.RED);
            close.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){ 
                    f.dispose();
                }
            });
            f.add(l);
            f.add(details);
            opti.add(viewbal);
            opti.add(transfer);
            opti.add(trans);
            opti.add(cp);
            opti.add(dac);
            opti.add(close);
            f.add(opti);
        }catch(Exception e){
            error er=new error();
            er.error(String.valueOf(e));
        }
    }
}
