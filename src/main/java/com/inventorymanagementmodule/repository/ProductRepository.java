package com.inventorymanagementmodule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventorymanagementmodule.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
	List<Product> findByBrand_BrandType(String brandType);
    List<Product> findByBrandIsDeletedFalse();
}
