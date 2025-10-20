package br.com.ecommerce.api.service.exceptions;

public class UploadImageException extends RuntimeException{
    public UploadImageException(String message){
        super(message);
    }
}
