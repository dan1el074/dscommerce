package br.com.devsuperior.dscommerce.controllers;

import br.com.devsuperior.dscommerce.dto.OrderDto;
import br.com.devsuperior.dscommerce.dto.OrderItemDto;
import br.com.devsuperior.dscommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
        OrderDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }
}
