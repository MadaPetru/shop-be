package ro.adi.shop;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConstantsTest {

    @Test
    void constants_areNotNull() {
        assertNotNull(Constants.BLANK_IMAGE);
        assertNotNull(Constants.EXPECTED_MESSAGE_FOR_INTERNAL_APP_ERROR);
    }
}
