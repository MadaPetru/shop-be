package ro.adi.shop.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceActivityRegistration {

    public static final byte MAX_RETRIES = 5;

    private Instant createdFirstRequest;
    private byte retries;
}
