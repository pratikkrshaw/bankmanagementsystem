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
import java.util.*;
import java.util.Date;
import java.text.*;
import javax.swing.*;
/**
 *
 * @author SHAW
 */
public class create {
    String str=Paths.get("").toAbsolutePath().toString();
    void create(int width,int height){
        Date ld=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY[HH:mm] :");
        String da=sdf.format(ld);
        //System.out.println(da);
        JFrame f=new JFrame("Create Account");
        Image icon=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\safe.png");
        f.setIconImage(icon);
        f.getContentPane().setBackground(Color.WHITE);
        f.setSize(width,height);
        f.setVisible(true);
        f.setLayout(null);
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        JLabel jl=new JLabel("CREATE ACCOUNT",JLabel.CENTER);
        f.add(jl);
        jl.setBounds(0,10,width-30,80);
        jl.setFont(new Font("SANS_SERIF",Font.BOLD,35));
        JLabel name=new JLabel("Name:",JLabel.RIGHT);
        JLabel fname=new JLabel("Fathers Name:",JLabel.RIGHT);
        JLabel dob=new JLabel("DOB (YYYY/MM/DD):",JLabel.RIGHT);
        JLabel gender=new JLabel("Gender:",JLabel.RIGHT);
        JLabel email=new JLabel("Email Id:",JLabel.RIGHT);
        JLabel pass=new JLabel("Password:",JLabel.RIGHT);
        JLabel cpassl=new JLabel("Confirm Password:",JLabel.RIGHT);
        JLabel bal=new JLabel("Enter the First Amount to Deposit:",JLabel.RIGHT);
        JLabel sq=new JLabel("SECURITY QUESTIONS",JLabel.CENTER);
        
       
        f.add(name);f.add(fname);f.add(dob);f.add(gender);f.add(email);f.add(pass);f.add(cpassl);f.add(bal);f.add(sq);
        name.setBounds(0,120,(width/2)-70,20);
        name.setFont(new Font("SANS_SERIF", Font.BOLD,15));
        JTextField t1,t2,t3,t4,t5,t8,t7;  
        t1=new JTextField();
               
        JPasswordField t6=new JPasswordField();
        f.add(t1);
        t1.setBounds((width/2)-50,120,150,20);
              
        fname.setBounds(0,160,(width/2)-70,20);
        fname.setFont(new Font("SANS_SERIF", Font.BOLD,15));
        t2=new JTextField();
        f.add(t2);
        t2.setBounds((width/2)-50,160,150,20);
    
        dob.setBounds(0,200,(width/2)-70,20);
        dob.setFont(new Font("SANS_SERIF", Font.BOLD,15));
        t3=new JTextField();
        f.add(t3);
        t3.setBounds((width/2)-50,200,150,20);

        Choice cg=new Choice();
        cg.add("Male");
        cg.add("Female");
        cg.add("Others");
        gender.setBounds(0,240,(width/2)-70,20);
        gender.setFont(new Font("SANS_SERIF", Font.BOLD,15));
       // t4=new JTextField();
        f.add(cg);
        cg.setBounds((width/2)-50,240,150,20);

        email.setBounds(0,280,(width/2)-70,20);
        email.setFont(new Font("SANS_SERIF", Font.BOLD,15));
        t5=new JTextField();
        f.add(t5);
        t5.setBounds((width/2)-50,280,150,20);

        pass.setBounds(0,320,(width/2)-70,20);
        pass.setFont(new Font("SANS_SERIF", Font.BOLD,15));
        f.add(t6);
        t6.setBounds((width/2)-50,320,150,20);

        
        JPasswordField cpass=new JPasswordField();
        cpassl.setBounds(0,360,(width/2)-70,20);
        cpassl.setFont(new Font("SANS_SERIF", Font.BOLD,15));
        //f.add(t6);
        cpass.setBounds((width/2)-50,360,150,20);
        f.add(cpass);
        
        bal.setBounds(0,400,(width/2)-70,20);
        bal.setFont(new Font("SANS_SERIF", Font.BOLD,15));
        t8=new JTextField();
        f.add(t8);
        t8.setBounds((width/2)-50,400,150,20);
        
        sq.setBounds(0,440,width-30,50);
        sq.setFont(new Font("SANS_SERIF",Font.BOLD,20));
        
        JLabel incase=new JLabel("(In case you forget your password)",JLabel.CENTER);
        incase.setBounds(0,480,width-30,50);
        incase.setFont(new Font("SANS_SERIF",Font.BOLD,14));
         
        Choice c=new Choice();
        c.setFont(new Font("SANS_SERIF",Font.PLAIN,15));
        c.setBounds((width/4),540,width/2,20);
        c.add("What was your childhood nickname?");
        c.add("What's the name  of the first school you attended?");
        c.add("What was your first pet's name?");
        c.add("What's the name of the city where your parents meet?");
        c.add("What is the name of your favourite childhood friend?");
        t7=new JTextField();
        f.add(incase);
        f.add(c);
        f.add(t7);
        t7.setBounds((width/4),580,width/2,27);

        JButton submit=new JButton("SUBMIT");
        f.add(submit);
        submit.setBounds((width/2)-140,640,100,30);
        submit.setBackground(new java.awt.Color(76,82,112));
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("SANS_SERIF",Font.BOLD,15));
        JButton close=new JButton("CLOSE");
        error er=new error();
        submit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
            final String ins="insert into accountdetails(name,fathername,birthdate,gender,emailid,password,accountno,status,security) values(?,?,TO_DATE(?,'YYYY-MM-DD'),?,?,?,?,?,?)";
                try{
                    boolean found=false;
                    if(!t6.getText().equals(cpass.getText())){
                        er.error("Password Not Same!");
                        //.dispose();
                        return;
                    }
                    Random random = new Random();
                    int r=random.nextInt(100000000);
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bms","root"); 
                    PreparedStatement stmt=con.prepareStatement(ins);
                    String id=t5.getText();
                    id=id.toLowerCase();
                    checkemailandpass ceap=new checkemailandpass();
                    if(ceap.checkemail(id)){
                        String pas=t6.getText();
                        if(ceap.checkpass(pas)){
                            stmt.setString(1,t1.getText());
                            stmt.setString(2,t2.getText());
                            stmt.setString(3,t3.getText());
                            stmt.setString(4,cg.getItem(cg.getSelectedIndex()));
                            stmt.setString(5,id);
                            stmt.setString(6,pas);
                            stmt.setInt(7,r);
                            stmt.setString(8,"ACTIVE");
                            stmt.setString(9,c.getItem(c.getSelectedIndex())+t7.getText());
                            stmt.executeUpdate();
                            String insbal="insert into balancefacilities(emailid,balance,accountno) values(?,?,?)";
                            stmt=con.prepareStatement(insbal);
                            stmt.setString(1,id);
                            String vala=t8.getText();
                            Float val=Float.valueOf(vala);
                            stmt.setFloat(2,val);
                            stmt.setInt(3,r);
                            stmt.executeUpdate();
                            String newtemp=str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\dit\\users\\"+r+".txt";
                            File ff=new File(newtemp);
                            ff.createNewFile();
                            FileWriter fw=new FileWriter(newtemp,true);
                            BufferedWriter bw=new BufferedWriter(fw);
                            String les=da+" Account Opening Deposit Rs."+val+"\n";
                            bw.write(les);
                            bw.close();
                            String inslog="insert into logindetails(emailid,pass,accountno) values(?,?,?)";
                            stmt=con.prepareStatement(inslog);
                            stmt.setString(1,id);
                            stmt.setString(2,pas);
                            stmt.setInt(3,r);
                            stmt.executeUpdate();
                            JFrame f= new JFrame();  
                            Image im=Toolkit.getDefaultToolkit().getImage(str+"\\src\\main\\java\\com\\mycompany\\bankmanagement\\data\\img\\tick.png");
                            f.setIconImage(im);
                            JDialog d = new JDialog(f , "Success", true);  
                            //d.setLocationRelativeTo(null);
                            d.setLocation(width/2-150,height/2-100);
                            d.setLayout(null);  
                            JButton b = new JButton ("OK");
                            b.setFont(new Font("SANS_SARIF",Font.BOLD,12));
                            b.setSize(40,20);
                            b.setFocusPainted(false);
                            b.setBackground(new java.awt.Color(76,82,112));
                            b.setForeground(Color.WHITE);
                            b.setBounds(110,32,60,25);
                            b.addActionListener ( new ActionListener(){  
                                public void actionPerformed( ActionEvent e ){
                                    
                                    f.dispose();                          
                                }  
                            });
                            found=true;
                            JLabel err=new JLabel("Account Created Successfully!",JLabel.CENTER);
                            err.setBounds(0,5,290,20);
                            d.add(err);
                            d.add(b);   
                            d.setSize(300,110);    
                            d.setVisible(true);
                            con.close();
                            
                        }else{
                            er.passerror("Password Criteria Not Met!");
                        }
                    }else{
                        er.error("Not a Valid Email!");
                    }
                    if(found)
                        f.dispose();
                }catch(SQLDataException sde){
                    er.error("Invalid Birthdate.Make sure it is in format (YYYY/MM/DD)");
                }catch(SQLIntegrityConstraintViolationException sicve ){
                    er.error("Email Id already Exists");
                }catch(NumberFormatException nfe){
                    er.error("Enter a valid amount.");
                }catch(Exception e){
                    er.error(String.valueOf(e));
                }
                
            }
        });
        close.setBounds((width/2)+10,640,100,30);
        close.setBackground(Color.RED);
        close.setForeground(Color.WHITE);
        close.setFont(new Font("SANS_SERIF",Font.BOLD,15));
        f.add(close);
        close.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                f.dispose();
            }
        });
    }
}