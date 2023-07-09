package ro.adi.shop.products.jpa.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductImageKeyTest {
    @Test
    void equals2IdenticalObjects_shouldReturnTrue() {
        var entity1 = new ProductImageKey();
        var entity2 = entity1;

        boolean actualResult = entity1.equals(entity2);

        assertTrue(actualResult);
    }

    @Test
    void equalsForDifferentTypes_shouldReturnFalse() {
        var entity1 = new ProductImageKey();
        var entity2 = "";

        boolean actualResult = entity1.equals(entity2);

        assertFalse(actualResult);
    }

    @Test
    void equals_shouldReturnTrue() {
        var entity1 = new ProductImageKey();
        entity1.setProductId(1);
        entity1.setImageId(1);
        var entity2 = new ProductImageKey();
        entity2.setProductId(1);
        entity2.setImageId(1);
        boolean actualResult = entity1.equals(entity2);

        assertTrue(actualResult);
        assertEquals(entity1.getProductId(), entity2.getProductId());
        assertEquals(entity1.getImageId(), entity2.getImageId());
    }

    @Test
    void equals_shouldReturnFalse() {
        var entity1 = new ProductImageKey();
        entity1.setProductId(1);
        entity1.setImageId(1);
        var entity2 = new ProductImageKey();
        entity2.setProductId(1);
        entity2.setImageId(2);
        boolean actualResult = entity1.equals(entity2);

        assertFalse(actualResult);

        entity1.setProductId(1);
        entity1.setImageId(1);
        entity2.setProductId(2);
        entity2.setImageId(1);
        actualResult = entity1.equals(entity2);

        assertFalse(actualResult);

        entity1.setProductId(1);
        entity1.setImageId(2);
        entity2.setProductId(3);
        entity2.setImageId(4);
        actualResult = entity1.equals(entity2);

        assertFalse(actualResult);
    }

    @Test
    void hashCode_shouldReturnExpectedCode() {
        var entity1 = new ProductImageKey();
        entity1.setImageId(1);
        entity1.setProductId(1);
        var actualHashCode = entity1.hashCode();
        var expectedHashCode = 33;

        assertEquals(expectedHashCode, actualHashCode);
    }
}