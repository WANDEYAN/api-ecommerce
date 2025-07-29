package br.com.ecommerce.api.service.exceptions;

public class InsufficientStockException extends RuntimeException{
    public InsufficientStockException(String message){
        super(message);
    }
}