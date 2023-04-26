package com.thebestgroup.teamquest.model.dto.quest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SaveQuestDto(@NotEmpty(message = "Не передано наименование квеста")
                           String name,
                           @NotNull(message = "Не задан тип квеста")
                           Short type,
                           @Valid Age age,
                           @Valid PersonNum personNum,
                           @NotNull(message = "Не задана сложность квеста")
                           Short complexity,
                           @NotNull(message = "Не задан рейтинг квеста")
                           Double rating,
                           @NotNull(message = "Не задана минимальная цена квеста")
                           BigDecimal startPrice,
                           @NotNull(message = "Не задано время прохождения квеста")
                           Short spentTime,
                           @NotEmpty(message = "Не задано описание квеста")
                           String description) {

    public record Age(@NotNull(message = "Не задан минимальный возврат для прохождения квеста")
                      Short min,
                      Short max) {
    }

    public record PersonNum(@NotNull(message = "Не задан минимальное количество человек для прохождения квеста")
                            Short min,
                            @NotNull(message = "Не задано максимальное количество человек для прохождения квеста")
                            Short max) {
    }
}