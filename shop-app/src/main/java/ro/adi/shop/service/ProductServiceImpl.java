package ro.adi.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.adi.shop.converter.ProductConverter;
import ro.adi.shop.dto.request.CreateProductRequestDto;
import ro.adi.shop.dto.response.ProductResponseDto;
import ro.adi.shop.jpa.repository.ProductRepository;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponseDto> findAll() {
        return ProductConverter.convertToProductResponseDto(productRepository.findAll());
    }

    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void create(CreateProductRequestDto requestDto) {
        productRepository.save(ProductConverter.convertToEntity(requestDto));
    }
}
