package cg.demo.saturdayAssessment1;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "abes_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_amt")
    private double orderAmt;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Order() {}    
    
    public Order(LocalDate orderDate, double orderAmt) {
        this.orderDate = orderDate;
        this.orderAmt  = orderAmt;
    }

    
    public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public double getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(double orderAmt) {
		this.orderAmt = orderAmt;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
    public String toString() {
        return "Order [ID=" + orderId + ", Date=" + orderDate + ", Amount=" + orderAmt + ", Customer=" + customer.getCustomerName() + "]";
    }
}