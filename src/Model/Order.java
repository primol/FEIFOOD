package Model;

import java.sql.Timestamp;
import java.util.List;

public class Order {
    private int id;
    private int alunoId;
    private Timestamp orderDate;
    private double total;
    private List<OrderItem> items;

    public Order() {
    }

    public Order(int alunoId, Timestamp orderDate, double total, List<OrderItem> items) {
        this.alunoId = alunoId;
        this.orderDate = orderDate;
        this.total = total;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
