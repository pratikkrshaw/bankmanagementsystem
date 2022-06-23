/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankmanagement;
import java.util.regex.*;
/**
 *
 * @author SHAW
 */
public class checkemailandpass {
    final String emailregex="[\\w]{6,15}@[a-z]{5,7}.com";
    final String passregex="^(?=.*[0-9])"+"(?=.*[a-z])(?=.*[A-Z])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$).{8,20}$";
    boolean checkemail(String email){
        Pattern ptr=Pattern.compile(emailregex);
        Matcher mch=ptr.matcher(email);
        boolean check=mch.matches();
        return check;
    }
    boolean checkpass(String pass){
        Pattern ptr=Pattern.compile(passregex);
        Matcher mch=ptr.matcher(pass);
        boolean check=mch.matches();
        return check;
    }
}
