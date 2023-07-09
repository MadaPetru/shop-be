package ro.adi.shop.products.jpa.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_images")
@Getter
@Setter
public class ProductImages {
    @EmbeddedId
    private ProductImageKey key;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductImages that)) return false;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return 31 * key.hashCode();
    }
}
