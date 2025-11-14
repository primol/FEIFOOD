/*
 * aqui vai ser o login  TELA IMPORTANTE
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
import Controller.UserSession;
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
                int id = res.getInt("id");
                String nome = res.getString("nome");
                
                Aluno loggedInAluno = new Aluno(id, nome, aluno.getUsuario(), aluno.getSenha());
                UserSession.getInstance().setLoggedInAluno(loggedInAluno);

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

                    "Erro: " + e.getMessage(),
                    "Erro de Conexão",
                    JOptionPane.ERROR_MESSAGE);
        }
                
    }
}
