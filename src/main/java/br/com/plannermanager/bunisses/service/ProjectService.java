package br.com.plannermanager.bunisses.service;

import br.com.plannermanager.dto.request.ProjectRequestDto;
import br.com.plannermanager.dto.response.ProjectResponseDto;

public interface ProjectService {

    ProjectResponseDto create(ProjectRequestDto projectRequestDto);
}
