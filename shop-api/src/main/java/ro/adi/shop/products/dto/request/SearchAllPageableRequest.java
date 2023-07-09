package ro.adi.shop.products.dto.request;

import lombok.*;
import org.springframework.data.domain.PageRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchAllPageableRequest {

    private PageRequest pageable;
}
