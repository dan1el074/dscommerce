package br.com.devsuperior.dscommerce.repositories;

import br.com.devsuperior.dscommerce.entitites.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
