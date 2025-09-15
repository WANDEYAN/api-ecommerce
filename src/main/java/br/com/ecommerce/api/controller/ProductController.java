package br.com.ecommerce.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.ecommerce.api.dto.ProductMapper;
import br.com.ecommerce.api.dto.ProductRequestDTO;
import br.com.ecommerce.api.dto.ProductResponseDTO;
import br.com.ecommerce.api.model.Product;
import br.com.ecommerce.api.service.FileStorageService;
import br.com.ecommerce.api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ProductMapper productMapper;

    @Operation(summary = "Create a product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "created successfully"),
        @ApiResponse(responseCode = "409", description = "Conflit: resource already exist")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO data){
        Product product = productService.createProduct(data);
        ProductResponseDTO productResponseDTO = productMapper.toResponseDTO(product);
        return new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
    }


    @Operation(summary = "search all products data")
    @ApiResponse(responseCode = "200")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(Pageable pageable){
        Page<Product> productPage = productService.getAllProducts(pageable);
        Page<ProductResponseDTO> responsePageDTO = productMapper.toResponsePageDTO(productPage);
        return new ResponseEntity<>(responsePageDTO, HttpStatus.OK);
    
    }

    @Operation(summary = "search for just one product data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "resource found"),
        @ApiResponse(responseCode = "404", description = "resource not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") Long id){
        Product product = productService.getProductById(id);
        ProductResponseDTO productResponseDTO = productMapper.toResponseDTO(product);
        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Update a product data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Updated successfully"),
        @ApiResponse(responseCode = "404", description = "resource not found"),
        @ApiResponse(responseCode = "409", description = "Conflit: resource already exist")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDTO data){
        Product product = productService.updateProduct(id, data);
        ProductResponseDTO productResponseDTO = productMapper.toResponseDTO(product);
        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);

    }

    @Operation(summary = "Delete one product")
    @ApiResponse(responseCode = "204", description = "resource deleted")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Upload a product image")
    @ApiResponse(responseCode = "200", description = "Image uploaded successfully")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/uploads/{id}/images")
    public ResponseEntity<?> uploadProductImage(@PathVariable("id") Long productId, @RequestParam("images") MultipartFile[] file){
        List<String> imageUrl = fileStorageService.ImageUploadFile(file);
        productService.updateProductImage(productId, imageUrl);
        return ResponseEntity.ok().build();
    }

}