/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.DAO;
import Model.DAO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MICRO
 */
public class TelaLoginController implements Initializable {

    private Stage stage;
    private Stage stage1;
    private Scene scene;
    private Parent root;
    private Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;
    private Alert alert;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Button btnLogin;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        stage = new Stage();
        stage1 = new Stage();
        txtLogin.setText("");
        txtSenha.setText("");

        // Adicionar listeners aos componentes
        btnLogin.setOnAction(event -> {
        // Validar os dados de login
        if (txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()) {
            // Exibir uma mensagem de erro
        } else {
            // Efetuar o login
            logar(event);
        }
    });
    }         
    
    @FXML
    private void logar(ActionEvent event){
        
        String Login = txtLogin.getText();
        String Senha = txtSenha.getText();
        String login = "SELECT * FROM usuarios WHERE login=? AND senha=?" ;        

        try {
            conexao = DAO.conector();
            pst = conexao.prepareStatement(login);
            pst.setString(1, Login);
            pst.setString(2, Senha);
            rs = pst.executeQuery(); 
            if (rs.next()) {
                if(rs != null){                
                    URL url = new File("src/main/java/com/mycompany/projetoacademia/TelaDashboard.fxml").toURI().toURL();
                    root = FXMLLoader.load(url);
                    Scene telalogin = new Scene(root);
                    stage = (Stage) btnLogin.getScene().getWindow();                
                    stage1.setScene(telalogin);
                    stage1.show();
                    stage.close();
                }else{
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText(null);
                    alert.setContentText("Login ou/e senha Invalidos");
                    alert.showAndWait();
                }
            } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText(null);
                    alert.setContentText("Login ou/e senha Invalidos");
                    alert.showAndWait();
                        
            }
            
            conexao.close();            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
}
