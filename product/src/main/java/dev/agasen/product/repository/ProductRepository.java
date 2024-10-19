package dev.agasen.product.repository;

import dev.agasen.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

   @Query("""
           SELECT p
           FROM Product p
           JOIN p.productCategories c
           WHERE c.id IN :categoryIds""")
   List<Product> getProductsByCategories(List<Long> categoryIds);

}
