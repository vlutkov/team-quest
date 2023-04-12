package com.thebestgroup.teamquest.model.mapper;

import com.thebestgroup.teamquest.model.dto.quest.QuestDto;
import com.thebestgroup.teamquest.model.dto.quest.SaveQuestDto;
import com.thebestgroup.teamquest.model.entity.Quest;
import jakarta.annotation.Nullable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Value;

@Mapper(componentModel = "spring")
public abstract class QuestMapper implements BaseMapper<Quest, QuestDto> {

    private final String HTTP_SEPARATOR = "/";

    @Value("${file-storage.url}")
    protected String fileStorageUrl;

    @Mapping(target = "image", source = "source.image", qualifiedByName = "buildImageUrl")
    public abstract QuestDto toDto(@Nullable Quest source);

    @Mapping(target = "image", source = "imagePath")
    public abstract Quest toEntity(SaveQuestDto source, String imagePath);

    @Named("buildImageUrl")
    protected String buildImageUrl(String imagePath) {
        return fileStorageUrl + HTTP_SEPARATOR + imagePath;
    }
}
