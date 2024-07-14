package br.com.plannermanager.controllers;

import br.com.plannermanager.bunisses.service.impl.ProjectServiceImpl;
import br.com.plannermanager.dto.request.ProjectRequestDto;
import br.com.plannermanager.dto.response.ProjectResponseDto;
import br.com.plannermanager.repository.ProjectRepository;
import br.com.plannermanager.utils.http.HttpUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.hibernate.query.SortDirection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/projects")
@AllArgsConstructor
public class ProjectController {

  private final ProjectServiceImpl projectService;
    private final ProjectRepository projectRepository;

    @PostMapping
  public ResponseEntity<ProjectResponseDto> createProject(@Valid @RequestBody ProjectRequestDto projectRequestDto){
      ProjectResponseDto response = projectService.create(projectRequestDto);
      URI uri = HttpUtil.getUriFromObject(response);
      return HttpUtil.getCreatedResponse(uri, response);
  }

  @GetMapping
  public ResponseEntity<Page<ProjectResponseDto>> findProjectsPaged(
          @RequestParam(value = "page", defaultValue = "0") Integer page,
          @RequestParam(value="linesPerPage", defaultValue = "12") Integer linesPerPage,
          @RequestParam(value="orderBy", defaultValue = "name") String orderBy,
          @RequestParam(value="direction", defaultValue = "ASC") String direction
  ){
      PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
      Page<ProjectResponseDto> projects = projectService.findAllPaged(pageRequest);
      return HttpUtil.getSuccessResponse(projects);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ProjectResponseDto> findProjectById(@PathVariable UUID id){
        ProjectResponseDto project = projectService.findProjectById(id);
        return HttpUtil.getSuccessResponse(project);
  }

}
