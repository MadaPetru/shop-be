package ro.adi.shop.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.adi.shop.jpa.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
