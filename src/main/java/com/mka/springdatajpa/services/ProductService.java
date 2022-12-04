package com.mka.springdatajpa.services;

import com.mka.springdatajpa.models.Product;
import com.mka.springdatajpa.models.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        Iterable<Product> source = productRepository.findAll();
        List<Product> products =  new ArrayList<>();
        source.forEach(products::add);
        return products;
    }

    public void addNewProductToRepo(Product product) {
        productRepository.save(product);
    }

    public void deleteProductFromRepoById(Long id) {
        productRepository.deleteById(id);
    }
}
