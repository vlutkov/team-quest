package com.thebestgroup.teamquest.filestorage;

public interface FileStorageService {

    byte[] download();

    void upload(byte[] file);
}
