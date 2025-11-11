/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import Model.Aluno;
/**
 *
 * @author unifvipereira
 */
public class AlunoDAO {
    private Connection conexao;

    public AlunoDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public ResultSet consultar(Aluno aluno) throws SQLException{
        String sql = "select * from tbaluno where usuario = ? and senha = ?";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, aluno.getUsuario());
        statement.setString(2, aluno.getSenha());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
    
    public boolean inserir(Aluno aluno) throws SQLException {
        // Check if user already exists
        String checkSql = "select * from tbaluno where usuario = ?";
        PreparedStatement checkStmt = conexao.prepareStatement(checkSql);
        checkStmt.setString(1, aluno.getUsuario());
        ResultSet rs = checkStmt.executeQuery();
        
        if (rs.next()) {
            return false; // User already exists
        }
        
        // Insert new user
        String sql = "insert into tbaluno (nome, usuario, senha) values (?, ?, ?)";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, aluno.getNome());
        statement.setString(2, aluno.getUsuario());
        statement.setString(3, aluno.getSenha());
        statement.executeUpdate();
        return true;
    }
}
