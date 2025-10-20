package br.com.ecommerce.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import br.com.ecommerce.api.service.exceptions.IllegalFileExtensionException;
import br.com.ecommerce.api.service.exceptions.MaxUploadFileException;
import br.com.ecommerce.api.service.exceptions.UploadImageException;

@Service
public class FileStorageService {

    private final Cloudinary cloudinary;

    private final String EXTENSION_ALLOWED = ".jpg";

    public FileStorageService(Cloudinary cloudinary){
        this.cloudinary = cloudinary;
    }

    public List<String> imageUploadFile(MultipartFile[] files){
        if(files.length > 5){
            throw new MaxUploadFileException("Only 5 files are allowed");
        }

        List<String> fileUrls = new ArrayList<>();
        for(MultipartFile multipartFile : files){
            try{
                String fileExtension = getFileExtension(multipartFile.getOriginalFilename());
                if(!fileExtension.equalsIgnoreCase(EXTENSION_ALLOWED)){
                    throw new IllegalFileExtensionException("Only JPG files are allowed");
                }
                String publicId = UUID.randomUUID().toString();

                Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(), 
                    ObjectUtils.asMap(
                        "public_id", publicId,
                        "resource_type", "image"
                    )
                );
                String secureUrl = (String) uploadResult.get("secure_url");
                fileUrls.add(secureUrl);
            }catch(IOException ex){
                ex.printStackTrace();
                throw new UploadImageException("Error when upload image in storage");
            }

        }
        return fileUrls;
    }

    private String getFileExtension(String originalFileName) {
        if(originalFileName != null && originalFileName.contains(".")){
            return originalFileName.substring(originalFileName.lastIndexOf("."));
        }
        return "";
    }
}

