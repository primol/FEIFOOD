/*
 * CartItem Model para o aplicativo de entregas
 */
package Model;

/**
 *
 * Representa um item presente no carrinho de compras.
 */
public class CartItem {
    private Food alimento;
    private int quantidade;
    
    public CartItem() {
    }
    
    public CartItem(Food alimento, int quantidade) {
        this.alimento = alimento;
        this.quantidade = quantidade;
    }

    public Food getFood() {
        return alimento;
    }

    public void setFood(Food alimento) {
        this.alimento = alimento;
    }

    public int getQuantity() {
        return quantidade;
    }

    public void setQuantity(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public double getTotalPrice() {
        return alimento.getPrice() * quantidade;
    }
    
    @Override
    public String toString() {
        return alimento.getName() + " x" + quantidade + " - R$ " + String.format("%.2f", getTotalPrice());
    }
}
