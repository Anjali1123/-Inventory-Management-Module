package com.inventorymanagementmodule.exceptiontest;


	import com.inventorymanagementmodule.exception.ResourceNotFoundException;
	import org.junit.jupiter.api.Test;
	import static org.junit.jupiter.api.Assertions.*;

	public class ExceptionTest {
	    @Test
	    public void testResourceNotFoundExceptionMessage() {
	        // Given
	        String errorMessage = "Resource not found";

	        // When
	        ResourceNotFoundException exception = assertThrows(
	                ResourceNotFoundException.class,
	                () -> {
	                    throw new ResourceNotFoundException(errorMessage);
	                }
	        );

	        // Then
	        assertEquals(errorMessage, exception.getMessage());
	    }
	}


