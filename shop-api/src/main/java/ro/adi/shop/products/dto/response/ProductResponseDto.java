package ro.adi.shop.products.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ProductResponseDto {

    private long id;
    private String name;
    private float price;
    private int quantity;
    private List<String> images;
}
