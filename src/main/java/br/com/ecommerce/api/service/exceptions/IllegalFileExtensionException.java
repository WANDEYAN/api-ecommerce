package br.com.ecommerce.api.service.exceptions;

public class IllegalFileExtensionException extends RuntimeException {
    public IllegalFileExtensionException(String message){
        super(message);
    }
}
