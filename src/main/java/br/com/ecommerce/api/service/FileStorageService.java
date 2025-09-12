package br.com.ecommerce.api.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.ecommerce.api.service.exceptions.IllegalFileExtensionException;
import br.com.ecommerce.api.service.exceptions.MaxUploadFileException;
import net.coobird.thumbnailator.Thumbnails;

@Service
public class FileStorageService {

    @Value("${api.filestorage.uploads}")
    private String UPLOAD_DIR;
    
    @Value("${api.filestorage.uploads.thumbnail}")
    private String PREFIX_THUMBNAIL_FILE_NAME;

    @Value("${api.filestorage.uploads.extension.allowed}")
    private String EXTESION_ALLOWED = ".jpg";
    
    public List<String> ImageUploadFile(MultipartFile[] files){
        if(files.length > 5){
            throw new MaxUploadFileException("Only 5 files are allowed");
        }

        List<String> fileNames = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            String ImageFileName = newUniqueFileName(multipartFile.getOriginalFilename());
            File destination = new File(UPLOAD_DIR, ImageFileName);
            try{
                Thumbnails.of(multipartFile.getInputStream()).size(800, 600 ).toFile(destination);
                saveThumbnail(multipartFile, ImageFileName);
                fileNames.add(ImageFileName);
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return fileNames;
    }

    private void saveThumbnail(MultipartFile image, String ImagefileName){
        try{
            File thumbnail = new File(UPLOAD_DIR, PREFIX_THUMBNAIL_FILE_NAME + ImagefileName);
            Thumbnails.of(image.getInputStream()).size(80, 60).toFile(thumbnail);
        }catch(IOException ex){
            System.out.println("=============Error saving thumbnail=============");
            ex.printStackTrace();
        }
    }

    private String newUniqueFileName(String originalFileName){
        String fileExtension ="";
        if(originalFileName != null && originalFileName.contains(".")){
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            if (!fileExtension.equalsIgnoreCase(EXTESION_ALLOWED)) {
                throw new IllegalFileExtensionException("Only JPG files are allowed");
            }
        }
        String newUniqueFileName = UUID.randomUUID().toString();

        return newUniqueFileName + fileExtension;
    }
}

