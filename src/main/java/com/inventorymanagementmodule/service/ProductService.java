package com.inventorymanagementmodule.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inventorymanagementmodule.entity.Brand;
import com.inventorymanagementmodule.entity.Product;
import com.inventorymanagementmodule.exception.ResourceNotFoundException;
import com.inventorymanagementmodule.repository.BrandRepository;
import com.inventorymanagementmodule.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
    private ProductRepository productRepository;
	
    @Autowired
    private BrandRepository brandRepository;
    
    // Retrieves all products that are linked to brands that are not deleted.
    public List<Product> getAllProducts() {
        return productRepository.findByBrandIsDeletedFalse();
    }

    //Retrieves a product by its ID. Throws an exception if the product is not found.
    public Product getProductById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
   
    public List<Product> getProductsByType(String brandType) {
        return productRepository.findByBrand_BrandType(brandType);
    }
    
    //Creates or updates a product entity. The brand of the product is verified before saving.
    public Product createOrUpdateProduct(Product product) {
        Brand brand = brandRepository.findById(product.getBrandId())
            .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
        product.setBrand(brand);
        return productRepository.save(product);
    }

    //Deletes a product by its ID. The product is permanently removed from the database.
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
