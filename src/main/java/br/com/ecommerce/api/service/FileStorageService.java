package br.com.ecommerce.api.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    static final String UPLOAD_DIR = "/home/user/api-ecommerce/uploads";
    
    public String uploadFile(MultipartFile file){

        File destination = new File(UPLOAD_DIR, newUniqueFileName(file.getOriginalFilename()));
        try {
            file.transferTo(destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination.getName();
    }

    private String newUniqueFileName(String originalFileName){
        String fileExtension ="";
        if(originalFileName != null && originalFileName.contains(".")){
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }
        String newUniqueFileName = UUID.randomUUID().toString();

        return newUniqueFileName + fileExtension;
    }
}

