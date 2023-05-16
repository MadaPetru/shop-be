package ro.adi.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.adi.shop.dto.request.CreateProductRequestDto;
import ro.adi.shop.dto.request.UpdateProductRequestDto;
import ro.adi.shop.dto.response.ProductResponseDto;

import java.util.List;

@Service
public interface ProductService {

    Page<ProductResponseDto> findAll(Pageable pageable);

    void deleteById(long id);

    void create(CreateProductRequestDto requestDto);

    void update(UpdateProductRequestDto requestDto);
}
