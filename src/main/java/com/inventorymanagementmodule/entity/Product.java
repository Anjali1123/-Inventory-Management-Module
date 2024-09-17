package com.inventorymanagementmodule.entity;

import jakarta.persistence.*;

//This class maps the Product entity to the corresponding database table.
@Entity
@Table(name = "products")
public class Product {
	
	// Primary key for the Product entity with auto-generated ID.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-One relationship between Product and Brand, where brand_id is a foreign key.
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    // Column representing the name of the product.
    @Column(name = "product_name")
    private String productName;

    // Column representing the price of the product.
    @Column(name = "product_price")
    private Double productPrice;

    // Getters and Setters
    
    //Get the ID of the product.
    public Long getId() {
        return id;
    }

    //Set the ID of the product.
    public void setId(Long id) {
        this.id = id;
    }

    // Get the Brand associated with the product.
    public Brand getBrand() {
        return brand;
    }

    //Set the Brand for the product.
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    //Get the name of the product.
    public String getProductName() {
        return productName;
    }

    //Set the name of the product.
    public void setProductName(String productName) {
        this.productName = productName;
    }

    //Get the price of the product.
    public Double getProductPrice() {
        return productPrice;
    }

    //Set the price of the product.
    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    //Set the Brand ID by creating a new Brand instance if needed.
    // This is useful when the ID is available but the full Brand object is not.
    public void setBrandId(Long brandId) {
        if (this.brand == null) {
            this.brand = new Brand();
        }
        this.brand.setId(brandId);
    }

    //Get the Brand ID associated with the product.
    public Long getBrandId() {
        return this.brand != null ? this.brand.getId() : null;
    }
}