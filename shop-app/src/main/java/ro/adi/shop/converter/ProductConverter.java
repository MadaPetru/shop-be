package ro.adi.shop.converter;

import lombok.experimental.UtilityClass;
import ro.adi.shop.dto.request.CreateProductRequestDto;
import ro.adi.shop.dto.request.UpdateProductRequestDto;
import ro.adi.shop.dto.response.ProductResponseDto;
import ro.adi.shop.jpa.entity.Image;
import ro.adi.shop.jpa.entity.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class ProductConverter {

    public List<ProductResponseDto> convertToProductResponseDto(List<Product> products) {
        return products.stream().map(ProductConverter::convertToProductResponseDto).collect(Collectors.toList());
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
        return entity;
    }

    private ProductResponseDto convertToProductResponseDto(Product product) {
        Set<Image> images = product.getImages();
        List<String> bytes = images.stream().map(image -> {
            try {
                return getImageAsByte(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).toList();
        return ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .id(product.getId())
                .images(bytes)
                .build();
    }

    private String getImageAsByte(Image image) throws IOException {
        File file = new File(image.getPath());
        byte[] bytes = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(bytes);
    }
}
