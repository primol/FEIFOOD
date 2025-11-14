/*
 * Cart Manager - Singleton to manage cart state
 */
package Controller;

import Model.CartItem;
import Model.Food;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author unifvipereira
 */
public class CartManager {
    private static CartManager instance;
    private List<CartItem> cartItems;
    
    private CartManager() {
        cartItems = new ArrayList<>();
    }
    
    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }
    
    public void addToCart(Food food, int quantity) {
        // Check if food already exists in cart
        for (CartItem item : cartItems) {
            if (item.getFood().getId() == food.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        // If not found, add new item
        cartItems.add(new CartItem(food, quantity));
    }
    
    public void updateQuantity(int foodId, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getFood().getId() == foodId) {
                item.setQuantity(quantity);
                return;
            }
        }
    }
    
    public void removeFromCart(int foodId) {
        cartItems.removeIf(item -> item.getFood().getId() == foodId);
    }
    
    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }
    
    public double getTax() {
        double tax = 0;
        for (CartItem item : cartItems) {
            if (item.getFood().isAlcoholic()) {
                tax += item.getTotalPrice() * 0.05;
            }
        }
        return tax;
    }
    
    public double getTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotalPrice();
        }
        total += getTax();
        return total;
    }
    
    public int getItemCount() {
        int count = 0;
        for (CartItem item : cartItems) {
            count += item.getQuantity();
        }
        return count;
    }
    
    public void clearCart() {
        cartItems.clear();
    }
}

