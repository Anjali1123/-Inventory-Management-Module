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
	    
	    //Get the ID of the brand.
	    public Long getId() {
			return id;
		}

	   // Set the ID of the brand.
		public void setId(Long id) {
			this.id = id;
		}

		//Get the Name of the brand.
		public String getBrandName() {
			return brandName;
		}

		//Set the Name of the brand.
		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}

		//Get the Type of the brand.
		public String getBrandType() {
			return brandType;
		}

		//Set the Type of the brand.
		public void setBrandType(String brandType) {
			this.brandType = brandType;
		}
       
		//Get the "isDeleted" status of the brand.
		public Boolean getIsDeleted() {
			return isDeleted;
		}

		//Set the "isDeleted" status of the brand.
		public void setIsDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
		}
  }

