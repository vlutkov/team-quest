package com.thebestgroup.teamquest.service;

import org.springframework.web.multipart.MultipartFile;

public interface QuestImageFacade {

    String saveImage(MultipartFile image);

    byte[] getImage(String imagePath);
}
