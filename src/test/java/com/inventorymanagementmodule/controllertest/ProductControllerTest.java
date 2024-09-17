package com.inventorymanagementmodule.controllertest;


	import com.inventorymanagementmodule.controller.ProductController;
	import com.inventorymanagementmodule.entity.Product;
	import com.inventorymanagementmodule.service.ProductService;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.mockito.InjectMocks;
	import org.mockito.Mock;
	import org.mockito.MockitoAnnotations;
	import org.springframework.http.MediaType;
	import org.springframework.test.web.servlet.MockMvc;
	import org.springframework.test.web.servlet.setup.MockMvcBuilders;

	import java.util.Arrays;
	import java.util.List;

	import static org.mockito.ArgumentMatchers.any;
	import static org.mockito.Mockito.*;
	import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
	import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
	import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

	public class ProductControllerTest {

	    private MockMvc mockMvc;

	    @Mock
	    private ProductService productService;

	    @InjectMocks
	    private ProductController productController;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	    }

	    @Test
	    public void testGetAllProducts() throws Exception {
	        // Given
	        Product product1 = new Product();
	        product1.setId(1L);
	        product1.setProductName("Product 1");
	        product1.setProductPrice(100.0);

	        Product product2 = new Product();
	        product2.setId(2L);
	        product2.setProductName("Product 2");
	        product2.setProductPrice(150.0);

	        List<Product> products = Arrays.asList(product1, product2);

	        // When
	        when(productService.getAllProducts()).thenReturn(products);

	        // Then
	        mockMvc.perform(get("/api/product/getall"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$[0].productName").value("Product 1"))
	                .andExpect(jsonPath("$[1].productName").value("Product 2"))
	                .andExpect(jsonPath("$[0].productPrice").value(100.0))
	                .andExpect(jsonPath("$[1].productPrice").value(150.0))
	                .andDo(print());

	        verify(productService, times(1)).getAllProducts();
	    }

	    @Test
	    public void testGetProductById() throws Exception {
	        // Given
	        Product product = new Product();
	        product.setId(1L);
	        product.setProductName("Product 1");
	        product.setProductPrice(100.0);

	        // When
	        when(productService.getProductById(1L)).thenReturn(product);

	        // Then
	        mockMvc.perform(get("/api/product/get/1"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.productName").value("Product 1"))
	                .andExpect(jsonPath("$.productPrice").value(100.0))
	                .andDo(print());

	        verify(productService, times(1)).getProductById(1L);
	    }

	    @Test
	    public void testGetMedicalProducts() throws Exception {
	        // Given
	        Product product = new Product();
	        product.setId(3L);
	        product.setProductName("Medical Product");
	        product.setProductPrice(200.0);

	        List<Product> products = Arrays.asList(product);

	        // When
	        when(productService.getProductsByType("medical")).thenReturn(products);

	        // Then
	        mockMvc.perform(get("/api/product/medical"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$[0].productName").value("Medical Product"))
	                .andExpect(jsonPath("$[0].productPrice").value(200.0))
	                .andDo(print());

	        verify(productService, times(1)).getProductsByType("medical");
	    }

	    @Test
	    public void testCreateProduct() throws Exception {
	        // Given
	        Product product = new Product();
	        product.setId(1L);
	        product.setProductName("New Product");
	        product.setProductPrice(99.99);

	        // When
	        when(productService.createOrUpdateProduct(any(Product.class))).thenReturn(product);

	        // Then
	        mockMvc.perform(post("/api/product/create")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{\"productName\": \"New Product\", \"productPrice\": 99.99}")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.productName").value("New Product"))
	                .andExpect(jsonPath("$.productPrice").value(99.99))
	                .andDo(print());

	        verify(productService, times(1)).createOrUpdateProduct(any(Product.class));
	    }

	    @Test
	    public void testUpdateProduct() throws Exception {
	        // Given
	        Product product = new Product();
	        product.setId(1L);
	        product.setProductName("Updated Product");
	        product.setProductPrice(199.99);

	        // When
	        when(productService.createOrUpdateProduct(any(Product.class))).thenReturn(product);

	        // Then
	        mockMvc.perform(put("/api/product/update/1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{\"productName\": \"Updated Product\", \"productPrice\": 199.99}")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.productName").value("Updated Product"))
	                .andExpect(jsonPath("$.productPrice").value(199.99))
	                .andDo(print());

	        verify(productService, times(1)).createOrUpdateProduct(any(Product.class));
	    }

	    @Test
	    public void testDeleteProduct() throws Exception {
	        // When & Then
	        doNothing().when(productService).deleteProduct(1L);

	        mockMvc.perform(delete("/api/product/delete/1"))
	                .andExpect(status().isNoContent())
	                .andDo(print());

	        verify(productService, times(1)).deleteProduct(1L);
	    }
	}


