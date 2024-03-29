package ro.adi.shop.images.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ro.adi.shop.BaseEntity;

@Entity
@Getter
@Setter
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_gen")
    @SequenceGenerator(name = "image_gen", sequenceName = "image_sequence", allocationSize = 32, initialValue = 10001)
    private long id;
    @Column
    private String name;
    @Column
    private String path;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image image)) return false;

        if (!name.equals(image.name)) return false;
        return path.equals(image.path);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + path.hashCode();
        return result;
    }
}
