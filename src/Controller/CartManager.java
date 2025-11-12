/*
 * Cart Manager - Singleton responsÃƒÂ¡vel pelo estado do carrinho
 */
package Controller;

import Model.CartItem;
import Model.Food;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitÃ¡ria para armazenar os itens do carrinho durante a sessÃ£o atual.
 */
public class CartManager {
    private static CartManager instance;
    private List<CartItem> itensCarrinho;
    
    private CartManager() {
        itensCarrinho = new ArrayList<>();
    }
    
    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }
    
    public void adicionarAoCarrinho(Food alimento, int quantidade) {
        for (CartItem item : itensCarrinho) {
            if (item.getFood().getId() == alimento.getId()) {
                item.setQuantity(item.getQuantity() + quantidade);
                return;
            }
        }
        itensCarrinho.add(new CartItem(alimento, quantidade));
    }
    
    public void atualizarQuantidade(int alimentoId, int quantidade) {
        for (CartItem item : itensCarrinho) {
            if (item.getFood().getId() == alimentoId) {
                item.setQuantity(quantidade);
                return;
            }
        }
    }
    
    public void removerDoCarrinho(int alimentoId) {
        itensCarrinho.removeIf(item -> item.getFood().getId() == alimentoId);
    }
    
    public List<CartItem> obterItensCarrinho() {
        return new ArrayList<>(itensCarrinho);
    }
    
    public double calcularTotal() {
        double total = 0;
        for (CartItem item : itensCarrinho) {
            total += item.getTotalPrice();
        }
        return total;
    }
    
    public int obterQuantidadeItens() {
        int quantidadeTotal = 0;
        for (CartItem item : itensCarrinho) {
            quantidadeTotal += item.getQuantity();
        }
        return quantidadeTotal;
    }
    
    public void limparCarrinho() {
        itensCarrinho.clear();
    }
}





