package com.thebestgroup.teamquest.service.impl.filestorage;

import com.thebestgroup.teamquest.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@Primary
@Slf4j
@Service
@RequiredArgsConstructor
public class FileSystemFileStorage implements FileStorageService {

    @Value("${file-storage.file.bucket}")
    private String bucket;

    @SneakyThrows
    @Override
    public void upload(String filePath, byte[] content) {
        Path fullFilePath = Path.of(bucket, filePath);

        Files.createDirectories(fullFilePath.getParent());
        Files.write(fullFilePath, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @SneakyThrows
    @Override
    public Optional<byte[]> download(String filePath) {
        Path fullFilePath = Path.of(bucket, filePath);

        return Files.exists(fullFilePath)
                ? Optional.of(Files.readAllBytes(fullFilePath))
                : Optional.empty();
    }
}
