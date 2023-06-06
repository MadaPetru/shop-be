package ro.adi.shop;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ro.adi.shop.dto.request.CreateProductRequestDto;
import ro.adi.shop.dto.request.SearchAllPageableRequest;
import ro.adi.shop.dto.request.UpdateProductRequestDto;
import ro.adi.shop.dto.response.ProductResponseDto;

@RequestMapping("/products")
public interface ProductController {

    @PostMapping("/search")
    Page<ProductResponseDto> findAllProducts(@RequestBody SearchAllPageableRequest pageable);

    @DeleteMapping(path = "/{id}")
    boolean deleteProductById(@PathVariable long id);

    @PostMapping
    ProductResponseDto saveProduct(@RequestBody CreateProductRequestDto requestDto);

    @PutMapping
    ProductResponseDto updateProduct(@RequestBody UpdateProductRequestDto requestDto);
}
