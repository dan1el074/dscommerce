package br.com.devsuperior.dscommerce.dto;

import br.com.devsuperior.dscommerce.entitites.OrderItem;

public class OrderItemDto {
    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;

    public OrderItemDto() {}

    public OrderItemDto(Long productId, String name, Double price, Integer quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderItemDto(OrderItem orderItem) {
        productId = orderItem.getProduct().getId();
        name = orderItem.getProduct().getName();
        price = orderItem.getProduct().getPrice();
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

    public Integer getQuantity() {
        return quantity;
    }

    public Double getSubTotal() {
        return price * quantity;
    }
}
