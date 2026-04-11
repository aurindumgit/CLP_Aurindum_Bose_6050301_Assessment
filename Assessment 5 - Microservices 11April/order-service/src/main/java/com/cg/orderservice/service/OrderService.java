package com.cg.orderservice.service;

import com.cg.orderservice.dto.*;
import com.cg.orderservice.exception.ServiceUnavailableException;
import com.cg.orderservice.model.Order;
import com.cg.orderservice.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    OrderRepository repo;

    @Autowired
    RestTemplate restTemplate;

    
    @CircuitBreaker(name = "moviedetailservice", fallbackMethod = "orderFallback")
    public OrderResponse createOrder(OrderRequest request) {

        User user = null;
        try {
            user = restTemplate.getForObject(
                    "http://USER-SERVICE/users/" + request.getUserId(),
                    User.class
            );
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException(
                    "User not found with id: " + request.getUserId());
        }

        Product product = null;
        try {
            product = restTemplate.getForObject(
                    "http://PRODUCT-SERVICE/products/" + request.getProductId(),
                    Product.class
            );
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException(
                    "Product not found with id: " + request.getProductId());
        }

        double totalPrice = product.getPrice() * request.getQuantity();

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setTotalPrice(totalPrice);
        Order saved = repo.save(order);

        OrderResponse response = new OrderResponse();
        response.setOrderId(saved.getOrderId());
        response.setUserName(user.getName());
        response.setProductName(product.getName());
        response.setQuantity(request.getQuantity());
        response.setTotalPrice(totalPrice);

        return response;
    }
    

    public OrderResponse orderFallback(OrderRequest request, Throwable t) {
    	if (t instanceof IllegalArgumentException) {
            throw (IllegalArgumentException) t;
        }
        throw new ServiceUnavailableException("Order Service");
    }
}