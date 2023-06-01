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
        files.forEach(this::saveMultipartFile);
    }


    public void saveMultipartFile(MultipartFile multipartFile) {
        try {
            var fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            var pathFile = "shop-app/src/main/resources/images/" + fileName;
            createFileInResourceLoader(multipartFile, pathFile);
            createImageEntity(fileName, pathFile);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private static void createFileInResourceLoader(MultipartFile multipartFile, String pathFile) throws IOException {
        var file = new File(pathFile);
        var content = multipartFile.getBytes();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(content);
        }
    }

    private void createImageEntity(String fileName, String pathFile) {
        if (imageIsNotCreatedByFileName(fileName)) {
            var entity = buildImageEntity(fileName, pathFile);
            imageRepository.save(entity);
        }
    }

    private boolean imageIsNotCreatedByFileName(String fileName) {
        return !imageRepository.existsImageByName(fileName);
    }

    private Image buildImageEntity(String fileName, String pathFile) {
        Image entity = new Image();
        entity.setName(fileName);
        entity.setPath(pathFile);
        return entity;
    }
}
