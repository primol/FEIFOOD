/*
 * Login Screen - Modern Design
 */
package View;

import Controller.ControleLogin;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author unifvipereira
 */
public class Login extends javax.swing.JFrame {

    private ControleLogin c;

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        c = new ControleLogin(this);
    }

    public JButton getBtCadastrar() {
        return btCadastrar;
    }

    public JButton getBtEntra1() {
        return btEntra1;
    }

    public JPasswordField getTxtSenha() {
        return txtSenha;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblSenha = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        btEntra1 = new javax.swing.JButton();
        btCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("iFood - Login");
        setResizable(false);
        getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        // Title Label
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 48));
        lblTitle.setForeground(new Color(220, 20, 60)); // Crimson red
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("iFood");

        // User Label
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUsuario.setForeground(new Color(80, 80, 80));
        lblUsuario.setText("Usuário:");

        // User Text Field
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 20, 60), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        txtUsuario.setBackground(Color.WHITE);

        // Password Label
        lblSenha.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSenha.setForeground(new Color(80, 80, 80));
        lblSenha.setText("Senha:");

        // Password Text Field
        txtSenha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtSenha.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 20, 60), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        txtSenha.setBackground(Color.WHITE);

        // Login Button
        btEntra1.setBackground(new Color(220, 20, 60));
        btEntra1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btEntra1.setForeground(Color.WHITE);
        btEntra1.setText("Entrar");
        btEntra1.setBorderPainted(false);
        btEntra1.setFocusPainted(false);
        btEntra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEntra1ActionPerformed(evt);
            }
        });

        // Register Button
        btCadastrar.setBackground(new Color(180, 0, 40));
        btCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btCadastrar.setForeground(Color.WHITE);
        btCadastrar.setText("Não tem uma conta? Cadastre-se");
        btCadastrar.setBorderPainted(false);
        btCadastrar.setFocusPainted(false);
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSenha)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEntra1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(lblTitle)
                .addGap(50, 50, 50)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btEntra1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
        Cadastro tela3 = new Cadastro();
        tela3.setVisible(true);
    }

    private void btEntra1ActionPerformed(java.awt.event.ActionEvent evt) {
        c.loginAluno();
    }
    
    // Variables declaration - do not modify
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btEntra1;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration
}
