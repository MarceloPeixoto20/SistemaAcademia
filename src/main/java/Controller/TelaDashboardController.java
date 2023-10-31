/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Clientes;
import Model.DAO;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MICRO
 */
public class TelaDashboardController implements Initializable {
    
    @FXML
    private Label lblTotalClientes;    
    @FXML
    private Label lblTotalProfessores;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnAlunos;
    @FXML
    private Button btnProfessores;
    @FXML
    private Button btnSair;
    
    Clientes clientes = new Clientes();
    DAO dao = new DAO();
    private Stage stage;
    private Stage stage1;
    private Scene scene;
    private Parent root;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        stage = new Stage();
        stage1 = new Stage();
        Totalclientes();
    }
    
    public void Totalclientes(){
        int qtd = 0;
        qtd = dao.Totalclientes();              
        lblTotalClientes.setText(String.valueOf(qtd));
    }
    
    
    public void sair(){
        System.exit(0);
    }
    public void Alunos (){
        try {
            URL url = new File("src/main/java/com/mycompany/projetoacademia/TelaIndex.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            Scene telalogin = new Scene(root);
            stage = (Stage) btnAlunos.getScene().getWindow();                
            stage1.setScene(telalogin);
            stage1.show();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
