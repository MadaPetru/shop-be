package ro.adi.shop.images.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.adi.shop.images.jpa.entity.Image;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByNameIn(List<String> names);
    boolean existsImageByName(String name);
}
