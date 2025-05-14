package br.com.devsuperior.dscommerce.dto;

import br.com.devsuperior.dscommerce.entitites.OrderItem;

public class OrderItemDto {
    private Long productId;
    private String name;
    private Double price;
    private String imgUrl;
    private Integer quantity;

    public OrderItemDto() {}

    public OrderItemDto(Long productId, String name, Double price, String imgUrl, Integer quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.quantity = quantity;
    }

    public OrderItemDto(OrderItem orderItem) {
        productId = orderItem.getProduct().getId();
        name = orderItem.getProduct().getName();
        price = orderItem.getProduct().getPrice();
        imgUrl = orderItem.getProduct().getImgUrl();
        quantity = orderItem.getQuantity();
    }

    public Long getProductId() {
        return productId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public Double getSubTotal() {
        return price * quantity;
    }
}
