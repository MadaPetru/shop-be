package ro.adi.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ro.adi.shop.ProductController;
import ro.adi.shop.dto.request.CreateProductRequestDto;
import ro.adi.shop.dto.request.SearchAllPageableRequest;
import ro.adi.shop.dto.request.UpdateProductRequestDto;
import ro.adi.shop.dto.response.ProductResponseDto;
import ro.adi.shop.service.ProductService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    public Page<ProductResponseDto> findAll(SearchAllPageableRequest request) {
        return productService.findAll(request.getPageable());
    }

    @Override
    public void deleteById(long id) {
        productService.deleteById(id);
    }

    @Override
    public void save(CreateProductRequestDto requestDto) {
        productService.create(requestDto);
    }

    @Override
    public void update(UpdateProductRequestDto requestDto) {
        productService.update(requestDto);
    }
}
