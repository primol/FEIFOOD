/*
 * 
 */
package aula09ex01;

import DAO.OrderDAO;
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
            initDatabase();
            // abrir a tela inicial
            Login login = new Login();
            login.setVisible(true);
    }
    
    private static void initDatabase() {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.createTables();
    }
    
}
