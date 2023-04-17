package ro.adi.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ro.adi.shop.ProductController;
import ro.adi.shop.dto.request.CreateProductRequestDto;
import ro.adi.shop.dto.response.ProductResponseDto;
import ro.adi.shop.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    public List<ProductResponseDto> findAll() {
        return productService.findAll();
    }

    @Override
    public void deleteById(long id) {
        productService.deleteById(id);
    }

    @Override
    public void save(CreateProductRequestDto requestDto) {
        productService.create(requestDto);
    }
}
