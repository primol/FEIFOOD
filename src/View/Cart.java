/*
 * Shopping Cart Window
 */
package View;

import Controller.CartController;
import Model.CartItem;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author unifvipereira
 */
public class Cart extends javax.swing.JFrame {

    private CartController controller;
    private DefaultListModel<CartItem> cartListModel;
    
    /**
     * Creates new form Cart
     */
    public Cart() {
        initComponents();
        cartListModel = new DefaultListModel<>();
        cartList.setModel(cartListModel);
        controller = new CartController(this);
        controller.loadCart();
        
        // Add listener to update spinner when item is selected
        cartList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                CartItem selected = cartList.getSelectedValue();
                if (selected != null) {
                    spnQuantity.setValue(selected.getQuantity());
                }
            }
        });
    }

    public JList<CartItem> getCartList() {
        return cartList;
    }

    public DefaultListModel<CartItem> getCartListModel() {
        return cartListModel;
    }

    public JSpinner getSpnQuantity() {
        return spnQuantity;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public javax.swing.JButton getBtnEdit() {
        return btnEdit;
    }

    public javax.swing.JButton getBtnRemove() {
        return btnRemove;
    }

    public javax.swing.JButton getBtnBack() {
        return btnBack;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        cartList = new javax.swing.JList<>();
        lblTitle = new javax.swing.JLabel();
        lblQuantity = new javax.swing.JLabel();
        spnQuantity = new javax.swing.JSpinner();
        btnEdit = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("iFood - Carrinho");
        getContentPane().setBackground(new java.awt.Color(245, 245, 245));

        cartList.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cartList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        cartList.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(cartList);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 87, 34));
        lblTitle.setText("🛒 iFood - Carrinho");

        lblQuantity.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuantity.setText("Quantidade:");

        spnQuantity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        spnQuantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, 99, 1));

        btnEdit.setBackground(new java.awt.Color(255, 87, 34));
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("✏️ Editar");
        btnEdit.setBorderPainted(false);
        btnEdit.setFocusPainted(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnRemove.setBackground(new java.awt.Color(220, 53, 69));
        btnRemove.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRemove.setForeground(new java.awt.Color(255, 255, 255));
        btnRemove.setText("🗑️ Remover");
        btnRemove.setBorderPainted(false);
        btnRemove.setFocusPainted(false);
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(255, 87, 34));
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("← Voltar");
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 87, 34));
        lblTotal.setText("Total:");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 87, 34));
        txtTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtTotal.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 87, 34), 2),
            javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtTotal.setText("R$ 0,00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblQuantity)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantity)
                    .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        controller.editQuantity();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        controller.removeItem();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        controller.backToMenu();
    }//GEN-LAST:event_btnBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnRemove;
    private javax.swing.JList<CartItem> cartList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JSpinner spnQuantity;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

