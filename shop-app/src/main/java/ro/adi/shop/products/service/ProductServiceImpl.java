package ro.adi.shop.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.adi.shop.config.GlobalConfigurations;
import ro.adi.shop.images.jpa.repository.ImageRepository;
import ro.adi.shop.products.converter.ProductConverter;
import ro.adi.shop.products.dto.request.CreateProductRequestDto;
import ro.adi.shop.products.dto.request.UpdateProductRequestDto;
import ro.adi.shop.products.dto.response.ProductResponseDto;
import ro.adi.shop.products.jpa.entity.Product;
import ro.adi.shop.products.jpa.repository.ProductRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Component
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final GlobalConfigurations globalConfigurations;

    @Override
    public Page<ProductResponseDto> findAllProducts(Pageable pageable) {
        log.info("Find all products with pageable: {}", pageable);
        Page<Product> pageableResult = productRepository.findAll(pageable);
        return ProductConverter.convertToProductPageableResponseDto(pageableResult);
    }

    @Override
    @Transactional
    public boolean deleteProductById(long id) {
        log.info("Delete product with id: {}", id);
        productRepository.findById(id).ifPresent(this::deleteFilesOfProductFromFolder);
        return productRepository.deleteById(id) != 0;
    }

    @Override
    @Transactional
    public ProductResponseDto saveProduct(CreateProductRequestDto requestDto) {
        log.info("Create product with request: {}", requestDto);
        var entity = buildEntityForCreate(requestDto);
        var saved = productRepository.save(entity);
        return ProductConverter.convertToProductResponseDto(saved);
    }

    @Override
    @Transactional
    public ProductResponseDto updateProduct(UpdateProductRequestDto requestDto) {
        log.info("Update product with id: {}, with request: {}", requestDto.getId(), requestDto);
        var entity = buildEntityForUpdate(requestDto);
        productRepository.findById(entity.getId()).ifPresent(setVersionAndCreationTimestamp(entity));
        var saved = productRepository.save(entity);
        return ProductConverter.convertToProductResponseDto(saved);
    }

    private Consumer<Product> setVersionAndCreationTimestamp(Product entityBuildFromRequest) {

        return product -> {
            entityBuildFromRequest.setVersion(product.getVersion());
            entityBuildFromRequest.setCreatedTimestamp(product.getCreatedTimestamp());
        };
    }

    private void deleteFilesOfProductFromFolder(Product product) {
        var images = product.getImages();
        images.forEach(image -> {
            var path = globalConfigurations.getPath() + image.getName();
            try {
                Files.delete(Path.of(path));
                log.info(String.format("File of product %s was successfully deleted", product));
            } catch (IOException e) {
                log.error(String.format("File could not be deleted from resources of product %s", product));
            }
        });
    }

    private Product buildEntityForUpdate(UpdateProductRequestDto requestDto) {
        var fileNames = requestDto.getFileNames();
        var images = imageRepository.findByNameIn(fileNames);
        var entity = ProductConverter.convertToEntity(requestDto);
        Optional.of(images).ifPresent(entity::setImages);
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