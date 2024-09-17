package com.inventorymanagementmodule.controllertest;

	import com.inventorymanagementmodule.controller.BrandController;
	import com.inventorymanagementmodule.entity.Brand;
	import com.inventorymanagementmodule.service.BrandService;
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

	import static org.mockito.Mockito.*;
	import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
	import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
	import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
	
	public class BrandControllerTest {
	

	    private MockMvc mockMvc;

	    @Mock
	    private BrandService brandService;

	    @InjectMocks
	    private BrandController brandController;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(brandController).build();
	    }

	    @Test
	    public void testGetAllBrands() throws Exception {
	        // Given
	        Brand brand1 = new Brand();
	        brand1.setId(1L);
	        brand1.setBrandName("Nike");

	        Brand brand2 = new Brand();
	        brand2.setId(2L);
	        brand2.setBrandName("Adidas");

	        List<Brand> brands = Arrays.asList(brand1, brand2);

	        // When
	        when(brandService.getAllBrands()).thenReturn(brands);

	        // Then
	        mockMvc.perform(get("/api/brand/getall"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$[0].brandName").value("Nike"))
	                .andExpect(jsonPath("$[1].brandName").value("Adidas"));

	        verify(brandService, times(1)).getAllBrands();
	    }

	    @Test
	    public void testGetBrandById() throws Exception {
	        // Given
	        Brand brand = new Brand();
	        brand.setId(1L);
	        brand.setBrandName("Nike");

	        // When
	        when(brandService.getBrandById(1L)).thenReturn(brand);

	        // Then
	        mockMvc.perform(get("/api/brand/get/1"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.brandName").value("Nike"));

	        verify(brandService, times(1)).getBrandById(1L);
	    }

	    @Test
	    public void testGetMedicalBrands() throws Exception {
	        // Given
	        Brand brand = new Brand();
	        brand.setId(3L);
	        brand.setBrandName("MedicalBrand");

	        List<Brand> brands = Arrays.asList(brand);

	        // When
	        when(brandService.getBrandById("medical")).thenReturn(brands);

	        // Then
	        mockMvc.perform(get("/api/brand/medical"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$[0].brandName").value("MedicalBrand"));

	        verify(brandService, times(1)).getBrandById("medical");
	    }

	    @Test
	    public void testCreateBrand() throws Exception {
	        // Given
	        Brand brand = new Brand();
	        brand.setId(1L);
	        brand.setBrandName("Nike");

	        // When
	        when(brandService.createOrUpdateBrand(any(Brand.class))).thenReturn(brand);

	        // Then
	        mockMvc.perform(post("/api/brand/create")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{\"brandName\": \"Nike\", \"brandType\": \"Sportswear\"}"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.brandName").value("Nike"));

	        verify(brandService, times(1)).createOrUpdateBrand(any(Brand.class));
	    }

	    @Test
	    public void testUpdateBrand() throws Exception {
	        // Given
	        Brand brand = new Brand();
	        brand.setId(1L);
	        brand.setBrandName("Adidas");

	        // When
	        when(brandService.createOrUpdateBrand(any(Brand.class))).thenReturn(brand);

	        // Then
	        mockMvc.perform(put("/api/brand/update")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{\"id\": 1, \"brandName\": \"Adidas\", \"brandType\": \"Sportswear\"}"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.brandName").value("Adidas"));

	        verify(brandService, times(1)).createOrUpdateBrand(any(Brand.class));
	    }

	    @Test
	    public void testDeleteBrand() throws Exception {
	        // When & Then
	        doNothing().when(brandService).deleteBrand(1L);

	        mockMvc.perform(delete("/api/brand/delete/1"))
	                .andExpect(status().isOk());

	        verify(brandService, times(1)).deleteBrand(1L);
	    }
	}


