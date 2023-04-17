package ro.adi.shop;

import org.springframework.web.bind.annotation.*;
import ro.adi.shop.dto.request.CreateProductRequestDto;
import ro.adi.shop.dto.request.UpdateProductRequestDto;
import ro.adi.shop.dto.response.ProductResponseDto;

import java.util.List;

@RequestMapping("/products")
public interface ProductController {

    @GetMapping
    List<ProductResponseDto> findAll();

    @DeleteMapping(path = "/{id}")
    void deleteById(@PathVariable long id);

    @PostMapping
    void save(@RequestBody CreateProductRequestDto requestDto);

    @PutMapping
    void update(@RequestBody UpdateProductRequestDto requestDto);
}
