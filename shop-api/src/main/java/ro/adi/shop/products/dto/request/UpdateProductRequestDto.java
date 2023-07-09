package ro.adi.shop.products.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequestDto {

    private long id;
    private String name;
    private float price;
    private int quantity;
    private List<String> fileNames;
}
