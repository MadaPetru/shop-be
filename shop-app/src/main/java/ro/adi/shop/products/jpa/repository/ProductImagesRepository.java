package ro.adi.shop.products.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.adi.shop.products.jpa.entity.ProductImageKey;
import ro.adi.shop.products.jpa.entity.ProductImages;

public interface ProductImagesRepository extends JpaRepository<ProductImages, ProductImageKey> {
}
