package com.thebestgroup.teamquest.service.impl.filestorage;

import com.thebestgroup.teamquest.model.entity.StorageFile;
import com.thebestgroup.teamquest.service.FileStorageService;
import com.thebestgroup.teamquest.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
class DbFileStorageService implements FileStorageService {

//    private final FileStorageRepository fileStorageRepository;

    @Override
    @Transactional
    public void upload(String filePath, byte[] data) {
        StorageFile file = new StorageFile();
        file.setFileName(filePath);
        file.setData(data);
        file.setSize(data.length);
        file.setExtension(FileUtils.extractExtension(filePath));

//        fileStorageRepository.save(file);
    }

    @Override
    @Transactional
    public Optional<byte[]> download(String filePath) {
//        StorageFile file = fileStorageRepository.findById(filePath)
//                .orElseThrow(() -> {
//                    log.info("Не найден файл с id = {}", filePath);
//                    return new NotFoundException("Не найден файл с id = %s".formatted(filePath));
//                });
//
//        return file.getData();

        return Optional.empty();
    }
}