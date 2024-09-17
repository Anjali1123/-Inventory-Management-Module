package com.inventorymanagementmodule.repositorytest;

import com.inventorymanagementmodule.entity.Brand;
import com.inventorymanagementmodule.entity.Product;
import com.inventorymanagementmodule.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Brand brand1;
    private Brand brand2;

    @BeforeEach
    void setUp() {
        // Create and persist brands
        brand1 = new Brand();
        brand1.setBrandName("Brand1");
        brand1.setBrandType("TypeA");
        brand1.setIsDeleted(false);
        testEntityManager.persist(brand1);

        brand2 = new Brand();
        brand2.setBrandName("Brand2");
        brand2.setBrandType("TypeB");
        brand2.setIsDeleted(true);
        testEntityManager.persist(brand2);

        // Create and persist products
        Product product1 = new Product();
        product1.setBrand(brand1);
        // set other properties
        testEntityManager.persist(product1);

        Product product2 = new Product();
        product2.setBrand(brand2);
        // set other properties
        testEntityManager.persist(product2);
    }

    @Test
    void testFindByBrandBrandType() {
        List<Product> products = productRepository.findByBrand_BrandType("TypeA");
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getBrand().getBrandType()).isEqualTo("TypeA");
    }

    @Test
    void testFindByBrandIsDeletedFalse() {
        List<Product> products = productRepository.findByBrandIsDeletedFalse();
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getBrand().getBrandType()).isEqualTo("TypeA");
    }
}
