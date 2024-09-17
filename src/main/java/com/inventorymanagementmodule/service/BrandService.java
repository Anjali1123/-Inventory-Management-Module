package com.inventorymanagementmodule.service;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inventorymanagementmodule.entity.Brand;
import com.inventorymanagementmodule.exception.ResourceNotFoundException;
import com.inventorymanagementmodule.repository.BrandRepository;

@Service
public class BrandService {
	
    @Autowired
    private BrandRepository brandRepository;

    // Logger for logging important information.
    private static final Logger logger = LogManager.getLogger(BrandService.class);
    public List<Brand> getAllBrands() {
    	logger.info("Fetching all brands");
        return brandRepository.findByIsDeletedFalse();
    }
    //Fetches all the brands that are not marked as deleted.
    public Brand getBrandById(Long id) {
        return brandRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
    }
    
   public List<Brand> getBrandById(String string) {
		
		return brandRepository.findByBrandType(string);
	}
    
    public Brand createOrUpdateBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    //Soft deletes a Brand entity by setting the isDeleted flag to true.
    public void deleteBrand(Long id) {
       
    	//Fetch the brand to ensure it exists.
    	Brand brand = getBrandById(id);
        
        //Mark the brand as deleted.
        brand.setIsDeleted(true);
        
        //Update the brand in the database
        brandRepository.save(brand);
    }

	

	
}