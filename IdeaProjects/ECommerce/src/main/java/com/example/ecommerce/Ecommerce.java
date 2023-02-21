package com.example.ecommerce;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Ecommerce extends Application {
    private final int width=500,height=600,headerLine=50;
ProductList productList=new ProductList();
static Customer loggedInCustomer=null;
Pane bodyPane;

Button signInButton=new Button("Sign IN");
static Label welcomeLabel=new Label("Welcome !!");

    private GridPane headerBar(){
        GridPane header=new GridPane();
        TextField searchBar=new TextField();
        Button searchButton=new Button("search");
        Button signInButton=new Button("signInButton");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                try {
                    bodyPane.getChildren().add(productList.getAllProducts());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                try {
                    bodyPane.getChildren().add(loginPage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
//        header.setHgap(10);
        header.add(searchBar,0,0);
        header.add(searchButton,1,0);
        header.add(signInButton,2,0);
        header.add(welcomeLabel,3,0);
        return header;
    }
    private static GridPane loginPage(){
        Label userLabel=new Label("User Name");
        Label passLabel=new Label("Password");
        TextField userName=new TextField();
        userName.setPromptText("Enter username");
        PasswordField password=new PasswordField();
        password.setPromptText("Enter Password");
        Button loginButton =new Button("Login");
        Label messageLabel=new Label("Login - Message");


        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user=userName.getText();
                String pass=password.getText();
                loggedInCustomer=Login.customerLogin(user,pass);
                if(loggedInCustomer!=null){
                    messageLabel.setText("Login Successfully!!");
                    welcomeLabel.setText("Welcome "+loggedInCustomer.getName());
                }
                else{
                    messageLabel.setText("Login Failed!!");
                }
            }
        });
        GridPane loginPane=new GridPane();
        loginPane.setTranslateY(50);
        loginPane.setVgap(10);
        loginPane.setHgap(10);


        loginPane.add(userLabel,0,0);
        loginPane.add(userName,1,0);
        loginPane.add(passLabel,0,1);
        loginPane.add(password,1,1);
        loginPane.add(loginButton,0,2);
        loginPane.add(messageLabel,1,2);
        return loginPane;
    }
    private Pane createContent() throws SQLException {
        Pane root=new Pane();
        root.setPrefSize(width,height+2*headerLine);
        bodyPane=new Pane();
        bodyPane.setPrefSize(width,height);
        bodyPane.setTranslateY(headerLine);
        bodyPane.setTranslateX(10);
//        ,productList.getAllProducts()
        bodyPane.getChildren().add(loginPage());
//        ,productList.getAllProducts()
//        bodyPane
//        ,ProductList.getAllProducts()
        root.getChildren().addAll(headerBar(),bodyPane,footerBar());
        return root;
    }

    private GridPane footerBar(){
    Button buyNowButton=new Button("Buy Now");
    GridPane footer=new GridPane();
    footer.setTranslateY(headerLine+height);
    footer.add(buyNowButton,0,0);
    return footer;
    }
    @Override
    public void start(Stage stage) throws IOException, SQLException {

        Scene scene = new Scene(createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        launch();

    }
}