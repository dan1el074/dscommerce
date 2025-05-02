package br.com.devsuperior.dscommerce.repositories.projections;

public interface ProductMinProjection {
    Long getId();
    String getName();
    Double getPrice();
    String getImgUrl();
}
