package ro.adi.shop.products.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import ro.adi.shop.config.GlobalConfigurations;
import ro.adi.shop.images.jpa.repository.ImageRepository;
import ro.adi.shop.products.ProductDataProvider;
import ro.adi.shop.products.jpa.entity.Product;
import ro.adi.shop.products.jpa.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static ro.adi.shop.products.ProductDataProvider.IMAGES;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl classUnderTest;
    @Mock
    ProductRepository productRepository;
    @Mock
    ImageRepository imageRepository;
    @Mock
    GlobalConfigurations globalConfigurations;


    @Test
    void findAllProducts_shouldCallRepository() {
        var request = ProductDataProvider.createPageableRequestMock();
        Page<Product> productsReturned = new PageImpl<>(new ArrayList<>());
        when(productRepository.findAll(request)).thenReturn(productsReturned);

        classUnderTest.findAllProducts(request);

        verify(productRepository, times(1)).findAll(request);
    }

    @Test
    void deleteProductById_shouldCallRepository() {
        var id = 102131231L;
        when(productRepository.deleteById(id)).thenReturn(1);
        when(productRepository.findById(id)).thenReturn(Optional.of(ProductDataProvider.getProductEntity()));
        when(globalConfigurations.getPath()).thenReturn("");

        classUnderTest.deleteProductById(id);

        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteProductById_shouldNotDeleteFromFolder() {
        var id = 102131231L;
        when(productRepository.deleteById(id)).thenReturn(1);
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        classUnderTest.deleteProductById(id);

        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    void saveProduct_shouldCallRepository() {
        var request = ProductDataProvider.createCreateProductRequestDtoMock();
        var entity = ProductDataProvider.createProductEntityConvertedAfterCreateProductRequest();
        var expectedResponse = ProductDataProvider.createCreateProductResponse();
        when(productRepository.save(entity)).thenReturn(entity);
        when(imageRepository.findByNameIn(request.getFileNames())).thenReturn(IMAGES);

        var actualResponse = classUnderTest.saveProduct(request);

        verify(productRepository, times(1)).save(entity);
        verify(imageRepository, times(1)).findByNameIn(request.getFileNames());
        assertEquals(actualResponse.getPrice(), expectedResponse.getPrice());
        assertEquals(actualResponse.getQuantity(), expectedResponse.getQuantity());
        assertEquals(actualResponse.getName(), expectedResponse.getName());
        assertEquals(actualResponse.getImages(), expectedResponse.getImages());
        assertEquals(actualResponse.getId(), expectedResponse.getId());
    }

    @Test
    void updateProduct_shouldCallRepository() {
        var request = ProductDataProvider.createUpdateProductRequestDtoMock();
        var entity = ProductDataProvider.createProductEntityConvertedAfterUpdateProductRequest();
        var expectedResponse = ProductDataProvider.createUpdateProductResponse();
        var fileNames = request.getFileNames();
        when(productRepository.save(entity)).thenReturn(entity);
        when(imageRepository.findByNameIn(fileNames)).thenReturn(IMAGES);

        var actualResponse = classUnderTest.updateProduct(request);

        verify(productRepository, times(1)).save(entity);
        verify(imageRepository, times(1)).findByNameIn(fileNames);
        assertEquals(actualResponse.getPrice(), expectedResponse.getPrice());
        assertEquals(actualResponse.getQuantity(), expectedResponse.getQuantity());
        assertEquals(actualResponse.getName(), expectedResponse.getName());
        assertEquals(actualResponse.getImages(), expectedResponse.getImages());
        assertEquals(actualResponse.getId(), expectedResponse.getId());
    }
}