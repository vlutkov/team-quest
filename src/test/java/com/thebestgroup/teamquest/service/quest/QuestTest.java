package com.thebestgroup.teamquest.service.quest;

import com.thebestgroup.teamquest.exception.type.NotFoundException;
import com.thebestgroup.teamquest.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class QuestTest {

    private final QuestService questService;

    @Test
    public void test_getting_quests() {
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> questService.findQuest(1L));
        System.out.println(notFoundException.getMessage());
        System.out.println("!!!!");
    }
}
