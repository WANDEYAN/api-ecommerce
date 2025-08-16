package br.com.ecommerce.api.controller;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.api.dto.CategoryRequestDTO;
import br.com.ecommerce.api.dto.CategoryResponseDTO;
import br.com.ecommerce.api.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Create category")
    @ApiResponses(value ={
        @ApiResponse(responseCode = "201", description = "Created successfully"),
        @ApiResponse(responseCode = "409", description = "Conflit: Category already exist")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO data){
        CategoryResponseDTO category = new CategoryResponseDTO(categoryService.createCategory(data));
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @Operation(summary = "Search all categories data")
    @ApiResponse(responseCode = "200", description = "all categories found")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<Page<CategoryResponseDTO>> getAllCategories(Pageable pageable){
        Page<CategoryResponseDTO> categoryPage = categoryService.getAllCategories(pageable)
        .map(CategoryResponseDTO::new);
        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
    }

    @Operation(summary = "Search for a specific category data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "resource found"),
        @ApiResponse(responseCode = "404", description = "resource not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable("id") Long id){
        CategoryResponseDTO responseCategory = new CategoryResponseDTO(categoryService.getCategoryById(id));
        return new ResponseEntity<>(responseCategory, HttpStatus.OK);
    }

    @Operation(summary = "Update category data")
    @ApiResponses(value ={
        @ApiResponse(responseCode = "200", description = "Update successfully"),
        @ApiResponse(responseCode = "409", description = "Conflit: resource already exist")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryRequestDTO data){
        CategoryResponseDTO categoryResponse = new CategoryResponseDTO(categoryService.updateCategory(id, data));
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @Operation(summary = "Delete one category")
    @ApiResponse(responseCode = "204", description = "Delete successfully")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
