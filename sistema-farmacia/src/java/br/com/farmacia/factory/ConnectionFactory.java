/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author samanta
 */
public class ConnectionFactory {

    private static final String USUARIO = "root";
    private static final String SENHA = "";
    private static final String URL = "jdbc:mysql://localhost:3306/sistema-farmacia";

    public static Connection conectar() throws SQLException {
        /*Referência ao drive mysql
          Necessário apenas para versões antigas do JAVA (Obsoleto)
          DriverManager.registerDriver(new com.mysql.jdbc.Driver());*/
        
        Connection connect = DriverManager.getConnection(URL, USUARIO, SENHA);
        return connect;
    }

    public static void main(String[] args)throws SQLException, ClassNotFoundException {
        try{
            Connection connect = ConnectionFactory.conectar();
            System.out.println("Conectado com sucesso!");
        } catch(SQLException ex){
            System.out.println("Erro de conexão!");            
        }
    }
}
