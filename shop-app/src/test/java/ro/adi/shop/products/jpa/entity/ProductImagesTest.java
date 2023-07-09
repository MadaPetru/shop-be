package ro.adi.shop.products.jpa.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductImagesTest {
    @Test
    void equals2IdenticalObjects_shouldReturnTrue() {
        var entity1 = new ProductImages();
        var entity2 = entity1;

        boolean actualResult = entity1.equals(entity2);

        assertTrue(actualResult);
    }

    @Test
    void equalsForDifferentTypes_shouldReturnFalse() {
        var entity1 = new ProductImages();
        var entity2 = "";

        boolean actualResult = entity1.equals(entity2);

        assertFalse(actualResult);
    }

    @Test
    void equals_shouldReturnTrue() {
        var entity1 = new ProductImages();
        var key = new ProductImageKey();
        entity1.setKey(key);
        var entity2 = new ProductImages();
        entity2.setKey(key);

        boolean actualResult = entity1.equals(entity2);

        assertTrue(actualResult);
        assertEquals(entity1.getKey(), entity2.getKey());
    }

    @Test
    void hashCode_shouldReturnExpectedCode() {
        var entity1 = new ProductImages();
        var key = new ProductImageKey();
        entity1.setKey(key);
        var actualHashCode = entity1.hashCode();
        var expectedHashCode = 961;

        assertEquals(expectedHashCode, actualHashCode);
    }
}