package ro.adi.shop.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.adi.shop.jpa.entity.Image;

import java.util.List;
import java.util.Set;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Set<Image> findByNameIn(List<String> names);
}
