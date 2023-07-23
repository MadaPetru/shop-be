package ro.adi.shop.products;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Pageable;
import ro.adi.shop.images.jpa.entity.Image;
import ro.adi.shop.products.dto.request.CreateProductRequestDto;
import ro.adi.shop.products.dto.request.SearchAllPageableRequest;
import ro.adi.shop.products.dto.request.UpdateProductRequestDto;
import ro.adi.shop.products.dto.response.ProductResponseDto;
import ro.adi.shop.products.jpa.entity.Product;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static ro.adi.shop.Constants.BLANK_IMAGE;

@UtilityClass
public class ProductDataProvider {

    public final static List<Image> IMAGES = List.of(new Image());
    private final static int PRICE = 1;
    private final static int QUANTITY = 100;
    private final static String PRODUCT_NAME = "Adidas";
    private final static List<String> FILE_NAMES = List.of("adidas.jpg");
    private final static int PRODUCT_ID = 10321312;
    private final static Long VERSION = 0L;
    private final static Instant UPDATED_TIMESTAMP = Instant.now();
    private final static Instant CREATION_TIMESTAMP = Instant.now();

    public static SearchAllPageableRequest createSearchAllPageableRequestMock() {
        return SearchAllPageableRequest.builder()
                .build();
    }

    public static Pageable createPageableRequestMock() {
        return Pageable.ofSize(1);
    }

    public static CreateProductRequestDto createCreateProductRequestDtoMock() {
        return CreateProductRequestDto.builder()
                .price(PRICE)
                .quantity(QUANTITY)
                .name(PRODUCT_NAME)
                .fileNames(FILE_NAMES)
                .build();
    }

    public static Product createProductEntityConvertedAfterCreateProductRequest() {
        var entity = new Product();
        entity.setName(PRODUCT_NAME);
        entity.setQuantity(QUANTITY);
        entity.setPrice(PRICE);
        entity.setImages(IMAGES);
        return entity;
    }

    public static Product getProductEntity() {
        var entity = new Product();
        entity.setName(PRODUCT_NAME);
        entity.setQuantity(QUANTITY);
        entity.setPrice(PRICE);
        entity.setImages(IMAGES);
        return entity;
    }

    public static Product createProductEntityConvertedAfterUpdateProductRequest() {
        var entity = new Product();
        entity.setName(PRODUCT_NAME);
        entity.setQuantity(QUANTITY);
        entity.setPrice(PRICE);
        entity.setImages(IMAGES);
        entity.setId(PRODUCT_ID);
        entity.setVersion(VERSION);
        entity.setCreatedTimestamp(CREATION_TIMESTAMP);
        entity.setUpdatedTimestamp(UPDATED_TIMESTAMP);
        return entity;
    }

    public static ProductResponseDto createUpdateProductResponse() {
        return ProductResponseDto.builder()
                .quantity(QUANTITY)
                .price(PRICE)
                .name(PRODUCT_NAME)
                .id(PRODUCT_ID)
                .images(Collections.singletonList(BLANK_IMAGE))
                .build();
    }

    public static ProductResponseDto createCreateProductResponse() {
        return ProductResponseDto.builder()
                .quantity(QUANTITY)
                .price(PRICE)
                .name(PRODUCT_NAME)
                .images(Collections.singletonList(BLANK_IMAGE))
                .build();
    }

    public static UpdateProductRequestDto createUpdateProductRequestDtoMock() {
        return UpdateProductRequestDto.builder()
                .price(PRICE)
                .quantity(QUANTITY)
                .name(PRODUCT_NAME)
                .fileNames(FILE_NAMES)
                .id(PRODUCT_ID)
                .build();
    }
}
