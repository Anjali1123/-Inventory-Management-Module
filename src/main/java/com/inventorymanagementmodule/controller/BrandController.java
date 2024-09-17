package com.inventorymanagementmodule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inventorymanagementmodule.entity.Brand;
import com.inventorymanagementmodule.service.BrandService;

@RestController
@RequestMapping("/api/brand")// Base URL for all brand-related operations
public class BrandController {
    @Autowired
    private BrandService brandService;

    //Get all brands that are not marked as deleted.
    @GetMapping("/getall")
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    //Get a specific brand by its ID
    @GetMapping("/get/{id}")
    public Brand getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id);
    }

    //Get all brands of type "medical"
    @GetMapping("/medical")
    public List<Brand> getMedicalBrands() {
        return brandService.getBrandById("medical");
    }

    //Create a new brand
    @PostMapping("/create")
    public Brand createBrand(@RequestBody Brand brand) {
        return brandService.createOrUpdateBrand(brand);
    }

    //Update an existing brand.
    @PutMapping("/update")
    public Brand updateBrand(@RequestBody Brand brand) {
        return brandService.createOrUpdateBrand(brand);
    }
    
    //Delete a brand by marking it as deleted instead of physically removing it
    @DeleteMapping("/delete/{id}")
    public void deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
    }
}