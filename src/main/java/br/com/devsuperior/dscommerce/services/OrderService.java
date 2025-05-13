package br.com.devsuperior.dscommerce.services;

import br.com.devsuperior.dscommerce.dto.OrderDto;
import br.com.devsuperior.dscommerce.dto.OrderItemDto;
import br.com.devsuperior.dscommerce.entitites.Order;
import br.com.devsuperior.dscommerce.entitites.OrderItem;
import br.com.devsuperior.dscommerce.repositories.OrderRepository;
import br.com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public OrderDto findById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return new OrderDto(order);
    }
}
