package com.thebestgroup.teamquest.model.mapper;

import com.thebestgroup.teamquest.model.dto.quest.QuestDto;
import com.thebestgroup.teamquest.model.entity.Quest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestMapper extends BaseMapper<Quest, QuestDto> {
}
