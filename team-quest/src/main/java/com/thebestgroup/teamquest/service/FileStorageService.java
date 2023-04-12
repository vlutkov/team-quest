package com.thebestgroup.teamquest.service;

import java.util.Optional;

public interface FileStorageService {

    void upload(String filePath, byte[] content);

    Optional<byte[]> download(String filePath);
}
