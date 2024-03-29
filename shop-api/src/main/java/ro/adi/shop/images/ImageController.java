package ro.adi.shop.images;

import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/images")
public interface ImageController {
    @PostMapping
    void saveImages(@RequestParam("files[]") @Size(max = 10) List<MultipartFile> files);
}
