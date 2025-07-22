package br.com.ecommerce.api.service.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message){
        super(message);
    }

}
