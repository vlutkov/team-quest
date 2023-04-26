package com.thebestgroup.teamquest.controller;

import com.thebestgroup.teamquest.exception.type.NotFoundException;
import com.thebestgroup.teamquest.service.FileStorageService;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/v1/filestorage")
@RequiredArgsConstructor
public class FileStorageController {

    private final FileStorageService fileStorageService;

    @SneakyThrows
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestParam MultipartFile file) {

        return fileStorageService.upload(file.getOriginalFilename(), file.getBytes());
    }

    @GetMapping(value = "/download/{filePath}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> download(
            @NotEmpty(message = "Не передайн путь к файлу")
            @PathVariable String filePath) {

        return ResponseEntity.ok(fileStorageService.download(filePath)
                .orElseThrow(() -> {
                    log.info("Не найден файл: {}", filePath);
                    return new NotFoundException("Не найден ресурс: %s".formatted(filePath));
                }));
    }
}
