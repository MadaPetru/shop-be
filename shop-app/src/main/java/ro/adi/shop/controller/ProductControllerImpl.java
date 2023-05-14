package ro.adi.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ro.adi.shop.ProductController;
import ro.adi.shop.converter.ProductConverter;
import ro.adi.shop.dto.request.CreateProductRequestDto;
import ro.adi.shop.dto.request.SearchAllPageableRequest;
import ro.adi.shop.dto.request.UpdateProductRequestDto;
import ro.adi.shop.dto.response.ProductResponseDto;
import ro.adi.shop.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    public List<ProductResponseDto> findAll(SearchAllPageableRequest request) {
        PageRequest pageable = ProductConverter.convertToPageable(request);
        return productService.findAll(pageable);
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
