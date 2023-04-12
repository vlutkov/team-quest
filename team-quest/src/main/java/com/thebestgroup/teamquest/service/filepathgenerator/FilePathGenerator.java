package com.thebestgroup.teamquest.service.filepathgenerator;

import com.thebestgroup.teamquest.utils.BucketUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.FileSystems;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FilePathGenerator {

    @Value("${quest.image.bucket}")
    private String imageBucket;

    public String generateQuestImagePath(String imageName, String user) {
        //TODO correctly set user directory
        String separator = FileSystems.getDefault().getSeparator();
        String userBucket = BucketUtils.setUser(imageBucket, user);
        return userBucket + separator + UUID.randomUUID() + "-" + imageName;
    }
}
