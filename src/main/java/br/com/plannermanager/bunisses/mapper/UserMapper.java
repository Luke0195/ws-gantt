package br.com.plannermanager.bunisses.mapper;

import br.com.plannermanager.domain.Group;
import br.com.plannermanager.domain.User;
import br.com.plannermanager.dto.request.UserRequestDto;
import br.com.plannermanager.dto.response.UserResponseDto;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDto mapEntityToDto(User user);

    @Mapping(target = "name", source = "userRequestDto.name")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "email", source = "userRequestDto.email")
    @Mapping(target = "role", source = "userRequestDto.role")
    User mapDtoToEntity(UserRequestDto userRequestDto, Group group);
}

