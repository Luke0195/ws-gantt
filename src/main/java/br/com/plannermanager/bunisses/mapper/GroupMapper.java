package br.com.plannermanager.bunisses.mapper;

import br.com.plannermanager.domain.Group;
import br.com.plannermanager.dto.request.GroupRequestDto;

import br.com.plannermanager.dto.response.GroupResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    @Mapping(target = "project.id", source = "projectId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore=true)
    Group mapDtoToEntity(GroupRequestDto groupRequestDto);

    @Mapping(target="id", source = "id")
    @Mapping(target="name", source = "name")
    GroupResponseDto mapEntityToDto(Group entity);

}
