package ro.adi.shop.products.converter;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import ro.adi.shop.images.jpa.entity.Image;
import ro.adi.shop.products.dto.request.CreateProductRequestDto;
import ro.adi.shop.products.dto.request.UpdateProductRequestDto;
import ro.adi.shop.products.dto.response.ProductResponseDto;
import ro.adi.shop.products.jpa.entity.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ro.adi.shop.Constants.BLANK_IMAGE;

@UtilityClass
@Slf4j
public class ProductConverter {


    public Page<ProductResponseDto> convertToProductPageableResponseDto(Page<Product> products) {
        var list = products.getContent();
        var responseDto = convertToProductResponseDto(list);
        return new PageImpl<>(responseDto, products.getPageable(), products.getTotalElements());
    }

    public Product convertToEntity(CreateProductRequestDto requestDto) {
        Product entity = new Product();
        entity.setName(requestDto.getName());
        entity.setPrice(requestDto.getPrice());
        entity.setQuantity(requestDto.getQuantity());
        return entity;
    }

    public Product convertToEntity(UpdateProductRequestDto requestDto) {
        Product entity = new Product();
        entity.setName(requestDto.getName());
        entity.setPrice(requestDto.getPrice());
        entity.setQuantity(requestDto.getQuantity());
        entity.setId(requestDto.getId());
        entity.setVersion(0L);
        return entity;
    }

    public ProductResponseDto convertToProductResponseDto(Product product) {
        var images = product.getImages();
        List<String> listOfImagesAsString;
        if (images != null) {
            listOfImagesAsString = images.stream().map(ProductConverter::buildImageAsString).toList();
        } else {
            listOfImagesAsString = Collections.singletonList(BLANK_IMAGE);
        }
        return ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .id(product.getId())
                .images(listOfImagesAsString)
                .build();
    }

    private List<ProductResponseDto> convertToProductResponseDto(List<Product> products) {
        return products.stream().map(ProductConverter::convertToProductResponseDto).collect(Collectors.toList());
    }

    private static String buildImageAsString(Image image) {
        try {
            return getImageAsString(image);
        } catch (IOException e) {
            log.error("Build image as string failed with exception: {} and message: {}", e, e.getMessage());
        }
        return null;
    }

    private String getImageAsString(Image image) throws IOException {
        try {
            File file = new File(image.getPath());
            byte[] bytes = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            log.error("Transform image in string failed with exception: {} and with the message: {}", e, e.getMessage());
            return BLANK_IMAGE;
        }
    }
}
