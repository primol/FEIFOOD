/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author unifvipereira
 */
public class Conexao {
    public Connection getConnection() throws SQLException{
        try {
            // Load PostgreSQL driver explicitly
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL não encontrado! Adicione postgresql-42.7.8.jar ao classpath.", e);
        }
        
        Connection conexao = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/alunos",
                "postgres",
                "vini");
        return conexao;
    }
}
