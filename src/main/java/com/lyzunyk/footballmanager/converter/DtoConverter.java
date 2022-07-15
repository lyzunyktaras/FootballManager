package com.lyzunyk.footballmanager.converter;

import com.lyzunyk.footballmanager.converter.marker.Convertable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class DtoConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public DtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T, D extends Convertable> T convertToDto(D entity, Type dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}
