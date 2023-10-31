/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Clientes;
import Model.DAO;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MICRO
 */
public class TelaIndexController implements Initializable {
    
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnAlunos;
    @FXML
    private Button btnProfessores;
    @FXML
    private Button btnSair;
    @FXML
    private Button btnCadastrar;
    @FXML
    private TextField txtPesquisa;
       
    
    private Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;
    private Alert alert;
    DAO dao = new DAO();
    Clientes clientes = new Clientes();
    @FXML
    private TableView<Clientes> tblTabelaAlunos; 
    private Stage stage;
    private Stage stage1;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TableColumn<Clientes, String> addCliente_col_CPF;
    @FXML
    private TableColumn<Clientes, String> addCliente_col_Nome;
    @FXML
    private TableColumn<Clientes, String> addCliente_col_TelefoneZap;
    @FXML
    private TableColumn<Clientes, String> addCliente_col_TelefoneEme;
    @FXML
    private TableColumn<Clientes, String> addCliente_col_Rua;    
    @FXML
    private TableColumn<Clientes, String> addCliente_col_Bairro;
    @FXML
    private TableColumn<Clientes, Date> addCliente_col_Dataven;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addlistar();
        stage = new Stage();
        stage1 = new Stage();
        
    }

    public void sair(){
        System.exit(0);
    }
    
    public ObservableList<Clientes> listar(){
        ObservableList<Clientes> data = FXCollections.observableArrayList();
        try {
            ArrayList<Clientes> clientes = dao.listarclientes();            
            data.addAll(clientes);            
        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
        
    }    
    
    private ObservableList<Clientes> addLista;
    public void addlistar(){        
        addLista = listar();           
        //System.out.println(addLista.get(0).getCPF());
        addCliente_col_CPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        addCliente_col_Nome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        addCliente_col_TelefoneZap.setCellValueFactory(new PropertyValueFactory<>("TelefoneZap"));
        addCliente_col_TelefoneEme.setCellValueFactory(new PropertyValueFactory<>("TelefoneEme"));
        addCliente_col_Rua.setCellValueFactory(new PropertyValueFactory<>("Rua"));
        addCliente_col_Bairro.setCellValueFactory(new PropertyValueFactory<>("Bairro"));        
        addCliente_col_Dataven.setCellValueFactory(cellData -> {
        SimpleObjectProperty<java.sql.Date> property = new SimpleObjectProperty<>(java.sql.Date.valueOf(cellData.getValue().getDataVencimento().toLocalDate()));
        return property;
        });        
              
        
        tblTabelaAlunos.setItems(addLista);
    }
    
    public void SelecionarCliente(){
        Clientes cliente = tblTabelaAlunos.getSelectionModel().getSelectedItem();
        
    }
    
    public void Cadastrar(ActionEvent event){
        try {
            URL url = new File("src/main/java/com/mycompany/projetoacademia/TelaCadastro.fxml").toURI().toURL();
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
