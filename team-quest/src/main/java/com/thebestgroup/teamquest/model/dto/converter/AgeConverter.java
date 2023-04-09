package com.thebestgroup.teamquest.model.dto.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thebestgroup.teamquest.model.entity.Age;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@ReadingConverter
@Component
public class AgeConverter implements Converter<String, Age> {

    @Override
    @SneakyThrows
    public Age convert(String source) {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(source, Age.class);
    }
}
