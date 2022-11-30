package com.mka.springdatajpa.controllers;

import com.mka.springdatajpa.models.Product;
import com.mka.springdatajpa.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/app/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "products";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/add_product_to_repo")
    public String postModelNewProduct(Model model) {
        model.addAttribute("product", new Product());
        return "addProductToRepo";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") Product product) {
        productService.addNewProductToRepo(product);
        return "redirect:/app/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductFromRepoById(@PathVariable Long id) {
        productService.deleteProductFromRepoById(id);
        return "redirect:/app/products";
    }
}
