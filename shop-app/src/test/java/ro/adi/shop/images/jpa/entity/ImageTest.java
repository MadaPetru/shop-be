package ro.adi.shop.images.jpa.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {

    @Test
    void equals2IdenticalObjects_shouldReturnTrue() {
        var entity1 = new Image();
        var entity2 = entity1;

        boolean actualResult = entity1.equals(entity2);

        assertTrue(actualResult);
    }

    @Test
    void equalsForDifferentTypes_shouldReturnFalse() {
        var entity1 = new Image();
        var entity2 = "";

        boolean actualResult = entity1.equals(entity2);

        assertFalse(actualResult);
    }

    @Test
    void equals_shouldReturnTrue() {
        var entity1 = new Image();
        entity1.setId(1);
        entity1.setName("picture.jpg");
        entity1.setPath("/c/Desktop/picture.jpg");
        var entity2 = new Image();
        entity2.setId(1);
        entity2.setName("picture.jpg");
        entity2.setPath("/c/Desktop/picture.jpg");

        boolean actualResult = entity1.equals(entity2);

        assertTrue(actualResult);
        assertEquals(entity1.getId(), entity2.getId());
        assertEquals(entity1.getName(), entity2.getName());
        assertEquals(entity1.getPath(), entity2.getPath());
    }

    @Test
    void equals_shouldReturnFalse() {
        var entity1 = new Image();
        entity1.setId(1);
        entity1.setName("picture.jpg");
        entity1.setPath("/c/Desktop/picture.jpg");
        var entity2 = new Image();
        entity2.setId(1);
        entity2.setName("otherPicture.jpg");
        entity2.setPath("/c/Desktop/otherPicture.jpg");

        boolean actualResult = entity1.equals(entity2);

        assertFalse(actualResult);
    }

    @Test
    void hashCode_shouldReturnExpectedCode() {
        var entity1 = new Image();
        entity1.setName("picture.jpg");
        entity1.setPath("/c/Desktop/picture.jpg");
        var actualHashCode = entity1.hashCode();
        var expectedHashCode = -1215408110;

        assertEquals(expectedHashCode, actualHashCode);
    }
}