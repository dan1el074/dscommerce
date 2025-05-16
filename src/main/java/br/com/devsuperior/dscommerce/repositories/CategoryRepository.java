package br.com.devsuperior.dscommerce.repositories;

import br.com.devsuperior.dscommerce.entitites.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
