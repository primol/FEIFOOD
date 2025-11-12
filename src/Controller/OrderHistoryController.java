package Controller;

import DAO.OrderDAO;
import Model.Order;
import View.OrderHistory;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class OrderHistoryController {

    private final OrderHistory view;
    private final OrderDAO orderDAO;
    private final int alunoId;

    public OrderHistoryController(OrderHistory view, int alunoId) {
        this.view = view;
        this.orderDAO = new OrderDAO();
        this.alunoId = alunoId;
        loadOrders();
    }

    private void loadOrders() {
        try {
            List<Order> orders = orderDAO.getOrdersByAlunoId(alunoId);
            DefaultTableModel tableModel = view.getTableModel();
            tableModel.setRowCount(0); // Clear existing data
            for (Order order : orders) {
                tableModel.addRow(new Object[]{
                    order.getId(),
                    order.getOrderDate(),
                    String.format("R$ %.2f", order.getTotal())
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Show error message to the user
        }
    }
}
