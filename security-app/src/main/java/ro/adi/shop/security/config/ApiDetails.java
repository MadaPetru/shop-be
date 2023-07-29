package ro.adi.shop.security.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiDetails {

    private String uri;
    private String method;
    private ApiRestriction apiRestriction;
}
