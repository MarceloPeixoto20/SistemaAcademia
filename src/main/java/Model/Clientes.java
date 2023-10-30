/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;


/**
 *
 * @author MICRO
 */
public class Clientes {
    
    private String CPF;
    private String Nome;
    private String TelefoneZap;
    private String TelefoneEme;
    private String Rua;
    private String Bairro;
    private Date DataVencimento;

    public Clientes() {
    }

    public Clientes(String CPF, String Nome, String TelefoneZap, String TelefoneEme, String Rua, String Bairro, Date DataVencimento) {
        this.CPF = CPF;
        this.Nome = Nome;
        this.TelefoneZap = TelefoneZap;
        this.TelefoneEme = TelefoneEme;
        this.Rua = Rua;
        this.Bairro = Bairro;
        this.DataVencimento = DataVencimento;
    }

    public String getCPF() {
        return CPF;
    }

    public String getNome() {
        return Nome;
    }

    public String getTelefoneZap() {
        return TelefoneZap;
    }

    public String getTelefoneEme() {
        return TelefoneEme;
    }

    public String getRua() {
        return Rua;
    }

    public String getBairro() {
        return Bairro;
    }

    public Date getDataVencimento() {
        return DataVencimento;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public void setTelefoneZap(String TelefoneZap) {
        this.TelefoneZap = TelefoneZap;
    }

    public void setTelefoneEme(String TelefoneEme) {
        this.TelefoneEme = TelefoneEme;
    }

    public void setRua(String Rua) {
        this.Rua = Rua;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public void setDataVencimento(Date DataVencimento) {
        this.DataVencimento = DataVencimento;
    }
    
    
    
}
