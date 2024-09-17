package com.inventorymanagementmodule.servicetest;

    import static org.junit.jupiter.api.Assertions.*;
	import static org.mockito.Mockito.*;

	import java.util.List;
	import java.util.Optional;

	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.junit.jupiter.api.extension.ExtendWith;
	import org.mockito.InjectMocks;
	import org.mockito.Mock;
	import org.mockito.junit.jupiter.MockitoExtension;

	import com.inventorymanagementmodule.entity.Brand;
	import com.inventorymanagementmodule.entity.Product;
	import com.inventorymanagementmodule.exception.ResourceNotFoundException;
	import com.inventorymanagementmodule.repository.BrandRepository;
	import com.inventorymanagementmodule.repository.ProductRepository;
import com.inventorymanagementmodule.service.ProductService;

	@ExtendWith(MockitoExtension.class)
	public class ProductServiceTest {

	    @Mock
	    private ProductRepository productRepository;

	    @Mock
	    private BrandRepository brandRepository;

	    @InjectMocks
	    private ProductService productService;

	    private Product product;
	    private Brand brand;

	    @BeforeEach
	    public void setUp() {
	        brand = new Brand();
	        brand.setId(1L);
	        brand.setBrandName("Test Brand");
	        brand.setBrandType("Test Type");
	        brand.setIsDeleted(false);

	        product = new Product();
	        product.setId(1L);
	        product.setProductName("Test Product");
	        product.setBrand(brand);
	        product.setBrandId(1L);
	    }

	    @Test
	    public void testGetAllProducts() {
	        when(productRepository.findByBrandIsDeletedFalse()).thenReturn(List.of(product));

	        List<Product> products = productService.getAllProducts();

	        assertNotNull(products);
	        assertEquals(1, products.size());
	        assertEquals("Test Product", products.get(0).getProductName());
	        verify(productRepository, times(1)).findByBrandIsDeletedFalse();
	    }

	    @Test
	    public void testGetProductById() {
	        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

	        Product foundProduct = productService.getProductById(1L);

	        assertNotNull(foundProduct);
	        assertEquals("Test Product", foundProduct.getProductName());
	        verify(productRepository, times(1)).findById(1L);
	    }

	    @Test
	    public void testGetProductByIdNotFound() {
	        when(productRepository.findById(1L)).thenReturn(Optional.empty());

	        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
	            productService.getProductById(1L);
	        });

	        assertEquals("Product not found", exception.getMessage());
	        verify(productRepository, times(1)).findById(1L);
	    }

	    @Test
	    public void testGetProductsByType() {
	        when(productRepository.findByBrand_BrandType("Test Type")).thenReturn(List.of(product));

	        List<Product> products = productService.getProductsByType("Test Type");

	        assertNotNull(products);
	        assertEquals(1, products.size());
	        assertEquals("Test Product", products.get(0).getProductName());
	        verify(productRepository, times(1)).findByBrand_BrandType("Test Type");
	    }

	    @Test
	    public void testCreateOrUpdateProduct() {
	        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
	        when(productRepository.save(product)).thenReturn(product);

	        Product savedProduct = productService.createOrUpdateProduct(product);

	        assertNotNull(savedProduct);
	        assertEquals("Test Product", savedProduct.getProductName());
	        assertEquals(brand, savedProduct.getBrand());
	        verify(brandRepository, times(1)).findById(1L);
	        verify(productRepository, times(1)).save(product);
	    }

	    @Test
	    public void testCreateOrUpdateProductBrandNotFound() {
	        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

	        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
	            productService.createOrUpdateProduct(product);
	        });

	        assertEquals("Brand not found", exception.getMessage());
	        verify(brandRepository, times(1)).findById(1L);
	        verify(productRepository, never()).save(any(Product.class));
	    }

	    @Test
	    public void testDeleteProduct() {
	        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
	        doNothing().when(productRepository).delete(product);

	        productService.deleteProduct(1L);

	        verify(productRepository, times(1)).findById(1L);
	        verify(productRepository, times(1)).delete(product);
	    }

	    @Test
	    public void testDeleteProductNotFound() {
	        when(productRepository.findById(1L)).thenReturn(Optional.empty());

	        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
	            productService.deleteProduct(1L);
	        });

	        assertEquals("Product not found", exception.getMessage());
	        verify(productRepository, times(1)).findById(1L);
	        verify(productRepository, never()).delete(any(Product.class));
	    }
	}


