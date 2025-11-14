/*
 * tela de cadastro basico
 */
package Controller;

import DAO.AlunoDAO;
import DAO.Conexao;
import Model.Aluno;
import View.Cadastro;
import View.Login;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author unifvipereira
 */
public class ControleCadastro {
    private Cadastro view;

    public ControleCadastro(Cadastro view) {
        this.view = view;
    }
    
    public void cadastrarAluno() {//igual da aula aqui
        String nome = view.getTxtNome().getText().trim();
        String usuario = view.getTxtUsuario().getText().trim();
        String senha = new String(view.getTxtSenha().getPassword()).trim();
        
        if (nome.isEmpty() || usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(view, 
                    "Por favor, preencha todos os campos!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Aluno aluno = new Aluno(nome, usuario, senha);
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            AlunoDAO dao = new AlunoDAO(conn);
            boolean sucesso = dao.inserir(aluno);
            
            if (sucesso) { ////////////////// aqui tem varias execoes que eu nem imaginei mas IA ajudou
                JOptionPane.showMessageDialog(view, 
                        "Cadastro realizado com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                view.dispose();
                Login login = new Login();
                login.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(view, 
                        "Erro ao cadastrar. Usuário já existe!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, 
                    "Erro de conexão com o banco de dados.\n\n" +

                    "Erro: " + e.getMessage(),
                    "Erro de Conexão",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

