/*
 * Login Screen - iFood Style
 */
package View;

import Controller.ControleLogin;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author unifvipereira
 */
public class Login extends javax.swing.JFrame {

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

    public JLabel getLblSenha() {
        return lblSenha;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        getContentPane().setBackground(new java.awt.Color(255, 87, 34));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("🍔 iFood");

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("Usuário:");

        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblSenha.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSenha.setForeground(new java.awt.Color(255, 255, 255));
        lblSenha.setText("Senha:");

        txtSenha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSenha.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btEntra1.setBackground(new java.awt.Color(255, 255, 255));
        btEntra1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btEntra1.setForeground(new java.awt.Color(255, 87, 34));
        btEntra1.setText("Entrar");
        btEntra1.setBorderPainted(false);
        btEntra1.setFocusPainted(false);
        btEntra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEntra1ActionPerformed(evt);
            }
        });

        btCadastrar.setBackground(new java.awt.Color(255, 255, 255));
        btCadastrar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btCadastrar.setForeground(new java.awt.Color(255, 87, 34));
        btCadastrar.setText("Cadastrar");
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
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblUsuario)
                            .addComponent(txtUsuario)
                            .addComponent(lblSenha)
                            .addComponent(txtSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addComponent(btEntra1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(lblTitle)
                .addGap(60, 60, 60)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btEntra1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        Cadastro tela3 = new Cadastro();
        tela3.setVisible(true);
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btEntra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEntra1ActionPerformed
        c.loginAluno();
    }//GEN-LAST:event_btEntra1ActionPerformed

    private ControleLogin c;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btEntra1;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
