package com.inventorymanagementmodule.repositorytest;

	import static org.assertj.core.api.Assertions.assertThat;
	import static org.junit.jupiter.api.Assertions.assertThrows;

	import java.util.List;
	import java.util.Optional;

	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.junit.jupiter.api.extension.ExtendWith;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
	import org.springframework.test.context.junit.jupiter.SpringExtension;

	import com.inventorymanagementmodule.entity.Brand;
	import com.inventorymanagementmodule.exception.ResourceNotFoundException;
import com.inventorymanagementmodule.repository.BrandRepository;

	@ExtendWith(SpringExtension.class)
	@DataJpaTest
	public class BrandRepositoryTest {

	    @Autowired
	    private BrandRepository brandRepository;

	    private Brand brand;

	    @BeforeEach
	    public void setUp() {
	        brand = new Brand();
	        brand.setBrandName("Test Brand");
	        brand.setBrandType("Test Type");
	        brand.setIsDeleted(false);
	        brandRepository.save(brand);
	    }

	    @Test
	    public void testFindByBrandType() {
	        List<Brand> brands = brandRepository.findByBrandType("Test Type");
	        
	        assertThat(brands).isNotEmpty();
	        assertThat(brands.get(0).getBrandName()).isEqualTo("Test Brand");
	    }

	    @Test
	    public void testFindByIsDeletedFalse() {
	        List<Brand> brands = brandRepository.findByIsDeletedFalse();
	        
	        assertThat(brands).isNotEmpty();
	        assertThat(brands.get(0).getBrandName()).isEqualTo("Test Brand");
	    }

	    @Test
	    public void testFindById() {
	        Optional<Brand> foundBrand = brandRepository.findById(brand.getId());
	        
	        assertThat(foundBrand).isPresent();
	        assertThat(foundBrand.get().getBrandName()).isEqualTo("Test Brand");
	    }

	    @Test
	    public void testSaveBrand() {
	        Brand newBrand = new Brand();
	        newBrand.setBrandName("New Brand");
	        newBrand.setBrandType("New Type");
	        newBrand.setIsDeleted(false);
	        
	        Brand savedBrand = brandRepository.save(newBrand);
	        
	        assertThat(savedBrand).isNotNull();
	        assertThat(savedBrand.getId()).isNotNull();
	        assertThat(savedBrand.getBrandName()).isEqualTo("New Brand");
	    }

	    @Test
	    public void testDeleteBrand() {
	        brandRepository.delete(brand);
	        
	        Optional<Brand> deletedBrand = brandRepository.findById(brand.getId());
	        
	        assertThat(deletedBrand).isNotPresent();
	    }
	}

    
    

