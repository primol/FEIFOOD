/*
 * iFood Delivery App - Main Class
 */
package aula09ex01;

import View.Login;
import View.FoodMenu;

/**
 *
 * @author unifvipereira
 */
public class Aula09Ex01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            // Skip login - open FoodMenu directly
            FoodMenu foodMenu = new FoodMenu();
            foodMenu.setVisible(true);
    }
    
}
