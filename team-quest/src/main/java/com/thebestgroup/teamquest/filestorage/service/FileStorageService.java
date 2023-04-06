package com.thebestgroup.teamquest.filestorage.service;

public interface FileStorageService {

    Long upload(String fileName, byte[] file);

    byte[] download(Long id);
}
