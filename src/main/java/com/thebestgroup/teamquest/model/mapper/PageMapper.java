package com.thebestgroup.teamquest.model.mapper;

import com.thebestgroup.teamquest.model.dto.PageDto;
import jakarta.annotation.Nullable;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PageMapper {

    default <T> PageDto<T> toPageDto(@Nullable Page<T> source) {

        return new PageDto<>(
                source.getContent(),
                source.getTotalPages(),
                source.getTotalElements(),
                source.getSize()
        );
    }
}
