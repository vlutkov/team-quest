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
import com.thebestgroup.teamquest.service.FileStorageService;
import com.thebestgroup.teamquest.service.QuestService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
    private final FileStorageService fileStorageService;

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

    @SneakyThrows
    @Override
    @Transactional
    public QuestDto saveQuest(SaveQuestDto questDto, MultipartFile image) {
        String imageName = fileStorageService.upload(image.getOriginalFilename(), image.getBytes());
        Quest quest = questRepository.save(questMapper.toEntity(questDto, imageName));

        return questMapper.toDto(quest);
    }

    @SneakyThrows
    @Override
    @Transactional
    public QuestDto updateQuest(Long questId, SaveQuestDto questDto, MultipartFile image) {
        Quest quest = questRepository.findById(questId)
                .orElseThrow(() -> {
                    log.info("Не найден квест с id: {}", questId);
                    return new NotFoundException("Не найден квест с id: %s".formatted(questId));
                });
        String previousImage = quest.getImage();

        String imageName = fileStorageService.upload(image.getOriginalFilename(), image.getBytes());
        questMapper.update(quest, questDto, imageName);
        quest = questRepository.saveAndFlush(quest);

        fileStorageService.delete(previousImage);

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
        fileStorageService.delete(quest.getImage());
    }

    @Override
    public byte[] downloadPrimaryImage(Long questId) {
        Quest quest = questRepository.findById(questId)
                .orElseThrow(() -> {
                    log.info("Не найден квест с id: {}", questId);
                    return new NotFoundException("Не найден квест с id: %s".formatted(questId));
                });

        return fileStorageService.download(quest.getImage())
                .orElseThrow(() -> {
                    log.info("Не найден image по имени: {}", quest.getImage());
                    return new NotFoundException("Не найден image по имени: %s".formatted(quest.getImage()));
                });
    }
}

