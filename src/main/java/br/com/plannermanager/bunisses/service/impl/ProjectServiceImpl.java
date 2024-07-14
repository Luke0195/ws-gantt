package br.com.plannermanager.bunisses.service.impl;

import br.com.plannermanager.bunisses.exceptions.EntityAlreadyExistsException;
import br.com.plannermanager.bunisses.mapper.ProjectMapper;
import br.com.plannermanager.bunisses.service.ProjectService;

import br.com.plannermanager.domain.Project;
import br.com.plannermanager.dto.request.ProjectRequestDto;
import br.com.plannermanager.dto.response.ProjectResponseDto;
import br.com.plannermanager.repository.ProjectRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public ProjectResponseDto create(ProjectRequestDto projectRequestDto) {
     Optional<Project> findProjectByName = projectRepository.findByName(projectRequestDto.getName());
     if(findProjectByName.isPresent()) throw new EntityAlreadyExistsException("Project name is already exists!");
     Project project = ProjectMapper.INSTANCE.mapDtoToEntity(projectRequestDto);
     project = projectRepository.save(project);
     return ProjectMapper.INSTANCE.mapEntityToDto(project);
    }
}
