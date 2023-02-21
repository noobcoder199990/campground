package com.example.ecommerce;

import javafx.scene.control.CustomMenuItem;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login {
    static private byte[] getSha(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String getEncryptedPassword(String password){
        try{
            BigInteger num=new BigInteger(1,getSha(password));
            StringBuilder hexString=new StringBuilder(num.toString(16));//16 mean convert to hexadecimal
            return hexString.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static Customer customerLogin(String userEmail,String password){
//        String query="SELECT * FROM customer WHERE email='"+userEmail+" ' and password='"+password+"'";
        String encryptedPass=getEncryptedPassword(password);
        System.out.print(encryptedPass);
        String query="SELECT * FROM customer WHERE email='"+userEmail+" ' and password='"+encryptedPass+"'";
        DatabaseConnection dbconn=new DatabaseConnection();
//        Customer newc=new Customer();
        try{
            ResultSet rs=dbconn.getQueryTable(query);
            if(rs!=null && rs.next() ){
                return new Customer(
                        rs.getInt("cid"),
                        rs.getString("name"),
                rs.getString("email"));
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//               System.out.println(customerLogin("Shijin@gmail.com", "Shijin"));
        System.out.println(getEncryptedPassword("Shijin"));
    }
}
