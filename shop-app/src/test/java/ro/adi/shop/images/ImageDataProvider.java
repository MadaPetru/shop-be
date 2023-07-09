package ro.adi.shop.images;

import lombok.experimental.UtilityClass;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@UtilityClass
public class ImageDataProvider {

    public static MultipartFile createMockMultipartFile(String filename) {
        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.getOriginalFilename()).thenReturn(filename);
        return multipartFile;
    }

    public static MockMultipartFile createMockMultipartFile() {
        var filename = "files[]";
        var content = new byte[100];
        var contentType = "image/jpeg";
        var image = "adidas.jpg";
        return new MockMultipartFile(
                filename,
                image,
                contentType,
                content);
    }
}
