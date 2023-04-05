package com.thebestgroup.teamquest.service.imp;

import com.thebestgroup.teamquest.exception.type.NotFoundException;
import com.thebestgroup.teamquest.model.dto.filter.QuestFilter;
import com.thebestgroup.teamquest.model.dto.quest.QuestDto;
import com.thebestgroup.teamquest.model.entity.Quest;
import com.thebestgroup.teamquest.model.mapper.QuestMapper;
import com.thebestgroup.teamquest.repository.QuestRepository;
import com.thebestgroup.teamquest.service.QuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class QuestServiceImpl implements QuestService {

    private final QuestRepository questRepository;
    private final QuestMapper questMapper;

    @Override
    public Page<QuestDto> findQuests(QuestFilter filter, Pageable page) {
//        QPredicate predicate = QPredicate.builder()
//                .add(filter.type(), QQ)


        return questRepository.findAll(page).map(questMapper::toDto);
    }

    @Override
    public QuestDto findQuest(Long questId) {

        return questMapper.toDto(
                questRepository.findById(questId)
                        .orElseThrow(() -> {
                                    log.info("Не найден квест с id = {}", questId);
                                    return new NotFoundException("Не найден квест с id = %s".formatted(questId));
                                }
                        )
        );
    }

    @Override
    @Transactional
    public QuestDto addQuest(QuestDto questDto) {
        Quest quest = questRepository.save(questMapper.toEntity(questDto));

        return questMapper.toDto(quest);
    }

    @Override
    @Transactional
    public QuestDto updateQuest(Long questId, QuestDto questDto) {
        Quest quest = questRepository.findById(questId)
                .orElseThrow(() -> {
                    log.info("Не найден квест с id = {}", questId);
                    return new NotFoundException("Не найден квест с id = %s".formatted(questId));
                });

        questMapper.update(quest, questDto);
        quest = questRepository.saveAndFlush(quest);

        return questMapper.toDto(quest);
    }

    @Override
    @Transactional
    public void deleteQuest(Long questId) {
        Quest quest = questRepository.findById(questId)
                .orElseThrow(() -> {
                    log.info("Не найден квест с id = {}", questId);
                    return new NotFoundException("Не найден квест с id = %s".formatted(questId));
                });

        questRepository.delete(quest);
    }
}

