package com.inventorymanagementmodule.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inventorymanagementmodule.entity.Brand;

//BrandRepository is a Data Access Layer interface that extends JpaRepository 
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    
	//Finds a list of Brand entities based on their brand type.
	List<Brand> findByBrandType(String brandType);
    
	//Retrieves all Brand entities that are not marked as deleted.
	List<Brand> findByIsDeletedFalse();
}