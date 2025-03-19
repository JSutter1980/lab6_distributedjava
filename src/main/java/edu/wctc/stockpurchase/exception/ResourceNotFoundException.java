package edu.wctc.stockpurchase.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceType, int id) {
        super("%s not found with ID %d".formatted(resourceType,id));
    }
}
