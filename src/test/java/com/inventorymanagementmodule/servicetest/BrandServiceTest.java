package com.inventorymanagementmodule.servicetest;
    
    import static org.mockito.Mockito.*;
	import static org.junit.jupiter.api.Assertions.*;

	import java.util.List;
	import java.util.Optional;

	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.junit.jupiter.api.extension.ExtendWith;
	import org.mockito.InjectMocks;
	import org.mockito.Mock;
	import org.mockito.junit.jupiter.MockitoExtension;

	import com.inventorymanagementmodule.entity.Brand;
	import com.inventorymanagementmodule.exception.ResourceNotFoundException;
	import com.inventorymanagementmodule.repository.BrandRepository;
import com.inventorymanagementmodule.service.BrandService;

	@ExtendWith(MockitoExtension.class)
	public class BrandServiceTest {

	    @Mock
	    private BrandRepository brandRepository;

	    @InjectMocks
	    private BrandService brandService;

	    private Brand brand;

	    @BeforeEach
	    public void setUp() {
	        brand = new Brand();
	        brand.setId(1L);
	        brand.setBrandName("Test Brand");
	        brand.setBrandType("Test Type");
	        brand.setIsDeleted(false);
	    }

	    @Test
	    public void testGetAllBrands() {
	        when(brandRepository.findByIsDeletedFalse()).thenReturn(List.of(brand));

	        List<Brand> brands = brandService.getAllBrands();

	        assertNotNull(brands);
	        assertEquals(1, brands.size());
	        assertEquals("Test Brand", brands.get(0).getBrandName());
	        verify(brandRepository, times(1)).findByIsDeletedFalse();
	    }

	    @Test
	    public void testGetBrandById() {
	        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));

	        Brand foundBrand = brandService.getBrandById(1L);

	        assertNotNull(foundBrand);
	        assertEquals("Test Brand", foundBrand.getBrandName());
	        verify(brandRepository, times(1)).findById(1L);
	    }

	    @Test
	    public void testGetBrandByIdNotFound() {
	        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

	        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
	            brandService.getBrandById(1L);
	        });

	        assertEquals("Brand not found", exception.getMessage());
	        verify(brandRepository, times(1)).findById(1L);
	    }

	    @Test
	    public void testCreateOrUpdateBrand() {
	        when(brandRepository.save(brand)).thenReturn(brand);

	        Brand savedBrand = brandService.createOrUpdateBrand(brand);

	        assertNotNull(savedBrand);
	        assertEquals("Test Brand", savedBrand.getBrandName());
	        verify(brandRepository, times(1)).save(brand);
	    }

	    @Test
	    public void testDeleteBrand() {
	        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
	        when(brandRepository.save(brand)).thenReturn(brand);

	        brandService.deleteBrand(1L);

	        assertTrue(brand.getIsDeleted());
	        verify(brandRepository, times(1)).findById(1L);
	        verify(brandRepository, times(1)).save(brand);
	    }

	    @Test
	    public void testGetBrandByType() {
	        when(brandRepository.findByBrandType("Test Type")).thenReturn(List.of(brand));

	        List<Brand> brands = brandService.getBrandById("Test Type");

	        assertNotNull(brands);
	        assertEquals(1, brands.size());
	        assertEquals("Test Type", brands.get(0).getBrandType());
	        verify(brandRepository, times(1)).findByBrandType("Test Type");
	    }
	}


