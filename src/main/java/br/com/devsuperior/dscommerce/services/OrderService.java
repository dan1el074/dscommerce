package br.com.devsuperior.dscommerce.services;

import br.com.devsuperior.dscommerce.dto.OrderDto;
import br.com.devsuperior.dscommerce.dto.OrderItemDto;
import br.com.devsuperior.dscommerce.entitites.*;
import br.com.devsuperior.dscommerce.repositories.OrderItemRepository;
import br.com.devsuperior.dscommerce.repositories.OrderRepository;
import br.com.devsuperior.dscommerce.repositories.ProductRepository;
import br.com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true)
    public OrderDto findById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return new OrderDto(order);
    }

    @Transactional
    public @Valid OrderDto insert(@Valid OrderDto dto) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User client = userService.authenticate();
        order.setClient(client);

        for (OrderItemDto orderItemDto : dto.getItems()) {
            Product product = productRepository.getReferenceById(orderItemDto.getProductId());
            OrderItem item = new OrderItem(order,product,orderItemDto.getQuantity(),product.getPrice());

            order.getItems().add(item);
        }

        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDto(order);
    }
}
