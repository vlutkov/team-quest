package com.thebestgroup.teamquest.model.mapper;

import com.thebestgroup.teamquest.model.dto.quest.QuestDto;
import com.thebestgroup.teamquest.model.dto.quest.SaveQuestDto;
import com.thebestgroup.teamquest.model.entity.Quest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestMapper extends BaseMapper<Quest, QuestDto> {

    @Mapping(target = "imageId", source = "imageId")
    Quest toEntity(SaveQuestDto source, Long imageId);
}
