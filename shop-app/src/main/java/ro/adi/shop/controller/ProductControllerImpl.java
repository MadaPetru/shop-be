package ro.adi.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ro.adi.shop.ProductController;
import ro.adi.shop.config.LoggingPerformanceApi;
import ro.adi.shop.dto.request.CreateProductRequestDto;
import ro.adi.shop.dto.request.SearchAllPageableRequest;
import ro.adi.shop.dto.request.UpdateProductRequestDto;
import ro.adi.shop.dto.response.ProductResponseDto;
import ro.adi.shop.service.ProductService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://192.168.0.106:4200"})
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
        return productService.create(requestDto);
    }

    @Override
    @LoggingPerformanceApi
    public ProductResponseDto updateProduct(UpdateProductRequestDto requestDto) {
        return productService.updateProduct(requestDto);
    }
}
