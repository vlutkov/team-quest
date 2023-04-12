package com.thebestgroup.teamquest.service;

import java.util.Optional;

public interface FileStorageService {

    String upload(String filePath, byte[] content);

    Optional<byte[]> download(String filePath);

    void delete(String previousImage);
}
