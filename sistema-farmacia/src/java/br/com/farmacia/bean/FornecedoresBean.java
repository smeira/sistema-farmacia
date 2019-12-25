/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

/**
 *
 * @author samanta
 */
@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {

    // variável para guardar o resultado das consultas
    private ListDataModel<Fornecedores> itens;
    //private Fornecedores fornecedores;

    //por ser privada, precisa de getters e setter para ser acessada externamente
    public ListDataModel<Fornecedores> getItens() {
        return itens;
    }

    public void setItens(ListDataModel<Fornecedores> itens) {
        this.itens = itens;
    }

    /* public Fornecedores getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(Fornecedores fornecedores) {
        this.fornecedores = fornecedores;
    }
    
    private ArrayList<Fornecedores> itens;*/

 /*Método para buscar registros do Banco de Dados
    Será utlilizado o listar() do FornecedoresDAO
    PostConstruct = a lista será iniciada automaticamente quando a página for carregada*/
    @PostConstruct
    public void prepararPesquisa() {
        
        
        
        try {
            FornecedoresDAO fdao = new FornecedoresDAO();
            ArrayList<Fornecedores> lista = fdao.listar();
            itens = new ListDataModel<Fornecedores>(lista);
            //itens = fdao.listar();

        } catch (SQLException e) {
            //System.out.println("Erro ao iniciar lista.");
            e.printStackTrace();
        }
      
    }
    /*
    @PostConstruct
    public void init() {
        prepararPesquisa();
    }*/
}
