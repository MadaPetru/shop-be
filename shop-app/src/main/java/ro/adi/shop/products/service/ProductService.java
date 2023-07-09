package ro.adi.shop.products.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.adi.shop.products.dto.request.CreateProductRequestDto;
import ro.adi.shop.products.dto.request.UpdateProductRequestDto;
import ro.adi.shop.products.dto.response.ProductResponseDto;

@Service
public interface ProductService {

    Page<ProductResponseDto> findAllProducts(Pageable pageable);

    boolean deleteProductById(long id);

    ProductResponseDto saveProduct(CreateProductRequestDto requestDto);

    ProductResponseDto updateProduct(UpdateProductRequestDto requestDto);
}
