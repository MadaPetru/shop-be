package ro.adi.shop.products.jpa.entity;

import org.junit.jupiter.api.Test;
import ro.adi.shop.images.jpa.entity.Image;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void equals2IdenticalObjects_shouldReturnTrue() {
        var entity1 = new Product();
        var entity2 = entity1;

        boolean actualResult = entity1.equals(entity2);

        assertTrue(actualResult);
    }

    @Test
    void equalsForDifferentTypes_shouldReturnFalse() {
        var entity1 = new Product();
        var entity2 = "";

        boolean actualResult = entity1.equals(entity2);

        assertFalse(actualResult);
    }

    @Test
    void equals_shouldReturnTrue() {
        var images = List.of(new Image());
        var entity1 = new Product();
        entity1.setId(1);
        entity1.setName("picture.jpg");
        entity1.setQuantity(1);
        entity1.setPrice(1);
        entity1.setImages(images);
        var entity2 = new Product();
        entity2.setId(1);
        entity2.setName("picture.jpg");
        entity2.setQuantity(1);
        entity2.setPrice(1);
        entity2.setImages(images);

        boolean actualResult = entity1.equals(entity2);

        assertTrue(actualResult);
        assertEquals(entity1.getId(), entity2.getId());
        assertEquals(entity1.getName(), entity2.getName());
        assertEquals(entity1.getQuantity(), entity2.getQuantity());
        assertEquals(entity1.getPrice(), entity2.getPrice());
        assertEquals(entity1.getImages(), entity2.getImages());
    }

    @Test
    void equals_shouldReturnFalse() {
        var images = List.of(new Image());
        var entity1 = new Product();
        entity1.setId(1);
        entity1.setName("picture.jpg");
        entity1.setQuantity(1);
        entity1.setPrice(1);
        entity1.setImages(images);
        var entity2 = new Product();
        entity2.setId(2);
        var actualResponse = entity1.equals(entity2);
        assertFalse(actualResponse);
        entity2.setId(1);
        entity2.setPrice(2);
        actualResponse = entity1.equals(entity2);
        assertFalse(actualResponse);
        entity2.setPrice(1);
        entity2.setName("picture2.jpg");
        actualResponse = entity1.equals(entity2);
        assertFalse(actualResponse);
        entity2.setName("picture.jpg");
        entity2.setQuantity(2);
        actualResponse = entity1.equals(entity2);
        assertFalse(actualResponse);
        entity2.setQuantity(1);
        entity2.setPrice(2);
        actualResponse = entity1.equals(entity2);
        assertFalse(actualResponse);
    }

    @Test
    void hashCode_shouldReturnExpectedCode() {
        var entity1 = new Product();
        entity1.setName("picture.jpg");
        entity1.setQuantity(1);
        entity1.setPrice(1);
        var actualHashCode = entity1.hashCode();
        var expectedHashCode = -1336722766;

        assertEquals(expectedHashCode, actualHashCode);

        entity1.setId(1);
        entity1.setName(null);
        entity1.setQuantity(1);
        entity1.setPrice(0);
        actualHashCode = entity1.hashCode();
        expectedHashCode = 1;

        assertEquals(expectedHashCode, actualHashCode);
    }
}