package com.example.ecommerce;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.sql.SQLException;

public class ProductList {
public static TableView<Product> productTable;
public static Pane getAllProducts() throws SQLException {
    TableColumn id=new TableColumn("idA");
    id.setCellValueFactory(new PropertyValueFactory<>("id"));

    TableColumn name=new TableColumn("Name");
    name.setCellValueFactory(new PropertyValueFactory<>("name"));

    TableColumn price=new TableColumn("price");
    price.setCellValueFactory(new PropertyValueFactory<>("price"));

    TableColumn quantity=new TableColumn("quantity");
    price.setCellValueFactory(new PropertyValueFactory<>("quantity"));

    ObservableList<Product>data= FXCollections.observableArrayList();
    data.addAll(new Product(123,"Laptop",234d,4),new Product(1243,"Laptop",2324d,5));
//getAllProducts
    ObservableList<Product> productList=Product.getAllProducts();



    productTable=new TableView();
    productTable.setItems(productList);
    productTable.getColumns().addAll(id,name,price,quantity);
    Pane tablePane=new Pane();
    tablePane.setTranslateY(50);
    tablePane.getChildren().add(productTable);
    return tablePane;
    }
}
