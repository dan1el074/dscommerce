package br.com.devsuperior.dscommerce.dto;

import br.com.devsuperior.dscommerce.entitites.Order;
import br.com.devsuperior.dscommerce.entitites.OrderItem;
import br.com.devsuperior.dscommerce.entitites.OrderStatus;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private Long id;
    private Instant moment;
    private OrderStatus status;

    private ClientDto client;

    private PaymentDto payment;

    @NotEmpty(message = "Deve ter pelo menos um item")
    private List<OrderItemDto> items = new ArrayList<>();

    public OrderDto() {}

    public OrderDto(Long id, Instant moment, OrderStatus status, ClientDto client, PaymentDto payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public OrderDto(Order order) {
        id = order.getId();
        moment = order.getMoment();
        status = order.getStatus();
        client = new ClientDto(order.getClient());
        payment = order.getPayment() == null ? null : new PaymentDto(order.getPayment());

        for (OrderItem item : order.getItems()) {
            items.add(new OrderItemDto(item));
        }
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClientDto getClient() {
        return client;
    }

    public PaymentDto getPayment() {
        return payment;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public Double getTotal() {
        Double sum = 0.0;
        for (OrderItemDto item : items) {
            sum += item.getSubTotal();
        }
        return sum;
    }
}
