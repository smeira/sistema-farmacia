/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmacia.DAO;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author samanta
 */
public class FornecedoresDAO {
    public void salvar(Fornecedores f) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO fornecedores ");
        sql.append("(descricao) ");
        sql.append("values(?)");
        
        Connection connect = ConnectionFactory.conectar();
        
        PreparedStatement comando = connect.prepareStatement(sql.toString());
        comando.setString(1, f.getDescricao());
        comando.executeUpdate();        
    }
    
    public void excluir(Fornecedores f) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM fornecedores ");
        sql.append("WHERE codigo=? ");
        
        Connection connect = ConnectionFactory.conectar();
        
        PreparedStatement comando = connect.prepareStatement(sql.toString());
        comando.setString(1, f.getDescricao());
        comando.setLong(1, f.getCodigo());
        comando.executeUpdate(); 
    }
    
    public void editar(Fornecedores f) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE fornecedores ");
        sql.append("SET descricao=? ");
        sql.append("WHERE codigo=? ");
        
        Connection connect = ConnectionFactory.conectar();
        
        PreparedStatement comando = connect.prepareStatement(sql.toString());
        comando.setString(1, f.getDescricao());
        comando.setLong(2, f.getCodigo());
        comando.executeUpdate(); 
    }
    
    public Fornecedores buscaPorCodigo (Fornecedores f) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT codigo, descricao ");
        sql.append("FROM fornecedores ");
        sql.append("WHERE codigo=? ");
        
        Connection connect = ConnectionFactory.conectar();
        
        PreparedStatement comando = connect.prepareStatement(sql.toString());
        comando.setLong(1, f.getCodigo());
        
        ResultSet resultado = comando.executeQuery();
        Fornecedores retorno = null;
        
        if (resultado.next()){
            retorno = new Fornecedores();
            retorno.setCodigo(resultado.getLong("codigo"));
            retorno.setDescricao(resultado.getString("descricao"));
    }
        return retorno;
    }
    
    public ArrayList<Fornecedores > buscaPorDescricao (Fornecedores f) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT codigo, descricao ");
        sql.append("FROM fornecedores ");
        sql.append("WHERE descricao LIKE ?");
        sql.append("ORDER BY descricao ASC ");
        
        Connection connect = ConnectionFactory.conectar();
        
        PreparedStatement comando = connect.prepareStatement(sql.toString());
        comando.setString(1, "%" + f.getDescricao() + "%");
        
        ResultSet resultado = comando.executeQuery();
        
        ArrayList<Fornecedores> lista = new ArrayList<>();
        
        while(resultado.next()){
            Fornecedores item = new Fornecedores();
            item.setCodigo(resultado.getLong("codigo"));
            item.setDescricao(resultado.getString ("descricao"));
            
            lista.add(item);
        }
        
        return lista;
    }
    
    public ArrayList<Fornecedores> listar() throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT codigo, descricao ");
        sql.append("FROM fornecedores ");
        sql.append("ORDER BY descricao ASC ");
        
        Connection connect = ConnectionFactory.conectar();
        
        PreparedStatement comando = connect.prepareStatement(sql.toString());
        
        ResultSet resultado = comando.executeQuery();
        
        //Instanciando o array
        ArrayList<Fornecedores> lista = new ArrayList<>();
        
        //Enquanto houver "próximo" resultado, ele continuará listando
        while(resultado.next()){
            Fornecedores f = new Fornecedores();
            //Guardando as informações dentro da variável f
            f.setCodigo(resultado.getLong("codigo"));
            f.setDescricao(resultado.getString("descricao"));
            //Serão add dentro do array "lista" os resultados que estão na var f
            lista.add(f);
        }
        return lista;
    }    
    
    /**
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException{
        
        // Incluíndo registros (INSERT)
        /*Fornecedores f1 = new Fornecedores();
        f1.setDescricao("Novo Forn Azul");
        Fornecedores f2 = new Fornecedores();
        f2.setDescricao("Amarelo Novo Forn");
        
        FornecedoresDAO fdao = new FornecedoresDAO();
        try{
        fdao.salvar(f1);
        fdao.salvar(f2);
        System.out.println("Salvo com sucesso");
    } catch (SQLException ex) {
            System.out.println("Erro ao salvar");*/
        
        //Excluíndo registros (DELETE)
        /*Fornecedores f1 = new Fornecedores();
        f1.setCodigo(1L);
        
        FornecedoresDAO fdao = new FornecedoresDAO();
        try{
        fdao.excluir(f1);
        System.out.println("Deletado com sucesso");
    } catch (SQLException e) {
            System.out.println("Erro ao deletar");
            e.printStackTrace();*/
        
        //Editando registros (SET)
        /*Fornecedores f1 = new Fornecedores();
        f1.setCodigo(2L);
        f1.setDescricao("Editado LTDA");
        
        FornecedoresDAO fdao = new FornecedoresDAO();
        try{
        fdao.editar(f1);
        System.out.println("Editado com sucesso");
    } catch (SQLException e) {
            System.out.println("Erro ao editar");
            e.printStackTrace();*/
        
        //Buscando pelo código
        /*Fornecedores f1 = new Fornecedores();
        f1.setCodigo(2L);
        
        FornecedoresDAO fdao = new FornecedoresDAO();
        try{
            Fornecedores ff = fdao.buscaPorCodigo(f1);
            System.out.println("Resultado: " + ff);
        } catch (SQLException e){
            System.out.println("Erro ao buscar.");
            e.printStackTrace();*/
        
        //Buscando pela Descrição
        Fornecedores f1 = new Fornecedores();
        f1.setDescricao("Az");
        
        FornecedoresDAO fdao = new FornecedoresDAO();
        try{
            ArrayList<Fornecedores> lista = fdao.buscaPorDescricao(f1);
            for (Fornecedores f : lista){
                System.out.println("Resultado " + f);
            }
        } catch (SQLException e){
            System.out.println("Erro ao buscar.");
            e.printStackTrace();
        }
        
        /*
        //Listando dados na tabela
        // Não é preciso instanciar uma var porque neste caso não será
        //inserido/editado/excluido nenhum dado. Apenas listado.
        FornecedoresDAO fdao = new FornecedoresDAO();
        // Necessário criar um "for" para listar todos os resultados obtidos
        try {
            ArrayList<Fornecedores>lista = fdao.listar();
            
            for (Fornecedores f : lista){
                System.out.println("Resultado " + f);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar.");
        }
         
        }*/
    }
}    
    
        
    
