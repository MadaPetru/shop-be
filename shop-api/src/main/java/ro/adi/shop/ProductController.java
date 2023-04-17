package ro.adi.shop;

import org.springframework.web.bind.annotation.*;
import ro.adi.shop.dto.request.CreateProductRequestDto;
import ro.adi.shop.dto.response.ProductResponseDto;

import java.util.List;

@RequestMapping("/products")
public interface ProductController {

    @GetMapping
    List<ProductResponseDto> findAll();

    @DeleteMapping
    void deleteById(@PathVariable long id);

    @PostMapping
    void save(@RequestBody CreateProductRequestDto requestDto);
}
