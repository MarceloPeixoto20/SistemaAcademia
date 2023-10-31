/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Clientes;
import Model.DAO;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MICRO
 */
public class TelaEditarController implements Initializable {

    @FXML
    private TextField txtClienteCPF;
    @FXML
    private TextField txtClienteNome;
    @FXML
    private TextField txtClienteTelefoneZap;
    @FXML
    private TextField txtClienteTelefoneEme;
    @FXML
    private TextField txtClienteRua;
    @FXML
    private TextField txtClienteBairro;
    @FXML
    private TextField txtClienteDataven;
    
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnAlunos;
    @FXML
    private Button btnProfessores;
    @FXML
    private Button btnSair;
    @FXML
    private Button btnAlterar;
    
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
        txtClienteCPF.setText("");
        txtClienteNome.setText("");
        txtClienteTelefoneZap.setText("");
        txtClienteTelefoneEme.setText("");
        txtClienteRua.setText("");
        txtClienteBairro.setText("");
        txtClienteDataven.setText("");        
        
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
    public void sair(){
        System.exit(0);
    }
    
    public void Setartext(){
        txtClienteCPF.setText("");
        txtClienteNome.setText("");
        txtClienteTelefoneZap.setText("");
        txtClienteTelefoneEme.setText("");
        txtClienteRua.setText("");
        txtClienteBairro.setText("");
        txtClienteDataven.setText("");
    }
    
    public void Inicializardados(Clientes Cliente){ 
        txtClienteCPF.setText(Cliente.getCPF());
        txtClienteNome.setText(Cliente.getNome());
        txtClienteTelefoneZap.setText(Cliente.getTelefoneZap());
        txtClienteTelefoneEme.setText(Cliente.getTelefoneEme());
        txtClienteRua.setText(Cliente.getRua());
        txtClienteBairro.setText(Cliente.getBairro());
        txtClienteDataven.setText(Cliente.getDataVencimento().toString());
    }
    
    public void Editar(ActionEvent Event){
        java.util.Date data = null;
        String txtdata = txtClienteDataven.getText();
        String Cpf = txtClienteCPF.getText();
        String nome = txtClienteNome.getText();
        String telefoneZap = txtClienteTelefoneZap.getText();
        String telefoneEme = txtClienteTelefoneEme.getText();
        String rua = txtClienteRua.getText();
        String bairro = txtClienteBairro.getText();
        if (Cpf.isEmpty() || nome.isEmpty() || telefoneZap.isEmpty() || telefoneEme.isEmpty() || rua.isEmpty() || bairro.isEmpty() || txtdata.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os campos");
            alert.showAndWait();
            return;
        }
        try {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                data = sdf.parse(txtdata);            
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Formato de Data errado");
                alert.showAndWait();
                return;
                }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dataFormatada = dateFormat.format(data);
            java.util.Date parsedDate = dateFormat.parse(dataFormatada);
            java.sql.Date datasql = new java.sql.Date(parsedDate.getTime());
            
            
            clientes.setCPF(Cpf);
            clientes.setNome(nome);
            clientes.setTelefoneZap(telefoneZap);
            clientes.setTelefoneEme(telefoneEme);
            clientes.setRua(rua);
            clientes.setBairro(bairro);
            clientes.setDataVencimento(datasql);
            dao.alterarclientes(clientes);          
                       
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmaçao");
            alert.setHeaderText(null);
            alert.setContentText("Alterado");
            alert.showAndWait();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
