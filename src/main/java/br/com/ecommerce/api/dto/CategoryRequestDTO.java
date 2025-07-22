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
public class CategoryRequestDTO {
    private String name;

    public CategoryRequestDTO(Category data){
        this.name = data.getName();
    }

}
