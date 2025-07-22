package br.com.ecommerce.api.service.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException{

    public CategoryAlreadyExistsException(String message){
        super(message);
    }
    
}
