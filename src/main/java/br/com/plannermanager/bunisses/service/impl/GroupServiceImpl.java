package br.com.plannermanager.bunisses.service.impl;

import br.com.plannermanager.bunisses.exceptions.EntityAlreadyExistsException;
import br.com.plannermanager.bunisses.exceptions.EntityNotExistsException;
import br.com.plannermanager.bunisses.exceptions.InvalidParamException;
import br.com.plannermanager.bunisses.mapper.GroupMapper;
import br.com.plannermanager.bunisses.service.GroupService;
import br.com.plannermanager.domain.Group;

import br.com.plannermanager.domain.Project;
import br.com.plannermanager.dto.request.GroupRequestDto;
import br.com.plannermanager.dto.response.GroupResponseDto;
import br.com.plannermanager.repository.GroupRepository;
import br.com.plannermanager.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public GroupResponseDto createGroup(GroupRequestDto groupRequestDto) {
       Project project =  projectRepository.findById(groupRequestDto.getProjectId())
                .orElseThrow(() -> new InvalidParamException("Project id not found!"));
      Optional<Group> groupAlreadyExists = groupRepository.findGroupByName(groupRequestDto.getName());
      if(groupAlreadyExists.isPresent()) throw new EntityAlreadyExistsException("This group name already exists!");
      Group group = GroupMapper.INSTANCE.mapDtoToEntity(groupRequestDto);
      group = groupRepository.save(group);
      return GroupMapper.INSTANCE.mapEntityToDto(group);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GroupResponseDto> findAll(PageRequest pageRequest) {
        Page<Group> groups = groupRepository.findAll(pageRequest);
        return groups.map(GroupMapper.INSTANCE::mapEntityToDto);

    }

    @Override
    @Transactional(readOnly = true)
    public GroupResponseDto findById(UUID id) {
        Group findGroupById = groupRepository.findById(id).orElseThrow(() -> new EntityNotExistsException("group id not found!"));
        return GroupMapper.INSTANCE.mapEntityToDto(findGroupById);
     }
}
