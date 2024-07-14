package br.com.plannermanager.factories;

import br.com.plannermanager.domain.Project;
import br.com.plannermanager.domain.enums.ProjectStatus;
import br.com.plannermanager.dto.request.ProjectRequestDto;
import br.com.plannermanager.dto.response.ProjectResponseDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProjectFactory {

    private ProjectFactory(){};

    public static ProjectRequestDto makeProjectDto(){
        return ProjectRequestDto.builder().name("any_name").description("any_description").projectStatus("PLANING").build();
    }

    public static Project makeProjectDtoToEntity(ProjectRequestDto projectRequestDto){
        return Project.builder()
                .id(UUID.fromString("a275faee-2b98-55a4-bba0-b358cb2f3456"))
                .name(projectRequestDto.getName())
                .description(projectRequestDto.getDescription())
                .projectStatus(ProjectStatus.valueOf(projectRequestDto.getProjectStatus()))
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ProjectResponseDto makeProjectToProjectResponseDto(Project project){
        return ProjectResponseDto.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .projectStatus(project.getProjectStatus().toString())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
