package com.thebestgroup.teamquest.model.mapper;

import com.thebestgroup.teamquest.model.dto.quest.QuestDto;
import com.thebestgroup.teamquest.model.dto.quest.SaveQuestDto;
import com.thebestgroup.teamquest.model.entity.Quest;
import jakarta.annotation.Nullable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Value;

@Mapper(componentModel = "spring")
public abstract class QuestMapper implements BaseMapper<Quest, QuestDto> {

    private final String HTTP_SEPARATOR = "/";

    @Value("${file-storage.url.download}")
    protected String downloadUrl;

    @Mapping(target = "image", source = "source.image", qualifiedByName = "buildImageUrl")
    public abstract QuestDto toDto(@Nullable Quest source);

    @Mapping(target = "image", source = "imageName")
    public abstract Quest toEntity(SaveQuestDto source, String imageName);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "image", source = "imageName")
    public abstract void update(@MappingTarget Quest target, @Nullable SaveQuestDto source, @Nullable String imageName);

    @Named("buildImageUrl")
    protected String buildImageUrl(String imageName) {
        return downloadUrl + HTTP_SEPARATOR + imageName;
    }
}
