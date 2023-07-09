package ro.adi.shop.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import ro.adi.shop.products.ProductController;
import ro.adi.shop.config.LoggingPerformanceApi;
import ro.adi.shop.products.dto.request.CreateProductRequestDto;
import ro.adi.shop.products.dto.request.SearchAllPageableRequest;
import ro.adi.shop.products.dto.request.UpdateProductRequestDto;
import ro.adi.shop.products.dto.response.ProductResponseDto;
import ro.adi.shop.products.service.ProductService;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    @LoggingPerformanceApi
    public Page<ProductResponseDto> findAllProducts(SearchAllPageableRequest request) {
        return productService.findAllProducts(request.getPageable());
    }

    @Override
    @LoggingPerformanceApi
    public boolean deleteProductById(long id) {
        return productService.deleteProductById(id);
    }

    @Override
    @LoggingPerformanceApi
    public ProductResponseDto saveProduct(CreateProductRequestDto requestDto) {
        return productService.saveProduct(requestDto);
    }

    @Override
    @LoggingPerformanceApi
    public ProductResponseDto updateProduct(UpdateProductRequestDto requestDto) {
        return productService.updateProduct(requestDto);
    }
}
