package com.thebestgroup.teamquest.model.dto.filter;

import java.math.BigDecimal;

public record QuestFilter(Short type,
                          Short age,
                          Short personNum,
                          Short complexity,
                          Double rating,
                          BigDecimal startPrice) {}
