package com.bgjshop.backend.mapper;

import com.bgjshop.backend.domain.Order;
import com.bgjshop.backend.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "userId", source = "dto.userId"),
            @Mapping(target = "firstName", source = "dto.firstName"),
            @Mapping(target = "lastName", source = "dto.lastName"),
            @Mapping(target = "phone", source = "dto.phone"),
            @Mapping(target = "country", source = "dto.country"),
            @Mapping(target = "city", source = "dto.city"),
            @Mapping(target = "address", source = "dto.address"),
            @Mapping(target = "note", source = "dto.note"),
            @Mapping(target = "total", source = "dto.total"),
            @Mapping(target = "code", source = "dto.code"),
            @Mapping(target = "status", source = "dto.status"),
            @Mapping(target = "datePlaced", source = "dto.datePlaced"),
            @Mapping(target = "dateDelivered", source = "dto.dateDelivered")
    })
    Order toEntity(OrderDto dto);

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "userId", source = "entity.userId"),
            @Mapping(target = "firstName", source = "entity.firstName"),
            @Mapping(target = "lastName", source = "entity.lastName"),
            @Mapping(target = "phone", source = "entity.phone"),
            @Mapping(target = "country", source = "entity.country"),
            @Mapping(target = "city", source = "entity.city"),
            @Mapping(target = "address", source = "entity.address"),
            @Mapping(target = "note", source = "entity.note"),
            @Mapping(target = "total", source = "entity.total"),
            @Mapping(target = "code", source = "entity.code"),
            @Mapping(target = "status", source = "entity.status"),
            @Mapping(target = "datePlaced", source = "entity.datePlaced"),
            @Mapping(target = "dateDelivered", source = "entity.dateDelivered")
    })
    OrderDto toDto(Order entity);
}
