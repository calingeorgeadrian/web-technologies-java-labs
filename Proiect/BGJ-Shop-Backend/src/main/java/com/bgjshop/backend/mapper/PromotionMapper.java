package com.bgjshop.backend.mapper;

import com.bgjshop.backend.domain.Promotion;
import com.bgjshop.backend.dto.PromotionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "title", source = "dto.title"),
            @Mapping(target = "discount", source = "dto.discount"),
            @Mapping(target = "startDate", source = "dto.startDate"),
            @Mapping(target = "endDate", source = "dto.endDate")
    })
    Promotion toEntity(PromotionDto dto);

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "title", source = "entity.title"),
            @Mapping(target = "discount", source = "entity.discount"),
            @Mapping(target = "startDate", source = "entity.startDate"),
            @Mapping(target = "endDate", source = "entity.endDate")
    })
    PromotionDto toDto(Promotion entity);
}
