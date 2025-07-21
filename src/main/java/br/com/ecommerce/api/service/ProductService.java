package br.com.ecommerce.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.api.dto.ProductRequestDTO;
import br.com.ecommerce.api.model.Product;
import br.com.ecommerce.api.repository.ProductRepository;
import br.com.ecommerce.api.service.exceptions.ProductAlreadyExistsException;
import br.com.ecommerce.api.service.exceptions.ProductNotFoundException;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(ProductRequestDTO data) {
        productRepository.findByName(data.getName()).ifPresent(product -> {
            throw new ProductAlreadyExistsException("Product name already registered");
        });
        Product product = new Product(data);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public Product updateProduct(Long id, ProductRequestDTO data){
        Product oldProduct = getProductById(id);
        Product productUpdate = new Product(data);  
        productUpdate.setId(oldProduct.getId());
        productUpdate.setCode(oldProduct.getCode());
        productUpdate.setRating(oldProduct.getRating());
        return productRepository.save(productUpdate);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


}
