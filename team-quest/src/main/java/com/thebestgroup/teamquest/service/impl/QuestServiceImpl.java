package com.thebestgroup.teamquest.service.impl;

import com.querydsl.core.types.Predicate;
import com.thebestgroup.teamquest.exception.type.NotFoundException;
import com.thebestgroup.teamquest.model.dto.filter.QuestFilter;
import com.thebestgroup.teamquest.model.dto.quest.QuestDto;
import com.thebestgroup.teamquest.model.dto.quest.SaveQuestDto;
import com.thebestgroup.teamquest.model.entity.Quest;
import com.thebestgroup.teamquest.model.mapper.QuestMapper;
import com.thebestgroup.teamquest.repository.QuestRepository;
import com.thebestgroup.teamquest.repository.helper.QPredicate;
import com.thebestgroup.teamquest.service.QuestImageFacade;
import com.thebestgroup.teamquest.service.QuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static com.thebestgroup.teamquest.model.entity.QQuest.quest;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class QuestServiceImpl implements QuestService {

    private final QuestRepository questRepository;
    private final QuestMapper questMapper;
    private final QuestImageFacade questImageFacade;

    @Override
    public Page<QuestDto> findQuests(QuestFilter filter, Pageable page) {
        Predicate predicate = QPredicate.builder()
                .add(filter.type(), quest.type::eq)
                .add(filter.age(), quest.age.min::loe)
                .add(filter.personNum(), personNum ->
                        quest.personNum.min.loe(personNum)
                                .and(quest.personNum.max.goe(personNum)))
                .build();

        return questRepository.findAll(predicate, page).map(questMapper::toDto);
    }

    @Override
    public QuestDto findQuest(Long questId) {

        return questMapper.toDto(
                questRepository.findById(questId)
                        .orElseThrow(() -> {
                                    log.info("Не найден квест с id: {}", questId);
                                    return new NotFoundException("Не найден квест с id: %s".formatted(questId));
                                }
                        )
        );
    }

    @Override
    @Transactional
    public QuestDto saveQuest(QuestDto questDto) {
        Quest quest = questRepository.save(questMapper.toEntity(questDto));

        return questMapper.toDto(quest);
    }

    @Override
    @Transactional
    public QuestDto saveQuest(SaveQuestDto questDto, MultipartFile image) {
        String imagePath = questImageFacade.saveImage(image);
        Quest quest = questRepository.save(questMapper.toEntity(questDto, imagePath));

        return questMapper.toDto(quest);
    }

    @Override
    @Transactional
    public QuestDto updateQuest(Long questId, SaveQuestDto questDto, MultipartFile image) {
        Quest quest = questRepository.findById(questId)
                .orElseThrow(() -> {
                    log.info("Не найден квест с id: {}", questId);
                    return new NotFoundException("Не найден квест с id: %s".formatted(questId));
                });

        String imagePath = questImageFacade.saveImage(image);
        questMapper.update(quest, questDto);
        quest = questRepository.saveAndFlush(quest);

        return questMapper.toDto(quest);
    }

    @Override
    @Transactional
    public void deleteQuest(Long questId) {
        Quest quest = questRepository.findById(questId)
                .orElseThrow(() -> {
                    log.info("Не найден квест с id: {}", questId);
                    return new NotFoundException("Не найден квест с id: %s".formatted(questId));
                });

        questRepository.delete(quest);
    }

    @Override
    public byte[] downloadPrimaryImage(Long questId) {
        Quest quest = questRepository.findById(questId)
                .orElseThrow(() -> {
                    log.info("Не найден квест с id: {}", questId);
                    return new NotFoundException("Не найден квест с id: %s".formatted(questId));
                });

        return questImageFacade.getImage(quest.getImage());
    }
}

