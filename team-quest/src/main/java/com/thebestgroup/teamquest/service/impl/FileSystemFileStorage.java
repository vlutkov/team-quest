package com.thebestgroup.teamquest.service.impl;

import com.thebestgroup.teamquest.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Slf4j
@Service
@RequiredArgsConstructor
public class FileSystemFileStorage implements FileStorageService {

    @Override
    public Long upload(String filePath, byte[] content) {

        return null;
    }

    @Override
    public byte[] download(String filePath) {
        return new byte[0];
    }
}
