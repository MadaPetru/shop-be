package ro.adi.shop.security.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiRestriction {

    private byte maxRetries;
    private byte minutesToBeBlocked;
}
