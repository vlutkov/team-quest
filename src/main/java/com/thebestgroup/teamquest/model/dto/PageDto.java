package com.thebestgroup.teamquest.model.dto;

import java.util.List;

public record PageDto<T>(List<T> data,
                         Integer totalPages,
                         Long totalElements,
                         Integer size) {}
