package cg.demo.saturdayAssessment1;

import java.util.List;

public interface OrderDao {

    public boolean addOrder(Order order, int custId);
    public Order getOrder(int orderId);
    public List<Order> getOrders(String custName);
}