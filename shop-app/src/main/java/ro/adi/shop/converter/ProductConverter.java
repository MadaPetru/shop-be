package ro.adi.shop.converter;

import lombok.experimental.UtilityClass;
import ro.adi.shop.dto.request.CreateProductRequestDto;
import ro.adi.shop.dto.response.ProductResponseDto;
import ro.adi.shop.jpa.entity.Product;

import java.util.List;
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

    private ProductResponseDto convertToProductResponseDto(Product product) {
        return ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .id(product.getId())
                .build();
    }
}
