package com.inventorymanagementmodule.entitytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.inventorymanagementmodule.entity.Brand;

public class BrandEntityTest {

    private Brand brand;

    @BeforeEach
    public void setUp() {
        brand = new Brand();  // Initialize brand object before each test
    }

    @Test
    public void testSetAndGetId() {
        Long id = 1L;
        brand.setId(id);
        assertEquals(id, brand.getId());
    }

    @Test
    public void testSetAndGetBrandName() {
        String brandName = "Nike";
        brand.setBrandName(brandName);
        assertEquals(brandName, brand.getBrandName());
    }

    @Test
    public void testSetAndGetBrandType() {
        String brandType = "Sportswear";
        brand.setBrandType(brandType);
        assertEquals(brandType, brand.getBrandType());
    }

    @Test
    public void testIsDeletedDefault() {
        // By default, isDeleted should be false
        assertFalse(brand.getIsDeleted());
    }

    @Test
    public void testSetAndGetIsDeleted() {
        brand.setIsDeleted(true);
        assertTrue(brand.getIsDeleted());
        
        brand.setIsDeleted(false);
        assertFalse(brand.getIsDeleted());
    }
}


