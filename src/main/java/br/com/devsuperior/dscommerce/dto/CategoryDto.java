package br.com.devsuperior.dscommerce.dto;

import br.com.devsuperior.dscommerce.entitites.Category;

public class CategoryDto {
    private Long id;
    private String name;

    public CategoryDto() {}

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDto(Category category) {
        id = category.getId();
        name = category.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
