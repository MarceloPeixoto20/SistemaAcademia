package com.mycompany.projetoacademia;

import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;



/**
 * JavaFX App
 */
public class App extends Application {  
    
    
    @Override
    public void start(Stage stage) {
        
        try {            
            URL url = new File("src/main/java/com/mycompany/projetoacademia/TelaLogin.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);            
            Scene telalogin = new Scene(root);
            stage.setScene(telalogin);
            stage.show();            
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}