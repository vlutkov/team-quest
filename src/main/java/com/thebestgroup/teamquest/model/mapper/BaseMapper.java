package com.thebestgroup.teamquest.model.mapper;

import jakarta.annotation.Nullable;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface BaseMapper<E, D> {

    D toDto(@Nullable E source);

    E toEntity(@Nullable D source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    void update(@MappingTarget E target, @Nullable D source);
}
