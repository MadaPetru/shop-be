package ro.adi.shop.images.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ro.adi.shop.config.GlobalConfigurations;
import ro.adi.shop.helper.FilenameValidator;
import ro.adi.shop.images.exception.FilenameInvalidException;
import ro.adi.shop.images.jpa.entity.Image;
import ro.adi.shop.images.jpa.repository.ImageRepository;

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
    private final GlobalConfigurations globalConfigurations;

    @Override
    public void saveImages(List<MultipartFile> files) {
        files.forEach(this::saveMultipartFile);
    }


    private void saveMultipartFile(MultipartFile multipartFile) {
        log.info("Save multipart file ...");
        try {
            var fileName = getFilename(multipartFile);
            var pathFile = globalConfigurations.getPath() + fileName;
            createFileOnDiskOfLocalMachine(multipartFile, pathFile);
            createImageEntity(fileName, pathFile);
        } catch (FilenameInvalidException e) {
            log.error("Saving multipart file: {} with exception: {}", multipartFile, e.getMessage());
            throw new FilenameInvalidException(e.getMessage());
        } catch (Exception e) {
            log.error("Error at saving multipart file " + e.getMessage());
            return;
        }
        log.info("Saved  multipart file : {} successfully", multipartFile);
    }

    private String getFilename(MultipartFile multipartFile) {
        var originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null || !FilenameValidator.isValidFilename(originalFilename)) {
            var message = String.format("File was tried to be saved with the filename: %s", originalFilename);
            log.error(message, originalFilename);
            throw new FilenameInvalidException(message);
        }
        return StringUtils.cleanPath(Objects.requireNonNull(originalFilename));
    }

    private void createFileOnDiskOfLocalMachine(MultipartFile multipartFile, String pathFile) throws IOException {
        var file = new File(pathFile);
        var content = multipartFile.getBytes();
        try {
            try (var fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(content);
            }
        } catch (Exception e) {
            log.error("Error creating the file on disk.");
        }
    }

    private void createImageEntity(String fileName, String pathFile) {
        if (imageIsNotCreatedByFileName(fileName)) {
            log.info("Create image with name: {}", fileName);
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
