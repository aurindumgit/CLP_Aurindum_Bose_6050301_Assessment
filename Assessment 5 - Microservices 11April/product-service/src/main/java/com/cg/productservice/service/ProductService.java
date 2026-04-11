package com.cg.productservice.service;

import com.cg.productservice.exception.ProductNotFoundException;
import com.cg.productservice.model.Product;
import com.cg.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repo;

    public Product createProduct(Product product) {
        return repo.save(product);
    }

    public Product getProductById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }
}