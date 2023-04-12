package com.thebestgroup.teamquest.controller;

import com.thebestgroup.teamquest.model.dto.PageDto;
import com.thebestgroup.teamquest.model.dto.filter.QuestFilter;
import com.thebestgroup.teamquest.model.dto.quest.QuestDto;
import com.thebestgroup.teamquest.model.dto.quest.SaveQuestDto;
import com.thebestgroup.teamquest.model.mapper.PageMapper;
import com.thebestgroup.teamquest.service.QuestService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j

@Validated
@RestController
@RequestMapping("/api/v1/quests")
@RequiredArgsConstructor
public class QuestController {

    private final QuestService questService;
    private final PageMapper pageMapper;

    @GetMapping
    public PageDto<QuestDto> findQuests(QuestFilter filter,
                                        @ParameterObject
                                        @PageableDefault Pageable page) {

        return pageMapper.toPageDto(questService.findQuests(filter, page));
    }

    @GetMapping("/{questId}")
    public QuestDto findQuest(@NotNull(message = "Не передан ИД квеста")
                              @PathVariable("questId")
                              Long questId) {

        return questService.findQuest(questId);
    }

//    @PostMapping
//    public QuestDto saveQuest(@NotNull(message = "Не передан объект квеста")
//                              @RequestBody(required = false)
//                              @Valid QuestDto questDto) {
//
//        return questService.saveQuest(questDto);
//    }

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public QuestDto saveQuest_Save(@NotNull(message = "Не передан объект квеста")
                                   @Valid
                                   @RequestPart
                                   SaveQuestDto questDto,
                                   @RequestPart
                                   MultipartFile image) {

        return questService.saveQuest(questDto, image);
    }

    @PutMapping("/{questId}")
    public QuestDto updateQuest(@NotNull(message = "Не передан ИД квеста")
                                @PathVariable
                                Long questId,
                                @Valid
                                @NotNull(message = "Не передан объект квеста")
                                @RequestBody(required = false)
                                QuestDto questDto) {

        return questService.updateQuest(questId, questDto);
    }

    @DeleteMapping("/{questId}")
    public void deleteQuest(@NotNull(message = "Не передан ИД квеста")
                            @PathVariable
                            Long questId) {

        questService.deleteQuest(questId);
    }

    @GetMapping("/{questId}/images/primary")
    public byte[] downloadPrimaryImage(@NotNull(message = "Не передан ИД квеста")
                                       @PathVariable
                                       Long questId) {

        return questService.downloadPrimaryImage(questId);
    }
}
