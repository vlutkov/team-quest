package com.thebestgroup.teamquest.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FileUtils {

    public String extractExtension(String fileName) {
        if (fileName == null) {
            return null;
        }

        return fileName.replaceFirst(".+\\.(.+)", "$1");
    }
}
