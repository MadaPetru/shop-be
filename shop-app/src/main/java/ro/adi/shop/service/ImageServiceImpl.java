package ro.adi.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ro.adi.shop.jpa.entity.Image;
import ro.adi.shop.jpa.repository.ImageRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;


@RequiredArgsConstructor
@Component
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public void save(MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            createFileInResourceLoader(fileName, file.getBytes());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    public void createFileInResourceLoader(String fileName, byte[] content) throws IOException {
        String pathFile = "shop-app/src/main/resources/images/" + fileName;
        File file = new File(pathFile);
        boolean created = file.createNewFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(content);
        }
        if (isCreated(created)) {
            createImageEntity(fileName, pathFile);
        }
    }

    private void createImageEntity(String fileName, String pathFile) {
        Image entity = new Image();
        entity.setName(fileName);
        entity.setPath(pathFile);
        imageRepository.save(entity);
    }

    private boolean isCreated(boolean created) {
        return created;
    }
}
