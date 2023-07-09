package ro.adi.shop.products.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class ProductImageKey implements Serializable {
    @Column(name = "images_id")
    private long imageId;
    @Column(name = "product_id")
    private long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductImageKey that)) return false;
        return imageId == that.imageId && productId == that.productId;
    }

    @Override
    public int hashCode() {

        return 31 + Long.hashCode(imageId) + Long.hashCode(productId);
    }
}
