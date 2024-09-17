package com.inventorymanagementmodule.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inventorymanagementmodule.entity.Product;
import com.inventorymanagementmodule.service.ProductService;

@RestController
@RequestMapping("/api/product")// Base URL for all product-related operations
public class ProductController {
    @Autowired
    private ProductService productService;

    //Get all product that are not marked as deleted.
    @GetMapping("/getall")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
 
    //Get a specific product by its ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    //Get all product of type "medical"
    @GetMapping("/medical")
    public ResponseEntity<List<Product>> getMedicalProducts() {
        return ResponseEntity.ok(productService.getProductsByType("medical"));
    }

    //Create a new product.
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createOrUpdateProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    //Update an existing product.
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        Product updatedProduct = productService.createOrUpdateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    //Delete a product by marking it as deleted instead of physically removing it
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}