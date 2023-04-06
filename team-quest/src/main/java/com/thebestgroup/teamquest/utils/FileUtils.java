package com.thebestgroup.teamquest.utils;

import com.thebestgroup.teamquest.exception.type.InternalException;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@UtilityClass
public class FileUtils {

    public String extractExtension(String fileName) {
        if (fileName == null) {
            return null;
        }

        return fileName.replaceFirst(".+\\.(.+)", "$1");
    }

    public static byte[] getBytes(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new InternalException(e);
        }
    }
}
