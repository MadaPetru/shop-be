package ro.adi.shop.products.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ro.adi.shop.BaseEntity;
import ro.adi.shop.images.jpa.entity.Image;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_gen")
    @SequenceGenerator(name = "product_gen", sequenceName = "product_sequence", allocationSize = 32, initialValue = 10001)
    private long id;
    @Column
    private String name;
    @Column
    private float price;
    @Column
    private int quantity;
    @ManyToMany
    private List<Image> images;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;

        if (Float.compare(product.price, price) != 0) return false;
        if (quantity != product.quantity) return false;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + quantity;
        return result;
    }
}
