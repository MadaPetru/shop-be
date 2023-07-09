package ro.adi.shop.images.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ImageService {

    void saveImages(List<MultipartFile> files);
}
