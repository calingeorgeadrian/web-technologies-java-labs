package com.bgjshop.backend.mapper;

import com.bgjshop.backend.domain.Code;
import com.bgjshop.backend.dto.CodeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CodeMapper {

    @Mappings({
            @Mapping(target = "code", source = "dto.code"),
            @Mapping(target = "type", source = "dto.type"),
            @Mapping(target = "discount", source = "dto.discount")
    })
    Code toEntity(CodeDto dto);

    @Mappings({
            @Mapping(target = "code", source = "entity.code"),
            @Mapping(target = "type", source = "entity.type"),
            @Mapping(target = "discount", source = "entity.discount")
    })
    CodeDto toDto(Code entity);
}
