package com.thebestgroup.teamquest.service;

import com.thebestgroup.teamquest.model.dto.filter.QuestFilter;
import com.thebestgroup.teamquest.model.dto.quest.QuestDto;
import com.thebestgroup.teamquest.model.dto.quest.SaveQuestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface QuestService {

    Page<QuestDto> findQuests(QuestFilter filter, Pageable page);

    QuestDto findQuest(Long questId);

    QuestDto saveQuest(QuestDto questDto);

    QuestDto saveQuest(SaveQuestDto questDto, MultipartFile image);

    QuestDto updateQuest(Long questId, QuestDto questDto);

    void deleteQuest(Long questId);

    byte[] downloadPrimaryImage(Long questId);
}
