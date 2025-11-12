/*
 * iFood Delivery App - Main Class
 */
package aula09ex01;

import DAO.OrderDAO;
import View.Login;
import View.FoodMenu;

/**
 *
 * @author unifvipereiraaaaaaaaaaaa
 */
public class Aula09Ex01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            initDatabase();
            // Open Login screen
            Login login = new Login();
            login.setVisible(true);
    }
    
    private static void initDatabase() {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.createTables();
    }
    
}
