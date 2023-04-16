package ro.adi.shop;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/images")
public interface ImageController {
    @PostMapping
    void save(@RequestParam("file") MultipartFile file);
}
