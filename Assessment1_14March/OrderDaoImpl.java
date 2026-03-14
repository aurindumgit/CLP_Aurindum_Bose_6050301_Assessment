package cg.demo.saturdayAssessment1;

import jakarta.persistence.*;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    private EntityManager em = emf.createEntityManager();

    @Override
    public boolean addOrder(Order order, int custId) {

        Customer customer = em.find(Customer.class, custId);
        if (customer == null) {
            return false;
        }
        order.setCustomer(customer);
        customer.getOrders().add(order);

        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();

        return true;
    }   
    
    @Override
    public Order getOrder(int orderId) {
        return em.createQuery(
                "SELECT o FROM Order o WHERE o.orderId = :id", Order.class)
                .setParameter("id", orderId).getResultStream().findFirst().orElse(null);
    }

    @Override
    public List<Order> getOrders(String custName) {
        return em.createQuery(
                "SELECT o FROM Order o WHERE o.customer.customerName = :name", Order.class)
                .setParameter("name", custName).getResultList();
    }

    public void close() {
        em.close();
        emf.close();
    }
}