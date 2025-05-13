package br.com.devsuperior.dscommerce.repositories;

import br.com.devsuperior.dscommerce.entitites.OrderItem;
import br.com.devsuperior.dscommerce.entitites.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
