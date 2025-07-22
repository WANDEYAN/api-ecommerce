package br.com.ecommerce.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.api.dto.ProductRequestDTO;
import br.com.ecommerce.api.dto.ProductResponseDTO;
import br.com.ecommerce.api.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO data){
        ProductResponseDTO product = new ProductResponseDTO(productService.createProduct(data));
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        List<ProductResponseDTO> productResponse = productService.getAllProducts()
        .stream().map(ProductResponseDTO::new).toList();
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") Long id){
        ProductResponseDTO responseProduct = new ProductResponseDTO(productService.getProductById(id));
        return new ResponseEntity<>(responseProduct, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDTO data){
        ProductResponseDTO productResponse = new ProductResponseDTO(productService.updateProduct(id, data));
        return new ResponseEntity<>(productResponse, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}