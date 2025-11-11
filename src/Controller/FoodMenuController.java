/*
 * Food Menu Controller
 */
package Controller;

import DAO.FoodDAO;
import Model.Food;
import View.Cart;
import View.FoodDetails;
import View.FoodMenu;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author unifvipereira
 */
public class FoodMenuController {
    private FoodMenu view;
    private FoodDAO foodDAO;
    private CartManager cartManager;
    
    public FoodMenuController(FoodMenu view) {
        this.view = view;
        this.foodDAO = new FoodDAO();
        this.cartManager = CartManager.getInstance();
    }
    
    public void loadFoods() {
        List<Food> foods = foodDAO.getAllFoods();
        view.displayFoods(foods);
        updateCartCount();
    }
    
    public void searchFoods() {
        String searchTerm = view.getTxtSearch().getText().trim();
        List<Food> results;
        
        if (searchTerm.isEmpty()) {
            results = foodDAO.getAllFoods();
        } else {
            results = foodDAO.searchFoods(searchTerm);
        }
        
        view.displayFoods(results);
        
        if (results.isEmpty() && !searchTerm.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nenhum alimento encontrado!", "Busca", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void addToCart() {
        Food selected = view.getSelectedFood();
        if (selected == null) {
            JOptionPane.showMessageDialog(view, "Por favor, selecione um alimento primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String quantityStr = JOptionPane.showInputDialog(view, "Quantidade:", "Adicionar ao Carrinho", JOptionPane.QUESTION_MESSAGE);
        if (quantityStr == null || quantityStr.trim().isEmpty()) {
            return;
        }
        
        try {
            int quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(view, "A quantidade deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            cartManager.addToCart(selected, quantity);
            updateCartCount();
            JOptionPane.showMessageDialog(view, quantity + "x " + selected.getName() + " adicionado(s) ao carrinho!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Por favor, insira um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void viewDetails() {
        Food selected = view.getSelectedFood();
        if (selected == null) {
            JOptionPane.showMessageDialog(view, "Por favor, selecione um alimento primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        FoodDetails detailsWindow = new FoodDetails(selected);
        detailsWindow.setVisible(true);
    }
    
    public void viewCart() {
        Cart cartWindow = new Cart();
        cartWindow.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                updateCartCount();
            }
        });
        cartWindow.setVisible(true);
    }
    
    public void updateCartCount() {
        int count = cartManager.getItemCount();
        view.updateCartCount(count);
    }
}

