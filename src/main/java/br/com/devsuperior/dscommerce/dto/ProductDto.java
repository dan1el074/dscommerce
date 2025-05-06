package br.com.devsuperior.dscommerce.dto;

import br.com.devsuperior.dscommerce.entitites.Category;
import br.com.devsuperior.dscommerce.entitites.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class ProductDto {
    private Long id;
    @NotBlank(message = "Campo requerido")
    @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
    private String name;
    @NotBlank(message = "Campo requerido")
    @Size(min = 10, message = "Descrição precisa ter pelo menos 10 caracteres")
    private String description;
    @Positive(message = "O preço precisa ser positivo")
    private Double price;
    private String imgUrl;

    @NotEmpty(message = "Deve ter pelo menos uma categoria")
    private List<CategoryDto> categories = new ArrayList<>();

    public ProductDto() {}

    public ProductDto(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();

        for (Category category : product.getCategories()) {
            CategoryDto categoryDto = new CategoryDto(category);
            this.categories.add(categoryDto);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }
}
