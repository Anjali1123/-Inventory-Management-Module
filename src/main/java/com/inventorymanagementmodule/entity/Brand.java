package com.inventorymanagementmodule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
    
    //This class maps the Brand entity to the corresponding database table.
    @Entity
	@Table(name = "brands")
	public class Brand{
	    
    	//Primary key for the Brand entity with auto-generated ID.
    	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

    	//Column representing the name of the brand.
	    @Column(name = "brand_name")
	    private String brandName;

	    //Column representing the type of the brand.
	    @Column(name = "brand_type")
	    private String brandType;

	    //Boolean flag to indicate if the brand is deleted.
	    @Column(name = "is_deleted")
	    private Boolean isDeleted = false;

	   // Getters and Setters method
	    
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getBrandName() {
			return brandName;
		}

		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}

		public String getBrandType() {
			return brandType;
		}

		public void setBrandType(String brandType) {
			this.brandType = brandType;
		}
       
		public Boolean getIsDeleted() {
			return isDeleted;
		}
		
		public void setIsDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
		}
  }

