package com.thebestgroup.teamquest.service.impl;

import com.thebestgroup.teamquest.exception.type.NotFoundException;
import com.thebestgroup.teamquest.service.FileStorageService;
import com.thebestgroup.teamquest.service.QuestImageFacade;
import com.thebestgroup.teamquest.service.filepathgenerator.FilePathGenerator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestImageFacadeImpl implements QuestImageFacade {

    private final FilePathGenerator filePathGenerator;
    private final FileStorageService fileStorageService;

    private static final String USER_ID = "1";

    @SneakyThrows
    @Override
    public String saveImage(MultipartFile image) {
        String imagePath = filePathGenerator.generateQuestImagePath(image.getOriginalFilename(), USER_ID);
        fileStorageService.upload(imagePath, image.getBytes());

        return imagePath;
    }

    @Override
    public byte[] getImage(String imagePath) {

        return fileStorageService.download(imagePath)
                .orElseThrow(() -> {
                    log.info("Не найден image по пути: {}", imagePath);
                    return new NotFoundException("Не найден image по пути: %s".formatted(imagePath));
                });
    }
}
