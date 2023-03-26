package com.thebestgroup.teamquest.service;

import com.thebestgroup.teamquest.model.dto.filter.QuestFilter;
import com.thebestgroup.teamquest.model.dto.quest.QuestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestService {

    Page<QuestDto> findQuests(QuestFilter filter, Pageable page);

    QuestDto findQuest(Long questId);

    QuestDto addQuest(QuestDto questDto);

    QuestDto updateQuest(Long questId, QuestDto questDto);

    void deleteQuest(Long questId);
}
