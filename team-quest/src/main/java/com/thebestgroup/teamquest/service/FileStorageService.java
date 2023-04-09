package com.thebestgroup.teamquest.service;

public interface FileStorageService {

    Long upload(String filePath, byte[] content);

    byte[] download(String filePath);
}
