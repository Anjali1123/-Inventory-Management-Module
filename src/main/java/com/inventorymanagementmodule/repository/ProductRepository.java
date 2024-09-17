package com.inventorymanagementmodule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventorymanagementmodule.entity.Product;

//ProductRepository is a Data Access Layer interface that extends JpaRepository 
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
	//Finds a list of Product entities based on the brand's type.
	List<Product> findByBrand_BrandType(String brandType);
	
	// Retrieves all Product entities that are associated with a brand.
    List<Product> findByBrandIsDeletedFalse();
}
