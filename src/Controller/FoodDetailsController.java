/*
 * Food Details Controller
 */
package Controller;

import DAO.FoodDAO;
import Model.Food;
import View.FoodDetails;
import javax.swing.JOptionPane;

/**
 *
 * @author unifvipereira
 */
public class FoodDetailsController {
    private FoodDetails view;
    private FoodDAO foodDAO;
    
    public FoodDetailsController(FoodDetails view) {
        this.view = view;
        this.foodDAO = new FoodDAO();
    }
    
    public void loadFoodDetails(Food food) {
        view.getLblFoodName().setText(food.getName());
        view.getLblCategory().setText("Categoria: " + food.getCategory());
        view.getTxtDescription().setText(food.getDescription());
        view.getLblPrice().setText("Preço: R$ " + String.format("%.2f", food.getPrice()));
        
        String ratingText = String.format("Avaliação: %.1f ⭐ (%d avaliações)", 
                food.getRating(), food.getRatingCount());
        view.getLblRating().setText(ratingText);
    }
    
    public void rateFood(int stars) {
        Food food = view.getCurrentFood();
        if (food == null) {
            return;
        }
        
        foodDAO.rateFood(food.getId(), stars);
        
        // Reload food to get updated rating
        Food updatedFood = foodDAO.getFoodById(food.getId());
        if (updatedFood != null) {
            loadFoodDetails(updatedFood);
            JOptionPane.showMessageDialog(view, 
                    "Obrigado! Você avaliou este alimento com " + stars + " estrela(s)!", 
                    "Avaliação Registrada", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

