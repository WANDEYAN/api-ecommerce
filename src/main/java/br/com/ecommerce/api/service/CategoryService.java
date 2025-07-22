package br.com.ecommerce.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.api.dto.CategoryRequestDTO;
import br.com.ecommerce.api.model.Category;
import br.com.ecommerce.api.repository.CategoryRepository;
import br.com.ecommerce.api.service.exceptions.CategoryAlreadyExistsException;
import br.com.ecommerce.api.service.exceptions.CategoryNotFoundException;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(CategoryRequestDTO data) {
        categoryRepository.findByName(data.getName()).ifPresent(category -> {
            throw new CategoryAlreadyExistsException("Category name already registered");
        });
        Category category = new Category();
        category.setName(data.getName());
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
        .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    public Category updateCategory(Long id, CategoryRequestDTO data){
        Category oldCategory = getCategoryById(id);
        Category categoryUpdate = new Category();
        categoryUpdate.setId(oldCategory.getId());
        categoryUpdate.setName(data.getName());
        return categoryRepository.save(categoryUpdate);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

}
