package ro.adi.shop.images.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ro.adi.shop.images.ImageDataProvider;
import ro.adi.shop.images.service.ImageServiceImpl;
import ro.adi.shop.security.JwtTokenProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ro.adi.shop.Constants.EXPECTED_MESSAGE_FOR_INTERNAL_APP_ERROR;
import static ro.adi.shop.Urls.BASE_URL_IMAGES;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest({ImageControllerImpl.class, JwtTokenProvider.class})
@TestPropertySource(locations = "classpath:application-test.yaml")
class ImageControllerImplTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ImageServiceImpl imageService;

    @Test
    void saveImages_ShouldSucceed() throws Exception {
        var request = ImageDataProvider.createMockMultipartFile();

        mockMvc.perform(multipart(BASE_URL_IMAGES)
                        .file(request))
                .andExpect(status().isOk());

        verify(imageService, times(1)).saveImages(anyList());
    }

    @Test
    void saveImages_ShouldThrowErrorAndBeHandledByHandler() throws Exception {
        var request = ImageDataProvider.createMockMultipartFile();
        doThrow(new RuntimeException()).when(imageService).saveImages(anyList());

        var response = mockMvc.perform(multipart(BASE_URL_IMAGES)
                        .file(request))
                .andExpect(status().is5xxServerError())
                .andReturn();

        verify(imageService, times(1)).saveImages(anyList());
        assertEquals(EXPECTED_MESSAGE_FOR_INTERNAL_APP_ERROR, response.getResponse().getContentAsString());
    }
}