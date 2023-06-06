package ro.adi.shop.dto.request;

import lombok.*;
import org.springframework.data.domain.PageRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SearchAllPageableRequest {

    private PageRequest pageable;
}
