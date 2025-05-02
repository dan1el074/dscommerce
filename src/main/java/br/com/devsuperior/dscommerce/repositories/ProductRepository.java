package br.com.devsuperior.dscommerce.repositories;

import br.com.devsuperior.dscommerce.entitites.Product;
import br.com.devsuperior.dscommerce.repositories.projections.ProductMinProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true,
            value = "SELECT p.id, p.name, p.price, p.img_url AS imageUrl " +
                    "FROM tb_product p " +
                    "WHERE UPPER(p.name) LIKE UPPER(CONCAT('%', :name, '%'))",
            countQuery = "SELECT COUNT(p.id)" +
                    "FROM tb_product p " +
                    "WHERE UPPER(p.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    public Page<ProductMinProjection> searchByName(String name, Pageable pageable);
}
