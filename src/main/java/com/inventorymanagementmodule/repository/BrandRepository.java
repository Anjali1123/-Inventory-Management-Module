package com.inventorymanagementmodule.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inventorymanagementmodule.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    
	List<Brand> findByBrandType(String brandType);
	List<Brand> findByIsDeletedFalse();
}