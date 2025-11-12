/*
 * Food Menu Controller
 */
package Controller;

import DAO.FoodDAO;
import Model.Aluno;
import Model.Food;
import View.Cart;
import View.FoodDetails;
import View.FoodMenu;
import View.OrderHistory;
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
        List<Food> alimentos = foodDAO.getAllFoods();
        view.displayFoods(alimentos);
        updateCartCount();
    }
    
    public void searchFoods() {
        String termoBusca = view.getTxtSearch().getText().trim();
        List<Food> resultados;
        
        if (termoBusca.isEmpty()) {
            resultados = foodDAO.getAllFoods();
        } else {
            resultados = foodDAO.searchFoods(termoBusca);
        }
        
        view.displayFoods(resultados);
        
        if (resultados.isEmpty() && !termoBusca.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nenhum alimento encontrado!", "Busca", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void addToCart() {
        Food selecionado = view.getSelectedFood();
        if (selecionado == null) {
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
        Food selecionado = view.getSelectedFood();
        if (selecionado == null) {
            JOptionPane.showMessageDialog(view, "Por favor, selecione um alimento primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        FoodDetails janelaDetalhes = new FoodDetails(selecionado);
        janelaDetalhes.setVisible(true);
    }
    
    public void viewCart() {
        Cart janelaCarrinho = new Cart();
        janelaCarrinho.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                updateCartCount();
            }
        });
        janelaCarrinho.setVisible(true);
    }

    public void seeMyOrders() {
        Aluno alunoLogado = UserSession.getInstance().getLoggedInAluno();
        if (alunoLogado == null) {
            JOptionPane.showMessageDialog(view, "Você precisa estar logado para ver seus pedidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        OrderHistory historicoPedidos = new OrderHistory();
        new OrderHistoryController(historicoPedidos, alunoLogado.getId());
        historicoPedidos.setVisible(true);
    }
    
    public void updateCartCount() {
        int contagem = cartManager.getItemCount();
        view.updateCartCount(contagem);
    }
}

