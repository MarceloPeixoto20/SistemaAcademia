/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author MICRO
 */
public class DAO {

    public static Connection conector() {
        String url = "jdbc:mysql://localhost:3306/projetoacademia";
        String user = "root";
        String password = "jana90le.";

        try {
            Connection conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    ArrayList<Clientes> Clientes = new ArrayList<>();

    public void inserirclientes(Clientes clientes) {
        String create = "INSERT INTO clientes (CPF, Nome, TelefoneZap, TelefoneEme, Rua, Bairro, DataVencimento)"
                + "VALUES(?,?,?,?,?,?,?) ";
        try {
            Connection con = conector();
            PreparedStatement pst = con.prepareStatement(create);
            pst.setString(1, clientes.getCPF());
            pst.setString(2, clientes.getNome());
            pst.setString(3, clientes.getTelefoneZap());
            pst.setString(4, clientes.getTelefoneEme());
            pst.setString(5, clientes.getRua());
            pst.setString(6, clientes.getBairro());
            pst.setDate(7, (Date) clientes.getDataVencimento());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<Clientes> listarclientes() {
        String read = "SELECT CPF, Nome, TelefoneZap, TelefoneEme, Rua, Bairro, DATE_FORMAT(DataVencimento, '%d/%m/%Y') AS DataVencimentoFormatada FROM clientes ORDER BY Nome";
        Clientes.clear();
        try {
            Connection con = conector();
            PreparedStatement pst = con.prepareStatement(read);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String CPF = rs.getString(1);
                String Nome = rs.getString(2);
                String TelefoneZap = rs.getString(3);
                String TelefoneEme = rs.getString(4);
                String Rua = rs.getString(5);
                String Bairro = rs.getString(6);
                Date DataVencimento = rs.getDate(7);
                Clientes.add(new Clientes(CPF,Nome,TelefoneZap,TelefoneEme,Rua,Bairro,DataVencimento));
                
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Clientes;
    }

    public void alterarclientes(Clientes clientes) {
        String create = "UPDATE clientes SET Nome=? ,TelefoneZap=?, TelefoneEme=?, Rua=?, Bairro=?, DataVencimento=? WHERE CPF=?";

        try {
            Connection con = conector();
            PreparedStatement pst = con.prepareStatement(create);
            pst.setString(1, clientes.getNome());
            pst.setString(2, clientes.getTelefoneZap());
            pst.setString(3, clientes.getTelefoneEme());
            pst.setString(4, clientes.getRua());
            pst.setString(5, clientes.getBairro());
            pst.setDate(6, (Date) clientes.getDataVencimento());
            pst.setString(7, clientes.getCPF());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void selecionarclientes(Clientes clientes) {
        String read2 = "SELECT CPF, Nome, TelefoneZap, TelefoneEme, Rua, Bairro, DATE_FORMAT(DataVencimento, '%d/%m/%Y') AS DataVencimentoFormatada FROM clientes WHERE CPF = ?";

        try {
            Connection con = conector();
            PreparedStatement pst = con.prepareStatement(read2);
            pst.setString(1, clientes.getCPF());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                clientes.setCPF(rs.getString("CPF"));
                clientes.setNome(rs.getString("Nome"));
                clientes.setTelefoneZap(rs.getString("TelefoneZap"));
                clientes.setTelefoneEme(rs.getString("TelefoneEme"));
                clientes.setRua(rs.getString("Rua"));
                clientes.setBairro(rs.getString("Bairro"));
                clientes.setDataVencimento(rs.getDate("DataVencimento"));
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deletarclientes(Clientes clientes) {
        String delete = "DELETE FROM clientes WHERE CPF =?";
        try {
            Connection con = conector();
            PreparedStatement pst = con.prepareStatement(delete);
            pst.setString(1, clientes.getCPF());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
