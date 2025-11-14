/*
 *aqui vai ser a telinha que limpa e fecha pedido
 */
package Controller;

import DAO.OrderDAO;
import Model.Aluno;
import Model.CartItem;
import Model.Order;
import Model.OrderItem;
import View.Cart;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author unifvipereira
 */
public class CartController {
    private Cart view;
    private CartManager cartManager;
    private OrderDAO orderDAO;
    
    public CartController(Cart view) {
        this.view = view;
        this.cartManager = CartManager.getInstance();
        this.orderDAO = new OrderDAO();
    }
    
    public void loadCart() {
        List<CartItem> items = cartManager.getCartItems();
        DefaultListModel<CartItem> model = view.getCartListModel();
        model.clear();
        for (CartItem item : items) {
            model.addElement(item);
        }
        updateTotal();
    }
    
    public void editQuantity() {
        CartItem selected = view.getCartList().getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(view, "Por favor, selecione um item primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int newQuantity = (Integer) view.getSpnQuantity().getValue();
        if (newQuantity <= 0) {
            JOptionPane.showMessageDialog(view, "A quantidade deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        cartManager.updateQuantity(selected.getFood().getId(), newQuantity);
        loadCart();
        JOptionPane.showMessageDialog(view, "Quantidade atualizada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void removeItem() {
        CartItem selected = view.getCartList().getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(view, "Por favor, selecione um item primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(view, 
                "Deseja remover " + selected.getFood().getName() + " do carrinho?",
                "Confirmar Remoção",
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            cartManager.removeFromCart(selected.getFood().getId());
            loadCart();
            JOptionPane.showMessageDialog(view, "Item removido do carrinho!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void finalizePurchase() {
        Aluno aluno = UserSession.getInstance().getLoggedInAluno();
        if (aluno == null) {
            JOptionPane.showMessageDialog(view, "Você precisa estar logado para finalizar a compra!", "Erro", JOptionPane.ERROR_MESSAGE);
            return; //mt avancado isso
        }

        List<CartItem> cartItems = cartManager.getCartItems();
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Seu carrinho está vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            orderItems.add(new OrderItem(cartItem.getFood().getId(), cartItem.getQuantity(), cartItem.getFood().getPrice()));
        }

        Order order = new Order(aluno.getId(), new Timestamp(System.currentTimeMillis()), cartManager.getTotal(), orderItems);

        try {
            orderDAO.saveOrder(order);
            cartManager.clearCart();
            loadCart();
            JOptionPane.showMessageDialog(view, "Compra finalizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            view.dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao finalizar a compra!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void backToMenu() {
        view.dispose();
    }
    
    private void updateTotal() {
        double total = cartManager.getTotal();
        view.getTxtTotal().setText("R$ " + String.format("%.2f", total));
    }
}

