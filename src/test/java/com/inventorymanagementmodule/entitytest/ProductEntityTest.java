package com.inventorymanagementmodule.entitytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.inventorymanagementmodule.entity.Brand;
import com.inventorymanagementmodule.entity.Product;

public class ProductEntityTest {
	
        private Product product;
	    private Brand brand;

	    @BeforeEach
	    public void setUp() {
	        product = new Product();
	        brand = new Brand();
	        brand.setId(1L);
	        brand.setBrandName("Nike");
	        brand.setBrandType("Sportswear");
	    }

	    @Test
	    public void testSetAndGetId() {
	        Long id = 100L;
	        product.setId(id);
	        assertEquals(id, product.getId());
	    }

	    @Test
	    public void testSetAndGetProductName() {
	        String productName = "Air Max";
	        product.setProductName(productName);
	        assertEquals(productName, product.getProductName());
	    }

	    @Test
	    public void testSetAndGetProductPrice() {
	        Double productPrice = 199.99;
	        product.setProductPrice(productPrice);
	        assertEquals(productPrice, product.getProductPrice());
	    }

	    @Test
	    public void testSetAndGetBrand() {
	        product.setBrand(brand);
	        assertEquals(brand, product.getBrand());
	        assertEquals(1L, product.getBrand().getId());
	        assertEquals("Nike", product.getBrand().getBrandName());
	    }

	    @Test
	    public void testSetAndGetBrandId() {
	        product.setBrandId(1L);
	        assertEquals(1L, product.getBrandId());
	    }

	    @Test
	    public void testGetBrandIdWhenBrandIsNull() {
	        product.setBrand(null); // setting brand to null
	        assertNull(product.getBrandId());
	    }

	    @Test
	    public void testSetBrandIdCreatesBrandObjectIfNull() {
	        product.setBrand(null); // making sure brand is null
	        product.setBrandId(2L); // set brand ID should create brand object internally
	        assertNotNull(product.getBrand());
	        assertEquals(2L, product.getBrandId());
	    }
	}


