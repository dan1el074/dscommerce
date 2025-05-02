package br.com.devsuperior.dscommerce.dto;

import br.com.devsuperior.dscommerce.repositories.projections.ProductMinProjection;

public class ProductMinDto {
    private Long id;
    private String name;
    private Double price;
    private String imgUrl;

    public ProductMinDto() {}

    public ProductMinDto(Long id, String name, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductMinDto(ProductMinProjection projection) {
        this.id = projection.getId();
        this.name = projection.getName();
        this.price = projection.getPrice();
        this.imgUrl = projection.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
