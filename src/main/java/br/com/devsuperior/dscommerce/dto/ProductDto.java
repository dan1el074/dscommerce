package br.com.devsuperior.dscommerce.dto;

import br.com.devsuperior.dscommerce.entitites.Product;
import jakarta.persistence.Column;

public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

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
}
