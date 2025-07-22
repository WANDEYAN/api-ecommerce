package br.com.ecommerce.api.dto;

import br.com.ecommerce.api.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {
    private Long id;
    private String name;

    public CategoryResponseDTO(Category data){
        this.id = data.getId();
        this.name = data.getName();
    }
}
