package com.inventorymanagementmodule.exception;

//Custom exception to handle cases where a requested resource is not found.
public class ResourceNotFoundException extends RuntimeException {
    
	//Constructor for ResourceNotFoundException that takes a message parameter
	public ResourceNotFoundException(String message) {
       
		//Passes the message to the superclass.
		super(message);
    }
}