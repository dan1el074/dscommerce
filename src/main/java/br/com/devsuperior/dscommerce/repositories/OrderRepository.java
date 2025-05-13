package br.com.devsuperior.dscommerce.repositories;

import br.com.devsuperior.dscommerce.entitites.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
