package ro.adi.shop.images.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.adi.shop.config.GlobalConfigurations;
import ro.adi.shop.images.exception.FilenameInvalidException;
import ro.adi.shop.images.jpa.entity.Image;
import ro.adi.shop.images.jpa.repository.ImageRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ro.adi.shop.images.ImageDataProvider.createMockMultipartFile;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    @Mock
    ImageRepository imageRepository;

    @Mock
    GlobalConfigurations globalConfigurations;
    @InjectMocks
    ImageServiceImpl imageService;


    @Test
    void saveImages_ShouldCallRepository() {
        when(globalConfigurations.getPath()).thenReturn("/path/to/images/");
        when(imageRepository.existsImageByName("file1.jpg")).thenReturn(false);
        when(imageRepository.existsImageByName("file2.jpg")).thenReturn(true);
        var file1 = createMockMultipartFile("file1.jpg");
        var file2 = createMockMultipartFile("file2.jpg");
        var request = Arrays.asList(file1, file2);

        imageService.saveImages(request);

        verify(imageRepository, times(1)).save(any(Image.class));
    }

    @Test
    void saveImages_ShouldNotCallRepositoryBecauseOfCorruptFilenameWithNull() {
        var file1 = createMockMultipartFile(null);
        var request = List.of(file1);

        assertThrows(FilenameInvalidException.class, () -> imageService.saveImages(request));

        verify(imageRepository, never()).save(any(Image.class));
    }

    @Test
    void saveImages_ShouldNotCallRepositoryBecauseOfCorruptFilenameContainedInWhiteList() {
        var file1 = createMockMultipartFile("           !@#");
        var request = List.of(file1);

        assertThrows(FilenameInvalidException.class, () -> imageService.saveImages(request));

        verify(imageRepository, never()).save(any(Image.class));
    }

    @Test
    void saveImages_ShouldNotCallRepositoryBecauseOfCorruptFilenameStartsWithInvalidCharacter() {
        Arrays.asList(".invalidCharacterAtBeginning", "end ", ".","/","\\","./\\ ",". "," .","adi.jpg ","adi/","adi\\","/").forEach(invalidName -> {
            var file1 = createMockMultipartFile(invalidName);
            var request = List.of(file1);

            assertThrows(FilenameInvalidException.class, () -> imageService.saveImages(request));

            verify(imageRepository, never()).save(any(Image.class));
        });
    }

    @Test
    void saveImages_ShouldNotCallRepositoryBecauseOfCorruptFilenameBecauseIsReservedKeyWord() {
        var file1 = createMockMultipartFile("con");
        var request = List.of(file1);

        assertThrows(FilenameInvalidException.class, () -> imageService.saveImages(request));

        verify(imageRepository, never()).save(any(Image.class));
    }
}