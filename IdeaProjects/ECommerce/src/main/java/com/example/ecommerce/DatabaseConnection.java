package com.example.ecommerce;
import java.sql.*;

public class DatabaseConnection {
    String dbUrl="jdbc:mysql://localhost:3306/ecomm";
    String username="root";
    String password="Shijin@99";
    private Statement getStatement() throws SQLException {
        try{
            Connection conn=DriverManager.getConnection(dbUrl,username,password);
            return conn.createStatement();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getQueryTable(String query) throws SQLException {
        Statement statement=getStatement();
        try{
            statement.executeQuery(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        String query="SELECT * FROM products";
        DatabaseConnection dbconn=new DatabaseConnection();
        ResultSet rs= dbconn.getQueryTable(query);
        if(rs!=null){
            System.out.println("Connected to database");
        }
    }

}
