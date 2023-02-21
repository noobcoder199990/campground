package com.example.ecommerce;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty quantity;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;



    public int getId(){
        return id.get();
    }
    public String getName(){
        return name.get();
    }
    public Double getPrice(){
        return price.get();
    }
    public Product (int id,String name,Double price){
        this.id=new SimpleIntegerProperty(id);
        this.name=new SimpleStringProperty(name);
        this.price=new SimpleDoubleProperty(price);
    }
    public Product (int id,String name,Double price,int q){
        this.id=new SimpleIntegerProperty(id);
        this.name=new SimpleStringProperty(name);
        this.price=new SimpleDoubleProperty(price);
        this.quantity=new SimpleIntegerProperty(q);
    }
    public static ObservableList<Product> getAllProducts() throws SQLException {
        String allProductList="SELECT * FROM products";
        return getProducts(allProductList);
    }
    public static ObservableList<Product> getProducts(String query) throws SQLException {
        DatabaseConnection dbconn=new DatabaseConnection();
        System.out.print(query);
        ResultSet rs= dbconn.getQueryTable(query);
        ObservableList<Product> result= FXCollections.observableArrayList();
        try{
            if(rs!=null){
                while(rs.next()){
                    result.add(
                            new Product(
                                    rs.getInt("pid"),
                                    rs.getString("name"),
                                    rs.getDouble("price"),
                                    rs.getInt("quantity")
                            )
                    );
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
