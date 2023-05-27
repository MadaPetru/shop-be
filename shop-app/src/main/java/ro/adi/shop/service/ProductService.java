package ro.adi.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.adi.shop.dto.request.CreateProductRequestDto;
import ro.adi.shop.dto.request.UpdateProductRequestDto;
import ro.adi.shop.dto.response.ProductResponseDto;

@Service
public interface ProductService {

    Page<ProductResponseDto> findAll(Pageable pageable);

    boolean deleteById(long id);

    ProductResponseDto create(CreateProductRequestDto requestDto);

    ProductResponseDto update(UpdateProductRequestDto requestDto);
}
