package br.com.plannermanager.factories;

import br.com.plannermanager.domain.Group;
import br.com.plannermanager.domain.Project;
import br.com.plannermanager.dto.request.GroupRequestDto;
import br.com.plannermanager.dto.response.GroupResponseDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class GroupFactory {

  public static GroupRequestDto makeGroupRequestDto(){
     return  GroupRequestDto.builder().name("any_name").projectId(UUID.randomUUID()).build();
  }

  public static Group makeGroup(GroupRequestDto groupRequestDto){
      Project project = ProjectFactory.makeProjectDtoToEntity(ProjectFactory.makeProjectDto());
      project.setId(groupRequestDto.getProjectId());
      return Group.builder().id(UUID.randomUUID()).name(groupRequestDto.getName()).project(project).createdAt(LocalDateTime.now()).build();
  }

  public static GroupResponseDto makeGroupResponseDto(Group entity){
      return GroupResponseDto.builder().id(entity.getId()).name(entity.getName()).project(entity.getProject()).createdAt(LocalDateTime.now()).build();
  }

}
