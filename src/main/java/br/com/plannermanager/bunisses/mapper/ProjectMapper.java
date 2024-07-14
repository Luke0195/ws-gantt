package br.com.plannermanager.bunisses.mapper;

import br.com.plannermanager.domain.Project;
import br.com.plannermanager.dto.request.ProjectRequestDto;
import br.com.plannermanager.dto.response.ProjectResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Project mapDtoToEntity(ProjectRequestDto request);

    ProjectResponseDto mapEntityToDto(Project project);
}
