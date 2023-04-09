package com.thebestgroup.teamquest.model.dto.quest;

import jakarta.validation.constraints.NotNull;

public record Age(@NotNull(message = "Не задан минимальный возврат для прохождения квеста")
                         Short min,
                         Short max) {
}
