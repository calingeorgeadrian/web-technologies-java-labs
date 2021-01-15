package com.bgjshop.backend.mapper;

import com.bgjshop.backend.domain.User;
import com.bgjshop.backend.dto.UserDto;
import com.bgjshop.backend.dto.UserLoginDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "email", source = "dto.email"),
            @Mapping(target = "firstName", source = "dto.firstName"),
            @Mapping(target = "lastName", source = "dto.lastName"),
            @Mapping(target = "phone", source = "dto.phone"),
            @Mapping(target = "country", source = "dto.country"),
            @Mapping(target = "city", source = "dto.city"),
            @Mapping(target = "address", source = "dto.address"),
            @Mapping(target = "roleType", source = "dto.roleType")
    })
    User toEntity(UserDto dto);

    @Mappings({
            @Mapping(target = "firstName", source = "dto.firstName"),
            @Mapping(target = "lastName", source = "dto.lastName"),
            @Mapping(target = "email", source = "dto.email")
    })
    User toEntity(UserLoginDto dto);

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "email", source = "entity.email"),
            @Mapping(target = "firstName", source = "entity.firstName"),
            @Mapping(target = "lastName", source = "entity.lastName"),
            @Mapping(target = "phone", source = "entity.phone"),
            @Mapping(target = "country", source = "entity.country"),
            @Mapping(target = "city", source = "entity.city"),
            @Mapping(target = "address", source = "entity.address"),
            @Mapping(target = "roleType", source = "entity.roleType")
    })
    UserDto toDto(User entity);
}
