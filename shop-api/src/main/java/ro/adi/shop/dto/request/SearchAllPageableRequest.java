package ro.adi.shop.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SearchAllPageableRequest {

    private String sort;
    private int pageSize;
    private int pageNumber;
}
