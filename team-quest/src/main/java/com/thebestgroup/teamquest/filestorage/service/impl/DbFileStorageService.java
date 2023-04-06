package com.thebestgroup.teamquest.filestorage.service.impl;

import com.thebestgroup.teamquest.exception.type.NotFoundException;
import com.thebestgroup.teamquest.filestorage.repository.FileStorageRepository;
import com.thebestgroup.teamquest.filestorage.service.FileStorageService;
import com.thebestgroup.teamquest.model.entity.StorageFile;
import com.thebestgroup.teamquest.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
class DbFileStorageService implements FileStorageService {

    private final FileStorageRepository fileStorageRepository;

    @Override
    @Transactional
    public Long upload(String fileName, byte[] data) {
        StorageFile file = new StorageFile();
        file.setFileName(fileName);
        file.setData(data);
        file.setSize(data.length);
        file.setExtension(FileUtils.extractExtension(fileName));

        return fileStorageRepository.saveAndFlush(file).getId();
    }

    @Override
    @Transactional
    public byte[] download(Long fileId) {
        StorageFile file = fileStorageRepository.findById(fileId)
                .orElseThrow(() -> {
                    log.info("Не найден файл с id = {}", fileId);
                    return new NotFoundException("Не найден файл с id = %s".formatted(fileId));
                });

        return file.getData();
    }
}
