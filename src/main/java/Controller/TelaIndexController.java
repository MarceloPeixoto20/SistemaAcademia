/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Clientes;
import Model.DAO;
import static Model.DAO.conector;
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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.*;
import javafx.scene.input.KeyEvent;
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
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
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
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);
        txtPesquisa.setOnMouseClicked(event -> {            
            txtPesquisa.clear();
        });
        txtPesquisa.setOnKeyReleased(event ->{            
            String searchTerm = txtPesquisa.getText().toLowerCase().trim();            
            List<Clientes> resultados = new ArrayList<>();
            // Percorra o seu modelo de dados e adicione os itens correspondentes à lista de resultados
            for (Clientes item : addLista) {
                if (item.getNome().toLowerCase().contains(searchTerm)) {
                    resultados.add(item);
                }
            }

            // Crie um ObservableList a partir dos resultados da pesquisa e defina-o na TableView
            ObservableList<Clientes> observableResultados = FXCollections.observableArrayList(resultados);
            tblTabelaAlunos.setItems(observableResultados);
            });
    }

    public void sair(){
        System.exit(0);
    }
    
    public void Dashboard(){
        try {
            URL url = new File("src/main/java/com/mycompany/projetoacademia/TelaDashboard.fxml").toURI().toURL();
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
    
    public Clientes SelecionarCliente(){
        btnEditar.setDisable(false);
        btnExcluir.setDisable(false);
        return tblTabelaAlunos.getSelectionModel().getSelectedItem();
        
    }
      
    public void editar(ActionEvent event){
        Clientes Cliente = SelecionarCliente();
        if (clientes != null) {
            try {
                URL url = new File("src/main/java/com/mycompany/projetoacademia/TelaEditar.fxml").toURI().toURL();                
                FXMLLoader loader = new FXMLLoader(url);
                root = loader.load();               
                TelaEditarController Editar = new TelaEditarController();
                Editar = loader.getController();                
                Editar.Inicializardados(Cliente);                
                Scene telaEditar = new Scene(root);
                stage = (Stage) btnAlunos.getScene().getWindow();              
                stage1.setScene(telaEditar);
                stage1.show();
                stage.close();                
            } catch (Exception e) {
                e.printStackTrace();            
            }            
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Selecione um cliente para editar.");
            alert.showAndWait();
        }
        
    }
    
    public void Excluir(ActionEvent event){
        clientes = SelecionarCliente();
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmaçao");
            alert.setHeaderText(null);
            alert.setContentText("Voce tem certeza que dejesa Excluir "+clientes.getNome()+"?");
            ButtonType buttonTypeOK = new ButtonType("Sim");
            ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
            ButtonType resultado = alert.showAndWait().orElse(ButtonType.CANCEL);            
            if(resultado==buttonTypeOK){
                addLista.remove(clientes);
                dao.deletarclientes(clientes);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Exito");
                alert.setHeaderText(null);
                alert.setContentText("Deletado");
                alert.showAndWait();
            }else{                
            }
        } catch (Exception e) {
        }
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
