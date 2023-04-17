package ro.adi.shop.service;

import org.springframework.stereotype.Service;
import ro.adi.shop.dto.request.CreateProductRequestDto;
import ro.adi.shop.dto.response.ProductResponseDto;

import java.util.List;

@Service
public interface ProductService {

    List<ProductResponseDto> findAll();

    void deleteById(long id);

    void create(CreateProductRequestDto requestDto);
}
