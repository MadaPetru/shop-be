package ro.adi.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.adi.shop.converter.ProductConverter;
import ro.adi.shop.dto.request.CreateProductRequestDto;
import ro.adi.shop.dto.request.UpdateProductRequestDto;
import ro.adi.shop.dto.response.ProductResponseDto;
import ro.adi.shop.jpa.entity.Product;
import ro.adi.shop.jpa.repository.ImageRepository;
import ro.adi.shop.jpa.repository.ProductRepository;

@RequiredArgsConstructor
@Component
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    @Override
    public Page<ProductResponseDto> findAll(Pageable pageable) {
        Page<Product> pageableResult = productRepository.findAll(pageable);
        return ProductConverter.convertToProductPageableResponseDto(pageableResult);
    }

    @Override
    public boolean deleteById(long id) {
        return productRepository.deleteById(id) != 0;
    }

    @Override
    public ProductResponseDto create(CreateProductRequestDto requestDto) {
        var entity = buildEntityForCreate(requestDto);
        var saved = productRepository.save(entity);
        return ProductConverter.convertToProductResponseDto(saved);
    }

    @Override
    public ProductResponseDto update(UpdateProductRequestDto requestDto) {
        var entity = buildEntityForUpdate(requestDto);
        var saved = productRepository.save(entity);
        return ProductConverter.convertToProductResponseDto(saved);
    }

    private Product buildEntityForUpdate(UpdateProductRequestDto requestDto) {
        var fileNames = requestDto.getFileNames();
        var images = imageRepository.findByNameIn(fileNames);
        var entity = ProductConverter.convertToEntity(requestDto);
        if (!images.isEmpty()) {
            entity.setImages(images);
        }
        return entity;
    }

    private Product buildEntityForCreate(CreateProductRequestDto requestDto) {
        var fileNames = requestDto.getFileNames();
        var images = imageRepository.findByNameIn(fileNames);
        var entity = ProductConverter.convertToEntity(requestDto);
        entity.setImages(images);
        return entity;
    }
}
