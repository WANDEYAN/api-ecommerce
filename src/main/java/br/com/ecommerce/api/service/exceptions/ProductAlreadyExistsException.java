package br.com.ecommerce.api.service.exceptions;

public class ProductAlreadyExistsException extends RuntimeException{
    
    public ProductAlreadyExistsException(String message){
        super(message);
    }
}
