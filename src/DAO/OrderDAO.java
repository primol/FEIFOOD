package DAO;

import Model.Order;
import Model.OrderItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private Connection connection;

    public OrderDAO() {
        try {
            this.connection = new Conexao().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTables() {
        String createOrdersTable = "CREATE TABLE IF NOT EXISTS orders ("
                + "id SERIAL PRIMARY KEY,"
                + "aluno_id INTEGER NOT NULL,"
                + "order_date TIMESTAMP NOT NULL,"
                + "total NUMERIC(10, 2) NOT NULL"
                + ");";

        String createOrderItemsTable = "CREATE TABLE IF NOT EXISTS order_items ("
                + "id SERIAL PRIMARY KEY,"
                + "order_id INTEGER NOT NULL REFERENCES orders(id),"
                + "food_id INTEGER NOT NULL,"
                + "quantity INTEGER NOT NULL,"
                + "price NUMERIC(10, 2) NOT NULL"
                + ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createOrdersTable);
            stmt.execute(createOrderItemsTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (aluno_id, order_date, total) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getAlunoId());
            stmt.setTimestamp(2, order.getOrderDate());
            stmt.setDouble(3, order.getTotal());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    order.setId(orderId);
                    for (OrderItem item : order.getItems()) {
                        item.setOrderId(orderId);
                        saveOrderItem(item);
                    }
                }
            }
        }
    }

    private void saveOrderItem(OrderItem item) throws SQLException {
        String sql = "INSERT INTO order_items (order_id, food_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, item.getOrderId());
            stmt.setInt(2, item.getFoodId());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getPrice());
            stmt.executeUpdate();
        }
    }

    public List<Order> getOrdersByAlunoId(int alunoId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE aluno_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, alunoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setAlunoId(rs.getInt("aluno_id"));
                    order.setOrderDate(rs.getTimestamp("order_date"));
                    order.setTotal(rs.getDouble("total"));
                    order.setItems(getOrderItemsByOrderId(order.getId()));
                    orders.add(order);
                }
            }
        }
        return orders;
    }

    public List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderItem item = new OrderItem();
                    item.setId(rs.getInt("id"));
                    item.setOrderId(rs.getInt("order_id"));
                    item.setFoodId(rs.getInt("food_id"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setPrice(rs.getDouble("price"));
                    items.add(item);
                }
            }
        }
        return items;
    }
}
