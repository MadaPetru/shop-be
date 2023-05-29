package ro.adi.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ro.adi.shop.ImageController;
import ro.adi.shop.service.ImageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ImageControllerImpl implements ImageController {

    private final ImageService imageService;

    @Override
    public void save(List<MultipartFile> files) {
        imageService.save(files);
    }
}
