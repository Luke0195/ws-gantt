package br.com.plannermanager.bunisses.service;

import br.com.plannermanager.dto.request.ProjectRequestDto;
import br.com.plannermanager.dto.response.ProjectResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProjectService {

    ProjectResponseDto create(ProjectRequestDto projectRequestDto);

    Page<ProjectResponseDto> findAllPaged(Pageable pageable);

    ProjectResponseDto findProjectById(UUID id);
}
