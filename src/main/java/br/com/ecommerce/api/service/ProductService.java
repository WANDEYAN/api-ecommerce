package br.com.ecommerce.api.service;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ecommerce.api.dto.ProductRequestDTO;
import br.com.ecommerce.api.model.Category;
import br.com.ecommerce.api.model.Product;
import br.com.ecommerce.api.repository.CategoryRepository;
import br.com.ecommerce.api.repository.ProductRepository;
import br.com.ecommerce.api.service.exceptions.CategoryNotFoundException;
import br.com.ecommerce.api.service.exceptions.ProductAlreadyExistsException;
import br.com.ecommerce.api.service.exceptions.ProductNotFoundException;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product createProduct(ProductRequestDTO data) {
        checkAlreadyExistsByName(data.getName());
        Category category = categoryRepository.findById(data.getCategoryId())
        .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        Product product = new Product(data, category);
        return productRepository.save(product);
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public Product updateProduct(Long id, ProductRequestDTO data){
        checkAlreadyExistsByName(data.getName());
        Product oldProduct = getProductById(id);
        Category category = categoryRepository.findById(data.getCategoryId())
        .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        Product productUpdate = new Product(data, category);  
        productUpdate.setId(oldProduct.getId());
        productUpdate.setCode(oldProduct.getCode());
        productUpdate.setRating(oldProduct.getRating());
        return productRepository.save(productUpdate);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    private void checkAlreadyExistsByName(String name){
        productRepository.findByName(name).ifPresent(product -> {
            throw new ProductAlreadyExistsException("Product name already registered");
        });
    }
}
