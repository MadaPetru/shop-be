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
import java.util.List;
import java.util.Objects;


@RequiredArgsConstructor
@Component
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public void save(List<MultipartFile> files) {
        for (MultipartFile file : files)
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
        boolean isCreated = file.createNewFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(content);
        }
        if (isCreated) {
            createNewImageEntity(fileName, pathFile);
        }
    }

    private void createNewImageEntity(String fileName, String pathFile) {
        Image entity = new Image();
        entity.setName(fileName);
        entity.setPath(pathFile);
        imageRepository.save(entity);
    }
}
