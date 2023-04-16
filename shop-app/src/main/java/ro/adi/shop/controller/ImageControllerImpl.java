package ro.adi.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ro.adi.shop.ImageController;
import ro.adi.shop.service.ImageService;

@RestController
@RequiredArgsConstructor
public class ImageControllerImpl implements ImageController {

    private final ImageService imageService;

    @Override
    @CrossOrigin(origins = "http://localhost:4200")
    public void save(MultipartFile file) {
        imageService.save(file);
    }
}
