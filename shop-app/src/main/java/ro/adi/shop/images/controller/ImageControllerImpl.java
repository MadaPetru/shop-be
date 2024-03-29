package ro.adi.shop.images.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ro.adi.shop.images.ImageController;
import ro.adi.shop.config.LoggingPerformanceApi;
import ro.adi.shop.images.service.ImageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ImageControllerImpl implements ImageController {

    private final ImageService imageService;

    @Override
    @LoggingPerformanceApi
    public void saveImages(List<MultipartFile> files) {
        imageService.saveImages(files);
    }
}
