/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import View.Login;
import Model.Aluno;
import DAO.AlunoDAO;
import DAO.Conexao;
import View.FoodMenu;
/**
 *
 * @author unifvipereira
 */
public class ControleLogin {
    private Login tela1;

    public ControleLogin(Login tela1) {
        this.tela1 = tela1;
    }
    
    public void loginAluno(){
        String senha = new String(tela1.getTxtSenha().getPassword());
        Aluno aluno = new Aluno(null,
                tela1.getTxtUsuario().getText(),
                senha);
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            AlunoDAO dao = new AlunoDAO(conn);
            ResultSet res = dao.consultar(aluno);
            if(res.next()){
                JOptionPane.showMessageDialog(tela1, 
                        "Login efetuado com sucesso",
                        "Aviso",
                        JOptionPane.INFORMATION_MESSAGE);
                tela1.dispose(); // Close login window
                FoodMenu foodMenu = new FoodMenu();
                foodMenu.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(tela1, 
                        "Login nao efetuado",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }catch(SQLException e ){
            JOptionPane.showMessageDialog(tela1, 
                    "Erro de conexão com o banco de dados.\n\n" +
                    "Verifique:\n" +
                    "• PostgreSQL está rodando?\n" +
                    "• Database 'alunos' existe?\n" +
                    "• Usuário: postgres\n" +
                    "• Senha: aluno\n" +
                    "• Porta: 5432\n\n" +
                    "Erro: " + e.getMessage(),
                    "Erro de Conexão",
                    JOptionPane.ERROR_MESSAGE);
        }
                
    }
}
