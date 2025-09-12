package br.com.ecommerce.api.service.exceptions;

public class MaxUploadFileException extends RuntimeException{
    public MaxUploadFileException(String message){
        super(message);
    }
}